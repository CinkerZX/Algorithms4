import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

/**
 * The {@CODE DequeOneStackOneSteque} realize the functions of Deque by one stack and one Steque
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
 * @param <Item>
 */

public class DequeOneStackOneSteque<Item> implements DequeBasic<Item> {
    private Stack stack;
    private Steque steque; // save into steque

    public DequeOneStackOneSteque(){
        stack = new Stack();
        steque = new Steque(); // pop, push, enqueue, dequeue
    }
    @Override
    public void addFirst(Item item) {
        //TODO: Inserts the specified element at the front of this deque
        steque.addFirst(item);
    }

    @Override
    public void addLast(Item item) {
        //TODO: Inserts the specified element at the end of this deque
        steque.addLast(item);
    }

    @Override
    public boolean offerFirst(Item item) {
        //TODO: Inserts the specified element at the front of this deque
        addFirst(item);
        return true;
    }

    @Override
    public boolean offerLast(Item item) {
        //TODO: Inserts the specified element at the end of this deque
        addLast(item);
        return true;
    }

    @Override
    public Item removeFirst() {
        //TODO: Retrieves and removes the first element of this deque.
        steque.removeFirst();
        return null;
    }

    @Override
    public Item removeLast() {
        //TODO: Retrieves and removes the last element of this deque
        steque.removeLast();
        return null;
    }

    @Override
    public Item pollFirst() {
        //TODO: Retrieves and removes the first element of this deque.
        return removeFirst();
    }

    @Override
    public Item pollLast() {
        return removeLast();
    }

    @Override
    public Item getFirst() {
        //TODO: Retrieves, but does not remove, the first element of this deque.
        steque.getFirst();
        return null;
    }

    @Override
    public Item getLast() {
        //TODO: Retrieves, but does not remove, the last element of this deque.
        steque.getLast();
        return null;
    }

    @Override
    public Item peekFirst() {
        return getFirst();
    }

    @Override
    public Item peekLast() {
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        //TODO: Removes the first occurrence of the specified element from this deque.
        steque.removeFirstOccurrence(o);
        return true;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        //TODO: Removes the last occurrence of the specified element from this deque.
        steque.removeLastOccurrence(o);
        return true;
    }

    @Override
    public boolean add(Item item) {
        // TODO: inserts the specified element into the tail of this deque
        return offerLast(item);
    }

    @Override
    public boolean offer(Item item) {
        // TODO: inserts the specified element into the tail of this deque
        return offerLast(item);
    }

    @Override
    public Item remove() {
        //TODO: Retrieves and removes the first element of this deque.
        return removeFirst();
    }

    @Override
    public Item poll() {
        //TODO: Retrieves and removes the first element of this deque.
        return removeFirst();
    }

    @Override
    public Item element() {
        //TODO: Retrieves, but does not remove, the first element of this deque.
        return getFirst();
    }

    @Override
    public Item peek() {
        //TODO: Retrieves, but does not remove, the first element of this deque.
        return getFirst();
    }

    @Override
    public void push(Item item) {
        // TODO: Pushes an element onto the Head of this deque
        addFirst(item);
    }

    @Override
    public Item pop() {
        //TODO: removes and returns the first element of this deque.
        return pollFirst();
    }

    @Override
    public boolean remove(Object o) {
        //TODO: Removes the first occurrence of the specified element from this deque.
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean contains(Object o) {
        //TODO: Returns true if this deque contains the specified element.
        return steque.contains(o);
    }

    @Override
    public int size() {
        //TODO: Returns the number of elements in this deque.
        return steque.size();
    }

    @Override
    public Iterator<Item> iterator() {
        //TODO: Returns an iterator over the elements in this deque in proper sequence.
        //The elements will be returned in order from first (head) to last (tail).
        steque.iterator();
        return null;
    }

    @Override
    public Iterator<Item> descendingIterator() {
        //TODO: Returns an iterator over the elements in this deque in reverse sequential order.
        // The elements will be returned in order from last (tail) to first (head).
        steque.descendingIterator();
        return null;
    }
}
