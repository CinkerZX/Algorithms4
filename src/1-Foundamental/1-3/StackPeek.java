import java.util.Stack;

public class StackPeek<E> extends Stack {
    public E peek(Stack s){
        E temp;
        temp = (E) s.pop();
        s.push(temp);
        return temp;
    }
}
