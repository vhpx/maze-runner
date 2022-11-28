public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze();
        Robot robot = new Robot(2,2);

        maze.setStartPos(robot.getPos());
        maze.print();

        // The robot will navigate the maze
        robot.navigate(maze);

        System.out.println("Number of moves: " + robot.getMoves());
        System.out.println("Number of visited positions: " + maze.getVisitedCount());
        System.out.println("Maze path:");
        maze.print();
    }
}
