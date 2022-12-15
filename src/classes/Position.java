package classes;

import enums.Direction;

public class Position {
    public static final Position ORIGIN = new Position(0, 0);
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

    public Position move(Direction dir) {
        switch (dir) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }

        return this;
    }

    public Position moveUp() {
        move(Direction.UP);
        return this;
    }

    public Position moveDown() {
        move(Direction.DOWN);
        return this;
    }

    public Position moveLeft() {
        move(Direction.LEFT);
        return this;
    }

    public Position moveRight() {
        move(Direction.RIGHT);
        return this;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }
}