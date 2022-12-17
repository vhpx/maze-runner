public class Maze {
    // Maze dimensions
    int rows;

    private final int startX;
    private final int startY;

    // Robot position
    private int robotX;
    private int robotY;

    // Number of steps taken by the robot
    int steps = 0;

    // The maze is represented as a 2D array of Strings
    // Each string is a row of the maze
    String[] map;

    public Maze() {
//        TEST 1
//        rows = 7;
//
//        robotX = 4;
//        robotY = 1;
//
//        startX = robotX;
//        startY = robotY;
//
//        map = new String[rows];
//        map[0] = ".......";
//        map[1] = ". .   .";
//        map[2] = ". ... .";
//        map[3] = ".X    .";
//        map[4] = ". . ...";
//        map[5] = ".     .";
//        map[6] = ".......";

        // TEST 2
        rows = 16;

        robotX = 38;
        robotY = 1;

        startX = robotX;
        startY = robotY;

        map = new String[rows];
        map[0] = "........................................";
        map[1] = ".    .          ...             ..     .";
        map[2] = ". .    .            .. . ..    .   .....";
        map[3] = ".   . .       .  .    .       ..    .  .";
        map[4] = ".     ..   ..    .           .         .";
        map[5] = ".  .                .        .  .      .";
        map[6] = "..              .   .          .       .";
        map[7] = "..      .      .  .                 X. .";
        map[8] = ".                 .               .    .";
        map[9] = ".    .                     . .  .     ..";
        map[10] =".  .    .   .                      . . .";
        map[11] =".   .       .        ....       .  .   .";
        map[12] =".    . .  .             .         .  . .";
        map[13] =". .  . . ..    ..                     ..";
        map[14] =".  . .                         .    .  .";
        map[15] ="........................................";

//        TEST 3
//        rows = 28;
//
//        robotX = 90;
//        robotY = 18;
//
//        startX = robotX;
//        startY = robotY;
//
//        map = new String[rows];
//        map[0] = "..........................................................................................................................";
//        map[1] = ".   .               .           ...     .    .            .       .   ..     .       .    .                   . ..   .   .";
//        map[2] = "..    .. .      .  . .  .    .    .     .. .                   .   .          .. . .  ..               .                 .";
//        map[3] = ".               .. ...   .   .    . ..  .  .      ..       .       .                       .  .    .                   . .";
//        map[4] = ".        .  .        .. .    .. .         . .     .   .  .      .   . ..        .  .       .  ..                 .  . .. .";
//        map[5] = ".    .      . . .        .   .  ..         .      .        .   .    .        .             . .  .  . .   .     ..     .  .";
//        map[6] = ".   . .  .                   .        ..    .    .  . . .        .               .    . ..   .      . .         ..      ..";
//        map[7] = ".  .   .          .                .      .    .   .           .    . .       .   .           .  .. .               .    .";
//        map[8] = "...  .    ... ..    .    ... ..  .    .      .        ..  .  .               .  .        . .                ..   .  .    .";
//        map[9] = "...      .   .         .  .     ..    .  .  ..   .   .    .   .         .    .       .       . . ..           .. .       .";
//        map[10] = ".          .   .    .                        .    .    . .   .  . .  .         ... .            .  .             .       .";
//        map[11] = ".   .    .    .   . .               ..    .     .  .  . ..                 .  .  . .     . .       ....             . .. .";
//        map[12] = ". .    .           .   .  .    ...  .  .   .    .  .     .    ..                .                    ... .. .         .  .";
//        map[13] = ".   .     ...      .  .             .   .   .. .  .       .                  . .      .    ..  .              .      .   .";
//        map[14] = "...                   .        .           .  ..               .  .  ..           .   . . .      ...         ...         .";
//        map[15] = "..          .  .         .           .  .            .   .                  .     .                  .    ..  .   ...   ..";
//        map[16] = ".        ..  .       . ...  .  .     ..              ..                . .   .          .. .           .             ..  .";
//        map[17] = ". .     .       .   ..     .   . .      .                     .    .    .  .        .  ..    .  . .  .     .     .       .";
//        map[18] = ". .              . . .         ..       .  .     .   .  .    .. .           ..      .            .          .   .        .";
//        map[19] = "..  .      . .   ..     .                 .      .               .               .. .                                  ...";
//        map[20] = ".    . .  ..   .     . ..   .    .         .   . ..                 ..  ..         .      .     .       .           ..   .";
//        map[21] = ".   ... .         .  ..          ..      .        .              .         .      ...                       X . .        .";
//        map[22] = ".  .   ..    .      .        .       .  .                      .  . .. .            . .   .       .    .      .   . . .  .";
//        map[23] = "..  .       .            .       .  . .  ..                   .            .   .     .       .             .    .    .   .";
//        map[24] = ".  .    .   ...          .     .  . ..             .     .   ..                                  .. .     ...        .   .";
//        map[25] = ". .    .      .       .  . .             .  .         .     .     .   ..                    ..     .              .      .";
//        map[26] = ".                        .. .           .  .       .  .    .        .   .        . ..               ..            .      .";
//        map[27] = "..........................................................................................................................";
    }

    public String go(String direction) {
        steps++;

        int currentRow = robotY;
        int currentCol = robotX;

        switch (direction) {
            case "UP" -> currentRow--;
            case "DOWN" -> currentRow++;
            case "LEFT" -> currentCol--;
            case "RIGHT" -> currentCol++;
            default -> {
                return "false";
            }
        }

        // Check if the robot has reached the exit gate
        if (map[currentRow].charAt(currentCol) == 'X') {
            System.out.println("Steps to reach the exit gate: " + steps );
            return "win";
        }

        // Check if the robot has hit a wall
        if (map[currentRow].charAt(currentCol) == '.')
            return "false";

        // Otherwise, move the robot
        robotY = currentRow;
        robotX = currentCol;

        return "true";
    }

    public Position getStartPosition() {
        return new Position(startX, startY);
    }

    public static void main(String[] args) {
        (new Robot()).navigate();
    }
}