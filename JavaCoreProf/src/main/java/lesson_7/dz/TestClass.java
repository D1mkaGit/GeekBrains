package lesson_7.dz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
1 Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов с аннотациями @Test.
Для этого у него должен быть статический метод start(), которому в качестве параметра передается или объект типа Class,
или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется.
Далее запущены методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite.
К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет выбираться порядок их выполнения.
Если приоритет одинаковый, то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
иначе необходимо бросить RuntimeException при запуске «тестирования».
 */
public class TestClass {
    public static void main( String[] args ) throws InvocationTargetException, IllegalAccessException {
        start();
    }

    static void start() throws InvocationTargetException, IllegalAccessException {
        checkDuplicated();
        executeBeforeOnce();
        executeTest();
        executeAfterOnce();
    }

    private static void executeBeforeOnce() throws IllegalAccessException,
            InvocationTargetException {
        Class<BeforeSuite> annotationBeforeClass = BeforeSuite.class;
        Method[] methods = TestClass.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(annotationBeforeClass) != null) {
                method.invoke(null);
            }
        }
    }

    private static void executeTest() throws IllegalAccessException,
            InvocationTargetException {
        Class<Test> annotationClass = Test.class;
        Method[] methods = TestClass.class.getDeclaredMethods();
        int testsFounded = 0;
        for (Method method : methods) {
            if (method.getAnnotation(annotationClass) != null) {
                testsFounded++;
            }
        }
        if (testsFounded == 0) {
            throw new Error(new RuntimeException("No tests were annotated"));
        } else {
            Method[] filedTests = new Method[testsFounded];
            int sortedTestsIndex = 0;
            for (Method method : methods) {
                if (method.getAnnotation(annotationClass) != null) {
                    filedTests[sortedTestsIndex] = method;
                    sortedTestsIndex++;
                }
            }
            for (int i = 0; i < filedTests.length; i++) {
                int min = filedTests[i].getAnnotation(annotationClass).priotity();
                int min_i = i;
                for (int j = i + 1; j < filedTests.length; j++) {
                    if (filedTests[j].getAnnotation(annotationClass).priotity() < min) {
                        min = filedTests[j].getAnnotation(annotationClass).priotity();
                        min_i = j;
                    }
                }
                if (i != min_i) {
                    Method tmp = filedTests[i];
                    filedTests[i] = filedTests[min_i];
                    filedTests[min_i] = tmp;
                }


            }
            for (Method metod : filedTests) {
                metod.invoke(null);
            }
        }

    }

    // не нашел как передать параметром анатациюКласса, чтобы одним методом сделать, пришлось копипастить...
    private static void executeAfterOnce() throws IllegalAccessException,
            InvocationTargetException {
        Class<AfterSuite> annotationClass = AfterSuite.class;
        Method[] methods = TestClass.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(annotationClass) != null) {
                method.invoke(null);
            }
        }
    }

    private static void checkDuplicated() {
        Class<BeforeSuite> annotationBeforeClass = BeforeSuite.class;
        Class<AfterSuite> annotationAfterClass = AfterSuite.class;
        Method[] methods = TestClass.class.getDeclaredMethods();
        int c1 = 0;
        for (Method method : methods) {
            if (method.getAnnotation(annotationBeforeClass) != null) {
                if (c1 == 0) {
                    System.out.println(annotationBeforeClass.getSimpleName() + " is found in annotations");
                    c1++;
                } else {
                    throw new Error(new RuntimeException("Multiples " + annotationBeforeClass.getSimpleName() + " " +
                            "annotations in test are not allowed"));
                }
            }
        }
        // не нашел как передать параметром анатациюКласса, чтобы одним методом сделать, пришлось копипастить...
        int c2 = 0;
        for (Method method : methods) {
            if (method.getAnnotation(annotationAfterClass) != null) {
                if (c2 == 0) {
                    System.out.println(annotationAfterClass.getSimpleName() + " is found in annotations");
                    c2++;
                } else {
                    throw new Error(new RuntimeException("Multiples " + annotationAfterClass.getSimpleName() + " " +
                            "annotations in test are not allowed"));
                }
            }
        }
    }

    //***** Test goes here *****
    @BeforeSuite
    public static void before() {
        System.out.println("Make preparations before test");
    }

//    @BeforeSuite
//    public static void before2() {
//        System.out.println("Make preparations before test");
//    }

    @Test
    public static void test1() {
        System.out.println("Test1");

    }

    @Test(priotity = 1)
    public static void test2() {
        System.out.println("Test2");

    }

    @Test(priotity = 1)
    public static void test2_2() {
        System.out.println("Test2.2");

    }

    @Test(priotity = 3)
    public static void test3() {
        System.out.println("Test3");

    }

    @Test(priotity = 4)
    public static void test4() {
        System.out.println("Test4");

    }

    @Test(priotity = 4)
    public static void test4_4() {
        System.out.println("Test4.4");

    }

    @AfterSuite
    public static void after() {
        System.out.println("Make something after test");
    }

//    @AfterSuite
//    public static void after2() {
//        System.out.println("Make something after test");
//    }
}
