import java.io.FileWriter;
import java.io.IOException;

public class MazeGenerator {
    static private String generate() {
        return new Maze().toString();
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
        generateFiles(10);
    }
}
