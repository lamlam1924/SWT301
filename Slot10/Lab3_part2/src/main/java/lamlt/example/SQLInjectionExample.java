package lamlt.example;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLInjectionExample {
    private static final Logger logger = Logger.getLogger(SQLInjectionExample.class.getName());
    public static void main(String[] args) {
        String userInput = "' OR '1'='1";
        // Lấy thông tin kết nối từ biến môi trường (an toàn hơn hardcode)
        String url = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        // Câu truy vấn SQL sử dụng dấu ? để tránh chèn mã độc từ người dùng
        String query = "SELECT username FROM users WHERE username = ?";

        // Sử dụng try-with-resources để tự động đóng Connection, PreparedStatement và ResultSet
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Truyền dữ liệu người dùng vào chỗ ? bằng cách binding giá trị
            pstmt.setString(1, userInput);
            logger.info("Executing prepared query...");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    logger.log(Level.INFO, "Found user: {0}", username);
                }
            }
        } catch (SQLException e) {
            // In ra tên người dùng nếu tìm thấy
            logger.log(Level.SEVERE, "Database error occurred", e);
        }
    }
}
