package lamlt.example;

import java.io.*;
import java.util.logging.Logger;

public class PathTraversalExample {
    private static final Logger logger = Logger.getLogger(PathTraversalExample.class.getName());
    // Thư mục gốc an toàn (base directory) để giới hạn truy cập file
    private static final File BASE_DIR = new File("safe/directory");

    public static void main(String[] args) throws IOException {
        String userInput = "../secret.txt"; //giả lập
        try {
            //Lấy file đã được xác thực
            File file = getValidated(userInput);

            //Kiểm tra file có tồn tại không
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    logger.info("Reading file: " + file.getPath());
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Xử lý hoặc ghi log nội dung từng dòng file (nếu cần)
                        logger.fine(line);
                    }
                }
            } else {
                logger.warning("File does not exist: " + file.getPath());
            }
        } catch (IOException e) {
            logger.severe("Error accessing file: " + e.getMessage());
        }
    }

    private static File getValidated(String userInput) throws IOException {
        // Tạo đối tượng File bằng cách ghép BASE_DIR với userInput
        // rồi chuyển sang đường dẫn canonical để loại bỏ "..", "." không hợp lệ
        File file = new File(BASE_DIR, userInput).getCanonicalFile();

        // Kiểm tra xem file có nằm trong BASE_DIR hay không
        if (!file.getPath().startsWith(BASE_DIR.getCanonicalPath())) {
            throw new IOException("Access denied to file: " + file.getPath());
        }
        return file;
    }
}
