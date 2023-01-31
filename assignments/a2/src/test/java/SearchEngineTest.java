import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    /*
    TODO: Write an efficiency test which asserts that the SortedArrayTest is faster than the ArrayList. Be sure to test for edge cases.
    Also be sure to write tests that check whether your SearchEngine has accurate results.
     */
    private static SearchEngine engine1;
    private static SearchEngine engine2;

    @BeforeAll
    static void initEngine() throws IOException {
        SearchEngineTest.engine1 = new SearchEngine(1);
        SearchEngineTest.engine2 = new SearchEngine(2);
    }
    @Test
    void searchTest() {
        long start1 = System.nanoTime();
        System.out.println(SearchEngineTest.engine1.search("red"));
        long end1 = System.nanoTime();
        assertEquals(true, SearchEngineTest.engine1.search("red").size() > 0);
        assertEquals(true, SearchEngineTest.engine1.search("APPLE").size() == 0);

        long start2 = System.nanoTime();
        System.out.println(SearchEngineTest.engine2.search("red"));
        long end2 = System.nanoTime();
        assertEquals(true, SearchEngineTest.engine2.search("red").size() > 0);
        assertEquals(true, (end1 - start1) > (end2 - start2));
    }
}
