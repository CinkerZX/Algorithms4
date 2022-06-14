import org.junit.Assert;
import org.junit.Test;

public class StequeTwoStacksTest {
    @Test
    public void testSet() {
        StequeTwoStacks mySteque = new StequeTwoStacks();
        //addEmpty
        Assert.assertTrue(mySteque.isEmpty());

        //TestPush
        mySteque.push("a"); // a
        mySteque.push("a"); // a a
        mySteque.push("b"); // b a a
        mySteque.push("1"); // 1 b a a
        mySteque.push("0"); // 0 1 b a a
        mySteque.push("2"); // 2 0 1 b a a

        //TestEmpty
        Assert.assertFalse(mySteque.isEmpty());

        //Testpop
        Assert.assertEquals("2", mySteque.pop()); // 2 0 1 b a a

        //Testpop
        Assert.assertEquals("0", mySteque.pop()); // 0 1 b a a

        //Testpop
        Assert.assertEquals("1", mySteque.pop()); // 1 b a a

        //Testenqueue
        mySteque.enqueue("x"); // b a a x

        //Testpop
        Assert.assertEquals("b",mySteque.pop()); // b a a x

        //Testpop
        Assert.assertEquals("a",mySteque.pop()); // a a x

        //Testpop
        Assert.assertEquals("a",mySteque.pop()); // a x

        //Testpop
        Assert.assertEquals("x",mySteque.pop()); // x
    }

}









