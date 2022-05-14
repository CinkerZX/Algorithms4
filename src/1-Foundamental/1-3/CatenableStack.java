import java.util.Stack;

public class CatenableStack<Item> implements Catenable<CatenableStack> {
    CircularLinkedList myStack;

    public CatenableStack(){
        myStack = new CircularLinkedList();
    }

    public CatenableStack(CircularLinkedList stack){
        myStack = stack;
    }

    /**
     * This: 0->1->2->last1->0    stack: a->b->c->last2->a
     * Return: a->b->c->0->1->2->last1->a
     * @param stack
     * @return
     */
    @Override
    public CatenableStack caten(CatenableStack stack) {
        // both this and queue is empty
        if (myStack.isEmpty() && stack.myStack.isEmpty()){return new CatenableStack();}
        if (myStack.isEmpty()){return stack;}
        if (stack.myStack.isEmpty()){return new CatenableStack(myStack);}

        myNode endOfThis = myStack.last.getNext();
        myNode endOfqueue = stack.myStack.last.getNext();
        myNode headOfqueue = stack.myStack.goToEnd();
        headOfqueue.setNext(endOfThis); // c->0
        myStack.last.setNext(endOfqueue); // last1->a
        return new CatenableStack(myStack);
    }

    public Item peek(){
        if (myStack.isEmpty()){return null;}
        Item result =  (Item) myStack.goToEnd().getData();
        return result;
    }

    public Item pop(){
        myNode result = myStack.goToEnd();
        myStack.remove(result);
        return (Item) result.getData();
    }

    public Item push(Item item){
        myStack.add(item);
        return item;
    }

    /**
     * return the 1-based position where an object is on this stack
     * @param o
     * @return
     */
    public int search(Object o){
        myNode pointer = new myNode(myStack.last.getNext()); //topOfStack
        if (pointer.equals(myStack.last)){return -1;}
        while(!pointer.getData().equals(o)){
            if (pointer.equals(myStack.last)){
                return -1;
            }
            pointer = pointer.getNext();
        }
        int position = 1;
        while(!pointer.getNext().equals(myStack.last)){position++;pointer = pointer.getNext();} // find
        return position;
    }
}
