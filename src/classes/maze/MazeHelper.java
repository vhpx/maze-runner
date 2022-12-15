package classes.maze;

import classes.common.LinkedList;
import classes.common.Node;
import classes.common.Position;
import classes.common.Stack;
import classes.robot.Robot;

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
        int robotX = maze.robotX;
        int robotY = maze.robotY;

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

//    private static Maze populateCoverage(Maze maze, Robot robot) {
//        var visited = robot.getVisited();
//        int maxSize = robot.getMaxSize();
//
//        int robotX = maze.robotX;
//        int robotY = maze.robotY;
//
//        Maze markedMaze = markStart(maze);
//
//        // Change all PATH_SYMBOL that belongs to the coverage to VISITED_SYMBOL
//        for (int i = 0; i < visited.length; i++) {
//            for (int j = 0; j < visited[i].length; j++) {
//                if (visited[i][j]) {
//                    int x = robotX + j - maxSize;
//                    int y = robotY + i - maxSize;
//
//                    if (x >= 0 && x < markedMaze.map[0].length() && y >= 0 && y < markedMaze.map.length &&
//                            markedMaze.map[y].charAt(x) == PATH_SYMBOL) {
//                        markedMaze.map[y] = markedMaze.map[y].substring(0, x) + VISITED_SYMBOL +
//                                markedMaze.map[y].substring(x + 1);
//                    }
//                }
//            }
//        }
//
//        return maze;
//    }

    private static Maze populateOptimalPath(Maze maze, Robot robot) {
        Stack optimalPath = robot.getOptimalPath();
        Node node = optimalPath.pop();

        int maxSize = robot.getMaxSize();
        int robotX = maze.robotX;
        int robotY = maze.robotY;

        var visited = robot.getVisited();

        // print all visited nodes
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                System.out.print(visited[i][j] ? "1" : "0");
            }
            System.out.println();
        }

//        while (node != null) {
//            Position position = (Position) node.getData();
//            int x = robotX + position.getX() - maxSize;
//            int y = robotY + position.getY() - maxSize;
//
//            System.out.println(position);
//
//            if (x >= 0 && x < maze.map[0].length() && y >= 0 && y < maze.map.length) {
//                maze.map[y] = maze.map[y].substring(0, x) + OPTIMAL_PATH_SYMBOL +
//                        maze.map[y].substring(x + 1);
//
//                System.out.println(maze.map[y]);
//            }
//
//            node = optimalPath.pop();
//        }

        return maze;
    }

    private static void printOptimalDefault(Maze maze, Robot robot) {
        Maze optimalMaze = populateOptimalPath(maze, robot);
        printDefault(optimalMaze);
    }

    private static void printOptimalColorized(Maze maze, Robot robot) {
        Maze optimalMaze = populateOptimalPath(maze, robot);
        printColorized(optimalMaze);
    }

    // Print the maze
    public static void print(boolean colorized) {
        System.out.println("Maze:");
        Maze maze = new Maze();

        if (colorized) printColorized(maze);
        else printDefault(maze);
    }

    // Print the maze with optimal path
    public static void printOptimal(Robot robot, boolean colorized) {
        System.out.println("Maze with optimal path:");
        Maze maze = new Maze();

        if (colorized) printOptimalColorized(maze, robot);
        else printOptimalDefault(maze, robot);
    }
}
