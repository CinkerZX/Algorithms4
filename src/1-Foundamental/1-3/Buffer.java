import edu.princeton.cs.algs4.Stack;

public class Buffer<Item> extends Stack<Item> {
    Stack<Character> myBufferStack;
    int cursor; // the ith object in the stack, i = 1,2, ...

    /**
     * Create an empty buffer
     */
    public Buffer(){
        myBufferStack = new Stack<Character>();
        cursor = 1;
    }

    /**
     * Insert c at the cursor position
     * @param c
     */
    public void insert(char c){
        Stack<Character> temp = new Stack<>();
        while(!myBufferStack.isEmpty()){
            temp.push(myBufferStack.pop());
        }
        for (int i = 1; i < cursor; i++) {
            myBufferStack.push(temp.pop());
        }
        myBufferStack.push(c);
        while(!temp.isEmpty()){
            myBufferStack.push(temp.pop());
        }
    }

    /**
     * Delete and return the character at the cursor
     */
    public char delete(){
        char result;
        Stack<Character> temp = new Stack<>();
        while(!myBufferStack.isEmpty()){
            temp.push(myBufferStack.pop());
        }
        for (int i = 1; i < cursor; i++) {
            myBufferStack.push(temp.pop());
        }
        result = temp.pop();
        while(!temp.isEmpty()){
            myBufferStack.push(temp.pop());
        }
        return result;
    }

    /**
     * move the cursor k position to the left
     */
    public void left(int k){
        if (k>=cursor){
            cursor = 1;}
        else{
            cursor = cursor-k;
        }
    }

    /**
     * move the cursor k position to the right
     */
    public void right(int k){
        if (k>=myBufferStack.size()-cursor) {
            if (myBufferStack.size() == 0) {cursor = 1;}
            else {cursor = myBufferStack.size();}
        }
        else{cursor = cursor+k;}
    }

    /**
     * number of chars in the buffer
     * @return
     */
    public int size(){
        return myBufferStack.size();
    }

    public static void main(String[] args) {
        Buffer myBuffer = new Buffer();
        System.out.println("The initial size of the buffer is: "+ myBuffer.size());
        myBuffer.right(5);
        myBuffer.insert('2');
        myBuffer.insert('1');
        myBuffer.insert('0');
        System.out.println("The current buffer is: ");
        for (Object c:myBuffer.myBufferStack) {
            System.out.print(c+" ");
        }

        myBuffer.right(1); // move cursor to the second position
        myBuffer.insert('c');
        System.out.println("\n"+"After insert 'c' at the second position: ");
        for (Object c:myBuffer.myBufferStack) {
            System.out.print(c+" ");
        }

        myBuffer.left(1);
        System.out.println("\n"+"After delete "+ myBuffer.delete()+" : ");
        for (Object c:myBuffer.myBufferStack) {
            System.out.print(c+" ");
        }
    }
}
