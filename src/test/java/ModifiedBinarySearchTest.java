import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModifiedBinarySearchTest {
    @Test
    public void TestIndexOf(){
        int a[] = new int[]{-2, 0, 0, 0, 0, 0, 0, 1, 1, 1, 3,5,5,5,5,5,5,5, 100};
        Assert.assertEquals(-1,ModifiedBinarySearch.smallestindexOf(a,99));
        Assert.assertEquals(0,ModifiedBinarySearch.smallestindexOf(a,-2));
        Assert.assertEquals(1,ModifiedBinarySearch.smallestindexOf(a,0));
        Assert.assertEquals(7,ModifiedBinarySearch.smallestindexOf(a,1));

        Assert.assertEquals(6,ModifiedBinarySearch.largestIndexOf(a,0));
        Assert.assertEquals(0,ModifiedBinarySearch.largestIndexOf(a,-2));
        Assert.assertEquals(9,ModifiedBinarySearch.largestIndexOf(a,1));
        Assert.assertEquals(17,ModifiedBinarySearch.largestIndexOf(a,5));

    }
}
