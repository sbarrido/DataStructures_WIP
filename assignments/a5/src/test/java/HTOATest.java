import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HTOATest {
    HashTableOpenAddressing tableDouble;
    HashTableOpenAddressing tableLine;
    HashTableOpenAddressing tableQuad;
    HashTableOpenAddressing tableDoubleFlavor;

    @BeforeEach
    void initTable() {
        //Default Constructor
        tableDouble = new HashTableOpenAddressing();
        //Mode constructor
        tableLine = new HashTableOpenAddressing(1);
        //Cap - factor constructor
        tableDoubleFlavor = new HashTableOpenAddressing(17, 0.5);
        //mode - cap - factor constructor
        tableQuad = new HashTableOpenAddressing(2, 11, 0.5);
    }
    @Test
    void initTest() {
        assertEquals(0, tableDouble.size());
        assertEquals(11, tableDouble.capacity());
        assertEquals(0, tableLine.size());
        assertEquals(11, tableLine.capacity());
        assertEquals(0, tableDoubleFlavor.size());
        assertEquals(17, tableDoubleFlavor.capacity());
        assertEquals(0, tableQuad.size());
        assertEquals(11, tableQuad.capacity());
    }
    @Test
    public void putDoubleTest() {
        HashTableOpenAddressing.Entry[] table = tableDouble.getTable();

        //Put to empty
        tableDouble.put(0, "a");
        assertEquals(1, tableDouble.size());
        assertEquals(11, tableDouble.capacity());
        assertEquals("a", table[0].getValue());

        //Update Value
        tableDouble.put(0, "butt");
        assertEquals(1, tableDouble.size());
        assertEquals(11, tableDouble.capacity());
        assertEquals("butt", table[0].getValue());

        //Put to non-empty
        //Resize
        for(int i = 1; i < 10; i++) {
            tableDouble.put(i, "broken");
        }
        assertEquals(10, tableDouble.size());
        assertEquals(23, tableDouble.capacity());

        //Collision at index 1: 24 % 23
        // double hashing: prev prime - hash(key) % prevPrime
        // 11 - (1 % 11) = next index 10, place
        tableDouble.put(24, "collision");
        assertEquals(11, tableDouble.size());
        assertEquals(23, tableDouble.capacity());
        assertEquals(1, tableDouble.getTable()[1].getKey());
        assertEquals(24, tableDouble.getTable()[10].getKey());
        assertEquals("collision", tableDouble.getTable()[10].getValue());

        //Update a Collided Item
        tableDouble.put(24, "Updated");
        assertEquals(11, tableDouble.size());
        assertEquals(23, tableDouble.capacity());
        assertEquals(1, tableDouble.getTable()[1].getKey());
        assertEquals(24, tableDouble.getTable()[10].getKey());
        assertEquals("Updated", tableDouble.getTable()[10].getValue());

    }
    @Test
    void putLineTest() {
        tableLine.put(0, "first");
        assertEquals(1, tableLine.size());
        assertEquals(11, tableLine.capacity());
        assertEquals(0, tableLine.getTable()[0].getKey());

        //Collision 1x: init index 0 -> index 1
        tableLine.put(11, "second");
        assertEquals(2, tableLine.size());
        assertEquals(11, tableLine.capacity());
        assertEquals(11, tableLine.getTable()[1].getKey());

        //Collision 2x: init index 0 -> index 2
        tableLine.put(22, "third");
        assertEquals(3, tableLine.size());
        assertEquals(11, tableLine.capacity());
        assertEquals(22, tableLine.getTable()[2].getKey());
        assertEquals("third", tableLine.getTable()[2].getValue());

        //Update a collided item
        tableLine.put(22, "Updated");
        assertEquals(3, tableLine.size());
        assertEquals(11, tableLine.capacity());
        assertEquals(22, tableLine.getTable()[2].getKey());
        assertEquals("Updated", tableLine.getTable()[2].getValue());

        tableLine.put(-1, "cat");

    }
    @Test
    void putQuadTest() {
        //put empty
        tableQuad.put(0, "first");
        assertEquals(1, tableQuad.size());
        assertEquals(11, tableQuad.capacity());

        //put non-empty
        //Different Load Factor
        for(int i = 1; i < 6; i++) {
            tableQuad.put(i, "broken");
        }
        assertEquals(6, tableQuad.size());
        assertEquals(23, tableQuad.capacity());

        //Update value
        tableQuad.put(2, "non broken");
        assertEquals(6, tableQuad.size());
        assertEquals(23, tableQuad.capacity());
        assertEquals("non broken", tableQuad.getTable()[2].getValue());
        //Collision
        //init index = 0;
        // quad check + 1 collide
        // quad check + 4 collide
        // quad check + 9 home
        tableQuad.put(23, "second");
        assertEquals(7, tableQuad.size());
        assertEquals(23, tableQuad.capacity());
        assertEquals(0, tableQuad.getTable()[0].getKey());
        assertEquals(1, tableQuad.getTable()[1].getKey());
        assertEquals(4, tableQuad.getTable()[4].getKey());
        assertEquals(23, tableQuad.getTable()[9].getKey());
        assertEquals("second", tableQuad.getTable()[9].getValue());

        //Update Collision
        tableQuad.put(23, "updated second");
        assertEquals(7, tableQuad.size());
        assertEquals(23, tableQuad.capacity());
        assertEquals(23, tableQuad.getTable()[9].getKey());
        assertEquals("updated second", tableQuad.getTable()[9].getValue());
    }

    @Test
    void getTest() {
        //Init Tables
        //DoubleFlavor this.capacity = 17, load = 0.5
        for(int i = 0; i < 7; i++) {
            tableLine.put(i, "woof");
            tableQuad.put(i, "rawr");
            tableDoubleFlavor.put(i, "POW");
        }

        //--------Line Tests---------------
        //null
        assertEquals(null, tableLine.get(10));
        //out of bounds
        assertEquals(null, tableLine.get(14));
        //exists
        assertEquals("woof", tableLine.get(2));
        //update
        tableLine.put(3, "meow");
        assertEquals(3, tableLine.getTable()[3].getKey());
        assertEquals("meow", tableLine.getTable()[3].getValue());
        assertEquals("meow", tableLine.get(3));
        assertEquals("woof", tableLine.get(2));

        //Collision Test get
        assertEquals(7, tableLine.size());
        assertEquals(11, tableLine.capacity());

        tableLine.put(11, "fish sound");
        assertEquals(8, tableLine.size());
        assertEquals(11, tableLine.capacity());
        assertEquals(11, tableLine.getTable()[7].getKey());
        assertEquals("fish sound", tableLine.get(11));
        //---- end line get tests -------

        // ------ begin quad get Tests------
        //null
        assertEquals(null, tableQuad.get(8));
        //out of bounds
        assertEquals(null, tableQuad.get(100));
        //exists
        assertEquals("rawr", tableQuad.get(4));

        //Update
        tableQuad.put(2, "pickles");
        assertEquals("pickles", tableQuad.get(2));

        //Quad Collision
        // init index : 0 collide
        // check 1: collid
        // check 4: collide
        // check 9: home
        tableQuad.put(23, "collide");
        assertEquals("rawr", tableQuad.get(0));
        assertEquals("rawr", tableQuad.get(1));
        assertEquals("rawr", tableQuad.get(4));
        assertEquals("collide", tableQuad.get(23));
        /// -------- end quad get Tests---------

        //----------Begin double hash Test---------
        //Null
        assertEquals(null, tableDoubleFlavor.get(8));
        //out of bounds
        assertEquals(null, tableDoubleFlavor.get(32487329));
        //exists
        assertEquals("POW", tableDoubleFlavor.get(2));

        //update(
        tableDoubleFlavor.put(2, "boom");
        assertEquals("boom", tableDoubleFlavor.get(2));
        //Double Hash Collision
        tableDoubleFlavor.put(18, "collide");
        assertEquals("collide", tableDoubleFlavor.get(18));

        //Double Hash runtime
        Exception ex = assertThrows(RuntimeException.class, () ->  {
            tableDoubleFlavor.put(17, "runtime");
        });
    }
}
