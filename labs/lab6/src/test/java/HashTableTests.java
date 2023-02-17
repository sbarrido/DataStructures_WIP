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
//        table.put("a", "a");
//        table.put("b", "b");
//        table.put("l", "l");
    }

    @Test
    void initTest() {
        assertEquals(true, table instanceof HashTable);
        assertEquals(true, table.getEntries() instanceof List<Entry>);
        assertEquals(0, table.getSize());
    }
    @Test
    void putTest() {
       //table.put("trash", "big");
//        for(int i = 0; i < table.getEntries().size(); i++){
//            System.out.print(i + ": ");
//            Entry target = table.getEntries().get(i);
//            if(target == null){
//                System.out.println(target);
//            } else {
//                System.out.println(target.getKey());
//            }
//        }
        List<Entry> entries = table.getEntries();
        table.put("a", "a");
        assertEquals(1, table.getSize());
        assertEquals("a", entries.get(9).getKey());
        assertEquals("a", entries.get(9).getValue());
        table.put("b", "b");
        assertEquals(2, table.getSize());
        assertEquals("b", entries.get(10).getKey());
        assertEquals("b", entries.get(10).getValue());
        table.put("l", "l");
        assertEquals(3, table.getSize());
        assertEquals("l", entries.get(2).getKey());
        assertEquals("l", entries.get(2).getValue());

        table.put("l", "update");
        assertEquals(3, table.getSize());
        assertEquals("l", entries.get(2).getKey());
        assertEquals("update", entries.get(2).getValue());

       // assertEquals(4, table.getSize());
//        assertEquals(false, table.getEntries().isEmpty());
//        assertEquals("Third", table.getEntries().get(1).getKey());
//      //  assertEquals("trash", table.getEntries().get(8).getKey());
//        assertEquals(null, table.getEntries().get(6));

    }
//    @Test
//    void getTest() {
//        assertEquals("Pickleball", table.get("Third"));
//        assertEquals("Dinosuar", table.get("second"));
//    }
}
