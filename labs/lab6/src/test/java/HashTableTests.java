import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTableTests {
    // TODO: you know what to do.. everything you write should be tested
    HashTable table;
    @BeforeEach
    void initTable() {
        table = new HashTable();

        //Putting in empty table
        table.put("a", "a");
        table.put("b", "b");
        table.put("l", "l");
    }

    @Test
    void initTest() {
        //Init Hashtable
        assertEquals(true, table instanceof HashTable);
        assertEquals(true, table.getEntries() instanceof List<Entry>);
        assertEquals(3, table.getSize());

        List<Entry> entries = table.getEntries();

        //Init Put Hashtable
        assertEquals("a", entries.get(9).getKey());
        assertEquals("a", entries.get(9).getValue());

        assertEquals("b", entries.get(10).getKey());
        assertEquals("b", entries.get(10).getValue());

        assertEquals("l", entries.get(2).getKey());
        assertEquals("l", entries.get(2).getValue());
    }
    @Test
    void putTest() {
        List<Entry> entries = table.getEntries();

        //Update Existing Key-Value Pair
        table.put("b","cat");
        assertEquals(3, table.getSize());
        assertEquals("b", entries.get(10).getKey());
        assertEquals("cat", entries.get(10).getValue());

        //Put new Values: non-empty list
        table.put("c", "c");
        table.put("d", "d");
        assertEquals(5, table.getSize());

        //Rehash
        //Test Rehashing size increases
        //New hashed index for existing KV-pairs
        table.put("e","e");
        entries = table.getEntries();
        assertEquals(6, table.getSize());
        assertEquals("a", entries.get(5).getKey());
        assertEquals("a", entries.get(5).getValue());
        assertEquals("b", entries.get(6).getKey());
        assertEquals("cat", entries.get(6).getValue());
        assertEquals("e", entries.get(11).getKey());
        assertEquals("e", entries.get(11).getValue());

        //Adding onto Tombstone
        table.remove("a");
        assertEquals(Entry.Type.TOMBSTONE, entries.get(5).getType());
        table.put("a", "I LOVE PICKLEBALL");
        assertEquals(6, table.getSize());
        assertEquals("a", entries.get(5).getKey());
        assertEquals("I LOVE PICKLEBALL", entries.get(5).getValue());
    }
    @Test
    void getTest() {
        assertEquals("a", table.get("a"));
        assertEquals("b", table.get("b"));
        assertEquals(null, table.get("BANANANANANNAAN"));

        //Add until rehash
        table.put("c", "c");
        table.put("d", "d");
        table.put("e", "e");

        //Check get funcs still work after rehash
        assertEquals(6, table.getSize());
        assertEquals("a", table.get("a"));
        assertEquals("b", table.get("b"));
        assertEquals(null, table.get("BANANANANANNAAN"));
    }
    @Test
    void rmvTest() {
        //Remove Existing
        table.remove("a");
        assertEquals(Entry.Type.TOMBSTONE, table.getEntries().get(9).getType());
        assertEquals(2, table.getSize());

        //Attempt to remove tombstone
        assertEquals(null, table.get("a"));

        //Attempt to remove non-existing key
        assertEquals(null, table.get("CHIHACIDAOCHU"));
    }
}
