import org.junit.Assert;
import org.junit.Test;

public class DynamicGrowthTest {
    @Test
    public void DynamicTree(){
        DynamicGrowth tree = new DynamicGrowth();
        Assert.assertEquals(0, tree.newSite());
        Assert.assertEquals(0,tree.count());

        tree.union(0,5); // 5 1 2 3 4 5
        Assert.assertEquals(6, tree.newSite());
        Assert.assertEquals(5,tree.count());
        Assert.assertEquals(tree.find(0),tree.find(5));
        Assert.assertEquals(4,tree.find(4));

        tree.union(5,3); // 5 1 2 5 4 5
        Assert.assertEquals(4,tree.count());
        Assert.assertEquals(3,tree.getSize(3));
        Assert.assertEquals(5,tree.find(3));
    }
}
