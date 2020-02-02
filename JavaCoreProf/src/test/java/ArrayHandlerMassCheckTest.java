import lesson_6.dz.ArrayHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayHandlerMassCheckTest {

    private final int[] a;
    private final boolean isTrue;
    private ArrayHandler arrayHandler;

    public ArrayHandlerMassCheckTest( int[] a, boolean isTrue ) {
        this.a = a;
        this.isTrue = isTrue;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 4}, true},
                {new int[]{1, 2, 3, 5, 6, 7, 8, 9, 0}, false},
                {new int[]{4, 2, 3, 5, 6, 7, 8, 9, 0}, false},
                {new int[]{2, 3, 5, 6, 7, 8, 9, 0}, false}
        });
    }

    @Before
    public void init() {
        arrayHandler = new ArrayHandler();
    }

    @Test
    public void workWithArrayMethodTest() {
        if (isTrue) Assert.assertTrue(arrayHandler.checkArray(a));
        else Assert.assertFalse(arrayHandler.checkArray(a));
    }
}
