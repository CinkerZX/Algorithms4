import java.util.Stack;

/**
 * The {@CODE QueueTwoStacks} class realize basic queue operations by two stacks
 *
 * Add: clean stack1 to stack2, add into stack2, and then clean stack2 into stack1
 * Remove: remove the first element from stack1 (the one at the top of stack is the head of the queue
 * @param <Item>
 */

public class QueueTwoStacks<Item> implements QueueBasic<Item> {
    private Stack<Item> stack1 = new Stack<>();
    private Stack<Item> stack2 = new Stack<>();

    @Override
    public boolean add(Item newItem) {
        //TODO: insert element into this queue
        stack2 = pourStack1toStack2(stack1,stack2);
        stack2.push(newItem);
        stack1 = pourStack1toStack2(stack2,stack1);
        return true;
    }

    public Stack<Item> pourStack1toStack2(Stack<Item> s1, Stack<Item> s2){
        if (!s1.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2;
    }

    @Override
    public boolean offer(Item item) {
        //TODO: Inserts the specified element into this queue
        return add(item);
    }

    @Override
    public Item remove() {
        if (stack1.isEmpty()){return null;}
        else {return stack1.pop();}
    }

    @Override
    public Item poll() {
        //TODO: Retrieves and removes the head of this queue
        return remove();
    }

    @Override
    public Item element() {
        //TODO: retrieves, but doesn't remove, the head of this queue |tail ..... head|
        if (stack1.isEmpty()){return null;}
        else{return stack1.peek();}
    }

    @Override
    public Item peek() {
        //TODO: Retrieves, but does not remove, the head of this queue
        return element();
    }
}
