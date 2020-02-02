import lesson_6.dz.ArrayHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

@RunWith(Parameterized.class)
public class ArrayHandlerMassCutTest {

    private final int[] a;
    private final int[] b;
    private ArrayHandler arrayHandler;

    public ArrayHandlerMassCutTest( int[] a, int[] b ) {
        this.a = a;
        this.b = b;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{5, 6, 7, 8, 9}},
                {new int[]{4, 1, 2, 3, 5, 6, 7, 8, 9}, new int[]{1, 2, 3, 5, 6, 7, 8, 9}},
                {new int[]{1, 2, 3, 5, 6, 7, 8, 9, 4}, new int[]{}},
                {new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{}},
                {new int[]{1, 2, 3, 4, 5, 6, 4, 7, 8}, new int[]{7, 8}},
                {new int[]{0, 9, 8, 7, 6, 3, 2, 4, 1}, new int[]{1}}
        });
    }

    @Before
    public void init() {
        arrayHandler = new ArrayHandler();
    }

    @Test
    public void inputArrContainsNumber4Test() {
        Assert.assertTrue(IntStream.of(a).anyMatch(x -> x == 4)); // входной массив содержит 4
    }

    @Test
    public void outputArrContainsNumber4Test() {
        Assert.assertFalse(IntStream.of(b).anyMatch(x -> x == 4)); // массив на выходе не содержит 4
    }

    @Test
    public void workWithArrayMethodTest() {
        Assert.assertArrayEquals(arrayHandler.cutArray(a), b); //
    }
}
