import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

public class TrieNodeWHashTableTest {
    @Test
    void initTest() {
        TrieNodeWHashTable testNode = new TrieNodeWHashTable();
        assertEquals(false, testNode.isWord);
        assertEquals(true, testNode.children instanceof Hashtable);
    }

    @Test
    void modifyChildrenTest() {
        TrieNodeWHashTable testNode = new TrieNodeWHashTable();
        testNode.children.put('a', new TrieNodeWHashTable());
        testNode.children.put('z', new TrieNodeWHashTable());
        assertEquals(true, testNode.children.get('a') instanceof TrieNodeWHashTable);
        assertEquals(false , testNode.children.get('o') instanceof TrieNodeWHashTable);
        assertEquals(null, testNode.children.remove('o'));
        assertEquals(true, (testNode.children.remove('a')) instanceof TrieNodeWHashTable);
        assertEquals(true, (testNode.children.remove('z')) instanceof TrieNodeWHashTable);
    }
}
