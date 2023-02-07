import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Feel free to reuse from HW1

public class MazeSolver {
    static char[][] maze;
    static Boolean[][] tTable;
    static Stack<int[]> stack;
    static Queue<int[]> queue;
    static int m, n; // dimensions of the maze

    /*
    TODO: setMaze - sets up the board
    This method will take in a String, file, which is the path of the file we want to look at.
    Using BufferedReader and FileReader, write this method so that it sets the rows, m, and columns, n,
    to the first line of input. Then it fills the maze with the maze from the file.
     */
    public static void setMaze(String file) throws IOException {
        InputStream in = MazeSolver.class.getClassLoader().getResourceAsStream(file);
        InputStreamReader inRead = new InputStreamReader((in));
        BufferedReader reader = new BufferedReader(inRead);
        //Creat and read lines into buffer
        /*BufferedReader buffRd = new BufferedReader(new FileReader(file));*/
        String[] initMazeProp = reader.readLine().split(" ");

        //First line always m,n of maze
        //initialize size of maze
        //initialize truth table
        MazeSolver.m = Integer.parseInt(initMazeProp[0]);
        MazeSolver.n = Integer.parseInt(initMazeProp[1]);
        MazeSolver.maze = new char[MazeSolver.m][MazeSolver.n];
        MazeSolver.tTable = new Boolean[MazeSolver.m][MazeSolver.n];
        //Store buffered lines into maze
        int row = 0;
        int col = 0;
        while(reader.ready() && row < MazeSolver.m) {
            String line = reader.readLine();
            char[] chars = line.toCharArray();
            for(col = 0; col < chars.length; col++) {
                MazeSolver.maze[row][col] = chars[col];
                MazeSolver.tTable[row][col] = MazeSolver.tHelper(chars[col]);
            }
            row++;
        }
    }

    //Helper Function for Truth Table
    //Boolean values returned can be either null or false
    //null = wall, false = not visited
    public static Boolean tHelper(char val) {
        Boolean target = null;
        if(val == 'S' || val == '.' || val == 'G') {
            target = false;
        }

        return target;
    }
    /*
    TODO: isValid - checks if a position on the board has not been visited and is within bounds
     */
    public static boolean isValid(int x, int y) {
        //Boundary
        boolean lowerBound = x < 0 || y < 0;
        boolean upperBound = x >= MazeSolver.m || y >= MazeSolver.n;
        if(lowerBound | upperBound) {
            return false;
        }

        //CheckTruth Table
        //null = wall = not valid
        //false = 'S', 'G', ',' = valid path
        boolean target = false;
        if(MazeSolver.tTable[x][y] == null) {
            target = false;
        } else {
            if(MazeSolver.tTable[x][y] == false) {
                target = true;
            }
        }
        return target;
    }
    //Helper function to find starting posn, 'S'
    public static int[] findStart() {
        int[] target = new int[2];
        for(int i = 0; i < MazeSolver.maze.length; i++) {
            for(int j = 0; j < MazeSolver.maze[i].length; j++) {
                if(MazeSolver.maze[i][j] == 'S') {
                    target[0] = i;
                    target[1] = j;
                    break;
                }
            }
        }
        return target;
    }

    /*
    TODO: Using a stack, solve the maze WITHOUT recursion.
    Pseudo:
    1) Push start position onto Stack.
    2) While it's not empty;
        3) Pop from the stack to get the current considered location
        4) If it's already explored ignore
        5) If it's the goal, return true
        6) If it's not the goal, then explore it.
        7) You will need to compute all the possible neighbors. Then push those on the stack
    8) Return false
     */

    public static boolean solveMazeStack(int x, int y)  throws EmptyStackE {
        //Init variables and starting posn in stack
        boolean isGoal = false;
        int[] startPosn = {x, y};
        stack = new Stack<>();
        stack.push(startPosn);

        //while stack is not empty
        while(stack.size() > 0) {

            //Pop stack and check if End, 'G'
            int[] curr = stack.pop();
            int cx, cy;
            cx = curr[0];
            cy = curr[1];

            if(maze[cx][cy] == 'G') {
                return true;
            } else {
                //tTable visited [][]
                //used in isValid
                tTable[cx][cy] = true;

                //Checks if Neighbor nodes valid
                //true: push onto stack
                if(isValid(cx-1, cy)) stack.push(new int[] {cx-1, cy});
                if(isValid(cx+1, cy)) stack.push(new int[] {cx+1, cy});
                if(isValid(cx, cy-1)) stack.push(new int[] {cx, cy-1});
                if(isValid(cx, cy+1)) stack.push(new int[] {cx, cy+1});
            }
        }
        return isGoal;
    }

    // TODO: Using a queue, solve the maze. Be sure to explain your algorithm for full points.
    public static boolean solveMazeQueue(int x, int y) throws EmptyQueueE{
        //init vars and starting posn for queue
        boolean isGoal = false;
        int[] startPosn = {x, y};
        queue = new Queue<>();
        queue.enqueue(startPosn);

        //loop while not empty
        while(queue.size() > 0 ) {
            //deque
            int[] curr = queue.dequeue();
            int cx, cy;
            cx = curr[0];
            cy = curr[1];

            //check if value == 'G'
            //else, check neighbor nodes
            //valid neigbor nodes enqueued
            // 1. not visited
            // 2. not wall/edge
            if(maze[cx][cy] == 'G') {
                return true;
            } else {
                tTable[cx][cy] = true;

                if(isValid(cx-1, cy)) queue.enqueue(new int[] {cx-1, cy});
                if(isValid(cx+1, cy)) queue.enqueue(new int[] {cx+1, cy});
                if(isValid(cx, cy-1)) queue.enqueue(new int[] {cx, cy-1});
                if(isValid(cx, cy+1)) queue.enqueue(new int[] {cx, cy+1});
            }
        }

        return false;
    }

    // TODO: Solve the board. Mode 1 = stack solving. Mode 2 = queue solving.
    // 1: stack
    // 2: queue
    public static boolean solve(String file, int mode) throws IOException, EmptyStackE, EmptyQueueE {
        // find starting point
        // check if the maze can be solved
        setMaze(file);
        int[] startPosn = findStart();

        boolean solved;
        if(mode == 1) {
            solved = solveMazeStack(startPosn[0], startPosn[1]);
        } else {
            solved = solveMazeQueue(startPosn[0], startPosn[1]);
        }

        System.out.println("Maze can be solved: " + solved);
        return solved;
    }


}