import classes.maze.Maze;
import classes.robot.Robot;
import classes.maze.MazeHelper;

public class Main {
    // Configure if the terminal should display colors or not
    // NOTE: Coloring might not be available on all terminals,
    // and it would take more time to process.
    // If you are having trouble with colors, set this to false.
    private final static boolean ENABLE_COLORS = true;

    public static void main(String[] args) {
        Robot robot = new Robot();

        // Print the original maze
        MazeHelper.print(ENABLE_COLORS);

        // * There are 4 algorithms to choose from:
        // Uncomment the algorithm you want to use.
        // robot.navigate(Algorithm.BFS);
        // robot.navigate(Algorithm.DFS);
        // robot.navigate(Algorithm.DIJKSTRA);
        // robot.navigate(Algorithm.A_STAR);

        // By default, the robot will use the A_STAR algorithm
        robot.navigate();

        // Print the maze with the optimal path
        MazeHelper.printOptimal(robot, ENABLE_COLORS);
    }
}