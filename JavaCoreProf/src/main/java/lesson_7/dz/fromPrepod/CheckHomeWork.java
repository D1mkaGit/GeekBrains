package lesson_7.dz.fromPrepod;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;

public class CheckHomeWork {

    public static void main( String[] args ) throws Exception {
        CheckHomeWork check = new CheckHomeWork();
        check.testSum();
    }

    public void testSum() throws Exception {
        File file = new File("C:/0123");
        String[] fileList = file.list();

        ArrayList<String> fileName = new ArrayList<String>();

        for (String o : fileList) {
            String[] mass = o.split("\\.");
            if (mass[1].equalsIgnoreCase("class")) {
                fileName.add(mass[0]);
            }
        }

        Iterator it = fileName.iterator();
        while (it.hasNext()) {
            String name = String.valueOf(it.next());
            Class ch = URLClassLoader.newInstance(new URL[]{new File("C:/0123").toURL()})
                    .loadClass(name);

            Constructor constructor = ch.getConstructor();
            Object calc = constructor.newInstance();
            Method m = ch.getDeclaredMethod("calc", int.class, int.class);
            int res = (Integer) m.invoke(calc, 2, 2);
            System.out.println(res);

            Method m3 = ch.getDeclaredMethod("calc3", int.class, int.class, int.class, int.class);
            int res3 = (Integer) m3.invoke(calc, 1, 1, 1, 1);
            System.out.println(res3);

            Method m4 = ch.getDeclaredMethod("calc4", int.class, int.class);
            boolean res4 = (boolean) m4.invoke(calc, 6, 6);
            System.out.println(res4);

            Method m5 = ch.getDeclaredMethod("calc5", int.class);
            String res5 = (String) m5.invoke(calc, 2);
            System.out.println(res5);

            Method m6 = ch.getDeclaredMethod("calc6", int.class);
            boolean res6 = (boolean) m6.invoke(calc, -6);
            System.out.println(res6);

            Method m7 = ch.getDeclaredMethod("calc7", String.class);
            String res7 = (String) m7.invoke(calc, "Ivan");
            System.out.println(res7);

            Method m8 = ch.getDeclaredMethod("calc8", int.class);
            String res8 = (String) m8.invoke(calc, 2020);
            System.out.println(res8);

            if (res == 4 && res3 == 2 && res4 && res5 == "positive" && res6 &&
                    res7.equalsIgnoreCase("Hello, Ivan") &&
                    res8 == "этот год високосный") {
                System.out.println(name + " Passed");
            } else {
                System.out.println(name + " Faild");
            }

        }
    }
}