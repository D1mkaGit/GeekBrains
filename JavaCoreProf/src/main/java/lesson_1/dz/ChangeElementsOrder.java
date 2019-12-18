package lesson_1.dz;

public class ChangeElementsOrder<T> {
    private final T[] array;

    public ChangeElementsOrder(T[] array) {
        this.array = array;
    }

    public void changeElements(int firstPlace, int secondPlace) {
        if (array.length > firstPlace && array.length > secondPlace) {
            T temp;
            for (int i = 0; i < array.length; i++) {
                if (i == firstPlace) {
                    temp = array[i];
                    array[i] = array[secondPlace];
                    array[secondPlace] = temp;
                    System.out.println("Происходит Магия, переносится " + firstPlace + " и " + secondPlace + " элементы массива местами");
                }
            }
        } else {
            System.out.println("Нет таких номеров элементов: " + firstPlace + " или " + secondPlace + " в массиве из " + array.length + ". Массив остается без изменений.");
        }
    }

    private void printArr() {
        //Выводим массив в консоль
        System.out.print(array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.print(", " + array[i]);
        }
        System.out.println("\n");
    }

    public void printCatArr(Cat[] cats) {
        System.out.print(cats[0].getSurName());
        for (int i = 1; i < cats.length; i++) {
            System.out.print(", " + cats[i].getSurName());
        }
        System.out.println("\n");
    }

    public void doWork(int fe, int se) {
        System.out.print("Массив до изменений вот такой: ");
        printArr(); // до изменения
        changeElements(fe, se); // изменяем
        System.out.print("Массив после изменений становится вот такой: ");
        printArr(); // выводим
    }
}

class ChangeElementsOrderMain {
    public static void main(String[] args) {
        String[] strArr = {"Первый", "Второй", "Третий", "Четверный"};
        ChangeElementsOrder<String> strObj = new ChangeElementsOrder<>(strArr);
        strObj.doWork(1, 2);

        System.out.println("****************************");

        Integer[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        ChangeElementsOrder<Integer> intObj = new ChangeElementsOrder<>(intArr);
        intObj.doWork(4, 55);

        System.out.println("****************************");

        Cat[] catArr = {new Cat("Мурзик"), new Cat("Матроскин"), new Cat("Соня")
                , new Cat("Кукусёнок")};


        ChangeElementsOrder<Cat> catObj = new ChangeElementsOrder<>(catArr);

        System.out.print("Массив кличек котов до изменений вот такой: ");
        catObj.printCatArr(catArr); // выводим клички, т.к. ссылки на объекты - не интересно
        catObj.changeElements(1, 3); // изменяем
        System.out.print("Массив кличек котов после изменений становится вот такой: ");
        catObj.printCatArr(catArr); // выводим клички, т.к. ссылки на объекты - не интересно

    }
}

class Cat {
    private String surName;

    public Cat(String surName) {
        this.surName = surName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}

