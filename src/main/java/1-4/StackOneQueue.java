import edu.princeton.cs.algs4.Queue;

import java.util.Stack;

/**
 * The {@CODE StackOneQueue} realized the operations of stack by Queue
 *
 * Idea: add is the same
 *       delete: remove and add until the last element
 *
 * Note: very time inefficient
 */
public class StackOneQueue<Item> implements StackBaisc<Item>{
    private edu.princeton.cs.algs4.Queue<Item> myStack;

    public StackOneQueue(){
        myStack = new Queue<>();
    }

    @Override
    public boolean empty() {
        return myStack.isEmpty();
    }

    @Override
    public Item peek() {
        //TODO: Look at the object at the top of the stack without moving
        if (myStack.isEmpty()){return null;}
        int n = myStack.size();
        for (int i = 0; i < n-1; i++) {
            myStack.enqueue(myStack.dequeue());
        }
        Item result = myStack.dequeue();
        myStack.enqueue(result);
        return result;
    }

    @Override
    public Item pop() {
        //TODO: Remove the object at the top
        if (myStack.isEmpty()){return null;}
        int n = myStack.size();
        for (int i = 0; i < n-1; i++) {
            myStack.enqueue(myStack.dequeue());
        }
        return myStack.dequeue();
    }

    @Override
    public Item push(Item e) {
        myStack.enqueue(e);
        return e;
    }

    @Override
    public int search(Object o) {
        if (myStack.isEmpty()){return 0;}
        int n = myStack.size();
        Item temp;
        int result = 0;
        for (int i = n-1; i >= 0; i--) {
            temp = myStack.dequeue();
            if (temp == o){result = i+1;}
            myStack.enqueue(temp);
        }
        return result;
    }
}
