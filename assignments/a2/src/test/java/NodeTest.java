import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    private static Node n1;
    private static Node n2;
    private static Node s1;
        /*
    TODO: write test cases testing the accuracy of the methods marked with TODO.s.
     */
    @BeforeEach
    void initNode() {
        n1 = new Node("banana", 1);
        n2 = new Node("yum", 1);
        s1 = new Node("banana" , 1);
    }

    @Test
    void initTest() {
        assertEquals("banana", n1.getKeyword());
        assertEquals(true, n1.equals(s1));
        assertEquals(false, n1.equals(n2));
        assertEquals(true, n1.compareTo(s1) == 0);
        assertEquals(true, n1.compareTo(n2) > 0);
    }
}
