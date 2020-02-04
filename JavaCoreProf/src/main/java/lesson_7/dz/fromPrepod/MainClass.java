package lesson_7.dz.fromPrepod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by EVDOKIMOVAL on 30.08.2019.
 */
public class MainClass {
    public static void main( String[] args ) throws InvocationTargetException, IllegalAccessException {
        processing(TestClass.class);
    }

    public static void processing( Class c ) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = c.getDeclaredMethods();
        List<Method> list = new ArrayList<Method>();
        // проходим по всем методам и добавлеям в список методы с анотацией
        for (Method o : methods) {
            if (o.isAnnotationPresent(Test.class)) {
                //если приоритет меньше одного или больше 10, не надо так!
                int prio = o.getAnnotation(Test.class).priority();
                if (prio < 1 || prio > 10) throw new RuntimeException("Priority exception!");
                list.add(o);
            }
        }
        // далее нужно их отсортировать методы по приоритету
        list.sort(new Comparator<Method>() {
            @Override
            public int compare( Method o1, Method o2 ) {
                // вычитаем один приоритет из другого
                // все методы отсортируются в убывающем порядке
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });
        // далее делаем вторую пробежку по методам и смотрим на анотации BeforeSuite
        // и добавляем их на нулевую позицию, чтобы они были первыми в списке
        for (Method o : methods) {
            if (o.isAnnotationPresent(BeforeSuite.class)) {
                // при условии что на первом месте уже не стоит данный класс
                // так как BeforeSuite должен быть на первом месте
                if (list.get(0).isAnnotationPresent(BeforeSuite.class))
                    throw new RuntimeException("BeforeSuite exception");
                list.add(0, o);
            }
            // аналогично делаем для AfterSuite на последнем месте
            if (o.isAnnotationPresent(AfterSuite.class)) {
                if (list.get(list.size() - 1).isAnnotationPresent(AfterSuite.class))
                    throw new RuntimeException("AfterSuite exception");
                list.add(o);
            }
        }
        // вызываем каждый объект из списка
        for (Method o : list) {
            o.invoke(null);
        }
    }
}