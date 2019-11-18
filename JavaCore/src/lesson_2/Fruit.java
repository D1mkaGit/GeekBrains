package lesson_2;

public enum Fruit {
    ORANGE("Апельсин", 3), APPLE("Яблоко", 2), BANANA("Бабнан", 4), CHERRY("Вишня", 1);

    private String rus;
    private int weigth;

    Fruit(String rus, int weigth) {
        this.rus = rus;
        this.weigth = weigth;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }
}

class MainFruit {
    public static void main(String[] args) {
//        Fruit f = Fruit.APPLE;
//
//        System.out.println(f);

        //System.out.println(Fruit.APPLE);

        for (Fruit o : Fruit.values()) {
            System.out.println(o.getRus() + " " + o + " " + o.getWeigth());
        }
        // System.out.println(Fruit.BANANA.ordinal());


    }
}
