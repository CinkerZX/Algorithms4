import org.junit.Assert;
import org.junit.Test;

public class QueueTwoStacksTest {
    @Test
    public void testSet(){
        QueueTwoStacks myqueue = new QueueTwoStacks();
        //addTest
        Assert.assertTrue(myqueue.add("a"));
        Assert.assertTrue(myqueue.add("a"));
        Assert.assertTrue(myqueue.add("b"));
        Assert.assertTrue(myqueue.add("1"));
        Assert.assertTrue(myqueue.add("0"));
        Assert.assertTrue(myqueue.add("2"));// 2 0 1 b a a

        //offerTest
        Assert.assertTrue(myqueue.offer("1")); //1 2 0 1 b a a

        //removeTest
        Assert.assertEquals("a", myqueue.remove()); //1 2 0 1 b a

        //pollTest
        Assert.assertEquals("a", myqueue.poll()); //1 2 0 1 b

        //elementTest
        Assert.assertEquals("b", myqueue.element()); //1 2 0 1 b

        //peekTest
        Assert.assertEquals("b", myqueue.peek()); //1 2 0 1 b

        //removeTest
        Assert.assertEquals("b", myqueue.remove()); //1 2 0 1

    }
}
