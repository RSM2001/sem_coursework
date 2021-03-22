import com.napier.sem.App;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.sql.*;
import java.util.Scanner;

class AppUnitTest
{
    static App app;
    
    @BeforeAll
    static void init ()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void executeQuery_ResultsTest ()
    {
        ResultSet rset = app.executeQuery("SELECT code, name, continent, region, population, capital " +
                "FROM country " +
                "WHERE code = \"GBR\" " +
                ";");
        try
        {
            while (rset.next())
            {
                assertEquals(rset.getString(0), "GBR");
                assertEquals(rset.getString(1), "United Kingdom");
                assertEquals(rset.getString(2), "Europe");
                assertEquals(rset.getString(3), "British Islands");
                assertEquals(rset.getString(4), "59623400");
                assertEquals(rset.getString(5), "456");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void executeQuery_NoResultsTest () {
        ResultSet rset = app.executeQuery("SELECT code, name, continent, region, population, capital " +
                "FROM country " +
                "WHERE code = \"AAA\" " +
                ";");
        try
        {
            assertFalse(rset.next());
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    @AfterAll
    static void disconnect () { app.disconnect(); }
}