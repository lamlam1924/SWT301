package lamlt.example;

import java.util.logging.Logger;

public class InterfaceFieldModificationExample {
    private static final Logger logger = Logger.getLogger(InterfaceFieldModificationExample.class.getName());
    public static void main(String[] args) {
        logger.info("Max users allowed: " + Constants.MAX_USERS);
        // Constants.MAX_USERS = 200; // Sai, vì là final constant
    }
}
