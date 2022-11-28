import java.io.FileWriter;
import java.io.IOException;

public class MazeGenerator {
    static private String generate() {
        do {
            // randomize maze size
            int rows = (int) (Math.random() * 21) + 10; // 10 - 30
            int cols = (int) (Math.random() * 121) + 30; // 30 - 150

            // randomize maze density
            float density = (float) (Math.random() * 0.21) + 0.1f; // 0.1 - 0.3

            // create maze
            Maze maze = new Maze(rows, cols, density);
            Robot robot = new Robot();

            // place robot in maze
            maze.placeRobot(robot);

            // navigate maze
            if (robot.navigate(maze))
                return maze.toString();
        } while (true);
    }

    public static void generateFiles(int start, int end) {
        try {
            for (int i = start; i < end; i++) {
                String filename = "tests/maze_" + (i + 1) + ".txt";
                String map = generate();

                System.out.println(
                        TerminalColors.GREEN + "Maze " + (i + 1) + " generated successfully!" + TerminalColors.RESET);

                FileWriter fw = new FileWriter(filename);
                fw.write(map);
                fw.close();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void generateFiles(int amount) {
        generateFiles(0, amount);
    }

    public static void main(String[] args) {
        generateFiles(800, 1000);
    }
}
