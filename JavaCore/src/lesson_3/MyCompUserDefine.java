package lesson_3;

import java.util.Comparator;
import java.util.TreeSet;

public class MyCompUserDefine {

    public static void main(String a[]) {

        TreeSet<Empl> salComp = new TreeSet<Empl>(new MySalaryComp());
        salComp.add(new Empl("Ram", 3000));
        salComp.add(new Empl("John", 6000));
        salComp.add(new Empl("Crish", 2000));
        salComp.add(new Empl("Tom", 2400));
        for (Empl e : salComp) {
            e.info();
        }
    }
}

class MySalaryComp implements Comparator<Empl> {

    @Override
    public int compare(Empl e1, Empl e2) {
        if (e1.getSalary() < e2.getSalary()) {
            return 1;
        } else {
            return -1;
        }
    }
}

class Empl {
    private String name;
    private int salary;

    public Empl(String n, int s) {
        this.name = n;
        this.salary = s;
    }

    public int getSalary() {
        return salary;
    }

    public void info() {
        System.out.println(name + " " + salary);
    }
}
