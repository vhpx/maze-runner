public class OriginalMaze {
    // Maze dimensions
    int rows;

    // Robot position
    int robotX;
    int robotY;

    // Number of steps taken by the robot
    int steps = 0;

    // The maze is represented as a 2D array of Strings
    // Each string is a row of the maze
    String[] map;

    public OriginalMaze() {
        // rows = 21;
        rows = 7;

        robotX = 4;
        robotY = 1;

        map = new String[rows];

        map[0] = ".......";
        map[1] = ".X.   .";
        map[2] = ". ... .";
        map[3] = ".     .";
        map[4] = ". . ...";
        map[5] = ".     .";
        map[6] = ".......";

        // map[0] =
        // ".................................................................................";
        // map[1] = ". . . . . . . . . . . . . . . . .";
        // map[2] = ". ... ... . ... ... . ..... ... . ... ... . ... . ... ..... ... ...
        // . ..... . ...";
        // map[3] = ". . . . . . . . . . . . . . . . . . .";
        // map[4] = "..... . ... ..... . ... ....... ....... . ......... . ... ..... . .
        // ....... ... .";
        // map[5] = ". . . . . . . . . . . . . . . . .";
        // map[6] = ". ..... ... ..... ......... . . . ..... ... ... ..... . ... ..... .
        // . ... ..... .";
        // map[7] = ". . . . . . . . . . . . . . . . . . . .";
        // map[8] = "... ... ... ..... ... ... ..... . ..... ......... . ....... ... .
        // ... . . .......";
        // map[9] = ". . . . . . . . . . . . . . . . . . . . . . . . .";
        // map[10] = ". . ..... ... . ... . . . . . ... ... . . . ... ..... . . .....
        // ... . ..... ... .";
        // map[11] = ". . . . . . . . . . . . . . . . .";
        // map[12] = ". . ..... ... ..... ... ... ... . ....... ....... . . . . .
        // ......... ... .......";
        // map[13] = ". . . . . . . . . . . . . . . . . . . . . .";
        // map[14] = "....... ... ... ... ... ... ... . ..... ..... ... . ....... . . .
        // ..... . ..... .";
        // map[15] = ". . . . . . . . . . . . . . . . . . . . .";
        // map[16] = ". ... ... ......... . . . ... ... ....... ........... ...........
        // . ... ... . . .";
        // map[17] = ". . . . . . . . . . . . . . . . . . . . .";
        // map[18] = ". ... ... ... ... ... ... . . ....... ..... . . . ... . . . . ...
        // ....... . .....";
        // map[19] = ". X. . . . . . . . . . . . . . . . . . . .";
        // map[20] =
        // ".................................................................................";

    }

    public String go(String direction) {
        steps++;

        int currentRow = robotY;
        int currentCol = robotX;

        switch (direction) {
            case "UP":
                currentRow--;
                break;

            case "DOWN":
                currentRow++;
                break;

            case "LEFT":
                currentCol--;
                break;

            case "RIGHT":
                currentCol++;
                break;

            default:
                return "false";
        }

        // Check if the robot has reached the exit gate
        if (map[currentRow].charAt(currentCol) == 'X') {
            System.out.println("Steps to reach the Exit gate: " + steps);
            return "win";
        }

        // Check if the robot has hit a wall
        if (map[currentRow].charAt(currentCol) == '.') {
            return "false";
        }

        // Otherwise, move the robot
        robotY = currentRow;
        robotX = currentCol;

        return "true";
    }

    // Enable colors when printing the maze
    // NOTE: This may not work on all systems.
    // If you are having trouble with colors, set this to false.
    private final static boolean enableColors = true;

    public static void main(String[] args) {
        OriginalMaze maze = new OriginalMaze();
        OriginalRobot robot = new OriginalRobot();

        // Print the original maze
        MazeHelper.print(enableColors);

        // * There are 4 algorithms to choose from:
        // Uncomment the algorithm you want to use.
        // robot.navigate(Algorithm.BFS);
        // robot.navigate(Algorithm.DFS);
        // robot.navigate(Algorithm.DIJKSTRA);
        // robot.navigate(Algorithm.A_STAR);

        // By default, the robot will use the A_STAR algorithm
        robot.navigate();

        // Print the maze with positions visited by the robot
        MazeHelper.printCoverage(maze, robot, enableColors);

        // Print the maze with the optimal path
        MazeHelper.printOptimal(maze, robot, enableColors);
    }
}