import java.io.FileWriter;
import java.io.IOException;

public class MazeGenerator {
    static private String generate() {
        // randomize maze size
        int rows = (int) (Math.random() * 26) + 5; // 5 - 30
        int cols = (int) (Math.random() * 146) + 5; // 5 - 150

        // randomize maze density
        float density = (float) (Math.random() * 0.5) + 0.05f; // 0.05 - 0.55

        return new Maze(rows, cols, density).toString();
    }

    static private String[] generate(int count) {
        String[] mazes = new String[count];

        for (int i = 0; i < count; i++) {
            mazes[i] = generate();
        }

        return mazes;
    }

    public static void generateFiles(int count) {
        String[] mazes = generate(count);

        for (int i = 0; i < count; i++) {
            String filename = "tests/maze_" + (i + 1) + ".txt";
            try {
                FileWriter fw = new FileWriter(filename);
                fw.write(mazes[i]);
                fw.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // generateFiles(1000);
    }
}
