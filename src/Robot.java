public class Robot {
    // Configure if the terminal should display colors or not
    // NOTE: Coloring might not be available on all terminals,
    // and it would take more time to process.
    // If you are having trouble with colors, set this to false.
    public final static boolean ENABLE_COLORS = true;

    // ------------- ROBOT CONFIGURATIONS -------------

    // Maximum size of the maze (width or height)
    private final int MAX_SIZE = 7;

    // Boundary for each row, taking into account the walls,
    // and MAX_SIZE to navigate in any direction.
    private final int BOUNDARY_LIMIT = (MAX_SIZE - 2) * 2 - 1;

    // Original robot position
    private final Position centerPos = new Position(BOUNDARY_LIMIT / 2, BOUNDARY_LIMIT / 2);

    // Save visited cells to avoid revisiting them
    private char[][] visited = new char[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    // Save the path to the destination
    public LinkedList<Direction> directions = new LinkedList<>();

    private boolean isOutOfBounds(Position pos) {
        return pos.getX() < 0 || pos.getX() >= BOUNDARY_LIMIT ||
                pos.getY() < 0 || pos.getY() >= BOUNDARY_LIMIT;
    }

    private boolean isVisited(Position pos) {
        return visited[pos.getX()][pos.getY()] == 'O' || visited[pos.getX()][pos.getY()] == 'X';
    }

    private boolean isWall(Position pos) {
        return visited[pos.getX()][pos.getY()] == 'X';
    }

    private void markPath(Position pos) {
        visited[pos.getX()][pos.getY()] = 'O';
    }

    private void markWall(Position pos) {
        visited[pos.getX()][pos.getY()] = 'X';
    }

    private boolean isNextPosWall(Position pos, Direction dir) {
        Position nextPos = Position.getNext(pos, dir);
        return isOutOfBounds(nextPos) || isWall(nextPos);
    }

    public char[][] getVisited() {
        return visited;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    public Position getCenterPos() {
        return centerPos;
    }

    private void clearData() {
        visited = new char[BOUNDARY_LIMIT][BOUNDARY_LIMIT];
    }

    private String explorePosition(Position pos, Maze maze, Stack<Position> path) {
        String result;

        for (Direction dir : Direction.values()) {
            Position nextPos = Position.getNext(pos, dir);

            // If the next position is not out of bounds, and it
            // has not already been visited
            if (!isOutOfBounds(nextPos) && !isVisited(nextPos) && !isWall(nextPos)) {
                // Attempt to move the robot to the next position
                result = maze.go(dir.toString());

                // If the robot has reached the exit, stop
                if (result.equals("win")) {
                    return "win";
                }

                // If the robot moved to the next position,
                // push it to the stack
                if (result.equals("true")) {
                    path.push(nextPos);
                    markPath(nextPos);

                    directions.addFirst(dir);
                    System.out.println(dir);
                    return "true";
                }

                // If the robot hit a wall, mark it
                markWall(nextPos);
            }
        }

        return "false";
    }

    private void goBack(Maze maze, Stack<Position> path) {
        // If the stack is empty, stop
        if (path.isEmpty()) return;

        // Get the direction to go back
        Position lastPos = path.pop();
        Direction dir = lastPos.getLastDirection();

        // If direction is null, stop
        if (dir == null) return;

        // If the position is a wall, stop
        if (isWall(lastPos)) return;

        // If the way back is a wall, stop
        Direction oppositeDir = DirectionHelper.getOpposite(dir);
        if (isNextPosWall(lastPos, oppositeDir)) return;

        // Go back to the last position
        maze.go(oppositeDir.toString());

    }

    public void navigate() {
        // Clear data
        clearData();

        // Generate the maze
        Maze maze = new Maze();

        // Print the original maze
        System.out.println("\nMaze: ");
        MazeHelper.print(maze, ENABLE_COLORS);

        System.out.println("\nDirections: ");

        // Push the initial position to the stack
        Stack<Position> path = new Stack<>();
        path.push(centerPos);

        // Mark the initial position as visited
        markPath(centerPos);

        // Store the result of the exploration
        String result;

        // While the robot still has cells to visit
        while (!path.isEmpty()) {
            Position pos = path.peek();
            result = explorePosition(pos, maze, path);

            if (result.equals("win")) {
                // If the robot has reached the exit, stop
                break;
            }

            if (result.equals("false")) {
                // Move the robot back to the previous position
                goBack(maze, path);
            }
        }

        // Print the path
        System.out.println("\nPath: ");
        MazeHelper.printPath(maze, this, ENABLE_COLORS);
    }
}
