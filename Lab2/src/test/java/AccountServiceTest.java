import lamlt.example.AccountService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)// để dùng @AfterAll không static
public class AccountServiceTest {

    private final AccountService accountService = new AccountService();
    private final List<String> resultLines = new ArrayList<>(); //dùng để kưu test case

    @BeforeAll //chạy trước tất cả test case, thêm dong tiêu đề vào List
    void setup(){
        resultLines.add("username,password,email,expected,actual,result");
    }

    @AfterAll //sau khi test đã chạy xong
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
}
