import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MazeSolverTest {
    /*
    TODO - write JUnit tests testing the boards we gave you on the assignment
     */

    @Test
    public void MazeTest() throws Exception {
        //setMaze
        MazeSolver.setMaze("maze1.txt");
        assertEquals('S', MazeSolver.maze[0][0]);
        assertEquals('#', MazeSolver.maze[1][0]);
        assertEquals('.', MazeSolver.maze[4][1]);
        assertEquals('G', MazeSolver.maze[5][4]);
        //findStart helper
        int[] maze1Start = {0,0};
        assertArrayEquals(maze1Start, MazeSolver.findStart());
    }
    @Test
    public void truthTableTest() throws Exception {
        //setMaze
        MazeSolver.setMaze("maze1.txt");

        //visited = false at 'S'
        assertEquals(false, MazeSolver.tTable[0][0]);
        //Wall = null at #
        assertEquals(null, MazeSolver.tTable[1][0]);
        //Visited = false at '.'
        assertEquals(false, MazeSolver.tTable[3][2]);

        //tHelper function
        assertEquals(false, MazeSolver.tHelper('S'));
        assertEquals(false, MazeSolver.tHelper('.'));
        assertEquals(false, MazeSolver.tHelper('G'));
        assertEquals(null, MazeSolver.tHelper('#'));

        //isValid function
        //valid at 'S'
        assertEquals(true, MazeSolver.isValid(0,0));
        //valid at '.'
        assertEquals(true, MazeSolver.isValid(3, 2));
        //valid at 'G'
        assertEquals(true, MazeSolver.isValid(5,4));
        //invalid at '#'
        assertEquals(false, MazeSolver.isValid(1,0));
        //invalid out of bounds
        assertEquals(false, MazeSolver.isValid(6,6));
    }
    @Test
    public void solveMazeTest() throws Exception {
        MazeSolver.setMaze("maze1.txt");
        //test "s" point
        assertEquals(true, MazeSolver.solveMaze(0,0));

        //test '#' wall point
        assertEquals(false, MazeSolver.solveMaze(0, 1));

        // test 'g' point
        assertEquals(true, MazeSolver.solveMaze(5,4));

    }
    @Test
    public void solveTest() throws Exception {
        System.out.println("Expected: Maze can be solved: true");
        assertEquals(true, MazeSolver.solve("maze1.txt"));
        System.out.println("Expected: Maze can be solved: true");
        assertEquals(true, MazeSolver.solve("maze2.txt"));
        System.out.println("Expected: Maze can be solved: false");
        assertEquals(false, MazeSolver.solve("maze3.txt"));
    }
}
