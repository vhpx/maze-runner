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

    public Position copyMove(Direction dir) {
        Position pos = new Position(this);

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

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }
}