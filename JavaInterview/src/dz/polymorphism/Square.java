package dz.polymorphism;

public class Square extends Shape implements Draw {

    private final Double side;

    public Square(String name, Double side) {
        super(name);
        this.side = side;
        setArea();
        setPerimeter();
    }

    @Override
    public void setArea() {
        this.area = side * side;
    }

    @Override
    public void setPerimeter() {
        this.perimeter = 4 * side;
    }
}
