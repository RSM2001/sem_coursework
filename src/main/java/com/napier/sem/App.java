package com.napier.sem;

import com.sun.jdi.connect.spi.TransportService;
import com.sun.org.apache.bcel.internal.generic.PopInstruction;

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
            // Create report type identifier
            String reportType = "";
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
                    readLine = readLine.replace(" n ", Integer.toString(n));
                    if (readLine.startsWith("--"))
                    {
                        reportType = readLine.substring(2);
                    }
                    else
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
            if (rset == null) {
                System.out.println("No results.");
                return;
            }
            // Create .csv file if it doesn't exist, open if it exists
            // Write results to .csv file
            while (rset.next()) {
                String resultStr;
                // Country report
                switch (reportType) {
                    case "Country":
                        resultStr = CountryReport(rset);
                        break;

                    case "City":
                        resultStr = CityReport(rset);
                        break;

                    case "CapitalCity":
                        resultStr = CapitalCityReport(rset);
                        break;

                    case "PopulationCountry":
                        resultStr = PopulationCountryReport(rset);
                        break;

                    case "PopulationRegion":
                        resultStr = PopulationRegionReport(rset);
                        break;

                    case "PopulationContinent":
                        resultStr = PopulationContinentReport(rset);
                        break;

                    default:
                        System.out.println("No output type specified.");
                }

                //.csv write
            }
            System.out.println("Query complete.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query.");
        }
    }

    /**
     * Creates Country Report line to write to .csv file
     * @param rset row from query result
     * @return string formatted to be written to .csv
     * @throws SQLException Field is not found
     */
    public String CountryReport(ResultSet rset) throws SQLException {
        Country country = new Country();
        country.code = rset.getString("Code");
        country.name = rset.getString("Name");
        country.continent = rset.getString("Continent");
        country.region = rset.getString("Region");
        country.population = rset.getInt( "Population");
        country.capital = rset.getInt("Capital");

        return country.code + "," + country.name + "," +
                country.continent + "," + country.region + "," +
                country.population+ "," + country.capital;
    }

    /**
     * Creates City report line to write to .csv file
     * @param rset row from query result
     * @return string formatted to be written to .csv
     * @throws SQLException Field is not found
     */
    public String CityReport(ResultSet rset) throws SQLException {
        City city = new City();
        city.name = rset.getString("Code");
        city.countryCode = rset.getString("Name");
        city.district = rset.getString("District");
        city.population = rset.getInt("Population");

        return city.name + "," + city.countryCode + "," +
                city.district + "," + city.population;
    }

    /**
     * Creates Capital City report line to write to .csv file
     * @param rset row from query result
     * @return string formatted to be written to .csv
     * @throws SQLException Field is not found
     */
    public String CapitalCityReport(ResultSet rset) throws SQLException {
        City city = new City();
        city.name = rset.getString("Code");
        city.countryCode = rset.getString("Name");
        city.population = rset.getInt("Population");

        return city.name + "," + city.countryCode + "," + city.population;
    }

    /**
     * Creates Population Country report line to write to .csv file
     * @param rset row from query result
     * @return string formatted to be written to .csv
     * @throws SQLException Field is not found
     */
    public String PopulationCountryReport(ResultSet rset) throws SQLException {
        Country country = new Country();
        country.name = rset.getString("Name");
        country.population = rset.getInt("Population");
        String urbanLivingPerc = rset.getString(2);
        String ruralLivingPerc = rset.getString(3);

        return country.name + "," + country.population + "," +
                urbanLivingPerc + "," + ruralLivingPerc;
    }

    /**
     * Creates Population Region report line to write to .csv file
     * @param rset row from query result
     * @return string formatted to be written to .csv
     * @throws SQLException Field is not found
     */
    public String PopulationRegionReport(ResultSet rset) throws SQLException {
        Country country = new Country();
        country.region = rset.getString("Region");
        country.population = rset.getInt("Population");
        String urbanLivingPerc = rset.getString(2);
        String ruralLivingPerc = rset.getString(3);

        return country.region + "," + country.population + "," +
                urbanLivingPerc + "," + ruralLivingPerc;
    }

    /**
     * Creates Population Continent report line to write to .csv file
     * @param rset row from query result
     * @return string formatted to be written to .csv
     * @throws SQLException Field is not found
     */
    public String PopulationContinentReport(ResultSet rset) throws SQLException {
        Country country = new Country();
        country.continent = rset.getString("Continent");
        country.population = rset.getInt("Population");
        String urbanLivingPerc = rset.getString(2);
        String ruralLivingPerc = rset.getString(3);

        return country.continent + "," + country.population + "," +
                urbanLivingPerc + "," + ruralLivingPerc;
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