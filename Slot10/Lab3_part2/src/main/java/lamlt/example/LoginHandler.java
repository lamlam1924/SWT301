package lamlt.example;

import java.util.logging.Logger;

public interface LoginHandler {
    boolean login(String username, String password);
}

class SimpleLoginHandler implements LoginHandler {
    private static final Logger logger = Logger.getLogger(SimpleLoginHandler.class.getName());

    @Override
    public boolean login(String username, String password) {
        boolean success = "admin".equals(username) && "1234".equals(password);
        logger.info(() -> success ? "Login successful" : "Login failed");
        return success;
    }

    public static void main(String[] args) {
        LoginHandler handler = new SimpleLoginHandler();

        if (handler.login("admin", "1234")) {
            logger.info("Access granted.");
        } else {
            logger.warning("Access denied.");
        }

        if (handler.login("user", "wrongpass")) {
            logger.info("Access granted.");
        } else {
            logger.warning("Access denied.");
        }
    }
}
