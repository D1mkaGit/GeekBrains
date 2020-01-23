import lesson_6.Calculator;
import org.junit.*;

public class CalcTest {
    Calculator calc;

    @Before
    public void init() {
        System.out.println("init");
        calc = new Calculator();
    }

    @Test
    public void testAdd1() {
        Assert.assertEquals(4, calc.add(2, 2));
    }

    @Test
    public void testAdd2() {
        Assert.assertEquals(5, calc.add(2, 2));
    }

    @Test
    public void testAdd3() {
        Assert.assertEquals(6, calc.add(3, 3));
    }

    @Test
    public void testAdd4() {
        Assert.assertEquals(8, calc.add(4, 4));
    }

    @Test(expected = ArithmeticException.class, timeout = 10000)
    @Ignore(value = "тест работает верно, проверено 22/21/20")
    public void testDiv() {
        Assert.assertEquals(10, calc.div(10, 0));
    }

    @After
    public void shutdown() {
        System.out.println("end");
    }
}
