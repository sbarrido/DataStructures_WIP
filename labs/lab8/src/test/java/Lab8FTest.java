import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Lab8FTest {

    labRunner lab = new labRunner();
    @Test
    void swap() {
        int[] arr = new int[]{12, 11, 13, 5, 6};
        assertEquals(11, arr[1]);
        assertEquals(5, arr[3]);
        lab.swap(arr, 1, 3);
        assertEquals(5, arr[1]);
        assertEquals(11, arr[3]);

    }

    @Test
    void insertionSort() {
        int[] arr = new int[]{12, 11, 13, 5, 6};
        arr = lab.insertionSort(arr);

        assertEquals(5, arr[0]);
        assertEquals(6, arr[1]);
        assertEquals(11, arr[2]);
        assertEquals(12, arr[3]);
        assertEquals(13, arr[4]);
    }

    @Test
    void quickSort() {
        int[] arr = new int[] {10, 80, 30, 90, 40, 50, 70};
        lab.quickSort(arr, 0, 6);

        assertEquals(10, arr[0]);
        assertEquals(30, arr[1]);
        assertEquals(40, arr[2]);
        assertEquals(50, arr[3]);
        assertEquals(70, arr[4]);
        assertEquals(80, arr[5]);
        assertEquals(90, arr[6]);

    }

    @Test
    void partition() {
        int[] arr = new int[] {10, 80, 30, 90, 40, 50, 70};
        int part = lab.partition(arr, 0, 6);
        assertEquals(4, part);

        assertEquals(10, arr[0]);
        assertEquals(30, arr[1]);
        assertEquals(40, arr[2]);
        assertEquals(50, arr[3]);
        assertEquals(70, arr[4]);
        assertEquals(90, arr[5]);
        assertEquals(80, arr[6]);

    }
}