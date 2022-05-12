import edu.princeton.cs.algs4.Stack;

import java.util.Deque;
import java.util.Queue;

public class ThreeStackOneQueue<Item> extends Stack<Item> {
    // realize the functions of queue by 3 stacks
    java.util.Stack tempor;
    java.util.Stack stackQueue;

    public ThreeStackOneQueue(){
        tempor = new java.util.Stack<Item>();
        stackQueue = new java.util.Stack();
    }

    /**
     * insert the specified element into the queue
     * @param e
     * @return
     */
    public boolean add(Item e){
        stackQueue.push(e);
        return true;
    }

    public boolean offer(Item e){
        return add(e);
    }

    public java.util.Stack emptyStackFillStack(java.util.Stack a, java.util.Stack b){
        java.util.Stack copya = (java.util.Stack) a.clone(); // trap: the a and b cannot be the object of "temper" or "stackqueue"
        while(!copya.isEmpty()){
            b.push((Item)copya.pop());
        }
        return b;
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
        if (stackQueue.isEmpty()){
            return null;
        }
        Item result;
        tempor = emptyStackFillStack(stackQueue,emptyStack());
        result = (Item) tempor.peek();
        tempor = emptyStack();
        return result;
    }

    public java.util.Stack emptyStack(){
        return new java.util.Stack();
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
        if (stackQueue.isEmpty()){
            return null;
        }
        Item result;
        tempor = emptyStackFillStack(stackQueue,emptyStack());
        result = (Item) tempor.pop();
        stackQueue = emptyStackFillStack(tempor,emptyStack());
        return result;
    }
}
