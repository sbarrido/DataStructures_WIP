import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
    /*
    TODO: write test cases testing the accuracy of the methods marked with TODO. Write an efficiency test which asserts that the
    SortedArrayTest is faster than the ArrayList. Be sure to test for edge cases.
     */
    @Test
    void initTest() {
        ArrayList<String> stringList = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();
        ArrayList<Comparable> compList = new ArrayList<>();

        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        assertEquals(0, compList.size());

        assertEquals(10, stringList.getCapacity());
        assertEquals(10, intList.getCapacity());
        assertEquals(10, compList.getCapacity());
    }

    @Test
    void addTest() {
        ArrayList<String> stringList = new ArrayList<>();

        //Add to empty list
        //test size and capacity
        stringList.add("Yum");
        assertEquals(1, stringList.size());
        assertEquals(10, stringList.getCapacity());

        //Add to non-empty list, but less than init capacity = 10
        //test size and capacity
        stringList.add("Yum");
        stringList.add("Yum");
        stringList.add("Yum");
        stringList.add("Yum");
        stringList.add("YUUUUUUUMMY");
        stringList.add("Yum");
        stringList.add("Yum");
        stringList.add("Yum");
        assertEquals(9, stringList.size());
        assertEquals(10, stringList.getCapacity());

        //Add to size at capacity
        //test size and capacity
        stringList.add("THICCCC");
        assertEquals(10, stringList.size());
        assertEquals(10, stringList.getCapacity());

        //Add when capacity = full
        //test size and capacity
        stringList.add("Dummy Thiccccc");
        assertEquals(11, stringList.size());
        assertEquals(20, stringList.getCapacity());
    }

    @Test
    void deleteTest() {
        ArrayList<String> stringList = new ArrayList<>();

        //invalid tests - throwing indexOOB
        Exception ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            stringList.delete(-2);
        });
        Exception ex2 = assertThrows(IndexOutOfBoundsException.class, () -> {
            stringList.delete(0);
        });
        //Add to empty list
        //test size and capacity
        stringList.add("Yum");
        assertEquals(1, stringList.size());
        assertEquals(10, stringList.getCapacity());
        //DELETE 1
        stringList.delete(0);
        assertEquals(0, stringList.size());
        assertEquals(10, stringList.getCapacity());

        //Add to empty list, but less than init capacity = 10
        //test size and capacity
        stringList.add("Yum");
        stringList.add("Yum");
        stringList.add("Yum");
        stringList.add("Yum");
        stringList.add("YUUUUUUUMMY");
        stringList.add("Yum");
        stringList.add("Yum");
        stringList.add("Yum");
        assertEquals(8, stringList.size());
        assertEquals(10, stringList.getCapacity());

        //Delete - mid
        stringList.delete(3);
        assertEquals(7, stringList.size());

        //Delete - Beg
        stringList.delete(0);
        assertEquals(6, stringList.size());

        //Delete - end
        stringList.delete(5);
        assertEquals(5, stringList.size());
    }

    @Test
    void getTest() {
        //TODO
        ArrayList<String> stringList = new ArrayList<>();

        //Add to empty list
        //test size and capacity
        stringList.add("Yum");
        assertEquals(1, stringList.size());
        assertEquals(10, stringList.getCapacity());

        //Add to non-empty list, but less than init capacity = 10
        //test size and capacity
        stringList.add("Double");
        stringList.add("Butt");
        stringList.add("Yuck");
        stringList.add("Yum");
        stringList.add("YUUUUUUUMMY");
        stringList.add("Flower");
        stringList.add("Candy");
        stringList.add("Bubble");
        assertEquals(9, stringList.size());
        assertEquals(10, stringList.getCapacity());

        Exception ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            stringList.get(-2);
        });
        Exception ex2 = assertThrows(IndexOutOfBoundsException.class, () -> {
            stringList.get(29);
        });

        //Get beginning, end, within list
        assertEquals("Bubble", stringList.get(8));
        assertEquals("Yum", stringList.get(0));
        assertEquals("Butt",stringList.get(2));
    }

    @Test
    void searchTest() {
        ArrayList<String> stringList = new ArrayList<>();

        //Add to empty list
        //test size and capacity
        stringList.add("Yum");
        assertEquals(1, stringList.size());
        assertEquals(10, stringList.getCapacity());

        //Add to non-empty list, but less than init capacity = 10
        //test size and capacity
        stringList.add("Double");
        stringList.add("Butt");
        stringList.add("Yuck");
        stringList.add("Yum");
        stringList.add("YUUUUUUUMMY");
        stringList.add("Flower");
        stringList.add("Candy");
        stringList.add("Bubble");
        assertEquals(9, stringList.size());
        assertEquals(10, stringList.getCapacity());

        //Search begin, mid, end, no exist
        assertEquals(0, stringList.search("Yum"));
        assertEquals(8, stringList.search("Bubble"));
        assertEquals(1, stringList.search("Double"));
        assertEquals(-1, stringList.search("yum"));
    }
}
