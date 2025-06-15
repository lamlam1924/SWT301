package lamlt.example;

import java.util.logging.Logger;

interface Drawable {
    void draw();
}

class Circle implements Drawable {
    private static final Logger logger = Logger.getLogger(Circle.class.getName());

    @Override
    public void draw() {
        logger.info("Drawing a Circle");
    }

    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.draw();
        Drawable d = new Circle();
        d.draw();
    }
}



