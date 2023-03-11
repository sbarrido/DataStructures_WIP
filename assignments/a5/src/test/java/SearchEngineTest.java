import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    // TODO: accuracy tests + 1 efficiency test to make sure both hashtables build in under a minute
    SearchEngine engineOpen;
    SearchEngine engineChain;

    @Test
    void initTest() {
        try{
            engineOpen = new SearchEngine(5);
            engineChain = new SearchEngine(6);

            assertEquals(true, engineOpen.getNodeTree() instanceof HashTableOpenAddressing<String,Node>);
            assertEquals(true, engineChain.getNodeTree() instanceof HashTableWithChaining<String,Node>);

            engineOpen.search("cat");
            engineOpen.buildList();

            engineChain.search("cat");
            engineChain.buildList();
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
}