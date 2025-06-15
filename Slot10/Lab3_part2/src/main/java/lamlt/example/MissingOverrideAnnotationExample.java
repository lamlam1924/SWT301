package lamlt.example;
import java.util.logging.Logger;

class Animal {
    private static final Logger logger = Logger.getLogger(Animal.class.getName());
    void speak() {
        logger.info("Animal speaks");
    }
}

class Dog extends Animal {
    private static final Logger logger = Logger.getLogger(Dog.class.getName());
    @Override
    void speak() {
        logger.info("Dog barks");
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.speak();  // Output: Animal speaks

        Dog dog = new Dog();
        dog.speak();     // Output: Dog barks
    }
}
