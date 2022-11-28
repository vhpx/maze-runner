public class Main {
    // Configure if the terminal should display colors or not
    // NOTE: Coloring might not be available on all terminals,
    // and it would take more time to process.
    static boolean colorized = true;

    public static void main(String[] args) {
        Maze maze = new Maze();
        Robot robot = new Robot(1, 1);

        // Place the robot in the maze
        // and print the maze
        boolean validPlacement = maze.placeRobot(robot);
        maze.print(colorized);

        // Check if the robot can be placed in the maze
        if (!validPlacement) {
            System.out.println(TerminalColors.RED + "\nThe robot cannot be placed in the maze!");
            System.out.println("Error at position: " + robot.getPos().toString() + TerminalColors.RESET);
            return;
        }

        // The robot will navigate the maze
        robot.navigate(maze);

        System.out.println("\nNumber of moves: " + robot.getMoves());
        System.out.println("Number of visited positions: " + maze.getVisitedCount());
        System.out.println("Maze path:\n");

        maze.print(colorized);
    }
}
