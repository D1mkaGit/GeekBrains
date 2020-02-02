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
    public void arrayCutTest1() {
        testCutArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{5, 6, 7, 8, 9});
    }

    @Test
    public void arrayCutTest2() {
        testCutArray(new int[]{4, 1, 2, 3, 5, 6, 7, 8, 9}, new int[]{1, 2, 3, 5, 6, 7, 8, 9});
    }

    @Test
    public void arrayCutTest3() {
        testCutArray(new int[]{1, 2, 3, 5, 6, 7, 8, 9, 4}, new int[]{});
    }

    @Test
    public void arrayCutTest4() {
        testCutArray(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{});
    }

    @Test
    public void arrayCutTest5() {
        testCutArray(new int[]{1, 2, 3, 4, 5, 6, 4, 7, 8}, new int[]{7, 8});
    }

    @Test
    public void arrayCutTest6() {
        testCutArray(new int[]{0, 9, 8, 7, 6, 3, 2, 4, 1}, new int[]{1});
    }

    private void testCutArray( int[] arrInput, int[] arrOut ) {
        int[] testArr = arrayHandler.cutArray(arrInput);
        Assert.assertArrayEquals(testArr, arrOut);
    }

    @Test
    public void arrayCheckHaveBothNumbersTest() {
        testCheckArray(new int[]{1, 4}, true);
    }

    @Test
    public void arrayCheckHaveNo4Test() {
        testCheckArray(new int[]{1, 2, 3, 5, 6, 7, 8, 9, 0}, false);
    }

    @Test
    public void arrayCheckHaveNo1Test() {
        testCheckArray(new int[]{4, 2, 3, 5, 6, 7, 8, 9, 0}, false);
    }

    @Test
    public void arrayCheckHaveNo1and4Test() {
        testCheckArray(new int[]{2, 3, 5, 6, 7, 8, 9, 0}, false);
    }

    private void testCheckArray( int[] arr, boolean isTrue ) {
        if (isTrue) Assert.assertTrue(arrayHandler.checkArray(arr));
        else Assert.assertFalse(arrayHandler.checkArray(arr));
    }
}
