import org.junit.Assert;
import org.junit.Test;

public class ThreeStackOneQueueTest {
    @Test
    public void TestThreeStackOneQueue(){
        ThreeStackOneQueue myQueue = new ThreeStackOneQueue();

        Assert.assertEquals(null,myQueue.peek()); // peek
        Assert.assertEquals(null,myQueue.element()); // element

        myQueue.add(0);  // add
        Assert.assertEquals(0,myQueue.peek()); // peek
        Assert.assertEquals(0,myQueue.element()); // element

        myQueue.add(1);  // add
        Assert.assertEquals(0,myQueue.peek()); // peek
        Assert.assertEquals(0,myQueue.element()); // element

        myQueue.offer(2); // offer
        Assert.assertEquals(0,myQueue.peek()); // peek
        Assert.assertEquals(0,myQueue.element()); // element

        Assert.assertEquals(0,myQueue.poll()); // poll
        Assert.assertEquals(1,myQueue.remove()); // remove

        myQueue.add(3);  // add
        myQueue.offer(4); // offer
        Assert.assertEquals(2,myQueue.peek()); // peek
        Assert.assertEquals(2,myQueue.element()); // element
        Assert.assertEquals(2,myQueue.poll()); // poll
        Assert.assertEquals(3,myQueue.remove()); // remove
        Assert.assertEquals(4,myQueue.remove()); // remove

        Assert.assertEquals(null,myQueue.element()); // element
        Assert.assertEquals(null,myQueue.element()); // element

        Assert.assertEquals(null,myQueue.remove()); // element
        Assert.assertEquals(null,myQueue.poll()); // element
    }

}
