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
        Item result =  (Item) myStack.goToEnd().getData();
        return result;
    }

    public Item pop(){
        Item result =  (Item) myStack.goToEnd().getData();
        myStack.remove(result);
        return result;
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
        int position = 1;
        myNode pointer = new myNode(myStack.goToEnd()); //topOfStack
        while(!pointer.getData().equals(o)){
            if (pointer.equals(myStack.last)){
                return -1;
            }
            pointer = pointer.getNext();
            position++;
        }
        return position;
    }
}
