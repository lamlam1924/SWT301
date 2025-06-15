package lamlt.example;

import java.util.logging.Logger;

public class OvercatchingExceptionExample {
    private static final Logger logger = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            int[] arr = {10, 20, 30, 40, 50};
            int index = 2;
            int value = arr[index];
            logger.info(() -> String.format("Value at index %d: %d", index, value));
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe("Array index out of bounds: " + e.getMessage());
        }
    }
}

