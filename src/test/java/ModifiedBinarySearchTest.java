import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModifiedBinarySearchTest {
    @Test
    public void TestIndexOf(){
        int a[] = new int[]{-2, 0, 0, 0, 0, 0, 0, 1, 1, 3, 5, 100};
        Assert.assertEquals(-1,ModifiedBinarySearch.indexOf(a,99));
        Assert.assertEquals(0,ModifiedBinarySearch.indexOf(a,-2));
        Assert.assertEquals(1,ModifiedBinarySearch.indexOf(a,0));
        Assert.assertEquals(7,ModifiedBinarySearch.indexOf(a,1));
    }
}
