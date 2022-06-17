import java.util.Iterator;
import java.util.Stack;

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
 * @param <Item>
 */

public class Deque3Stacks<Item> implements DequeBasic<Item>{
    private Stack<Item> stack1; // saved in stack1
    private Stack<Item> stack2;
    private Stack<Item> stack3;

    public Deque3Stacks(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        stack3 = new Stack<>();
    }

    @Override
    public void addFirst(Item item) {
        //TODO: Inserts the specified element at the front of this deque
        stack1.push(item);
    }

    @Override
    public void addLast(Item item) {
        //TODO: Inserts the specified element at the end of this deque
        //Idea: stack1 -> stack2, stack1.push, stack2 -> stack1
        stack2 = transferStack1toStack2(stack1,stack2);
        stack1.push(item);
        stack1 = transferStack1toStack2(stack2,stack1);
    }

    public static Stack transferStack1toStack2(Stack a, Stack b){
        while(!a.isEmpty()){
            b.push(a.pop());
        }
        return b;
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
        if (stack1.isEmpty()){return null;}
        return stack1.pop();
    }

    @Override
    public Item removeLast() {
        //TODO: Retrieves and removes the last element of this deque
        //Idea: stack1 -> stack2, stack2 pop, stack2 -> stack1
        Item result = null;
        stack2 = transferStack1toStack2(stack1, stack2);
        if (!stack2.isEmpty()){
            result = stack2.pop();
            stack1 = transferStack1toStack2(stack2,stack1);
        }
        return result;
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
        if (stack1.isEmpty()){return null;}
        return stack1.peek();
    }

    @Override
    public Item getLast() {
        //TODO: Retrieves, but does not remove, the last element of this deque.
        //Idea: stack1 -> stack2, stack2.peek, stack2 -> stack1
        Item result = null;
        stack2 = transferStack1toStack2(stack1,stack2);
        if (!stack2.isEmpty()){
            result = stack2.peek();
            stack1 = transferStack1toStack2(stack2, stack1);
        }
        return result;
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
        //Idea: stack1-> stack2, check if equals, when yes, stop pop out, stack2 -> stack1
        if(stack1.contains(o)){
            Item temp;
            while (!stack1.isEmpty()){
                temp = stack1.pop();
                stack2.push(temp);
                if (temp.equals(o)){
                    stack2.pop();
                    stack1 = transferStack1toStack2(stack2,stack1);
                    break;
                }
            } // or else, stack1 is empty => do nothing
        }
        return true;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        //TODO: Removes the last occurrence of the specified element from this deque.
        //Idea: stack1 -> stack2, stack2 -> stack3, check if equals, when yes, stop
        if (stack1.contains(o)){
            stack2 = transferStack1toStack2(stack1, stack2);
            Item temp;
            while (!stack2.isEmpty()){
                temp = stack2.pop();
                stack3.push(temp);
                if (temp.equals(o)){
                    stack3.pop();
                    stack2 = transferStack1toStack2(stack3,stack2);
                    break;
                }
            }
        }
        stack1 = transferStack1toStack2(stack2,stack1);
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
        return stack1.contains(o);
    }

    @Override
    public int size() {
        //TODO: Returns the number of elements in this deque.
        return stack1.size();
    }

    @Override
    public Iterator<Item> iterator() {
        //TODO: Returns an iterator over the elements in this deque in proper sequence.
        //The elements will be returned in order from first (head) to last (tail).
        Stack<Item> tempStack = new Stack<>();
        Stack<Item> tempStack1 = (Stack<Item>) stack1.clone();
        tempStack = transferStack1toStack2(tempStack1, tempStack);
        Iterator<Item> itr = tempStack.iterator();
        return itr;
    }

    @Override
    public Iterator<Item> descendingIterator() {
        //TODO: Returns an iterator over the elements in this deque in reverse sequential order.
        // The elements will be returned in order from last (tail) to first (head).
        Stack<Item> tempStack = (Stack<Item>) stack1.clone();
        Iterator<Item> itr = tempStack.iterator();
        return itr;
    }
}
