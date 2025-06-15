package lamlt.example;

import java.util.logging.Logger;

public class CatchGenericExceptionExample {

    private static final Logger logger = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        String s = "hello";
        logger.info("Attempting to get string length...");

        int len = s.length(); // Safe, no null
        logger.info(() -> "String length: " + len);
    }
}
