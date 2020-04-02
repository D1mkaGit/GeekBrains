package Lesson_2;

import java.util.*;

public class Main {
    public static void main( String[] args ) {
//        MyArr arr = new MyArr(10);
//        arr.insert(5);
//        arr.insert(1);
//        arr.insert(2);
//        arr.insert(5);
//        arr.insert(4);
//        arr.insert(5);
//        arr.insert(6);
//        arr.insert(5);
//        arr.insert(8);
//        arr.insert(9);
//
//        int search = 8;
//
//        System.out.println("Выводим исходный массив");
//        arr.display();
//
//        if (arr.find(search)) {
//            arr.delete(search);
//            System.err.println("Элемент " + search + " удален");
//            System.out.println("Выводим новый массив");
//            arr.display();
//        } else {
//            System.out.println("Не удалось найти элемент " + search);
//        }

//        Main main = new Main();
//        main.sortBubble();
//
//        System.out.println(Arrays.toString(arr));


//        ArrayList<Integer> ai = new ArrayList<>();
//        ai.ensureCapacity(1000000);
//
//        System.out.println(ai.size());
//
//        ai.add(1);
//        ai.add(2);
//        System.out.println(ai.size());
//        ai.add(3);
//        ai.add(4);
//
//        if (ai.contains(4)) {
//            System.out.println(true);
//        }
//
//        System.out.println(ai.size());
//
//      //  ai.add(1, 2);
//
//        ai.remove((Integer) 2);
//
//        ai.trimToSize();
//
//        System.out.println(ai);

//        ArrayList<String> states = new ArrayList<>();
//
//        states.add("Россия");
//        states.add("Германия");
//        states.add("Германия");
//        states.add("Испания");
//        states.add("Италия");
//
//        Iterator<String> iter = states.iterator();
//
//        while (iter.hasNext()) {
//            if (iter.next().equalsIgnoreCase("Германия")) {
//                iter.remove();
//            }
//        }

//        for (int i = 0; i < states.size(); i++) {
//            if (states.get(i).equalsIgnoreCase("Германия")) {
//                states.remove(i);
//                i--;
//            }
//        }

        //  System.out.println(states);

        TreeSet<Worker> ts = new TreeSet<>(new MyComp());

        ts.add(new Worker("Sofia", 25, 40000));
        ts.add(new Worker("Danil", 35, 50000));
        ts.add(new Worker("Artem", 20, 18000));
        ts.add(new Worker("Petr", 28, 38000));
        ts.add(new Worker("Ivan", 21, 23000));

        //System.out.println(ts);

        for (Worker w : ts) {
            w.info();
        }
    }
}

class MyComp implements Comparator<Worker> {
    @Override
    public int compare( Worker o1, Worker o2 ) {
        return o1.getSalary() > o2.getSalary() ? 1 : -1;
    }
}

class Worker {
    private String name;
    private int age;
    private int salary;

    public void info() {
        System.out.println(name + " " + age + " " + salary);
    }

    public Worker( String name, int age, int salary ) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary( int salary ) {
        this.salary = salary;
    }
}


//
//    static int[] arr = {40,5,1,12,10};
//    int size = arr.length;
//
//    public void sortBubble(){
//        int out, in;
//        for (out = this.size - 1; out >= 1; out--) {
//            for(in = 0; in < out; in++) {
//                if (this.arr[in] > this.arr[in + 1]) {
//                    change(in, in + 1);
//                }
//            }
//        }
//    }
//
//    private void change(int a, int b){
//        int tmp = this.arr[a];
//        this.arr[a] = this.arr[b];
//        this.arr[b] = tmp;
//    }
//
//
//    public void sortSelect(){
//        int out, in, mark;
//        for(out=0;out<this.size;out++){
//            mark = out;
//            for(in = out+1;in<this.size;in++){
//                if (this.arr[in]< this.arr[mark]){
//                    mark = in;
//                }
//            }
//            change(out, mark);
//        }
//    }


//    int[] myArr1 = {1, 2, 3, 4, 5, 2};
//    int i;
//    int len = myArr1.length;
//    int search = 4;
//
//        System.out.println("Выводим массив");
//                for (int j = 0; j < len; j++) {
//        System.out.print(myArr1[j] + " ");
//        }
//
//        System.out.println("Поиск искомого элемента");
//        for (i = 0; i < len; i++) {
//        if (myArr1[i] == search) {
//        break;
//        }
//        }
//
//        System.out.println("Сдвиг всех элементов на 1 шаг влево");
//        for (int j = i; j < len - 1; j++) {
//        myArr1[j] = myArr1[j + 1];
//        }
//        len--;
//
//        System.out.println("Вывод массива с удаленным элементом");
//        for (int j = 0; j < len; j++) {
//        System.out.print(myArr1[j] + " ");
//        }

class People {
    String name;
}
