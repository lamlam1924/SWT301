package lamlt.example;

import java.util.logging.Logger;

public class HardcodedCredentialsExample {
    private static final Logger logger = Logger.getLogger(HardcodedCredentialsExample.class.getName());
    private static final String EXPECTED_USER = "admin";
    private static final String EXPECTED_PASS = System.getenv("ADMIN_PASSWORD");

    public static void main(String[] args) {
        // Test với đúng credentials
        testAuthenticate(EXPECTED_USER, EXPECTED_PASS);

        // Test với credentials sai
        testAuthenticate("user", "wrongpass");
    }


    private static boolean authenticate(String user, String pass) {
        // So sánh với giá trị đúng (không hardcode password)
        return user != null && user.equals(EXPECTED_USER) &&
                pass != null && pass.equals(EXPECTED_PASS);
    }

    private static void testAuthenticate(String user, String pass) {
        if (authenticate(user, pass)) {
            logger.info(() -> "Trying login with user: " + user);

        } else {
            logger.warning(() -> "Access denied");
        }
    }
}

