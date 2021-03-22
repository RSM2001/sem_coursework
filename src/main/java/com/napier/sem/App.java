package com.napier.sem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.sql.*;

/**
 * App
 * Connects to MySQL database, executes queries and disconnects
 */
public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * The number of results to display
     */
    private final int n = 10;

    /**
     * Connect to the MySQL database.
     */
    public void connect (String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 30;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + 
                        "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect ()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * gets SQL query from folder
     * @param filename name of the file containing the query to be executed
     * @return sql query
     */
    public String getQuery (String filename)
    {
        System.out.println("\nExecuting Query " + filename + ".");
        try
        {
            // Create string for SQL statement
            String query = "";
            // Read in SQL file
            try
            {
                // Create filepath from filename
                File file = new File("./sqlqueries/" + filename + ".sql");
                // Create new Scanner
                Scanner scanner = new Scanner(file);
                // Read lines into string
                while (scanner.hasNextLine())
                {
                    String readLine = scanner.nextLine();
                    readLine = readLine.replace(" n ", " " + n + " ");
                    if (!readLine.startsWith("--"))
                        query = query.concat(readLine + ' ');
                }
                return query;
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Error - File not found. File " + filename + ".");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query.");
        }
        return null;
    }

    /**
     * executes sql query
     * @param query sql query to be executed
     * @return result set of results from query
     */
    public ResultSet executeQuery (String query)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);
            // Check if result is empty
            if (rset.isAfterLast()) {
                System.out.println("No results.");
                return null;
            }
            return rset;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query.");
        }
        return null;
    }

    /**
     * writes query results to .csv file
     * @param rset sql query results
     * @param count identifying number of the query
     */
    public void writeQuery (ResultSet rset, int count, String filename)
    {
        try 
        {
            // Create .csv file if it doesn't exist, open if it exists
            FileWriter csvWriter = new FileWriter("./" + filename + ".csv", true);
            // Append query number
            csvWriter.append(Integer.toString(count).concat("\n"));
            // Get column names from meta data
            ResultSetMetaData rsetMetaData = rset.getMetaData();

            String columnNames = "";
            for (int i = 1; i <= rsetMetaData.getColumnCount(); i++) 
                columnNames = columnNames.concat(rsetMetaData.getColumnName(i)).concat(",");
            columnNames = columnNames.substring(0, columnNames.length() - 1);
            csvWriter.append(columnNames.concat("\n"));

            // Write results to .csv file
            String resultStr;
            while (rset.next()) 
            {
                resultStr = "";
                for (int i = 1; i <= rsetMetaData.getColumnCount(); i++)
                    try 
                    {
                        resultStr = resultStr.concat("\"").concat(rset.getString(i)).concat("\",");
                    } 
                    catch (Exception e) 
                    {
                        resultStr = resultStr.concat("n/a,");
                    }
                // .csv writer
                resultStr = resultStr.substring(0, resultStr.length() - 1);
                csvWriter.append(resultStr.concat("\n"));
            }
            csvWriter.append("\n\n\n");
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Query complete.");
        }
        catch (Exception e)
        {
            System.out.println("Failed to write query.");
        }
    }
    
    /**
     * Main method
     * @param args command line arguments
     */
    public static void main (String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1)
            a.connect("localhost:3306");
        else
            a.connect(args[0]);
        
        // Wipe file for writing results into
        try 
        {
            FileWriter csvWriter = new FileWriter("./query-results.csv");
            csvWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        // Execute SQL statements in SQLQueries directory
        for (int i = 1; i <= 32; i++)
            a.writeQuery(a.executeQuery(a.getQuery(Integer.toString(i))), i, "query-results");

        // Disconnect from database
        a.disconnect();
    }
}