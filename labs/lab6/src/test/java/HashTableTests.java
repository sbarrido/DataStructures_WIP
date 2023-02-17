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
        table.put("First", "Apple");
//        table.put("second", "Dinosaur");
//        table.put("Third", "Pickleball");
    }

    @Test
    void initTest() {
        assertEquals(true, table instanceof HashTable);
        assertEquals(true, table.getEntries() instanceof List<Entry>);
        assertEquals(0, table.getSize());
    }
}
