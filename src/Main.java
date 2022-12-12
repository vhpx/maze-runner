public class Main {
    // Configure if the terminal should display colors or not
    // NOTE: Coloring might not be available on all terminals,
    // and it would take more time to process.
    public static boolean COLORIZED = true;

    // Configure test folder to use
    public static String TEST_FOLDER = "./resources/tests/";
    public static int TEST_CASES = 1000;

    public static void main(String[] args) {
        Maze[] mazes = MazeIO.loadMazes();

        Robot bfsRobot = new Robot();
        Robot dfsRobot = new Robot();

        for (int i = 0; i < TEST_CASES; i++) {
            // Load the current maze
            Maze maze = mazes[i];

            // Place the robot in the maze
            // and print the maze
            maze.placeRobot(bfsRobot);
            maze.placeRobot(dfsRobot);

            // The robot will navigate the maze
            // using the BFS algorithm
            bfsRobot.navigate(maze, true);

            // The robot will navigate the maze
            // using the DFS algorithm
            dfsRobot.navigate(maze, false);

            // Wait until the user presses enter
            Utility.waitForKey();
        }
    }
}
