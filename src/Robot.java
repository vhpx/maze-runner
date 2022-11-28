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

    public boolean navigate(Maze maze) {
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
}