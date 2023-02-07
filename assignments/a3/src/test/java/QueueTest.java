import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    @Test
    void en_dequeueTest() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        assertEquals(1, q.peek());
        assertEquals(3, q.size());

        try{
            q.dequeue();
            assertEquals(2, q.peek());
            assertEquals(2, q.size());

            q.dequeue();
            assertEquals(3, q.peek());
            assertEquals(1, q.size());

            //remove last
            q.dequeue();

            Exception empty = assertThrows(EmptyQueueE.class, () -> {
                q.dequeue();
            });
            assertEquals(0, q.size());
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
}
