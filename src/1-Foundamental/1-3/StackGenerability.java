import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

class StackGenerability extends Stack implements Iterable {
    Stack<Integer> mixedOperations;

    public StackGenerability(){
        mixedOperations = new Stack<Integer>();
    }

    /**
     * Generate the intermixed sequence
     */
    public void generatePermutation(int n){
        Random r = new Random();
        for (int i = 0; i < 2*n; i++) {
            mixedOperations.push(r.nextInt(2));
        }
    }

    /**
     * Check if the intermixed operation is will cause overflow
     */
//    public boolean overflowCheck(){
//
//    }

    /**
     * Check if a given permutation can be generated
     */

    @Override
    public Iterator iterator(){
        Stack<Integer> temp = (Stack<Integer>) mixedOperations.clone();
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return(!temp.isEmpty());
            }

            @Override
            public Object next() {
                return temp.pop();
            }
        };
    }
    public static void main(String[] args) {
        StackGenerability myOperations = new StackGenerability();
        myOperations.generatePermutation(5);
        for (Object i:myOperations) {
            System.out.println(i);
        }
    }

}
