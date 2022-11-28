import java.io.IOException;

public class Main {
    // Configure if the terminal should display colors or not
    // NOTE: Coloring might not be available on all terminals,
    // and it would take more time to process.
    public static boolean COLORIZED = true;

    // Configure test folder to use
    public static String TEST_FOLDER = "./resources/tests/";
    public static int TEST_CASES = 1000;

    public static void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Maze[] mazes = MazeIO.loadMazes();
        Robot robot = new Robot();

        for (int i = 0; i < TEST_CASES; i++) {
            // Load the current maze
            Maze maze = mazes[i];
            maze.print(COLORIZED);

            // Place the robot in the maze
            // and print the maze
            maze.placeRobot(robot);

            // The robot will navigate the maze
            robot.navigate(maze);

            // Wait until the user presses enter
            promptEnterKey();
        }
    }
}
