import org.junit.Assert;
import org.junit.Test;

public class WeightedQuickUnionbyHeightTest {
    @Test
    public void byHeightTest(){
        WeightedQuickUnionbyHeight myNet = new WeightedQuickUnionbyHeight(10);

        myNet.union(0,1);
        Assert.assertEquals(2,myNet.getSize()[0]);
        Assert.assertEquals(2,myNet.getSize()[1]);

        myNet.union(2,1);
        Assert.assertEquals(2,myNet.getSize()[1]);
        Assert.assertEquals(2,myNet.getSize()[2]);
        Assert.assertEquals(0,myNet.getParent()[2]);
        Assert.assertEquals(8,myNet.count);

        myNet.union(3,4);
        Assert.assertEquals(3,myNet.getParent()[4]);
        myNet.union(5,6);
        Assert.assertEquals(5,myNet.getParent()[6]);
        myNet.union(6,7);
        Assert.assertEquals(5,myNet.getParent()[7]);
        myNet.union(3,7);
        Assert.assertEquals(3,myNet.getParent()[3]);
        myNet.union(1,3);
        Assert.assertEquals(3,myNet.getParent()[0]);

        Assert.assertEquals(3,myNet.count);
        for (int i = 0; i < 7; i++) {
            Assert.assertEquals(3,myNet.getSize()[i]);
        }
    }
}
