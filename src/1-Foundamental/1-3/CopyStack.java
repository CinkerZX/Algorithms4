import java.util.Iterator;
import java.util.Stack;

public class CopyStack<Item> extends Stack<Item> implements Iterable<Item> {
    Stack<Item> myStack;

    public CopyStack(){
        myStack = new Stack<>();
    }

    public CopyStack(CopyStack s){
        myStack = new Stack<>();
        Stack<Item> copyOfS;
        Stack<Item> middle = new Stack<>(); // saving what pop from s
        copyOfS = (Stack<Item>) s.myStack.clone();
        while(!copyOfS.isEmpty()){
            Item temp = copyOfS.pop();
            middle.push(temp);
        }

        while(!middle.isEmpty()){
            Item temp = middle.pop();
            myStack.push(temp);
        }
    }

    @Override
    public Item push(Item i){
        myStack.push(i);
        return i;
    }

    @Override
    public Iterator<Item> iterator(){
        return new Iterator<Item>() {
            Stack<Item> s = (Stack<Item>) myStack.clone();

            @Override
            public boolean hasNext() {
                return !s.isEmpty();
            }

            @Override
            public Item next() {
                return s.pop();
            }
        };
    }

    public static void main(String[] args) {
        CopyStack myS = new CopyStack();
        myS.push(0);
        myS.push(1);
        myS.push(2);
        myS.push(3);
        for (Object o:myS) { // 3 2 1 0
            System.out.print(o+" ");
        }

        System.out.println("******2******");
        CopyStack S2 = new CopyStack(myS);
        for (Object o:S2) {
            System.out.print(o+" ");
        }
    }


}
