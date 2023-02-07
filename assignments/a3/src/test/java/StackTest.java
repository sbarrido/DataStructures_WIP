import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    void pushTest() {
        Stack<Integer> fatStack = new Stack<>();
        assertEquals(0, fatStack.size());

        fatStack.push(10);
        assertEquals(1, fatStack.size());
        assertEquals("10 ", fatStack.toString());

        fatStack.push(8);
        assertEquals(2, fatStack.size());
        assertTrue(fatStack.toString().equals("8 10 "));

        fatStack.push(6);
        assertEquals(3, fatStack.size());
        assertTrue(fatStack.toString().equals("6 8 10 "));

        fatStack.push(4);
        assertEquals(4, fatStack.size());
        assertTrue(fatStack.toString().equals("4 6 8 10 "));

        fatStack.push(2);
        assertEquals(5, fatStack.size());
        assertTrue(fatStack.toString().equals("2 4 6 8 10 "));

        fatStack.push(0);
        assertEquals(6, fatStack.size());
        assertTrue(fatStack.toString().equals("0 2 4 6 8 10 "));
    }
    @Test
    void popTest() {
        Stack<Integer> fatStack = new Stack<>();
        assertEquals(0, fatStack.size());

        fatStack.push(10);
        assertEquals(1, fatStack.size());
        assertEquals("10 ", fatStack.toString());

        fatStack.push(8);
        assertEquals(2, fatStack.size());
        assertTrue(fatStack.toString().equals("8 10 "));

        try {
            assertEquals(8, fatStack.pop());
            assertEquals(1, fatStack.size());

            assertEquals(10, fatStack.pop());
            assertEquals(0, fatStack.size());
        } catch(Exception ex) {
            System.out.print(ex);
        }
    }
    @Test
    void peekTest() {
        Stack<Integer> fatStack = new Stack<>();
        assertEquals(0, fatStack.size());
        Exception invalid = assertThrows(IndexOutOfBoundsException.class, () -> {
            fatStack.peek();
        });

        fatStack.push(1);
        assertEquals(1, fatStack.peek());
        fatStack.push(2);
        assertEquals(2, fatStack.peek());
        try{
            fatStack.pop();
        }catch(Exception ex) {
            System.out.print(ex);
        }
        assertEquals(1, fatStack.peek());
    }
    @Test
    void equalTest() {
        Stack<Integer> fatStack = new Stack<>();
        Stack<Integer> flapJacks = new Stack<>();


        try {
            fatStack.push(1);
            fatStack.push(3);

            flapJacks.push(2);
            assertEquals(false, fatStack.equals(flapJacks));

            flapJacks.pop();
            flapJacks.push(1);
            flapJacks.push(3);
            assertEquals(true, fatStack.equals(flapJacks));

        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
}
