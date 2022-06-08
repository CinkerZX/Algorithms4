import org.junit.Assert;
import org.junit.Test;

public class ThrowingTwoEggsfromaBuildingTest {
    @Test
    public void findF_NTest(){
        ThrowingTwoEggsfromaBuilding myThrow = new ThrowingTwoEggsfromaBuilding(7,10);
        Assert.assertEquals(7, myThrow.findF_N());

        ThrowingTwoEggsfromaBuilding myThrow2 = new ThrowingTwoEggsfromaBuilding(1,10);
        Assert.assertEquals(1, myThrow2.findF_N());

        ThrowingTwoEggsfromaBuilding myThrow3 = new ThrowingTwoEggsfromaBuilding(0,10);
        Assert.assertEquals(0, myThrow3.findF_N()); //0

        ThrowingTwoEggsfromaBuilding myThrow4 = new ThrowingTwoEggsfromaBuilding(9,10);
        Assert.assertEquals(9,myThrow4.findF_N()); //9
    }

    @Test
    public void findF_FTest(){
        ThrowingTwoEggsfromaBuilding myThrow = new ThrowingTwoEggsfromaBuilding(7,10);
        Assert.assertEquals(7, myThrow.findF_F());

        ThrowingTwoEggsfromaBuilding myThrow2 = new ThrowingTwoEggsfromaBuilding(1,10);
        Assert.assertEquals(1, myThrow2.findF_F());

        ThrowingTwoEggsfromaBuilding myThrow3 = new ThrowingTwoEggsfromaBuilding(0,10);
        Assert.assertEquals(0, myThrow3.findF_F()); //0

        ThrowingTwoEggsfromaBuilding myThrow4 = new ThrowingTwoEggsfromaBuilding(9,10);
        Assert.assertEquals(9,myThrow4.findF_F()); //9
    }
}
