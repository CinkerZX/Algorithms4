import org.junit.Assert;
import org.junit.Test;

public class PointsCollinearCheckTest {
    @Test
    public void findCollinearNTest(){
        Point2D p1 = new Point2D(1,1);
        Point2D p2 = new Point2D(1,2);
        Point2D p3 = new Point2D(1,3);
        Point2D p4 = new Point2D(2,2);
        Point2D p5 = new Point2D(3,3);

        Point2D[] pointSet0 = new Point2D[]{p1, p2, p5};
        Assert.assertEquals(0,PointsCollinearCheck.findCollinearN(pointSet0));

        Point2D[] pointSet1 = new Point2D[]{p1, p2, p3};
        Assert.assertEquals(1,PointsCollinearCheck.findCollinearN(pointSet1));

        Point2D[] pointSet2 = new Point2D[]{p1, p2, p3, p4, p5};
        Assert.assertEquals(2,PointsCollinearCheck.findCollinearN(pointSet2));
    }

    @Test
    public void ThreeSumbyCollinearTest(){
        int[] a = new int[]{-2, -1, 0, 1,2,3};
        Assert.assertEquals(3,PointsCollinearCheck.ThreeSumbyCollinear(a));

        int[] b = new int[]{-5, -1, 0, 1,2,3};
        Assert.assertEquals(2,PointsCollinearCheck.ThreeSumbyCollinear(b));

        int[] c = new int[]{-5, -1, 0, 1,2,3,5,6};
        Assert.assertEquals(4,PointsCollinearCheck.ThreeSumbyCollinear(c));
    }
}
