import org.junit.Assert;
import org.junit.Test;

public class StackOneQueueTest {
    @Test
    public void testSet(){
        StackOneQueue myStack = new StackOneQueue();
        //addEmpty
        Assert.assertTrue(myStack.empty());

        //TestPush
        Assert.assertEquals("a", myStack.push("a"));
        Assert.assertEquals("a", myStack.push("a"));
        Assert.assertEquals("b", myStack.push("b"));
        Assert.assertEquals("1", myStack.push("1"));
        Assert.assertEquals("0", myStack.push("0"));
        Assert.assertEquals("2", myStack.push("2"));// 2 0 1 b a a

        //TestEmpty
        Assert.assertFalse(myStack.empty());

        //TestPeek
        Assert.assertEquals("2",myStack.peek()); // 2 0 1 b a a

        //TestSearch
        Assert.assertEquals(1,myStack.search("2"));

        //TestPeek
        Assert.assertEquals("2",myStack.peek()); // 2 0 1 b a a

        //TestPop
        Assert.assertEquals("2", myStack.pop()); // 0 1 b a a

        //TestSearch
        Assert.assertEquals(0,myStack.search("2"));

        //TestPop
        Assert.assertEquals("0", myStack.pop()); // 1 b a a

    }

}
