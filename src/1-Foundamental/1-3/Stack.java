import java.util.Iterator;
import java.util.Stack;

class IterableStack<E> implements Iterable<E>{ // If here extend Stack, then iterator will use the iterator of father class Stack's, thus here cannot extend Stack

    Stack<E> myStack;

    public IterableStack(){
        myStack = new Stack();
    }

    public void add(E e){
        myStack.add(e);
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
        Iterator<E> iter = new Iterator<E>() {
            private int i=0;

            @Override // Override the original result
            public boolean hasNext(){
                return (i < myStack.size());
            }

            @Override
            public E next(){
                E temp;
                temp = (E) myStack.elementAt(i);
                i++;
                return temp;
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
