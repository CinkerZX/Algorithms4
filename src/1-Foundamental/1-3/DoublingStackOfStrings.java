import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class DoublingStackOfStrings<E> extends Stack {
    public static void main(String[] args) {
        Stack s = new Stack();
        for (int i = 0; i < args.length; i++) {
            s.push(args[i]);
        }
        System.out.println("The size of the array is "+ s.size());
        while(!s.isEmpty()){
            System.out.print(s.pop());
        }
    }
}
