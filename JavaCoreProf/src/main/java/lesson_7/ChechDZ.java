package lesson_7;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ChechDZ {
    public static void main( String[] args ) throws Exception {
        File file = new File("C:/123");
        String[] str = file.list();

        for (String o : str) {

            String[] mass = o.split("\\.");
            if (!mass[1].equalsIgnoreCase("class")) {
                throw new RuntimeException(o, new Exception());
            }

            Class ch = URLClassLoader.newInstance(new URL[]{file.toURL()}).loadClass(mass[0]);
            Constructor constructor = ch.getConstructor(String.class);

            Object test1 = constructor.newInstance("String");
            Method m = ch.getDeclaredMethod("info");
            m.invoke(test1);
        }
    }
}
