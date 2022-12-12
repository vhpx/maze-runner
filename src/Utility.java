import java.util.Scanner;

public class Utility {
    private static final Scanner sc = new Scanner(System.in);

    public static void waitForKey() {
        System.out.print("Press any key to continue...");
        sc.nextLine();
        System.out.println();
    }
}
