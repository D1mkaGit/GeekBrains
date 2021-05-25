package dz.polymorphism;

public abstract class Shape implements Draw {
    final String name;
    Double area;
    Double perimeter;

    public Shape(String name, Double area, Double perimeter) {
        this.name = name;
        this.area = area;
        this.perimeter = perimeter;
    }

    public Shape(String name) {
        this.name = name;
    }

    public abstract void setArea();

    public abstract void setPerimeter();

    @Override
    public String toString() {
        return "figure with " +
                "name='" + name + '\'' +
                ", area=" + area +
                ", perimeter='" + perimeter;
    }

    @Override
    public void draw() {
        System.out.println("This is " + toString());
    }
}
