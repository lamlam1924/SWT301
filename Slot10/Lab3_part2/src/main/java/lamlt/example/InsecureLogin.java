package lamlt.example;

import java.util.logging.Logger;

public class InsecureLogin {

    private static final Logger logger = Logger.getLogger(InsecureLogin.class.getName());

    public static void login(String username, String password) {
        if (username.equals("admin") && password.equals(System.getenv("ADMIN_PASSWORD"))) {
            logger.info("Login successful");
        } else {
            logger.warning("Login failed");
        }
    }

    public void printUserInfo(String user) {
        if (user != null && user.isEmpty()) {
            logger.info("User: " + user);
        } else {
            logger.warning("User information is empty or null");
        }
    }
}
