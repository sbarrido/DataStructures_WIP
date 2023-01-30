import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;

public class SortedArrayListTest {
    /*
    write test cases testing the accuracy of the methods marked with TODO. Write an efficiency test which asserts that the
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
        System.out.print(SortedArrayListTest.stringList);
        assertEquals(0, SortedArrayListTest.stringList.search("Apple"));
        assertEquals(1, SortedArrayListTest.stringList.search("Banana"));
        assertEquals(2, SortedArrayListTest.stringList.search("Calamansi"));
        assertEquals(3, SortedArrayListTest.stringList.search("Durian"));
        assertEquals(-1, SortedArrayListTest.stringList.search("KIDNEYS"));
        System.out.println("==============Success Search=====================");
    }

    @Test
    public void deleteTest() {

        //BadIndex delete
        System.out.println(SortedArrayListTest.doubleList);
        Exception ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            SortedArrayListTest.doubleList.delete(-1);
        });
        Exception ex2 = assertThrows(IndexOutOfBoundsException.class, () -> {
            SortedArrayListTest.doubleList.delete(9);
        });

        //Delete at End
        SortedArrayListTest.doubleList.delete(8);
        assertEquals(8, SortedArrayListTest.doubleList.size());
        assertEquals(10, SortedArrayListTest.doubleList.getCapacity());
        assertEquals(15.0, SortedArrayListTest.doubleList.get(7));
        assertEquals(7, SortedArrayListTest.doubleList.search(15.0));
        System.out.println(SortedArrayListTest.doubleList);

        //Delete Beginning
        SortedArrayListTest.doubleList.delete(0);
        assertEquals(7, SortedArrayListTest.doubleList.size());
        assertEquals(10, SortedArrayListTest.doubleList.getCapacity());
        assertEquals(1.0, SortedArrayListTest.doubleList.get(0));
        assertEquals(6, SortedArrayListTest.doubleList.search(15.0));
        assertEquals(2.0, SortedArrayListTest.doubleList.get(1));
        System.out.println(SortedArrayListTest.doubleList);

        //Delete Middle
        SortedArrayListTest.doubleList.delete(3);
        assertEquals(6, SortedArrayListTest.doubleList.size());
        assertEquals(10, SortedArrayListTest.doubleList.getCapacity());
        assertEquals(5.0, SortedArrayListTest.doubleList.get(3));
        assertEquals(5, SortedArrayListTest.doubleList.search(15.0));
        System.out.println(SortedArrayListTest.doubleList);
        System.out.println("=============Success Delete================");
    }
    @Test
    void speedTest() {
        ArrayList<Double> simpleList = new ArrayList<>();
        SortedArrayList<Double> sortList = new SortedArrayList<>();

        //ADD EFFICIENCY
        Random gen = new Random();
        long simpleStartTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            simpleList.add(gen.nextDouble());
        }
        long simpleEndTime = System.nanoTime();
        long simpleDuration = (simpleEndTime - simpleStartTime);

        long sortedStartTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            sortList.add(gen.nextDouble());
        }
        long sortedEndTime = System.nanoTime();
        long sortedDuration = (sortedEndTime - sortedStartTime);
        System.out.println("ArrayList ADD Speed:" + simpleDuration);
        System.out.println("SortedList ADD Speed:" + sortedDuration);

        assertEquals(false, sortedDuration < simpleDuration);

        //GETTING EFFICIENCY
        simpleStartTime = System.nanoTime();
        simpleList.get(50);
        simpleEndTime = System.nanoTime();
        simpleDuration = (simpleEndTime - simpleStartTime);

        sortedStartTime = System.nanoTime();
        sortList.get(50);
        sortedEndTime = System.nanoTime();
        sortedDuration = (sortedEndTime - sortedStartTime);

        System.out.println("ArrayList GET Speed:" + simpleDuration);
        System.out.println("SortedList GET Speed:" + sortedDuration);
        assertEquals(true, sortedDuration <= simpleDuration);

        //DELETE EFFICIENCY
        simpleStartTime = System.nanoTime();
        simpleList.delete(50);
        simpleEndTime = System.nanoTime();
        simpleDuration = (simpleEndTime - simpleStartTime);

        sortedStartTime = System.nanoTime();
        sortList.delete(50);
        sortedEndTime = System.nanoTime();
        sortedDuration = (sortedEndTime - sortedStartTime);

        System.out.println("ArrayList DELETE Speed:" + simpleDuration);
        System.out.println("SortedList DELETE Speed:" + sortedDuration);
        assertEquals(true, sortedDuration > simpleDuration);

        //SEARCH EFFICIENCY
        simpleStartTime = System.nanoTime();
        simpleList.search(simpleList.get(67));
        simpleEndTime = System.nanoTime();
        simpleDuration = (simpleEndTime - simpleStartTime);

        sortedStartTime = System.nanoTime();
        sortList.search(sortList.get(67));
        sortedEndTime = System.nanoTime();
        sortedDuration = (sortedEndTime - sortedStartTime);

        System.out.println("ArrayList SEARCH Speed:" + simpleDuration);
        System.out.println("SortedList SEARCH Speed:" + sortedDuration);
        assertEquals(true, sortedDuration < simpleDuration);
    }
}
