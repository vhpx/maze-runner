import classes.Maze;
import classes.Robot;
import helpers.MazeHelper;

public class Main {
    // Configure if the terminal should display colors or not
    // NOTE: Coloring might not be available on all terminals,
    // and it would take more time to process.
    // If you are having trouble with colors, set this to false.
    private final static boolean enableColors = true;

    public static void main(String[] args) {
        Maze maze = new Maze();
        Robot robot = new Robot();

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