public class Maze {
    // Maze dimensions
    int rows;

    // Robot position
    int robotRow;
    int robotCol;

    // Number of steps taken by the robot
    int steps = 0;

    // The maze is represented as a 2D array of Strings
    // Each string is a row of the maze
    String[] map;

    public Maze() {
        rows = 7;

        robotCol = 4;
        robotRow = 1;

        map = new String[rows];
        map[0] = ".......";
        map[1] = ".X.   .";
        map[2] = ". ... .";
        map[3] = ".     .";
        map[4] = ". . ...";
        map[5] = ".     .";
        map[6] = ".......";
    }

    public String go(String direction) {
        steps++;

        int currentRow = robotRow;
        int currentCol = robotCol;

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
            System.out.println("\nSteps to reach the exit gate: " + steps);
            return "win";
        }

        // Check if the robot has hit a wall
        if (map[currentRow].charAt(currentCol) == '.') {
            // System.out.println("Hit a wall at (" + currentRow + ", " + currentCol + ")");
            return "false";
        }

        // Otherwise, move the robot
        robotRow = currentRow;
        robotCol = currentCol;

        return "true";
    }

    public static void main(String[] args) {
        (new Robot()).navigate();
    }
}