public class DirectionHelper {
    public static Direction toDirection(String dir) {
        if (dir == null)
            return null;

        return switch (dir) {
            case "UP" -> Direction.UP;
            case "DOWN" -> Direction.DOWN;
            case "LEFT" -> Direction.LEFT;
            case "RIGHT" -> Direction.RIGHT;
            default -> null;
        };
    }

    public static Direction getOpposite(Direction dir) {
        if (dir == null)
            return null;

        return switch (dir) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        };
    }

    public static String getOpposite(String dir) {
        return getOpposite(toDirection(dir)).toString();
    }
}
