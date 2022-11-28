public class Maze {
    // Maze size
    int rows = 30;
    int cols = 150;

    // The maze is represented as a 2D array of Strings
    // Each string is a row of the maze
    String[] map = new String[rows];

    // * Maze configurations
    // Special properties of the maze
    final float DENSITY = 0.1f;

    final char WALL_SYMBOL = '.';
    final char PATH_SYMBOL = ' ';
    final char START_SYMBOL = 'S';
    final char END_SYMBOL = 'X';
    final char VISITED_SYMBOL = '+';

    // Maze destination
    Position destination = new Position(0, 0);

    public Maze() {
        // Randomly generate the maze
        // when the Maze object is created
        randomize();
    }

    public Maze(int rows, int cols) {
        // Set the maze size
        setSize(rows, cols);

        // Randomly generate the maze
        // when the Maze object is created
        randomize();
    }

    public Maze(String[] map) {
        setMap(map);
    }

    public Maze(int rows, int cols, String[] map) {
        setSize(rows, cols);
        setMap(map);
    }

    private void setSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        map = new String[rows];
    }

    private void setMap(String[] map) {
        this.map = map;
    }

    public void setObject(int x, int y, char object) {
        // Don't allow the robot to change start or end positions
        if (map[y].charAt(x) == START_SYMBOL || map[y].charAt(x) == END_SYMBOL) {
            return;
        }

        // Set the object at the given position
        map[y] = map[y].substring(0, x) + object + map[y].substring(x + 1);
    }

    public void setDestination(int x, int y) {
        destination.setPos(x, y);
        setObject(x, y, END_SYMBOL);
    }

    public void setDestination(Position pos) {
        destination.setPos(pos);
        setObject(pos.getX(), pos.getY(), END_SYMBOL);
    }

    public void setStartPos(int x, int y) {
        setObject(x, y, START_SYMBOL);
    }

    public void setStartPos(Position pos) {
        setObject(pos.getX(), pos.getY(), START_SYMBOL);
    }

    public boolean markVisited(Position pos) {
        if (canNavigate(pos)) {
            setObject(pos.getX(), pos.getY(), VISITED_SYMBOL);
            return true;
        }

        return false;
    }

    public Position getDestination() {
        return destination;
    }

    public String[] getMap() {
        return map;
    }

    public int getVisitedCount() {
        int count = 0;
        for (String row : map)
            for (char c : row.toCharArray())
                if (c == VISITED_SYMBOL)
                    count++;

        return count;
    }

    private void fillWall(int row) {
        // Fill a row with a character
        String s = "";
        for (int i = 0; i < cols; i++) {
            s += WALL_SYMBOL;
        }

        // Replace the row with the new string
        map[row] = s;
    }

    private void setMapBoundary() {
        for (int i = 0; i < rows; i++) {
            // If the row is the first or
            // last row, fill it with walls
            if (i == 0 || i == rows - 1) {
                fillWall(i);
            } else {
                // Otherwise, only fill the first
                // and last characters with walls
                map[i] = WALL_SYMBOL + map[i].substring(1, cols - 1) + WALL_SYMBOL;
            }
        }
    }

    // Randomly generate the maze
    private void randomize() {
        // Generate the maze
        for (int i = 0; i < rows; i++) {
            String row = "";
            for (int j = 0; j < cols; j++) {
                double rnd = Math.random();
                if (rnd <= DENSITY) {
                    row += WALL_SYMBOL;
                } else {
                    row += PATH_SYMBOL;
                }
            }
            map[i] = row.toString();
        }

        // Set the maze boundary
        setMapBoundary();

        // Set the destination
        int randomX = (int) (Math.random() * (cols - 2)) + 1;
        int randomY = (int) (Math.random() * (rows - 2)) + 1;

        Position pos = new Position(randomX, randomY);
        setDestination(pos);
    }

    public boolean placeRobot(Robot robot) {
        Position pos = robot.getPos();
        if (canNavigate(pos)) {
            setStartPos(pos);
            return true;
        }

        return false;
    }

    // Print the maze without colors
    public void printNoColors() {
        for (String row : map) {
            System.out.println(row);
        }
    }

    // Print the maze with colors
    public void printWithColors() {
        for (String row : map) {
            for (char c : row.toCharArray()) {
                switch (c) {
                    case WALL_SYMBOL -> System.out.print(TerminalColors.CYAN + c + TerminalColors.RESET);
                    case START_SYMBOL, END_SYMBOL -> System.out.print(TerminalColors.RED + c + TerminalColors.RESET);
                    case VISITED_SYMBOL -> System.out.print(TerminalColors.YELLOW + c + TerminalColors.RESET);
                    default -> System.out.print(c);
                }
            }
            System.out.println();
        }
    }

    // Print the maze
    public void print(boolean colorized) {
        if (colorized)
            printWithColors();
        else
            printNoColors();
    }

    // Check if the position is valid
    public boolean canNavigate(Position pos) {
        // Check if the position is out of bounds
        if (pos.getX() < 0 || pos.getX() >= cols || pos.getY() < 0 || pos.getY() >= rows)
            return false;

        // Check if the position is a wall
        return map[pos.getY()].charAt(pos.getX()) != WALL_SYMBOL;
    }

    public boolean canNavigate(int x, int y) {
        return canNavigate(new Position(x, y));
    }

    // Check if the position is the destination
    public boolean isDestination(Position pos) {
        return pos.equals(destination);
    }

    public boolean isDestination(int x, int y) {
        return isDestination(new Position(x, y));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String row : map) {
            sb.append(row).append("\n");
        }

        return sb.toString();
    }
}
