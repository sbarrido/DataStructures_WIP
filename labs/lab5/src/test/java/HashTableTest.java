import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTableTest {
    private HashTable hTable;
    @BeforeEach
    void initHashTable() {
        hTable = new HashTable();
        hTable.put("First", "Monkey");
        hTable.put("Second", "Donkey");
        hTable.put("Bounce", "Ball");
        hTable.put("Thicc", "Boots");


    }
    @Test
    void rmvTest() {
        assertEquals(4, hTable.getSize());
        assertEquals("Ball", hTable.get("Bounce"));

        //Remove Non-Empty
        hTable.remove("Bounce");
        assertEquals(3, hTable.getSize());
        assertEquals(null, hTable.get("Bounce"));

        //Invalid Target
        hTable.remove("INVALID");
        assertEquals(3, hTable.getSize());

        //EMPTY LIST
        hTable.remove("First");
        hTable.remove("Second");
        hTable.remove("Thicc");
        assertEquals(0, hTable.getSize());

        hTable.remove("Donkey");
        assertEquals(0, hTable.getSize());
    }
    @Test
    void getTest() {
        //Base init
        assertEquals("Monkey", hTable.get("First"));
        assertEquals("Donkey", hTable.get("Second"));
        assertEquals("Boots", hTable.get("Thicc"));

        //Update
        hTable.put("Thicc", "Prawns");
        assertEquals("Prawns", hTable.get("Thicc"));

        //Same HASH?
        hTable.put("ccihT", "sticks");
        assertEquals("sticks", hTable.get("ccihT"));

        //NOT FOUND
        assertEquals(null, hTable.get("EXTRATHICC"));
    }
    @Test
    void putTest() {
        assertEquals(4, hTable.getSize());
        assertEquals(11, hTable.getCapacity());

        //New Entry
        hTable.put("Third", "Elephant");
        assertEquals(5, hTable.getSize());
        assertEquals(11, hTable.getCapacity());

        //Update Entry
        hTable.put("Third", "Trident");
        assertEquals(5, hTable.getSize());
        assertEquals(11, hTable.getCapacity());

        //Rehash and Double capacity
        hTable.put("Peanut", "Butter");
        assertEquals(6, hTable.getSize());
        assertEquals(22, hTable.getCapacity());

        //Check update Entry after rehash
        hTable.put("First", "Carrrot");
        assertEquals(6, hTable.getSize());
        assertEquals(22, hTable.getCapacity());
    }
}
