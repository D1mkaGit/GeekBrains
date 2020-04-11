package lesson_4.my_dz;

public class MyCar {
    private final String make;
    private final String mark;
    private final int year;

    public MyCar(String make, String mark, int year) {
        this.make = make;
        this.mark = mark;
        this.year = year;
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();

        list.insert("Audi");
        list.insert("BMW");
        list.insert("Volkswagen");

        if (list.isNull()) {
            System.out.println("Список пуст");
        } else {
            list.display();
            System.out.println("Found: " + list.find("BMW"));
            System.out.println("Deleted: " + list.delete("BMW"));
            list.display();
        }

        System.out.println("************");

        MyLinkedList<MyCar> carList = new MyLinkedList<>();
        carList.insert(new MyCar("Mercedes Benz", "SL500", 2003));
        carList.insert(new MyCar("BMW", "535", 2018));
        carList.insert(new MyCar("Audi", "RS8", 2019));

        if (carList.isNull()) {
            System.out.println("Список пуст");
        } else {
            carList.display();
            MyCar carToDelete = new MyCar("BMW", "535", 2018);
            System.out.println("Found: " + carList.find(carToDelete));
            System.out.println("Deleted: " + carList.delete(carToDelete));
            carList.display();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MyCar otherCar = (MyCar) obj;
        if (!this.make.equals(otherCar.make)) {
            return false;
        }
        return this.year == otherCar.year;
    }

    @Override
    public String toString() {
        return "Car:" + this.make + "-" + this.mark + ", year:" + this.year;
    }
}
