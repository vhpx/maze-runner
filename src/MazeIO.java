import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MazeIO {
    private static String loadFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Maze[] loadMazes() {
        Maze[] mazes = new Maze[Main.TEST_CASES];

        for (int i = 0; i < Main.TEST_CASES; i++) {
            String content = loadFile(Main.TEST_FOLDER + "maze_" + (i + 1) + ".txt");

            // Make sure content is not null
            assert content != null;

            // split into rows
            String[] rows = content.split("\n");
            mazes[i] = new Maze(rows);
        }

        return mazes;
    }
}
