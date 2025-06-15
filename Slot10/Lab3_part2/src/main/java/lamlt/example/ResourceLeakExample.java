package lamlt.example;

import java.io.*;
import java.util.logging.Logger;

public class ResourceLeakExample {
    private static final Logger logger = Logger.getLogger(ResourceLeakExample.class.getName());
    public static void main(String[] args) {
        // Sử dụng try-with-resources để đảm bảo tài nguyên (BufferedReader) được đóng tự động sau khi sử dụng
        try(BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            // Đọc từng dòng trong file cho đến khi hết (null)
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        } catch (IOException e) {
            logger.severe("Error reading file: " + e.getMessage());
        }
    }
}
