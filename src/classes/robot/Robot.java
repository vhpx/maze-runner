package classes.robot;

import classes.common.Stack;
import classes.common.Node;
import classes.maze.Maze;
import classes.common.Position;
import classes.common.Queue;
import enums.Algorithm;
import enums.Direction;

public class Robot {
    // Original robot position
    private final Position pos = Position.ORIGIN;

    // Maximum size of the maze width or height,
    // depending on which is larger
    private final int MAX_SIZE = 7;

    // Boundary for each row, taking into account the walls,
    // and MAX_SIZE to navigate in any direction.
    private final int BOUNDARY_LIMIT = MAX_SIZE * 2 + 1;

    // Save visited cells to avoid revisiting them
    boolean[][] visited = new boolean[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    // Memoize the shortest path to each cell
    int[][] distance = new int[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    // Memoize the previous cell in the shortest path
    Position[][] prev = new Position[BOUNDARY_LIMIT][BOUNDARY_LIMIT];

    // Store the optimal path
    Stack optimalPath = new Stack();

    public void navigate() {
        navigate(Algorithm.BFS);
    }

    public void navigate(Algorithm algorithm) {
        if (algorithm == null) {
            System.out.println("classes.Algorithm should not be null.");
            return;
        }

        Maze maze = new Maze();
        navigate(maze, algorithm);
    }

    public void navigate(Maze maze, Algorithm algorithm) {
        if (maze == null) {
            System.out.println("Maze should not be null.");
            return;
        }

        if (algorithm == null) {
            System.out.println("classes.Algorithm should not be null.");
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
                System.out.println("classes.Algorithm not implemented.");
                break;
        }
    }

    private void navigateBFS(Maze maze) {
        String result = "";

        Queue queue = new Queue();
        queue.enqueue(pos);

        while (!queue.isEmpty() && !result.equals("win")) {
            Position currentPos = queue.dequeue();

            for (Direction dir : Direction.values()) {
                Position nextPos = (new Position(currentPos)).move(dir);
                String dirStr = DirectionHelper.toString(dir);

                int newX = nextPos.getX() + MAX_SIZE;
                int newY = nextPos.getY() + MAX_SIZE;

                if (newX < 0 || newX >= BOUNDARY_LIMIT || newY < 0 || newY >= BOUNDARY_LIMIT)
                    continue;

                boolean visitedBefore = visited[newX][newY];

                if (visitedBefore) continue;

                result = maze.go(dirStr);
                visited[newX][newY] = true;

                boolean isEnd = result.equals("win");

                if (isEnd) break;

                boolean cannotGo = result.equals("false");

                if (cannotGo) continue;

                queue.enqueue(nextPos);
                prev[newX][newY] = currentPos;
            }
        }

        optimalPath = reconstructPath(prev, queue.peek());
    }

    private Stack reconstructPath(Position[][] prev, Position endPos) {
        Stack path = new Stack();
        Node currentNode = new Node(endPos);

        while (!currentNode.getData().equals(pos)) {
            Position currentPos = currentNode.getData();

            int newX = currentPos.getX() + MAX_SIZE;
            int newY = currentPos.getY() + MAX_SIZE;

            Position prevPos = prev[newX][newY];
            Node prevNode = new Node(prevPos);

            path.push(prevNode);
            currentNode = prevNode;
        }

        return path;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    public Stack getOptimalPath() {
        return optimalPath;
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
