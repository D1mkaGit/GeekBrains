package dz.polymorphism;

public class Main {
    public static void main(String[] args) {
        Draw draw1 = new Circle("Circle1",7.5);
        Draw draw2 = new Triangle("Triangle1", 5.0, 3.2, 3.1 );
        Draw draw3 = new Square("Square1", 5.0);
        drawShape(draw1);
        drawShape(draw2);
        drawShape(draw3);
    }

    public static void drawShape(Draw draw) {
        draw.draw();
    }
}
