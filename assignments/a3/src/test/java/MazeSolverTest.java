import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTest {
    @Test
    void mazeStackTest() {
        try{
            assertEquals(true, MazeSolver.solve("maze1.txt", 1));
            assertEquals(true, MazeSolver.solve("maze2.txt", 1));
            assertEquals(false, MazeSolver.solve("maze3.txt", 1));

            assertEquals(true, MazeSolver.solve("maze1.txt", 2));
            assertEquals(true, MazeSolver.solve("maze2.txt", 2));
            assertEquals(false, MazeSolver.solve("maze3.txt", 2));
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
}
