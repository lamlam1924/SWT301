package lamlt.example;

public class AccountService {
    private String username;
    private String password;
    private String email;

    public boolean registerAccount(String username, String password, String email) {
        if (username == null || username.isEmpty()) return false;
        if (password == null || password.length() <= 6) return false;
        if (!isValidEmail(email)) return false;
        return true;
    }

    public boolean isValidEmail(String email){
        if(email == null) return false;
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }


}
