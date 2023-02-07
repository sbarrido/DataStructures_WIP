import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest {

    static DoublyLinkedList<String> sList;
    static DoublyLinkedList<Double> dList;
    @BeforeEach
    void initList() {
        DoublyLinkedListTest.sList = new DoublyLinkedList<>();
        DoublyLinkedListTest.dList = new DoublyLinkedList<>();

        DoublyLinkedListTest.sList.insertAtTail("End");
        DoublyLinkedListTest.sList.insertAtHead("Mid");
    }
    @Test
    void insertTest() {
        //Insert at Tail - empty list
        //insert at head - size == 1
        assertEquals("End", DoublyLinkedListTest.sList.get(1));
        assertEquals("Mid", DoublyLinkedListTest.sList.get(0));

        //Insert at head - non-empty
        DoublyLinkedListTest.sList.insertAtHead("Begin");
        assertEquals("Begin", DoublyLinkedListTest.sList.get(0));
        assertEquals("Mid", DoublyLinkedListTest.sList.get(1));
        assertEquals("End", DoublyLinkedListTest.sList.get(2));

        //Insert at tail - non empty
        DoublyLinkedListTest.sList.insertAtTail("Super End");
        assertEquals("Begin", DoublyLinkedListTest.sList.get(0));
        assertEquals("Mid", DoublyLinkedListTest.sList.get(1));
        assertEquals("End", DoublyLinkedListTest.sList.get(2));
    }

    @Test
    void removeHeadTest() throws EmptyListE {
        //Delete the 2 items
        assertEquals("Mid", DoublyLinkedListTest.sList.deleteAtHead());
        assertEquals("End", DoublyLinkedListTest.sList.deleteAtHead());

        //Empty List
        Exception ex = assertThrows(Exception.class, () -> {
            DoublyLinkedListTest.sList.deleteAtHead();
        });
    }
    @Test
    void removeTailTest() throws EmptyListE {
        //Dellete from end
        assertEquals("End", DoublyLinkedListTest.sList.deleteAtTail());
        assertEquals("Mid", DoublyLinkedListTest.sList.deleteAtTail());

        //Empty List
        Exception ex = assertThrows(Exception.class, () -> {
            DoublyLinkedListTest.sList.deleteAtTail();
        });
    }

    @Test
    void getTest() {
        assertEquals("Mid", DoublyLinkedListTest.sList.get(0));
        assertEquals("End", DoublyLinkedListTest.sList.get(1));

        Exception big = assertThrows(IndexOutOfBoundsException.class, () -> {
            DoublyLinkedListTest.sList.get(4);
        });

        Exception small = assertThrows(IndexOutOfBoundsException.class, () -> {
            DoublyLinkedListTest.sList.get(-23);
        });
    }
    @Test
    void searchTest() {
        assertEquals(0, DoublyLinkedListTest.sList.search("Mid"));
        assertEquals(1, DoublyLinkedListTest.sList.search("End"));
        assertEquals(-1, DoublyLinkedListTest.sList.search("BANANA"));
    }
}
