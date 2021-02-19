package com.napier.sem;

import java.io.File;
import java.io.FileNotFoundException;
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
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        Country country = a.getCountry();

        System.out.println("Code:"+country.code);
        System.out.println("Name:"+country.name);
        System.out.println("Population:"+country.population);

        // Disconnect from database
        a.disconnect();
    }

    public Country getCountry ()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String query = "";
            try
            {
                File file = new File("./sqlqueries/1.sql");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine())
                {
                    String readLine = scanner.nextLine();
                    if (!readLine.startsWith("--"))
                        query = query.concat(scanner.nextLine() + ' ');
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Error");
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);
            // Return new country if valid.
            // Check one is returned
            Country country = new Country();
            if (rset.next()) {
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.population = rset.getInt("Population");
                return country;
            } else
                return null;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}