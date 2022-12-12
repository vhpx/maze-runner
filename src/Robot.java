public class Robot {
    // The robot's current position
    private final Position pos;

    public Robot() {
        // The robot starts in the top left corner
        pos = new Position(0, 0);
    }

    public Robot(int x, int y) {
        // The robot starts at the given position
        pos = new Position(x, y);
    }

    public Robot(Position pos) {
        // The robot starts at the given position
        this.pos = new Position(pos);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        this.pos.setPos(x, y);
    }

    public void setPos(Position pos) {
        this.pos.setPos(pos);
    }

    public boolean navigateBFS(Maze maze) {
        // find the shortest path to the destination and store in prev array
        Position[][] prev = findPathBFS(maze);

        // reconstruct the shortest path to the destination
        int moves = reconstructPath(maze, prev);

        // print the maze
        maze.printWithColors();

        System.out.println("\nThe BFS Algorithm found a path of length " + moves + " in " + maze.getSteps() +
                " steps. Visited " + maze.getVisitedCount() + " cells.\n");
        return true;
    }

    public boolean navigateDFS(Maze maze) {
        // find the shortest path to the destination and store in prev array
        Position[][] prev = findPathDFS(maze);

        // reconstruct the shortest path to the destination
        int moves = reconstructPath(maze, prev);

        // print the maze
        maze.printWithColors();

        System.out.println("\nThe DFS Algorithm found a path of length " + moves + " in " + maze.getSteps() +
                " steps. Visited " + maze.getVisitedCount() + " cells.\n");
        return true;
    }

    public Position[][] findPathBFS(Maze maze) {
        Position startPos = maze.getStartPos();

        Queue queue = new Queue();
        queue.enqueue(startPos);

        boolean[][] visited = new boolean[maze.getCols()][maze.getRows()];
        Position[][] prev = new Position[maze.getCols()][maze.getRows()];

        while (!queue.isEmpty() && !maze.isDestination(queue.peek())) {
            Position currentPos = queue.dequeue();
            maze.markVisited(currentPos);

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                Position nextPos = new Position(currentPos.getX() + dr[i], currentPos.getY() + dc[i]);
                if (!maze.canNavigate(nextPos) || visited[nextPos.getX()][nextPos.getY()])
                    continue;

                queue.enqueue(nextPos);
                visited[nextPos.getX()][nextPos.getY()] = true;
                prev[nextPos.getX()][nextPos.getY()] = currentPos;
            }
        }

        return prev;
    }

    public Position[][] findPathDFS(Maze maze) {
        Position startPos = maze.getStartPos();

        Stack stack = new Stack();
        stack.push(startPos);

        boolean[][] visited = new boolean[maze.getCols()][maze.getRows()];
        Position[][] prev = new Position[maze.getCols()][maze.getRows()];

        while (!stack.isEmpty() && !maze.isDestination(stack.peek())) {
            Position currentPos = stack.pop();
            maze.markVisited(currentPos);

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                Position nextPos = new Position(currentPos.getX() + dr[i], currentPos.getY() + dc[i]);
                if (!maze.canNavigate(nextPos) || visited[nextPos.getX()][nextPos.getY()])
                    continue;

                stack.push(nextPos);
                visited[nextPos.getX()][nextPos.getY()] = true;
                prev[nextPos.getX()][nextPos.getY()] = currentPos;
            }
        }

        return prev;
    }

    public int reconstructPath(Maze maze, Position[][] prev) {
        Position currentPos = maze.getDestination();
        maze.markPosition(currentPos);
        int moves = 0;

        // while currentPos is not the start position
        while (!currentPos.equals(maze.getStartPos())) {
            // set currentPos to prev of currentPos
            currentPos = prev[currentPos.getX()][currentPos.getY()];
            moves++;
            maze.markPosition(currentPos);
        }

        return moves;
    }

    public boolean navigate(Maze maze, boolean useBFS) {
        if (useBFS)
            return navigateBFS(maze);
        else
            return navigateDFS(maze);
    }
}