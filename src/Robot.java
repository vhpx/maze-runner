public class Robot {
    // The robot's current position
    Position pos = new Position(0, 0);
    int moves = 0;

    public Robot() {
        // The robot starts in the top left corner
        pos.setPos(0, 0);
    }

    public Robot(int x, int y) {
        // The robot starts at the given position
        this.pos.setPos(x, y);
    }

    public Robot(Position pos) {
        // The robot starts at the given position
        this.pos.setPos(pos);
    }

    public int getMoves() {
        return moves;
    }

    public Position getPos() {
        return pos;
    }

    private boolean move(Position.Direction dir, Maze maze) {
        // Prepare the new position
        Position newPos = new Position(pos);
        newPos.move(dir);

        // Check if the robot can move to the given position
        if (maze.markVisited(pos)) {
            // Move the robot to the given position
            this.pos.setPos(pos);
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

    public void navigate(Maze maze) {
        // Check if the robot can be placed in the maze
        if (!maze.canNavigate(pos)) {
            System.out.println("The robot cannot be placed in the maze!");
            return;
        }

        // Set the robot's starting position
        maze.setStartPos(pos);

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
    }
}