import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * The {@CODE Deque3Stacks} realize the functions of Deque by one stack and one Steque
 *
 * --------------------------------------------------------------------
 * boolean addFirst(Item item)/push(Item item)    Inserts the specified element at the front of this deque
 * boolean addLast(Item item)                     Inserts the specified element at the end of this deque
 * boolean offerFirst(Item item)                  Inserts the specified element at the front of this deque
 * boolean offerLast(Item item)                   Inserts the specified element at the end of this deque
 * Item removeFirst() Item pollFirst() pop()      Retrieves and removes the first element of this deque
 * Item removeLast() Item pollLast()              Retrieves and removes the last element of this deque
 * Item getFirst()/element()/peek()               Retrieves, but does not remove, the first element of this deque
 * Item getLast()                                 Retrieves, but does not remove, the last element of this deque.
 * boolean removeFirstOccurrence(Object o)        Removes the first occurrence of the specified element from this deque
 * boolean remove(Object o)                       Removes the first occurrence of the specified element from this deque
 * boolean add(Item item)         Inserts the specified element into the tail of this deque
 * boolean offer(Item item)       Inserts the specified element into the tail of this deque
 * Item remove() / poll()         Retrieves and removes the first element of this deque
 * boolean contains(Object o)     Returns true if this deque contains the specified element.
 * int size()                     Returns the number of elements in this deque
 * Iterator<Item> iterator()      Returns an iterator over the elements in this deque in proper sequence
 *                                The elements will be returned in order from first (head) to last (tail)
 * descendingIterator()           Returns an iterator over the elements in this deque in reverse sequential order.
 *                                The elements will be returned in order from last (tail) to first (head).
 *
 * Seems doesn't need the Stack to realize these functions
 *
 * @param
 */

public class Deque3StacksTest {
    @Test
    public void testSet(){
        Deque3Stacks myDeque = new Deque3Stacks();

        //Test size
        Assert.assertEquals(0,myDeque.size());

        //Test addFirst
        myDeque.addFirst("a"); // a
        myDeque.addFirst("0"); // a 0
        myDeque.addFirst("b"); // a 0 b
        Assert.assertEquals(3,myDeque.size());

        //Test RemoveFirst
        Assert.assertEquals("b", myDeque.removeFirst()); // a 0

        //Test RemoveLast
        Assert.assertEquals("a", myDeque.removeLast()); // 0

        //Test addLast
        myDeque.addLast("woo"); // woo 0
        myDeque.addFirst("hoo"); // woo 0 hoo
        Assert.assertEquals("woo", myDeque.removeLast()); // 0 hoo

        //Test getFirst
        Assert.assertEquals("hoo", myDeque.getFirst()); // 0 hoo
        Assert.assertEquals("hoo", myDeque.getFirst()); // 0 hoo

        //Test getLast
        Assert.assertEquals("0", myDeque.getLast()); // 0 hoo
        Assert.assertEquals("0", myDeque.getLast()); // 0 hoo

        // Test removeFirstOccurrence(Object o)
        myDeque.addFirst("xin"); // 0 hoo xin
        myDeque.addFirst("xinxin"); // 0 hoo xin xinxin
        myDeque.addLast("nothing"); // nothing 0 hoo xin xinxin
        myDeque.addLast("xin"); // xin nothing 0 hoo xin xinxin
        myDeque.addFirst("123"); // xin nothing 0 hoo xin xinxin 123
        myDeque.removeFirstOccurrence("xin"); // xin nothing 0 hoo xinxin 123
        Assert.assertEquals("123", myDeque.removeFirst()); // xin nothing 0 hoo xinxin
        Assert.assertEquals("xinxin", myDeque.removeFirst()); // xin nothing 0 hoo
        Assert.assertEquals("hoo", myDeque.peek()); // xin nothing 0 hoo

        //Test add & offer
        myDeque.add("xin"); // xin xin nothing 0 hoo
        myDeque.offer("gaga"); // gaga xin xin nothing 0 hoo

        //Test removeLastOccurance
        Assert.assertEquals("gaga", myDeque.getLast());
        myDeque.removeLast(); // xin xin nothing 0 hoo
        myDeque.removeLastOccurrence("xin"); // xin nothing 0 hoo
        Assert.assertEquals("xin", myDeque.getLast()); // xin nothing 0 hoo

        //Test contains
        Assert.assertTrue(myDeque.contains("xin")); // xin nothing 0 hoo
        Assert.assertFalse(myDeque.contains("Xin")); // xin nothing 0 hoo

        //Test iterator
        Iterator iter = myDeque.iterator();
        Assert.assertTrue(iter.hasNext()); // xin nothing 0 hoo
        Assert.assertEquals("hoo", iter.next());
        Assert.assertTrue(iter.hasNext()); // xin nothing 0 hoo
        Assert.assertEquals("0", iter.next());
        Assert.assertTrue(iter.hasNext()); // xin nothing 0 hoo
        Assert.assertEquals("nothing", iter.next());
        Assert.assertTrue(iter.hasNext()); // xin nothing 0 hoo
        Assert.assertEquals("xin", iter.next());
        Assert.assertFalse(iter.hasNext());

        //Test descendingIterator
        Iterator iter2 = myDeque.descendingIterator();
        Assert.assertTrue(iter2.hasNext()); // xin nothing 0 hoo
        Assert.assertEquals("xin", iter2.next());
        Assert.assertTrue(iter2.hasNext()); // xin nothing 0 hoo
        Assert.assertEquals("nothing", iter2.next());
        Assert.assertTrue(iter2.hasNext()); // xin nothing 0 hoo
        Assert.assertEquals("0", iter2.next());
        Assert.assertTrue(iter2.hasNext()); // xin nothing 0 hoo
        Assert.assertEquals("hoo", iter2.next());
        Assert.assertFalse(iter2.hasNext());
    }


}
