import lesson_6.dz.ArrayHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ArrayHandlerTest {

    private ArrayHandler arrayHandler;

    @Before
    public void init() {
        arrayHandler = new ArrayHandler();
    }

    @Test
    public void arrayTest1() {
        testArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{5, 6, 7, 8, 9});
    }

    @Test
    public void arrayTest2() {
        testArray(new int[]{4, 1, 2, 3, 5, 6, 7, 8, 9}, new int[]{1, 2, 3, 5, 6, 7, 8, 9});
    }

    @Test
    public void arrayTest3() {
        testArray(new int[]{1, 2, 3, 5, 6, 7, 8, 9, 4}, new int[]{});
    }

    @Test
    public void arrayTest4() {
        testArray(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{});
    }

    @Test
    public void arrayTest5() {
        testArray(new int[]{1, 2, 3, 4, 5, 6, 4, 7, 8}, new int[]{7, 8});
    }

    @Test
    public void arrayTest6() {
        testArray(new int[]{0, 9, 8, 7, 6, 3, 2, 4, 1}, new int[]{1});
    }

    private void testArray( int[] arrInput, int[] arrOut ) {
        int[] testArr = arrayHandler.workWithArray(arrInput);
        Assert.assertArrayEquals(testArr, arrOut);
    }
}
