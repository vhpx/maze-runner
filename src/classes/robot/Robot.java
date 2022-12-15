package classes.robot;

import classes.common.Stack;
import classes.common.Node;
import classes.maze.Maze;
import classes.common.Position;
import classes.common.Queue;
import enums.Algorithm;
import enums.Direction;

public class Robot {
    // Maximum size of the maze (width or height)
    private final int MAX_SIZE = 7;

    // Boundary for each row, taking into account the walls,
    // and MAX_SIZE to navigate in any direction.
    private final int BOUNDARY_LIMIT = (MAX_SIZE - 2) * 2 - 1;

    // Original robot position
    private Position pos = new Position(MAX_SIZE / 2, MAX_SIZE / 2);

    // Save visited cells to avoid revisiting them
    boolean[][] visited = new boolean[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    // Memoize the previous cell in the shortest path
    Position[][] prev = new Position[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    // Store the optimal path
    Stack optimalPath = new Stack();

    public void navigate() {
        navigate(Algorithm.DFS);
    }

    public void navigate(Algorithm algorithm) {
        if (algorithm == null) {
            System.out.println("classes.Algorithm should not be null.");
            return;
        }

        Maze maze = new Maze();
        navigate(maze, algorithm);
    }

    public void navigate(Maze maze, Algorithm algorithm) {
        if (maze == null) {
            System.out.println("Maze should not be null.");
            return;
        }

        if (algorithm == null) {
            System.out.println("classes.Algorithm should not be null.");
            return;
        }

        // Clear all memoization arrays
        clearData();

        switch (algorithm) {
            case BFS:
                // navigateBFS(maze);
                break;

            case DFS:
                navigateDFS(maze);
                break;

            default:
                System.out.println("classes.Algorithm not implemented.");
                break;
        }
    }

    private void navigateDFS(Maze maze) {
        // Push the initial position to the stack
        Stack stack = new Stack();
        stack.push(pos);

        // Mark the initial position as visited
        visited[pos.getX()][pos.getY()] = true;

        // Keep track of maze's result of the robot's movement
        String result;

        // While the stack is not empty
        while (!stack.isEmpty()) {
            // Pop the top position from the stack
            System.out.println("_______________________");
            System.out.println("\nPre-pop");
            stack.print();
            Position position = stack.pop();


            System.out.println("\nPopped " + position);
            System.out.println("\nPost-pop");
            stack.print();

            // For each direction
            for (Direction direction : Direction.values()) {
                // Get the next position
                Position nextPosition = getNextPosition(position, direction);

                boolean reachable = canReach(nextPosition);

                // Attempt to move to the next position
                String dir = DirectionHelper.toString(direction);
                result = maze.go(dir);

                // If the result is "win", then we have found the exit
                if (result.equals("win")) {
                    // Mark the next position as visited
                    visited[nextPosition.getX()][nextPosition.getY()] = true;

                    // Save the previous position
                    prev[nextPosition.getX()][nextPosition.getY()] = position;

                    // Save the optimal path
                    saveOptimalPath(nextPosition);
                    return;
                }

                // If the result is "false", then we cannot move to the next position
                if (result.equals("false")) {
//                    System.out.println("ROBOT: Cannot move to " + nextPosition +"\n");
                    continue;
                }

                // If the next position has not been visited before
                if (!visited[nextPosition.getX()][nextPosition.getY()]) {
                    // Mark the next position as visited
                    visited[nextPosition.getX()][nextPosition.getY()] = true;

                    // Save the previous position
                    prev[nextPosition.getX()][nextPosition.getY()] = position;

                    // Push the next position to the stack
                    stack.push(nextPosition);

                    System.out.println("ROBOT: " + position + " -> " + direction + " -> " + nextPosition + '\n');
                }
            }
        }
    }

    // Get the next position given the current position and the direction
    private Position getNextPosition(Position position, Direction direction) {
        return position.copyMove(direction);
    }

    // Check if the position is valid
    private boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < BOUNDARY_LIMIT
                && position.getY() >= 0 && position.getY() < BOUNDARY_LIMIT;
    }

    // Check if the next position can be reached from the current position
    private boolean canReach(Position nextPos) {
        Direction direction = DirectionHelper.getDirection(pos, nextPos);
        if (direction == null) return false;
        var newPos = getNextPosition(pos, direction);
        return newPos.getX() == nextPos.getX() && newPos.getY() == nextPos.getY();
    }

    // Save the optimal path
    private void saveOptimalPath(Position position) {
        // Push the exit position to the stack
        optimalPath.push(position);

        // While the position is not the initial position
        while (!position.equals(pos)) {
            // Get the previous position
            position = prev[position.getX()][position.getY()];

            // Push the previous position to the stack
            optimalPath.push(position);
        }
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    public Stack getOptimalPath() {
        return optimalPath;
    }

    private void clearData() {
        for (int i = 0; i < BOUNDARY_LIMIT; i++) {
            for (int j = 0; j < BOUNDARY_LIMIT; j++) {
                visited[i][j] = false;
                prev[i][j] = null;
            }
        }
    }
}
