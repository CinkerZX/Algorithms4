import org.junit.Assert;
import org.junit.Test;

public class QuickUnionUFPathCompressionTest {
    @Test
    public void TestUnion(){
        QuickUnionUFwithPathCompression myQuickUnion = new QuickUnionUFwithPathCompression(7);
        myQuickUnion.union(1,2);
        myQuickUnion.union(3,4);
        myQuickUnion.union(5,6);
        myQuickUnion.union(2,3);
        myQuickUnion.union(4,6);
        Assert.assertEquals(2,myQuickUnion.pa[1]);
        Assert.assertEquals(4,myQuickUnion.pa[2]);
        Assert.assertEquals(6,myQuickUnion.pa[4]);
        Assert.assertEquals(6,myQuickUnion.pa[6]);
    }
}
