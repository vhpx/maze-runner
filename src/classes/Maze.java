package classes;

public class Maze {
    // Maze dimensions
    int rows;

    // Robot position
    public int robotX;
    public int robotY;

    // Number of steps taken by the robot
    int steps = 0;

    // The maze is represented as a 2D array of Strings
    // Each string is a row of the maze
    public String[] map;

    public Maze() {
        // rows = 21;
        rows = 7;

        robotX = 4;
        robotY = 1;

        map = new String[rows];

        map[0] = ".......";
        map[1] = ".X.   .";
        map[2] = ". ... .";
        map[3] = ".     .";
        map[4] = ". . ...";
        map[5] = ".     .";
        map[6] = ".......";

        // map[0] =
        // ".................................................................................";
        // map[1] = ". . . . . . . . . . . . . . . . .";
        // map[2] = ". ... ... . ... ... . ..... ... . ... ... . ... . ... ..... ... ...
        // . ..... . ...";
        // map[3] = ". . . . . . . . . . . . . . . . . . .";
        // map[4] = "..... . ... ..... . ... ....... ....... . ......... . ... ..... . .
        // ....... ... .";
        // map[5] = ". . . . . . . . . . . . . . . . .";
        // map[6] = ". ..... ... ..... ......... . . . ..... ... ... ..... . ... ..... .
        // . ... ..... .";
        // map[7] = ". . . . . . . . . . . . . . . . . . . .";
        // map[8] = "... ... ... ..... ... ... ..... . ..... ......... . ....... ... .
        // ... . . .......";
        // map[9] = ". . . . . . . . . . . . . . . . . . . . . . . . .";
        // map[10] = ". . ..... ... . ... . . . . . ... ... . . . ... ..... . . .....
        // ... . ..... ... .";
        // map[11] = ". . . . . . . . . . . . . . . . .";
        // map[12] = ". . ..... ... ..... ... ... ... . ....... ....... . . . . .
        // ......... ... .......";
        // map[13] = ". . . . . . . . . . . . . . . . . . . . . .";
        // map[14] = "....... ... ... ... ... ... ... . ..... ..... ... . ....... . . .
        // ..... . ..... .";
        // map[15] = ". . . . . . . . . . . . . . . . . . . . .";
        // map[16] = ". ... ... ......... . . . ... ... ....... ........... ...........
        // . ... ... . . .";
        // map[17] = ". . . . . . . . . . . . . . . . . . . . .";
        // map[18] = ". ... ... ... ... ... ... . . ....... ..... . . . ... . . . . ...
        // ....... . .....";
        // map[19] = ". X. . . . . . . . . . . . . . . . . . . .";
        // map[20] =
        // ".................................................................................";

    }

    public String go(String direction) {
        steps++;

        int currentRow = robotY;
        int currentCol = robotX;

        switch (direction) {
            case "UP":
                currentRow--;
                break;

            case "DOWN":
                currentRow++;
                break;

            case "LEFT":
                currentCol--;
                break;

            case "RIGHT":
                currentCol++;
                break;

            default:
                return "false";
        }

        // Check if the robot has reached the exit gate
        if (map[currentRow].charAt(currentCol) == 'X') {
            System.out.println("Steps to reach the Exit gate: " + steps);
            return "win";
        }

        // Check if the robot has hit a wall
        if (map[currentRow].charAt(currentCol) == '.') {
            return "false";
        }

        // Otherwise, move the robot
        robotY = currentRow;
        robotX = currentCol;

        return "true";
    }
}