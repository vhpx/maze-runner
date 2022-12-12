public class Robot {
    // The robot's current position
    private final Position pos;
    private int moves = 0;

    public Robot() {
        // The robot starts in the top left corner
        pos = new Position(0, 0);
    }

    public Robot(int x, int y) {
        // The robot starts at the given position
        pos = new Position(x, y);
    }

    public Robot(Position pos) {
        // The robot starts at the given position
        this.pos = new Position(pos);
    }

    public int getMoves() {
        return moves;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        this.pos.setPos(x, y);
    }

    public void setPos(Position pos) {
        this.pos.setPos(pos);
    }

    private boolean move(Position.Direction dir, Maze maze) {
        // Prepare the new position
        Position newPos = new Position(pos);
        newPos.move(dir);

        // Check if the robot can move to the given position
        if (maze.markVisited(newPos)) {
            // Move the robot to the given position
            this.pos.setPos(newPos);
            moves++;
            return true;
        }
        return false;
    }

    private boolean moveUp(Maze maze) {
        return move(Position.Direction.UP, maze);
    }

    private boolean moveDown(Maze maze) {
        return move(Position.Direction.DOWN, maze);
    }

    private boolean moveLeft(Maze maze) {
        return move(Position.Direction.LEFT, maze);
    }

    private boolean moveRight(Maze maze) {
        return move(Position.Direction.RIGHT, maze);
    }

    public boolean navigateRandom(Maze maze) {
        Position startPos = maze.getStartPos();
        setPos(startPos);

        while (!maze.isDestination(pos)) {
            double rnd = Math.random();

            if (rnd <= 0.25) {
                moveUp(maze);
            } else if (rnd <= 0.5) {
                moveDown(maze);
            } else if (rnd <= 0.75) {
                moveLeft(maze);
            } else {
                moveRight(maze);
            }
        }

        System.out.println("\nNumber of moves: " + getMoves());
        System.out.println("Number of visited positions: " + maze.getVisitedCount());
        System.out.println("Maze path:\n");
        maze.print(Main.COLORIZED);
        return true;
    }

    public boolean navigateBFS(Maze maze) {
        // find shortest path to the destination and store in prev array
        Position[][] prev = findPath(maze);

        // reconstruct the shortest path to the destination
        reconstructPath(maze, prev);

        return true;
    }

    public Position[][] findPath(Maze maze) {
        Position startPos = maze.getStartPos();

        Queue queue = new Queue();
        queue.enqueue(startPos);

        boolean[][] visited = new boolean[maze.getCols()][maze.getRows()];
        Position[][] prev = new Position[maze.getCols()][maze.getRows()];

        while (!queue.isEmpty() && !maze.isDestination(queue.peek())) {
            Position currentPos = queue.dequeue();
            maze.markVisited(currentPos);

            int[] dr = { -1, 1, 0, 0 };
            int[] dc = { 0, 0, -1, 1 };

            for (int i = 0; i < 4; i++) {
                Position nextPos = new Position(currentPos.getX() + dr[i], currentPos.getY() + dc[i]);
                if (!maze.canNavigate(nextPos) || visited[nextPos.getX()][nextPos.getY()])
                    continue;

                queue.enqueue(nextPos);
                visited[nextPos.getX()][nextPos.getY()] = true;
                prev[nextPos.getX()][nextPos.getY()] = currentPos;
            }
        }

        return prev;
    }

    public void reconstructPath(Maze maze, Position[][] prev) {
        // mark all the prev positions

        Position currentPos = maze.getDestination();
        // markPath of currentPos
        maze.markPosition(currentPos);
        int steps = 0;

        // while currentPos is not the start position
        while (!currentPos.equals(maze.getStartPos())) {
            // set currentPos to prev of currentPos
            currentPos = prev[currentPos.getX()][currentPos.getY()];
            steps++;
            maze.markPosition(currentPos);
        }

        System.out.println("\nNumber of moves: " + steps);
        System.out.println("Maze path:\n");
        // print the maze with the shortest path
        maze.printPath();
    }

    public boolean navigate(Maze maze) {
        return navigateBFS(maze);
    }
}