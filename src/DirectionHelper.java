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
            if (a.getY() < b.getY()) {
                return Direction.DOWN;
            } else if (a.getY() > b.getY()) {
                return Direction.UP;
            }
        } else if (a.getY() == b.getY()) {
            if (a.getX() < b.getX()) {
                return Direction.RIGHT;
            } else if (a.getX() > b.getX()) {
                return Direction.LEFT;
            }
        }

        return null;
    }
}
