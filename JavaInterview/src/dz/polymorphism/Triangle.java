package dz.polymorphism;

public class Triangle extends Shape implements Draw {
    private final Double a;
    private final Double b;
    private final Double c;

    public Triangle(String name, Double a, Double b, Double c) {
        super(name);
        this.a = a;
        this.b = b;
        this.c = c;
        setArea();
        setPerimeter();
    }


    @Override
    public void setArea() {
        if (this.perimeter == null) setPerimeter();
        Double S = this.perimeter;
        this.area = Math.sqrt(S * (S - a) * (S - b) * (S - c));
    }

    @Override
    public void setPerimeter() {
        this.perimeter = (a + b + c) / 2;
    }

}
