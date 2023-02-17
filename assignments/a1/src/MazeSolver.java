import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeSolver {
    static char[][] maze;
    static int m, n; // dimensions of the maze; row and col
    static Boolean[][] tTable;
    /*
    TODO: setMaze - sets up the board
    This method will take in a String, file, which is the path of the file we want to look at.
    Using BufferedReader and FileReader, write this method so that it sets the rows, m, and columns, n,
    to the first line of input. Then it fills the maze with the maze from the file.
     */
    public static void setMaze(String file) throws IOException {

        //Creat and read lines into buffer
        BufferedReader buffRd = new BufferedReader(new FileReader(file));
        String[] initMazeProp = buffRd.readLine().split(" ");

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
        while(buffRd.ready() && row < MazeSolver.m) {
            String line = buffRd.readLine();
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
    /*
    TODO: solveMaze - recursive function which will traverse the maze and find whether it's solveable S -> G
    The input is the index that we want to check: x = row, y = column
    ------ Hint -------
    Cell(i,j) -> if it’s a wall return false
    Cell(i,j) is ‘G’ return true
    Otherwise, mark cell(i,j) as visited (maybe make it a wall) and
    return if you can find a way out from the top, bottom, left, or right…
     */
    public static boolean solveMaze(int x, int y) {
        //If out of bounds or wall, return false
        if(!MazeSolver.isValid(x, y)) {
            return false;
        } else {

            //Base case: return true once reaching 'G'
            if(MazeSolver.maze[x][y] == 'G') {
                return true;
            } else {
                //Assign visited
                //Recursive case: check up, down, left, right
                MazeSolver.tTable[x][y] = true;
                return MazeSolver.solveMaze(x - 1, y ) ||
                        MazeSolver.solveMaze(x + 1, y) ||
                        MazeSolver.solveMaze(x, y - 1) ||
                        MazeSolver.solveMaze(x, y + 1);
            }
        }
    }

    /*
    TODO: solve - sets the maze up, solves the board, print whether it can be solved or not, returns whether its solvable or not
     */
    public static boolean solve(String file) throws IOException {
        MazeSolver.setMaze(file);
        int[] startPosn = MazeSolver.findStart();
        boolean result = MazeSolver.solveMaze(startPosn[0], startPosn[1]);
        System.out.println("Maze can be solved: " + result);
        return result;
    }
    public static void main(String[] args) {
        try{
            MazeSolver.solve("maze1.txt");
            MazeSolver.solve("maze2.txt");
            MazeSolver.solve("maze3.txt");
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}