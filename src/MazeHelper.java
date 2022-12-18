public class MazeHelper {
    // * Maze configurations
    // Special properties of the maze
    private static final char WALL_SYMBOL = '.';
    private static final char PATH_SYMBOL = ' ';
    private static final char START_SYMBOL = 'S';
    private static final char END_SYMBOL = 'X';
    private static final char VISITED_SYMBOL = '~';
    private static final char OPTIMAL_PATH_SYMBOL = 'x';

    private static Maze markStart(Maze maze) {
        int robotX = maze.robotCol;
        int robotY = maze.robotRow;

        maze.map[robotY] = maze.map[robotY].substring(0, robotX) + START_SYMBOL +
                maze.map[robotY].substring(robotX + 1);

        return maze;
    }

    // Print the maze without colors
    private static void printDefault(Maze maze) {
        Maze markedMaze = markStart(maze);
        for (String row : markedMaze.map) {
            System.out.println(row);
        }
    }

    // Print the maze with colors
    private static void printColorized(Maze maze) {
        Maze markedMaze = markStart(maze);
        for (String row : markedMaze.map) {
            for (char c : row.toCharArray()) {
                switch (c) {
                    case WALL_SYMBOL -> System.out.print(ColorHelper.RESET + c + ColorHelper.RESET);
                    case START_SYMBOL, END_SYMBOL -> System.out.print(ColorHelper.RED + c + ColorHelper.RESET);
                    case VISITED_SYMBOL, OPTIMAL_PATH_SYMBOL -> System.out.print(PATH_SYMBOL);
                    default -> System.out.print(c);
                }
            }
            System.out.println();
        }
    }

    public static void print(Maze maze, boolean colorized) {
        if (colorized)
            printColorized(maze);
        else
            printDefault(maze);

        System.out.println();
    }
}
