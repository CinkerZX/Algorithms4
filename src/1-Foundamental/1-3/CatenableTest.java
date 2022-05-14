import org.junit.Assert;
import org.junit.Test;

public class CatenableTest {
    @Test
    public void CatenableQueueTest(){  // add/offer, remove/poll, element/pool
        CatenableQueue myQueue1 = new CatenableQueue();
        CatenableQueue myQueue2 = new CatenableQueue();

        myQueue1.add("0");
        myQueue1.add("1");
        myQueue1.offer("2"); // 2->1->0
        Assert.assertEquals("0", myQueue1.remove()); // 2->1
        Assert.assertEquals("1", myQueue1.poll()); // 2
        Assert.assertEquals("2", myQueue1.poll()); //
        Assert.assertEquals(null,myQueue1.remove());

        myQueue1.add("0");
        myQueue1.add("1");
        myQueue1.add("2");

        myQueue2.offer("a"); //a
        Assert.assertEquals("a",myQueue2.peek());
        myQueue2.offer("b"); //b->a
        Assert.assertEquals("a",myQueue2.element());
        myQueue2.offer("c");

        CatenableQueue combinedQueue = myQueue1.caten(myQueue2); // 2->1->0->c->b->a
        Assert.assertEquals("a", combinedQueue.remove()); // 2->1->0->c->b
        Assert.assertEquals("b", combinedQueue.remove()); // 2->1->0->c
        Assert.assertEquals("c", combinedQueue.remove()); // 2->1->0
        Assert.assertEquals("0", combinedQueue.remove()); // 2->1
    }

    @Test
    public void CatenableStackTest(){ // peek, pop, push, search
        CatenableStack myStack1 = new CatenableStack();
        CatenableStack myStack2 = new CatenableStack();

        Assert.assertEquals(null, myStack1.peek());
        Assert.assertEquals(-1, myStack1.search("2"));
        myStack1.push("0"); // 0
        Assert.assertEquals("0", myStack1.peek());
        myStack1.push("1"); // 1->0
        Assert.assertEquals("1", myStack1.peek());
        Assert.assertEquals(-1, myStack1.search("2"));
        myStack1.push("2"); // 2->1->0
        Assert.assertEquals("2", myStack1.peek());
        Assert.assertEquals(1, myStack1.search("2"));
        myStack1.push("3"); // 3->2->1->0
        Assert.assertEquals("3", myStack1.peek());
        Assert.assertEquals(2, myStack1.search("2"));

        myStack1.pop(); // 2->1->0
        Assert.assertEquals("2", myStack1.peek());

        myStack2.push("a");
        myStack2.push("b");
        myStack2.push("c"); // c->b->a

        CatenableStack combineStack = myStack1.caten(myStack2); // 2->1->0->c->b->a
        Assert.assertEquals("2", combineStack.pop()); // 2->1->0->c->b
        Assert.assertEquals("1", combineStack.pop()); // 2->1->0->c
        Assert.assertEquals("0", combineStack.pop()); // 2->1->0
        Assert.assertEquals("c", combineStack.pop()); // 2->1
    }

    @Test
    public void CatenableStequeTest(){ //pop, push, enqueue
        CatenableSteque mySteque1 = new CatenableSteque();
        CatenableSteque mySteque2 = new CatenableSteque();

        Assert.assertEquals(null, mySteque1.pop());
        mySteque1.push("1");
        mySteque1.push("2"); // 2->1
        Assert.assertEquals("2",mySteque1.pop()); //1
        mySteque1.push("2"); // 3->2->1
        mySteque1.push("3"); // 3->2->1
        Assert.assertEquals("3",mySteque1.pop()); //2->1

        mySteque1.enqueue("0"); // 2->1->0
        Assert.assertEquals("2",mySteque1.pop()); //1->0
        Assert.assertEquals("1",mySteque1.pop()); //0
        Assert.assertEquals("0",mySteque1.pop());

        mySteque1.enqueue("2");
        mySteque1.enqueue("1");
        mySteque1.enqueue("0"); // 2->1->0
        Assert.assertEquals("2",mySteque1.pop()); // 1->0
        mySteque1.push("2"); // 2->1->0

        mySteque2.push("b"); // b
        mySteque2.push("c"); // c->b
        mySteque2.enqueue("a"); // c->b->a

        CatenableSteque combinedSteque = mySteque1.caten(mySteque2); // 2->1->0->c->b->a
        Assert.assertEquals("2",combinedSteque.pop()); // 1->0->c->b->a
        Assert.assertEquals("1",combinedSteque.pop()); // 0->c->b->a
        Assert.assertEquals("0",combinedSteque.pop()); // c->b->a
        Assert.assertEquals("c",combinedSteque.pop()); // b->a
    }
}
