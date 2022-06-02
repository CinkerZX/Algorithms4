import org.junit.Assert;
import org.junit.Test;

public class BitonicSearchTest {
    @Test
    public void TestFindMaximum(){
        int[] a = new int[]{1,4,7,9,5,3,0};
        Assert.assertEquals(3,BitonicSearch.findMaximum(a));
    }

    @Test
    public void TestifExists(){
        int[] a = new int[]{1,4,7,9,5,3,0};
        Assert.assertTrue(BitonicSearch.ifExists(3,a));
        Assert.assertTrue(BitonicSearch.ifExists(9,a));
        Assert.assertFalse(BitonicSearch.ifExists(100,a));
        Assert.assertTrue(BitonicSearch.ifExists(0,a));
        Assert.assertTrue(BitonicSearch.ifExists(1,a));
    }
}
