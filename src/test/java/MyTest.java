import com.napier.sem.App;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.sql.*;
import java.util.Scanner;

class MyTest
{
    static App app;
    
    @BeforeAll
    static void init ()
    {
        app = new App();
        app.connect("localhost:3306");
    }

    @Test
    void test()
    {
        assertEquals(5, 5);
    }
    
    @AfterAll
    static void disconnect () { app.disconnect(); }
}