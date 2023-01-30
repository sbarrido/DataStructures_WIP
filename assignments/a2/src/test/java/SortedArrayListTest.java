import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class SortedArrayListTest {
    /*
    TODO: write test cases testing the accuracy of the methods marked with TODO. Write an efficiency test which asserts that the
    SortedArrayTest is faster than the ArrayList. Be sure to test for edge cases.
     */
    private static SortedArrayList<String> stringList;
    private static SortedArrayList<Double> doubleList;
    @BeforeEach
    public void init() {
        SortedArrayListTest.stringList = new SortedArrayList<>();
        SortedArrayListTest.doubleList = new SortedArrayList<>();
        SortedArrayListTest.stringList.add("Apple");
        SortedArrayListTest.stringList.add("Calamansi");
        SortedArrayListTest.stringList.add("Banana");
        SortedArrayListTest.stringList.add("Durian");

        SortedArrayListTest.doubleList.add(5.0);
        SortedArrayListTest.doubleList.add(2.0);
        SortedArrayListTest.doubleList.add(15.0);
        SortedArrayListTest.doubleList.add(3.0);
        SortedArrayListTest.doubleList.add(1.0);
        SortedArrayListTest.doubleList.add(12.0);
        SortedArrayListTest.doubleList.add(52.0);
        SortedArrayListTest.doubleList.add(3.0);
        SortedArrayListTest.doubleList.add(0.0);
    }

    @Test
    public void checkInit() {
        assertEquals(4, SortedArrayListTest.stringList.size());
        assertEquals(10, SortedArrayListTest.stringList.getCapacity());
        assertEquals(9, SortedArrayListTest.doubleList.size());
        assertEquals(10, SortedArrayListTest.doubleList.getCapacity());
    }
    @Test
    public void addTest() {
        SortedArrayListTest.doubleList.add(-1.0);
        assertEquals(10, SortedArrayListTest.doubleList.size());
        assertEquals(10, SortedArrayListTest.doubleList.getCapacity());
        SortedArrayListTest.doubleList.add(8.5);
        assertEquals(11, SortedArrayListTest.doubleList.size());
        assertEquals(20, SortedArrayListTest.doubleList.getCapacity());
    }
    @Test
    public void searchTest() {
        System.out.println(SortedArrayListTest.stringList);
        assertEquals(0, SortedArrayListTest.stringList.search("Apple"));
        assertEquals(1, SortedArrayListTest.stringList.search("Banana"));
        assertEquals(2, SortedArrayListTest.stringList.search("Calamansi"));
        assertEquals(3, SortedArrayListTest.stringList.search("Durian"));
        assertEquals(-1, SortedArrayListTest.stringList.search("KIDNEYS"));
    }
}
