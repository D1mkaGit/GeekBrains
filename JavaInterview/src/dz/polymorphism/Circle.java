package dz.polymorphism;


public class Circle extends Shape {

    private final Double radius;

    public Circle(String name, Double radius) {
        super(name);
        this.radius = radius;
        setArea();
        setPerimeter();
    }

    @Override
    public void setArea() {
        this.area = Math.PI * (radius * radius);
    }

    @Override
    public void setPerimeter() {
        this.perimeter = (2 * Math.PI * this.radius);
    }
}
