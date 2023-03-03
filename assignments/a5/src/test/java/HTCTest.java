import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HTCTest {
    // TODO: accuracy tests
    HashTableWithChaining chain;

    @BeforeEach
    void initTable() {
        chain = new HashTableWithChaining<>();
    }

    @Test
    void initTest() {
        assertEquals(0, chain.size());
        assertEquals(11, chain.capacity());
    }
    @Test
    void putTest() {
        chain.put(1, "a");
        Integer first = new Integer(1);
        int index = first.hashCode() % chain.capacity();
        List table = chain.table();
        LinkedList bin = (LinkedList) chain.table().get(index);
        Iterator binIter = bin.iterator();
        HashTableWithChaining.Entry entry = null;
        boolean found = false;

        while(binIter.hasNext()) {
            entry = (HashTableWithChaining.Entry) binIter.next();
            if(new Integer(1).equals(entry.getKey())) {
                found = true;
            }
        }
        assertTrue(found);
        assertEquals(1, chain.size());
        assertEquals(11, chain.capacity());

        //update
        chain.put(1, "apple");
        assertEquals("apple", entry.getValue());
        assertEquals(1, chain.size());
        assertEquals(11, chain.capacity());

        chain.put(2, "b");
        assertEquals(2, chain.size());
        assertEquals(11, chain.capacity());

        chain.put(3, "c");
        assertEquals(3, chain.size());
        assertEquals(11, chain.capacity());

        chain.put(4, "d");
        assertEquals(4, chain.size());
        assertEquals(11, chain.capacity());

        chain.put(5, "e");
        assertEquals(5, chain.size());
        assertEquals(11, chain.capacity());

        chain.put(6, "f");
        assertEquals(6, chain.size());
        assertEquals(11, chain.capacity());

        chain.put(7, "g");
        assertEquals(7, chain.size());
        assertEquals(11, chain.capacity());

        chain.put(8, "h");
        assertEquals(8, chain.size());
        assertEquals(11, chain.capacity());

        //Resize
        chain.put(9, "i");
        boolean found9 = false;
        Integer nine = new Integer(9);
        index = nine.hashCode() % chain.capacity();
        bin = (LinkedList) chain.table().get(index);
        binIter = bin.iterator();
        while(binIter.hasNext()) {
            entry = (HashTableWithChaining.Entry) binIter.next();
            if(new Integer(9).equals(entry.getKey())) {
                found9 = true;
            }
        }
        assertTrue(found9);
        assertEquals(9, chain.size());
        assertEquals(23, chain.capacity());

        chain.put(10, "j");
        assertEquals(10, chain.size());
        assertEquals(23, chain.capacity());

    }
    @Test
    void getTest() {
        chain.put(0, "a");

        assertEquals(null, chain.get(100));
        assertEquals("a", chain.get(0));

        chain.put(0, "bubble");
        assertEquals("bubble", chain.get(0));

        chain.put(2, "a");
        chain.put(3, "aa");
        chain.put(4, "aaa");
        chain.put(5, "aaaa");
        chain.put(6, "aaaaa");
        chain.put(7, "aaaaaa");
        chain.put(8, "aaaaaaa");
        chain.put(9, "aaaaaaaa");
        //resized
        assertEquals("bubble", chain.get(0));
        assertEquals("aaaaaaa", chain.get(8));
    }
    @Test
    void removeTest() {
        assertFalse(chain.remove(1));

        chain.put(1, "apple");
        assertTrue(chain.remove(1));

        List table = chain.table();
        Integer val = new Integer(1);
        int index = val.hashCode() % chain.capacity();
        LinkedList bin = (LinkedList) table.get(index);
        Iterator<HashTableWithChaining.Entry> binIter = bin.iterator();
        boolean found = false;
        while(binIter.hasNext()) {
            if(binIter.next().getKey().equals(val)) {
                found = true;
            }
        }

        assertFalse(found);
    }
}
