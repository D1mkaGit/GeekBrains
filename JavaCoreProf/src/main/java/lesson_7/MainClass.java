package lesson_7;

import lesson_7.Anon.MyAnon;

import java.lang.reflect.Method;

public class MainClass {
    public static void main( String[] args ) throws Exception {
//        Class c = Class.forName("java.lang.String");
//        System.out.println(c);


//        Class c = Cat.class;
//        Field[] fields = c.getDeclaredFields();
//
//        for (Field f: fields) {
//            System.out.println(f);
//        }

//        Cat cat = new Cat("Barsik", "red", 10);
//        cat.info();
//
//
//        Class c = Cat.class;
//        Field f = c.getDeclaredField("age");
//        f.setAccessible(true);
//        f.set(cat, 20);
//
//        Method m = c.getDeclaredMethod("info2");
//        m.setAccessible(true);
//        m.invoke(cat);
//
//        int mods = f.getModifiers();
//        System.out.println(mods);
//
//        if (Modifier.isPublic(mods)) {
//            System.out.println("public");
//        }
//
//        if (Modifier.isPrivate(mods)) {
//            System.out.println("private");
//        }
//
//        cat.info();


//        Class ch = URLClassLoader.newInstance(new URL[]{
//                new File("C:/1234").toURL()}).loadClass("Human");
//
//        Constructor constructor = ch.getConstructor(String.class);
//        Object human = constructor.newInstance("Bob");
//
//        Method m = ch.getDeclaredMethod("info");
//        m.invoke(human);

        Class c = Cat.class;
        Cat cat = new Cat("C", "A", 2);
        Method[] methods = c.getDeclaredMethods();
        for (Method o : methods) {
            if (o.isAnnotationPresent(MyAnon.class)) {
                System.out.println(o.getAnnotation(MyAnon.class).priotity());
                o.invoke(cat);
            }
        }
    }
}
