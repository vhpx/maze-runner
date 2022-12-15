package helpers;

import classes.Maze;
import classes.Robot;

import java.io.FileWriter;
import java.io.IOException;

public class MazeHelper {
    // * Maze configurations
    // Special properties of the maze
    private static final char WALL_SYMBOL = '.';
    private static final char PATH_SYMBOL = ' ';
    private static final char START_SYMBOL = 'S';
    private static final char END_SYMBOL = 'X';
    private static final char VISITED_SYMBOL = '~';
    private static final char OPTIMAL_PATH_SYMBOL = 'x';

    // Print the maze without colors
    private static void printDefault() {
        Maze mazeCopy = new Maze();

        int robotX = mazeCopy.robotX;
        int robotY = mazeCopy.robotY;

        mazeCopy.map[robotY] = mazeCopy.map[robotY].substring(0, robotX) + START_SYMBOL +
                mazeCopy.map[robotY].substring(robotX + 1);

        for (String row : mazeCopy.map) {
            System.out.println(row);
        }
    }

    // Print the maze without colors
    private static void printDefault(Maze maze) {
        Maze mazeCopy = new Maze();

        int robotX = maze.robotX;
        int robotY = maze.robotY;

        mazeCopy.map[robotY] = mazeCopy.map[robotY].substring(0, robotX) + START_SYMBOL +
                mazeCopy.map[robotY].substring(robotX + 1);

        for (String row : mazeCopy.map) {
            System.out.println(row);
        }
    }

    // Print the maze with colors
    private static void printColorized() {
        Maze mazeCopy = new Maze();

        int robotX = mazeCopy.robotX;
        int robotY = mazeCopy.robotY;

        mazeCopy.map[robotY] = mazeCopy.map[robotY].substring(0, robotX) + START_SYMBOL +
                mazeCopy.map[robotY].substring(robotX + 1);

        for (String row : mazeCopy.map) {
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

    private static void printColorized(Maze maze) {
        Maze mazeCopy = new Maze();

        int robotX = maze.robotX;
        int robotY = maze.robotY;

        mazeCopy.map[robotY] = mazeCopy.map[robotY].substring(0, robotX) + START_SYMBOL +
                mazeCopy.map[robotY].substring(robotX + 1);

        for (String row : mazeCopy.map) {
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

    private static void printCoverageDefault(Maze maze, Robot robot) {
        int robotX = maze.robotX;
        int robotY = maze.robotY;

        maze.map[robotY] = maze.map[robotY].substring(0, robotX) + START_SYMBOL +
                maze.map[robotY].substring(robotX + 1);

        for (String row : maze.map) {
            System.out.println(row);
        }
    }

    private static void printCoverageColorized(Maze maze, Robot robot) {
        for (String row : maze.map) {
            for (char c : row.toCharArray()) {
                switch (c) {
                    case WALL_SYMBOL -> System.out.print(ColorHelper.RESET + c + ColorHelper.RESET);
                    case START_SYMBOL, END_SYMBOL -> System.out.print(ColorHelper.RED + c + ColorHelper.RESET);
                    case VISITED_SYMBOL -> System.out.print(ColorHelper.CYAN + c + ColorHelper.RESET);
                    case OPTIMAL_PATH_SYMBOL ->
                            System.out.print(ColorHelper.GREEN_BOLD_BRIGHT + c + ColorHelper.RESET);
                    default -> System.out.print(c);
                }
            }
            System.out.println();
        }
    }

    private static void printOptimalDefault(Maze maze) {
        for (String row : maze.map) {
            for (char c : row.toCharArray()) {
                switch (c) {
                    case WALL_SYMBOL -> System.out.print(ColorHelper.RESET + c + ColorHelper.RESET);
                    case START_SYMBOL, END_SYMBOL -> System.out.print(ColorHelper.RED + c + ColorHelper.RESET);
                    case VISITED_SYMBOL -> System.out.print(PATH_SYMBOL);
                    case OPTIMAL_PATH_SYMBOL ->
                            System.out.print(ColorHelper.GREEN_BOLD_BRIGHT + c + ColorHelper.RESET);
                    default -> System.out.print(c);
                }
            }
            System.out.println();
        }
    }

    private static void printOptimalColorized(Maze maze) {
        for (String row : maze.map) {
            for (char c : row.toCharArray()) {
                switch (c) {
                    case WALL_SYMBOL -> System.out.print(ColorHelper.RESET + c + ColorHelper.RESET);
                    case START_SYMBOL, END_SYMBOL -> System.out.print(ColorHelper.RED + c + ColorHelper.RESET);
                    case VISITED_SYMBOL -> System.out.print(PATH_SYMBOL);
                    case OPTIMAL_PATH_SYMBOL ->
                            System.out.print(ColorHelper.GREEN_BOLD_BRIGHT + c + ColorHelper.RESET);
                    default -> System.out.print(c);
                }
            }
            System.out.println();
        }
    }

    // Print the maze
    public static void print(boolean colorized) {
        System.out.println("Maze:");

        if (colorized)
            printColorized();
        else
            printDefault();
    }

    // Print the maze
    protected static void print(Maze maze, boolean colorized) {
        System.out.println("Maze:");

        if (colorized)
            printColorized(maze);
        else
            printDefault(maze);
    }

    // Print the maze with coverage
    public static void printCoverage(Maze maze, Robot robot, boolean colorized) {
        System.out.println("Maze with coverage:");

        if (colorized)
            printCoverageColorized(maze,robot);
        else
            printCoverageDefault(maze,robot);
    }

    // Print the maze with optimal path
    public static void printOptimal(Maze maze, Robot robot, boolean colorized) {
        System.out.println("Maze with optimal path:");

        if (colorized)
            printOptimalColorized(maze);
        else
            printOptimalDefault(maze);
    }
}
