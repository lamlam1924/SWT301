import lamlt.example.AccountService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)// để dùng @AfterAll không static
public class AccountServiceTest {

    private final AccountService accountService = new AccountService();
    private final List<String> resultLines = new ArrayList<>(); //dùng để lưu test case

    //chạy trước tất cả test case, thêm dong tiêu đề vào List
    @BeforeAll
    void setup(){
        resultLines.add("username,password,email,expected,actual,result");
    }

    //sau khi test đã chạy xong
    @AfterAll
    void writeResultFile() throws IOException{

        //đường dẫn đến file kết quả
        Path outputDir = Paths.get("test/output");

        //nếu chưa có folder
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }
        //tạo file kết quả
        Path output = outputDir.resolve("UnitTest.csv");

        //ghi kết quả vào file
        Files.write(output, resultLines);

        System.out.println("Test results written to " + output.toAbsolutePath());
    }

    //chạy các test case với dữ liệu từ file CSV
    @ParameterizedTest(name = "Test {index} => username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, boolean expected) {
        boolean actual = accountService.registerAccount(username, password, email);
        boolean passed = (expected == actual);

        resultLines.add(String.join(",",
                safe(username), safe(password), safe(email),
                String.valueOf(expected), String.valueOf(actual),
                passed ? "PASS" : "FAIL"));

        assertEquals(expected, actual, () ->
                String.format("Expected %s but got %s for input (username='%s', password='%s', email='%s')",
                        expected, actual, username, password, email));
    }


    //hàm xử lí chuỗi để tránh lỗi khi chuỗi có dấu phẩy
    private String safe(String input){
        return input == null ? "" : input.replace(",",";");
    }

    // Kiểm tra email hợp lệ
    @Test
    void testIsValidEmail_Valid() {
        assertTrue(accountService.isValidEmail("user@example.com"));
    }

    @Test
    void testIsValidEmail_Invalid() {
        assertFalse(accountService.isValidEmail("userexample.com")); // thiếu @
        assertFalse(accountService.isValidEmail("user@.com"));       // thiếu domain
        assertFalse(accountService.isValidEmail(""));                // rỗng
        assertFalse(accountService.isValidEmail(null));              // null
    }

    // Kiểm tra username rỗng hoặc null
    @Test
    void testRegisterAccount_UsernameEmpty() {
        assertFalse(accountService.registerAccount("", "password123", "email@example.com"));
    }

    @Test
    void testRegisterAccount_UsernameNull() {
        assertFalse(accountService.registerAccount(null, "password123", "email@example.com"));
    }

    // Kiểm tra password nhỏ hơn hoặc bằng 6 ký tự
    @Test
    void testRegisterAccount_ShortPassword() {
        assertFalse(accountService.registerAccount("user1", "123456", "email@example.com")); // đúng 6 ký tự
        assertFalse(accountService.registerAccount("user1", "abc", "email@example.com"));
    }

    // Kiểm tra password null
    @Test
    void testRegisterAccount_PasswordNull() {
        assertFalse(accountService.registerAccount("user1", null, "email@example.com"));
    }

    // Email không hợp lệ
    @Test
    void testRegisterAccount_InvalidEmail() {
        assertFalse(accountService.registerAccount("user1", "password123", "invalid-email"));
        assertFalse(accountService.registerAccount("user1", "password123", ""));
        assertFalse(accountService.registerAccount("user1", "password123", null));
    }

    // Kiểm tra đăng ký thành công khi hợp lệ
    @Test
    void testRegisterAccount_Success() {
        assertTrue(accountService.registerAccount("user1", "password123", "user1@example.com"));
    }
}
