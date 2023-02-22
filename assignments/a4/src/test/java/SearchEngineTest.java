import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    SearchEngine bstEngine;
    SearchEngine avlEngine;

    void initEngines(){
        try{
            bstEngine = new SearchEngine(3);
            avlEngine = new SearchEngine(4);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    @Test
    void initTest() {
        initEngines();
        assertEquals(true, bstEngine.getNodeTree() instanceof BST);
        assertEquals(true, avlEngine.getNodeTree() instanceof AVL);
    }
    @Test
    void buildEngineTest() {
        try{
            long startTime = System.nanoTime();
            bstEngine = new SearchEngine(3);
            long endTime = System.nanoTime();
            long bstBuildTime = endTime - startTime;

            startTime = System.nanoTime();
            avlEngine = new SearchEngine(4);
            endTime = System.nanoTime();
            long avlBuildTime = endTime - startTime;

            //Build Speed is faster for BST - No need to Rotate
            assertEquals(true, bstBuildTime < avlBuildTime);
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    @Test
    void searchTest() {
        initEngines();

        long startTime = System.nanoTime();
        bstEngine.search("donkey");
        long endTime = System.nanoTime();
        long bstSearch = endTime - startTime;

        startTime = System.nanoTime();
        avlEngine.search("donkey");
        endTime = System.nanoTime();
        long avlSearch = endTime - startTime;

        //AVL is faster because balanced
        assertEquals(true, avlSearch < bstSearch);
    }
}
