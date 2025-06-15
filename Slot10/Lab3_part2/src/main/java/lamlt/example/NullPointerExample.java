package lamlt.example;

import java.util.Scanner;
import java.util.logging.Logger;

public class NullPointerExample {
    private static final Logger logger = Logger.getLogger(NullPointerExample.class.getName());
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter your age: ");
        int age = scanner.nextInt();

        if (age < 0 || age > 100) {
            logger.warning("Invalid age");
        } else {
            logger.info(() -> String.format("Age is valid: %d", age));
        }
        scanner.close();
    }
}
