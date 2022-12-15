enum Algorithm {
    BFS, DFS, DIJKSTRA, A_STAR
}

public class OriginalRobot {
    // Original robot position
    private final Position pos = Position.ORIGIN;

    // Maximum size of the maze width or height,
    // depending on which is larger
    private final int MAX_SIZE = 1000;

    // Boundary for each row, taking into account the walls,
    // and MAX_SIZE to navigate in any direction.
    private final int BOUNDARY_LIMIT = MAX_SIZE * 2 - 1;

    // Save visited cells to avoid revisiting them
    boolean[][] visited = new boolean[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    // Memoize the shortest path to each cell
    int[][] distance = new int[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    // Memoize the previous cell in the shortest path
    Position[][] prev = new Position[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    public void navigate() {
        OriginalMaze maze = new OriginalMaze();
        navigate(maze, Algorithm.BFS);
    }

    public void navigate(Algorithm algorithm) {
        if (algorithm == null) {
            System.out.println("Algorithm should not be null.");
            return;
        }

        OriginalMaze maze = new OriginalMaze();
        navigate(maze, algorithm);
    }

    public void navigate(OriginalMaze maze, Algorithm algorithm) {
        if (maze == null) {
            System.out.println("Maze should not be null.");
            return;
        }

        if (algorithm == null) {
            System.out.println("Algorithm should not be null.");
            return;
        }

        // Clear all memoization arrays
        clearData();

        switch (algorithm) {
            case BFS:
                navigateBFS(maze);
                break;

            case DFS:
//                navigateDFS(maze);
                break;

            case DIJKSTRA:
//                navigateDijkstra(maze);
                break;

            case A_STAR:
//                navigateAStar(maze);
                break;

            default:
                System.out.println("Algorithm not implemented.");
                break;
        }
    }

    private void navigateBFS(OriginalMaze maze) {
        String result = "";

        Queue queue = new Queue();
        queue.enqueue(pos);

        while (!queue.isEmpty() && !result.equals("win")) {
            Position currentPos = queue.dequeue();

            for (Direction dir : Direction.values()) {
                Position nextPos = (new Position(currentPos)).move(dir);
                String dirStr = DirectionHelper.toString(dir);

                boolean visitedBefore = visited[nextPos.getX() + 25][nextPos.getY() + 25];

                if (visitedBefore)
                    continue;

                result = maze.go(dirStr);
                visited[nextPos.getX() + 25][nextPos.getY() + 25] = true;

                boolean isEnd = result.equals("win");

                if (isEnd)
                    break;

                boolean cannotGo = result.equals("false");

                if (cannotGo)
                    continue;

                queue.enqueue(nextPos);
                prev[nextPos.getX() + 25][nextPos.getY() + 25] = currentPos;
//                System.out.println(dirStr);
            }
        }

        int leastMoves = reconstructPath(prev, queue.peek());
        System.out.println("The BFS Algorithm found a path of length " + leastMoves);
    }

    private int reconstructPath(Position[][] prev, Position endPos) {
        Position currentPos = endPos;
        int moves = 0;

        while (!currentPos.equals(pos)) {
            Position prevPos = prev[currentPos.getX() + 25][currentPos.getY() + 25];
            Direction dir = DirectionHelper.getDirection(prevPos, currentPos);
            String dirStr = DirectionHelper.toString(dir);
            System.out.println(dirStr);
            currentPos = prevPos;
            moves++;
        }

        return moves;
    }

    private void clearData() {
        for (int i = 0; i < BOUNDARY_LIMIT; i++) {
            for (int j = 0; j < BOUNDARY_LIMIT; j++) {
                visited[i][j] = false;
                distance[i][j] = 0;
                prev[i][j] = null;
            }
        }
    }
}
