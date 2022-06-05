import org.junit.Assert;
import org.junit.Test;

public class BinarySearchwithAddSubtractTest {
    @Test
    public void TestifContain(){
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,10,11};
        BinarySearchwithAddSubtract myBS = new BinarySearchwithAddSubtract(a);
        Assert.assertTrue(myBS.ifContain(1));
        myBS.ifContain(11);
        Assert.assertTrue(myBS.ifContain(11));
        Assert.assertTrue(myBS.ifContain(8));
        Assert.assertFalse(myBS.ifContain(0));
    }
}
