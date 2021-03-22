import com.napier.sem.App;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }
    
    @Test
    void integrationTest()
    {
        assertEquals(5, 5);
    }

    @AfterAll
    static void disconnect ()
    {
        app.disconnect();
    }

}