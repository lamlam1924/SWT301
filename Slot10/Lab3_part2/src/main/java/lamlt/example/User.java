package lamlt.example;

import java.util.logging.Logger;

public class User {
    private static final Logger logger = Logger.getLogger(User.class.getName());
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        setAge(age); // Sử dụng setter để đảm bảo tính hợp lệ của age
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }

    public void display() {
        logger.info(() -> String.format("Name: %s, Age: %d", name, age));
    }

    public static void main(String[] args) {
        User u1 = new User("Lâm", 21);      // Dùng constructor mặc định
        u1.setName("Test");
        u1.setAge(20);
        logger.info(() -> "Age: " + u1.getAge());
        u1.display();
    }
}
