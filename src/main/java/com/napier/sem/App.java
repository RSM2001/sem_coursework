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
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
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
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
    public void disconnect()
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
     * Executes SQL query
     * @param count number of query to be executed
     */
    public void executeQuery(int count)
    {
        System.out.println("\nExecuting Query " + count + ".");
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String query = "";
            // Read in SQL file
            try
            {
                // Create filepath from count
                File file = new File("./sqlqueries/" + count + ".sql");
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
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Error - File not found. Query number " + count + ".");
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);
            // Check if result is empty
            if (rset.isAfterLast()) {
                System.out.println("No results.");
                return;
            }
            // Create .csv file if it doesn't exist, open if it exists
            FileWriter csvWriter = new FileWriter("./query-results.csv", true);
            // Get column names from meta data
            ResultSetMetaData rsetMetaData = rset.getMetaData();

            for (int i= 1; i<=rsetMetaData.getColumnCount(); i++)
                csvWriter.append(rsetMetaData.getColumnName(i)).append(",");
            csvWriter.append("\n");

            // Write results to .csv file
            String resultStr;
            while (rset.next()) {
                resultStr = "";
                for (int i= 1; i<=rsetMetaData.getColumnCount(); i++)
                    resultStr = resultStr.concat(rset.getString(i)).concat(",");
                // .csv writer
                csvWriter.append(resultStr.concat("\n"));
            }
            csvWriter.append("\n\n\n");
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Query complete.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query.");
        }
    }

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Execute SQL statements in SQLQueries directory
        for (int i = 1; i <= 32; i++)
            a.executeQuery(i);

        // Disconnect from database
        a.disconnect();
    }
}