import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTOATest {
    HashTableOpenAddressing tableOpen;

    @BeforeEach
    void initTable() {
        tableOpen = new HashTableOpenAddressing();
    }
    @Test
    void initTest() {
        assertEquals(0, tableOpen.size());
        assertEquals(11, tableOpen.capacity());
    }
    @Test
    public void putTest() {
        HashTableOpenAddressing.Entry[] table = tableOpen.getTable();

        tableOpen.put(0, "a");
        assertEquals(1, tableOpen.size());
        assertEquals(11, tableOpen.capacity());
        assertEquals("a", table[0].getValue());

        tableOpen.put(0, "butt");
        assertEquals(1, tableOpen.size());
        assertEquals(11, tableOpen.capacity());
        assertEquals("butt", table[0].getValue());

        for(int i = 1; i < 10; i++) {
            tableOpen.put(i, "broken");
        }
        assertEquals(10, tableOpen.size());
        assertEquals(23, tableOpen.capacity());
    }
}
