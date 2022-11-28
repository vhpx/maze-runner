public class Position {
    private int x = 0, y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPos(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position random(int minX, int maxX, int minY, int maxY) {
        int x = (int) (Math.random() * (maxX - minX)) + minX;
        int y = (int) (Math.random() * (maxY - minY)) + minY;

        return new Position(x, y);
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public void move(Direction dir) {
        switch (dir) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
    }

    public void moveUp() {
        move(Direction.UP);
    }

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }
}