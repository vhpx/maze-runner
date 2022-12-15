package classes.common;

import enums.Direction;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public static Position copy(Position pos) {
        return new Position(pos);
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

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }
}