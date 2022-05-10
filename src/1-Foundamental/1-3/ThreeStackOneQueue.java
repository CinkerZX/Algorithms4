import edu.princeton.cs.algs4.Stack;

public class ThreeStackOneQueue<Item> extends Stack<Item> {
    // realize the functions of queue by 3 stacks
    Stack collector;
    Stack tempor;
    Stack stackQueue;

    public ThreeStackOneQueue(){
        collector = new Stack();
        tempor = new Stack();
        stackQueue = new Stack();
    }

    /**
     * insert the specified element into the queue
     * @param e
     * @return
     */
    public boolean add(Item e){
        collector.push(e);
        tempor = emptyStackFillStack(collector,stackQueue);
        collector.pop();
        return true;
    }

    public boolean offer(Item e){
        return add(e);
    }

    public Stack emptyStackFillStack(Stack needEmpty, Stack needFill){
        while(!needEmpty.isEmpty()){
            needFill.push(needEmpty.pop());
        }
        return needFill;
    }

    /**
     * return the head of the queue, but don't remove
     * @return
     */
    public Item element(){
        return peek();
    }

    /**
     * return the head of the queue, but don't remove
     * @return
     */
    public Item peek(){
        Item result;
        tempor = emptyStackFillStack(stackQueue,tempor);
        result = (Item) tempor.peek();
        tempor = emptyStack();
        return result;
    }

    public Stack emptyStack(){
        return new Stack();
    }

    /**
     * return the head of the queue, and remove
     * @return
     */
    public Item poll(){
        return remove();
    }

    /**
     * return the head of the queue, and remove
     * @return
     */
    public Item remove(){
        Item result;
        tempor = emptyStackFillStack(stackQueue,tempor);
        result = (Item) tempor.pop();
        collector = emptyStackFillStack(tempor,collector);
        stackQueue = collector;
        collector = emptyStack();
        return result;
    }
}
