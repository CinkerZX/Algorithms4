import org.junit.Assert;
import org.junit.Test;

public class WeightedQuickUnionUFwithPathCompressiontest {
    @Test
    public void WeightedQiickUnionTest() {
        WeightedQuickUnionUFwithPathCompression myUnion = new WeightedQuickUnionUFwithPathCompression(15);
        myUnion.union(0,1);
        Assert.assertEquals(0,myUnion.find(1));

        myUnion.union(0,2);
        Assert.assertEquals(0,myUnion.find(2));

        myUnion.union(6,7);
        Assert.assertEquals(6,myUnion.find(6));
        Assert.assertEquals(6,myUnion.find(7));

        myUnion.union(3,4);
        myUnion.union(3,5);
        myUnion.union(3,6);
        Assert.assertEquals(3,myUnion.find(6));
        Assert.assertEquals(3,myUnion.find(7));

        myUnion.union(0,8);
        myUnion.union(0,9);
        myUnion.union(0,10);
        myUnion.union(10,7);
        Assert.assertEquals(6,myUnion.getParent()[7]);
        Assert.assertEquals(3,myUnion.getParent()[6]);
        Assert.assertEquals(0,myUnion.getParent()[3]);
    }
}