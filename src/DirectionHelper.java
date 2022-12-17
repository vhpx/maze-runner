public class DirectionHelper {
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
}
