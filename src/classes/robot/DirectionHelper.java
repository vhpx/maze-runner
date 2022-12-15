package classes.robot;

import enums.Direction;
import classes.common.Position;

public class DirectionHelper {
    public static String toString(Direction dir) {
        if (dir == null)
            return "null";

        return switch (dir) {
            case UP -> "UP";
            case DOWN -> "DOWN";
            case LEFT -> "LEFT";
            case RIGHT -> "RIGHT";
        };
    }

    public static Direction getDirection(Position a, Position b) {
        if (a.getX() == b.getX()) {
            if (a.getY() < b.getY())
                return Direction.DOWN;

            if (a.getY() > b.getY())
                return Direction.UP;
        }

        if (a.getY() == b.getY()) {
            if (a.getX() < b.getX())
                return Direction.RIGHT;

            if (a.getX() > b.getX())
                return Direction.LEFT;
        }

        return null;
    }
}
