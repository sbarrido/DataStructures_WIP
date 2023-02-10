import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryTest {
    @Test
    void initEntryTest() {
        Entry e = new Entry("TestKey", "Value");
        Entry e2 = new Entry("monkey", "Sees");

        assertEquals("TestKey", e.key);
        assertEquals("Value", e.value);
        assertEquals("monkey", e2.key);
        assertEquals("Sees", e2.value);
        assertEquals(false, e.key.equals("Donkey"));
    }
}