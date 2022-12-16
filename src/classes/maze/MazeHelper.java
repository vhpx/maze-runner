package classes.maze;

import classes.common.Position;
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
        Position start = maze.getStartPosition();

        int robotX = start.getX();
        int robotY = start.getY();

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

    private static void offsetPosition(Position pos, Robot robot, Maze maze) {
        Position robotMapOGPos = robot.getCenterPos();
        Position mazeMapOGPos = maze.getStartPosition();

        int robotX = robotMapOGPos.getX();
        int robotY = robotMapOGPos.getY();

        int mazeX = mazeMapOGPos.getX();
        int mazeY = mazeMapOGPos.getY();

        int xDiff = mazeX - robotX;
        int yDiff = mazeY - robotY;

        pos.setX(pos.getX() + (xDiff < 0 ? xDiff * -1 : xDiff));
        pos.setY(pos.getY() + (yDiff < 0 ? yDiff * -1 : yDiff));
    }

    private static Maze populatePath(Maze maze, Robot robot) {
        Maze markedMaze = markStart(maze);
        var directions = robot.directions;

        Position pos = markedMaze.getStartPosition();

        for (int i = 0; i < directions.getSize(); i++) {
            Position nextPos = Position.getNext(pos, directions.get(i));
            offsetPosition(nextPos, robot, maze);

            int x = nextPos.getX();
            int y = nextPos.getY();

            if (i == directions.getSize() - 1) {
                markedMaze.map[y] = markedMaze.map[y].substring(0, x) + END_SYMBOL +
                        markedMaze.map[y].substring(x + 1);
            } else {
                markedMaze.map[y] = markedMaze.map[y].substring(0, x) + OPTIMAL_PATH_SYMBOL +
                        markedMaze.map[y].substring(x + 1);
            }
        }

        return markedMaze;
    }

    private static void printPathDefault(Maze maze, Robot robot) {
        Maze optimalMaze = populatePath(maze, robot);
        printDefault(optimalMaze);
    }

    private static void printPathColorized(Maze maze, Robot robot) {
        Maze optimalMaze = populatePath(maze, robot);
        printColorized(optimalMaze);
    }

    public static void print(Maze maze, boolean colorized) {
        if (colorized)
            printColorized(maze);
        else
            printDefault(maze);

        System.out.println();
    }

    // Print the maze with optimal path
    public static void printPath(Maze maze, Robot robot, boolean colorized) {
        if (colorized)
            printPathColorized(maze, robot);
        else
            printPathDefault(maze, robot);

        System.out.println();
    }
}
