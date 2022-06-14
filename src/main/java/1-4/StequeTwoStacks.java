import java.util.Stack;

/**
 * The {@CODE StequeTwoStacks} realize the functions of Staque by two stacks
 *
 * A stack-ended queue or steque is a data type that supports push, pop, and enqueue.
 *
 * public class Steque<Item> implements Iterable<Item>
 * --------------------------------------------------------------------
 *         Steque()            create an empty steque
 * boolean isEmpty()           is the steque empty?
 *     int size()              number of items in the steque
 *    void push(Item item)     add an item to the top / beginning
 *    void enqueue(Item item)  add an item to the bottom / end
 *    Item pop()               remove an item from the top / beginning
 *
 * Idea: for pushing, just push into stack1
 *       for enqueue, pop all items in stack1 to stack2, and push the object into stack2; then trans all from stack2 to stack1
 *       for pop, just pop stack1
 */
public class StequeTwoStacks<Item> implements StequeBasic<Item>{
    private Stack<Item> stack1;
    private Stack<Item> stack2;

    public StequeTwoStacks(){
        stack1 = new Stack<Item>();
        stack2 = new Stack<Item>();
    }

    @Override
    public boolean isEmpty() {
        return stack1.isEmpty();
    }

    @Override
    public int size() {
        return stack1.size();
    }

    @Override
    public void push(Item i) {
        stack1.push(i);
    }

    @Override
    public void enqueue(Item i) {
        stack2 = transStack1toStack2(stack1, stack2);
        stack2.push(i);
        stack1 = transStack1toStack2(stack2, stack1);
    }

    public static Stack transStack1toStack2(Stack s1, Stack s2){
        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }
        return s2;
    }

    @Override
    public Item pop() {
        return stack1.pop();
    }
}
