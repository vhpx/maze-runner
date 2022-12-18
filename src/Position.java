public class Position {
    private int x, y;
    private Direction lastDirection = null;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
        this.lastDirection = pos.lastDirection;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    // Get the next position given the current position and the direction
    public static Position getNext(Position pos, Direction dir) {
        return pos.copyMove(dir);
    }

    public Position copyMove(Direction dir) {
        Position pos = new Position(this);
        pos.lastDirection = dir;

        switch (dir) {
            case UP -> pos.y--;
            case DOWN -> pos.y++;
            case LEFT -> pos.x--;
            case RIGHT -> pos.x++;
        }

        return pos;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}