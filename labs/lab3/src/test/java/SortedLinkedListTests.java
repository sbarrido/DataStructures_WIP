import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SortedLinkedListTests {
    SortedLinkedList list = new SortedLinkedList();
    /*
    TODO: tests
    - Make sure you have 100% code coverage
        + This also means you should break your tests up by method
    - Make sure you test the full functionality of this class...
      think edge cases (bounds, exceptions, etc...)
    - Use JUnit (you will not receive points for testing if you do
      not use JUnit)
     */
    @Test
    void constructorTest() {
        assertEquals(null, list.head);
        assertEquals(null, list.tail);

    }
    @Test
    void insertTest() {
        //Empty List Insertion Test
        list.insertSorted(10);
        assertEquals(10, list.head.data);
        assertEquals(10, list.tail.data);

        //Insert at End Test
        list.insertSorted(11);
        assertEquals(10, list.head.data);
        assertEquals(11, list.tail.data);

        //Insert at Beginning Test
        list.insertSorted(0);
        assertEquals(0, list.head.data);
        assertEquals(11, list.tail.data);

        //Insert at Mid
        list.insertSorted(1);
        assertEquals(0, list.head.data);
        assertEquals(11, list.tail.data);

        assertEquals("0, 1, 10, 11", list.toString());
    }

    @Test
    void getTest() {
        list.insertSorted(10);
        assertEquals(10, list.head.data);
        assertEquals(10, list.tail.data);

        //Insert at End Test
        list.insertSorted(11);
        assertEquals(10, list.head.data);
        assertEquals(11, list.tail.data);

        //Insert at Beginning Test
        list.insertSorted(0);
        assertEquals(0, list.head.data);
        assertEquals(11, list.tail.data);

        //Insert at Mid
        list.insertSorted(1);
        assertEquals(0, list.head.data);
        assertEquals(11, list.tail.data);

        //Get within bounds
        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(11, list.get((3)));

        //Get outside Bounds
        //assertEquals(new IndexOutOfBoundsException(), list.get(-2));
        Exception ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-2);
        });
        Exception ex2 = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        });
    }
    @Test
    void searchTest() {
        list.insertSorted(10);
        assertEquals(10, list.head.data);
        assertEquals(10, list.tail.data);

        //Insert at End Test
        list.insertSorted(11);
        assertEquals(10, list.head.data);
        assertEquals(11, list.tail.data);

        //Insert at Beginning Test
        list.insertSorted(0);
        assertEquals(0, list.head.data);
        assertEquals(11, list.tail.data);

        //Insert at Mid
        list.insertSorted(1);
        assertEquals(0, list.head.data);
        assertEquals(11, list.tail.data);

        //Search within Bounds
        assertEquals(0, list.search(0));
        assertEquals(1, list.search(1));
        assertEquals(2, list.search(10));
        assertEquals(3, list.search(11));

        //Search outside Bounds
        assertEquals(-1, list.search(-2));
        assertEquals(-1, list.search(5));
    }

    @Test
    void deleteTest() {
        list.insertSorted(10);
        assertEquals(10, list.head.data);
        assertEquals(10, list.tail.data);

        //Insert at End Test
        list.insertSorted(11);
        assertEquals(10, list.head.data);
        assertEquals(11, list.tail.data);

        //Insert at Beginning Test
        list.insertSorted(0);
        assertEquals(0, list.head.data);
        assertEquals(11, list.tail.data);

        //Insert at Mid
        list.insertSorted(1);
        assertEquals(0, list.head.data);
        assertEquals(11, list.tail.data);

        //Delete Beginning
        list.delete(0);
        assertEquals(1, list.head.data);

        //Delete Mid
        list.delete(10);
        assertEquals(1, list.head.data);
        assertEquals(11, list.tail.data);

        //Delete End
        list.delete(11);
        assertEquals(1, list.tail.data);

    }
}
