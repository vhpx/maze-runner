public class Robot {
    // A very simple implementation
    // where the robot just go randomly
    public void navigate() {
        Maze maze = new Maze();
        String result = "";
        while (!result.equals("win")) {
            double rnd = Math.random();
            if (rnd <= 0.25) {
                System.out.println("UP");
                result = maze.go("UP");
            } else if (rnd <= 0.50) {
                System.out.println("DOWN");
                result = maze.go("DOWN");
            } else if (rnd <= 0.75) {
                System.out.println("LEFT");
                result = maze.go("LEFT");
            } else {
                System.out.println("RIGHT");
                result = maze.go("RIGHT");
            }
        }
    }
}