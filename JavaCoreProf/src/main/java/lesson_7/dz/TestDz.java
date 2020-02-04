package lesson_7.dz;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class TestDz {
    public static void main( String[] args ) throws Exception {
        File file = new File("src/main/java/lesson_7/dz/file");
        String[] str = file.list();

        assert str != null;
        for (String o : str) {
            String[] mass = o.split("\\.");
            if (!mass[1].equalsIgnoreCase("class")) {
                throw new RuntimeException(o, new Exception());
            }

            Class ch = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()}).loadClass(mass[0]);
//            Constructor[] constructors = ch.getConstructors();
//            for (Constructor c : constructors) {
//                System.out.println(c);
//            }
//
//            System.out.println("---");
//
//            Method[] methods = ch.getDeclaredMethods();
//            for (Method m : methods) {
//                System.out.println(m.getReturnType() + " ||| " + m.getName() + " ||| " + Arrays.toString(m.getParameterTypes()));
//                //m.invoke(ch.getDeclaredMethod("main"));
//            }

            //  test1 - запускаем main c дефолтовыми полями
            Method m1 = ch.getDeclaredMethod("main", String[].class);
            m1.invoke(null, (Object) null);

            System.out.println("\nДальше идет через рефлексию\n");

            //  test2 - запускаем calculation c интами
            Method m2 = ch.getDeclaredMethod("calculate", int.class, int.class, int.class, int.class);
            m2.setAccessible(true);
            System.out.println("Reflected Calculation with ints gives us: " + m2.invoke(null, 1, 1, 1,
                    1));

            // test3 - запускаем calculation c флоатами
            Method m3 = ch.getDeclaredMethod("calculate", float.class, float.class, float.class, float.class);
            m3.setAccessible(true);
            System.out.println("Reflected Calculation with floats gives us: " + m3.invoke(null, 1f, 1f
                    , 1f, 1f));

            // test4 - запускаем checkTwoNumbers (positive and negative)
            Method m4 = ch.getDeclaredMethod("checkTwoNumbers", int.class, int.class);
            m4.setAccessible(true);
            System.out.println("Reflected checkTwoNumbers - Does the sum of two given numbers fit the range (from 10 " +
                    "till 20)? : " + m4.invoke(null, 1, 15));
            System.out.println("Reflected checkTwoNumbers - Does the sum of two given numbers fit the range (from 10 " +
                    "till 20)? : " + m4.invoke(null, 15, 15));

            // test5 - запускаем printIsPositive (positive and negative)
            Method m5 = ch.getDeclaredMethod("printIsPositive", int.class);
            m5.setAccessible(true);
            System.out.print("Positive: ");
            m5.invoke(null, 1);
            System.out.print("Negative: ");
            m5.invoke(null, -1);

            // test6 - запускаем isNegative (positive and negative)
            Method m6 = ch.getDeclaredMethod("isNegative", int.class);
            m6.setAccessible(true);
            System.out.print("If number is negative there should be true: ");
            System.out.println(m6.invoke(null, -1));
            System.out.print("If number is positive there should be false: ");
            System.out.println(m6.invoke(null, 1));

            // test7 - запускаем printWelocome (с именем)
            Method m7 = ch.getDeclaredMethod("printWelocome", String.class);
            m7.setAccessible(true);
            m7.invoke(null, "Тест");

            //test8 - запускаем isLeapYear с високосным и не високосным годлом
            Method m8 = ch.getDeclaredMethod("isLeapYear", int.class);
            m8.setAccessible(true);
            int leapYear = 2020;
            int notLeapYear = 2019;
            System.out.println("Reflected checkTwoNumbers - Is the " + notLeapYear + " year leap? " + m8.invoke(null, notLeapYear));
            System.out.println("Reflected checkTwoNumbers - Is the " + leapYear + " year leap? " + m8.invoke(null, leapYear));
        }
    }
}
