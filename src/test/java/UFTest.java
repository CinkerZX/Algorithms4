import org.junit.Assert;
import org.junit.Test;

public class UFTest {
    @Test
    public void QuickFindTest(){
        // * void union(int p, int q)          add connection between p and q (p and q are two nodes)
        // * int traceRoot(int p)              return the root of node p
        // * int find(int p)                   component indentifier for p (result is in [0, N-1]. N is the size of the network)
        // * boolean connected(int p, int q)   return true if p and q are in the component
        // * int count();                      number of components
        QuickFindUF mynet = new QuickFindUF(10); // 0 1 2 3 4 5 6 7 8 9
        mynet.union(0,1); // 1 1 2 3 4 5 6 7 8 9
        Assert.assertEquals(1, mynet.find(0));
        Assert.assertEquals(1, mynet.find(1));

        Assert.assertFalse(mynet.connected(0,2));
        mynet.union(1,2);
        Assert.assertTrue(mynet.connected(0,2));

        Assert.assertEquals(8,mynet.count());
    }

    @Test
    public void QuickUnionTest(){
        QuickUnionUF myweb = new QuickUnionUF(10); // 0 1 2 3 4 5 6 7 8 9
        myweb.union(0,1); // 1 1 2 3 4 5 6 7 8 9
        Assert.assertEquals(9,myweb.count());

        myweb.union(3,4); // 1 1 2 4 4 5 6 7 8 9
        Assert.assertEquals(8,myweb.count());

        Assert.assertFalse(myweb.connected(1,4));

        myweb.union(0,4); // 1 4 2 4 4 5 6 7 8 9
        Assert.assertEquals(7,myweb.count());
        Assert.assertTrue(myweb.connected(1,4));

    }
}
