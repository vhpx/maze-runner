public class Maze {
    // Maze size
    private int rows = 30;
    private int cols = 150;
    private float density = 0.1f;

    // The maze is represented as a 2D array of Strings
    // Each string is a row of the maze
    private String[] map = new String[rows];

    // * Maze configurations
    // Special properties of the maze
    private final char WALL_SYMBOL = '.';
    private final char PATH_SYMBOL = ' ';
    private final char START_SYMBOL = 'S';
    private final char END_SYMBOL = 'X';
    private final char VISITED_SYMBOL = '+';

    // Maze destination
    Position destination = new Position(0, 0);

    // Miscelaneous stats
    private int visitedCells = 0;

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

    public Maze(int rows, int cols, float density) {
        // Set the maze size
        setSize(rows, cols);

        // Set the maze density
        setDensity(density);

        // Randomly generate the maze
        // when the Maze object is created
        randomize();
    }

    public Maze(String[] map) {
        // Set the maze size
        setSize(map.length, map[0].length());

        // Set the maze map
        setMap(map);

        // Find the destination
        Position dest = getObjectPosition(END_SYMBOL);

        // Make sure the destination is not null
        assert dest != null;

        // Set the destination
        setDestination(dest);
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

    private void setDensity(float density) {
        this.density = density;
    }

    public void setObject(int x, int y, char object) {
        // Don't allow the robot to change start or end positions
        if (map[y].charAt(x) == START_SYMBOL || map[y].charAt(x) == END_SYMBOL) {
            return;
        }

        // Set the object at the given position
        map[y] = map[y].substring(0, x) + object + map[y].substring(x + 1);
    }

    public Position getObjectPosition(char object) {
        // Find the position of the given object
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (map[y].charAt(x) == object) {
                    return new Position(x, y);
                }
            }
        }

        // Return null if the object is not found
        return null;
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

    public Position getStartPos() {
        return getObjectPosition(START_SYMBOL);
    }

    public boolean markVisited(Position pos) {
        if (canNavigate(pos)) {
            setObject(pos.getX(), pos.getY(), VISITED_SYMBOL);
            visitedCells++;
            return true;
        }

        return false;
    }

    public void resetVisited() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (map[y].charAt(x) == VISITED_SYMBOL) {
                    setObject(x, y, PATH_SYMBOL);
                }
            }
        }
    }

    public int getVisitedCount() {
        return visitedCells;
    }

    public void resetVisitedCount() {
        visitedCells = 0;
    }

    public Position getDestination() {
        return destination;
    }

    public String[] getMap() {
        return map;
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
                if (rnd <= density) {
                    row += WALL_SYMBOL;
                } else {
                    row += PATH_SYMBOL;
                }
            }
            map[i] = row.toString();
        }

        // Set the maze boundary
        setMapBoundary();

        // randomly set the start and end positions
        Position startPos = Position.random(1, cols - 2, 1, rows - 2);
        Position endPos = Position.random(1, cols - 2, 1, rows - 2);

        // Make sure the start and end positions are not the same
        while (startPos.equals(endPos)) {
            endPos = Position.random(1, cols - 2, 1, rows - 2);
        }

        // Set the start and end positions
        setStartPos(startPos);
        setDestination(endPos);
    }

    public void placeRobot(Robot robot) {
        // Get start position
        Position pos = getObjectPosition(START_SYMBOL);

        // Place the robot at the start position
        robot.setPos(pos);
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
        // replace all VISITED_SYMBOL with PATH_SYMBOL
        String[] mapCopy = map.clone();
        for (int i = 0; i < mapCopy.length; i++) {
            mapCopy[i] = mapCopy[i].replace(VISITED_SYMBOL, PATH_SYMBOL);
        }

        // return the map as a string
        StringBuilder sb = new StringBuilder();

        for (String row : mapCopy) {
            sb.append(row).append("\n");
        }

        return sb.toString();
    }
}
