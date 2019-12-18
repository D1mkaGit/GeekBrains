package lesson_1.dz.fruits_boxes;

public class Main {
    public static void main(String[] args) {
        Box<Orange> oBox1 = new Box<>(new Orange().setupArr(15));
        Box<Orange> oBox2 = new Box<>(new Orange().setupArr(10));

        oBox1.getWeight();
        oBox2.getWeight();

        Box<Apple> aBox1 = new Box<>(new Apple().setupArr(10));
        Box<Apple> aBox2 = new Box<>(new Apple().setupArr(10));

        aBox1.getWeight();
        aBox2.getWeight();

        System.out.println("Сравниваем первую коробку c апельсинами со второй");
        if (oBox1.compare(oBox2.inBox)) System.out.println("Коробки равны");
        else System.out.println("Коробки не равны");

        System.out.println("****************");

        System.out.println("Сравниваем первую коробку с яблаками со второй");
        if (aBox1.compare(aBox2.inBox)) System.out.println("Коробки равны");
        else System.out.println("Коробки не равны");

        System.out.println("****************");

        System.out.println("В пером ящике: " + oBox1.getWeight() + " апельсинов");
        System.out.println("Во втором ящике: " + oBox2.getWeight() + " апельсинов");
        System.out.println("Пересыпаем из первого во второй");
        oBox2.copyContentFrom(oBox1.inBox); // переносим
        oBox1.emptyContentInArr(); // удаляем
        System.out.println("В пером ящике: " + oBox1.getWeight() + " апельсинов");
        System.out.println("Во втором ящике: " + oBox2.getWeight() + " апельсинов");

        System.out.println("****************");

        System.out.println("В пером ящике: " + aBox1.getWeight() + " яблок");
        System.out.println("Во втором ящике: " + aBox2.getWeight() + " яблок");
        System.out.println("Пересыпаем из первого во второй");
        aBox2.copyContentFrom(aBox1.inBox); // переносим
        aBox1.emptyContentInArr(); // удаляем
        System.out.println("В пером ящике: " + aBox1.getWeight() + " яблок");
        System.out.println("Во втором ящике: " + aBox2.getWeight() + " яблок");

        System.out.println("****************");
        System.out.println("Доюавляем в первые ящики по одному фрукту");
        aBox1.addFruit(new Apple());
        oBox1.addFruit(new Orange());
        System.out.println("В пером ящике: " + oBox1.getWeight() + " апельсинов");
        System.out.println("В пером ящике: " + aBox1.getWeight() + " яблок");
    }
}
