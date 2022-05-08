import javax.management.OperationsException;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

class IterableStack<E> extends Stack<E> implements Iterable<E> { // If here extend Stack, then iterator will use the iterator of father class Stack's, thus here cannot extend Stack

    Stack<E> myStack;
    int numPush = 0;
    int numPop = 0;

    public IterableStack(){
        myStack = new Stack();
    }

    @Override
    public boolean add(E e){
        myStack.add(e);
        numPush++;
        return true;
    }

    @Override
    public E push(E e){
        myStack.push(e);
        numPush++;
        return e;
    }

    @Override
    public E pop(){
        if (!myStack.isEmpty()){
            numPop++;
            return myStack.pop();
        }
        return null;
    }

    public static Stack<String> copy(IterableStack<String> s){
        Stack<String> result = new Stack<>();
        for (String temp : s) {
            result.add(temp);
        }
        return result;
    }

    @Override
    public Iterator<E> iterator(){ // Define my own iterator
        final int numpush = numPush;
        final int numpop = numPop;
        Iterator<E> iter = new Iterator<E>() {
            private int i=0;

            @Override // Override the original result
            public boolean hasNext(){
                try{
                    return (i < myStack.size());
                }catch(ConcurrentModificationException exception){
                    throw new FailFastException(numpush==numPush, numpop==numPop);
                }
            }

            @Override
            public E next(){
                try{
                    E temp;
                    temp = (E) myStack.elementAt(i);
                    i++;
                    return temp;
                }catch(ConcurrentModificationException exception){
                    throw new FailFastException(numpush==numPush, numpop==numPop);
                }
            }
        };
        return iter;
    }

    public static void main(String[] args) {
        IterableStack iterStack = new IterableStack();
        iterStack.add("Java ");
        iterStack.add("is ");
        iterStack.add("interesting.");
        Stack copyStack = copy(iterStack);
        while(!copyStack.isEmpty()){
            System.out.println(copyStack.pop());
        }
    }
}
