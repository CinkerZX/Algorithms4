import edu.princeton.cs.algs4.SET;
import sun.awt.image.ImageWatched;

import java.util.*;

class StackGenerability extends Stack implements Iterable, Set {
    Stack<Integer> mixedOperations;

    public StackGenerability(){
        mixedOperations = new Stack<Integer>();
    }

    public StackGenerability(LinkedList operations){
        mixedOperations = new Stack<Integer>();
        for (int i = 0; i < operations.size(); i++) {
            mixedOperations.push((Integer) operations.get(operations.size()-1-i));
        }
    }

    /**
     * Generate the intermixed sequence: 1 => push -1 => pop
     */
    public void generatePermutation(int n){
        List<Integer> operationSet = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            operationSet.add(1);
            operationSet.add(-1);
        }
        Collections.shuffle(operationSet); // random order
        for (int i = 0; i < 2*n; i++) {
            mixedOperations.push(operationSet.get(i));
        }
    }

    /**
     * Check if the intermixed operation is will cause overflow
     * If the sum of former k th operation is >= 0, the it won't have overflow
     */
    public boolean overflowCheck(){
        Stack<Integer> temp = (Stack<Integer>) mixedOperations.clone();
        int sum = 0;
        while(!temp.isEmpty()){
            sum = sum+temp.pop();
            if (sum<0){return false;}
        }
        return true;
    }

    /**
     * Check if a given permutation can be generated
     */
    public static boolean permutationCheck(int[] permutation){
        Stack<Integer> temp = new Stack<>();
        int n = permutation.length;
        LinkedList comparePermutation = new LinkedList();
        LinkedList permutations = intArrToLinkedList(permutation);
        LinkedList permutations2 = (LinkedList) permutations.clone(); // trap: permutations has already being empty with polling
        int i = 0;
        while(!permutations.isEmpty()){
            if ((int) permutations.pollFirst() == n-1-i){
                comparePermutation = permutationCheckHelper2(temp,comparePermutation);
                comparePermutation.addLast(n-1-i);
            }
            else{
                temp.push(n-1-i);
            }
            i++;
        }
        comparePermutation = permutationCheckHelper2(temp,comparePermutation); // trap: in the end, need to empty the stack: temp
//        for (Object o:comparePermutation) {
//            System.out.print(o+" ");
//        }
        return permutationCheckHelper(comparePermutation, permutations2);
    }

    // If the two linked list are the same, then the permutation can be generated
    public static boolean permutationCheckHelper(LinkedList comparePermutation, LinkedList permutation){
        while(!comparePermutation.isEmpty()) {
            if (comparePermutation.pop() != permutation.pop()){return false;}
        }
        return true;
    }

    // Empty the elements in temp, by adding them into the comparePermuation
    public static LinkedList permutationCheckHelper2(Stack temp, LinkedList comparePermuation){
        if (temp.isEmpty()){
            return comparePermuation;
        }
        else{
            comparePermuation.addLast(temp.pop());
            return permutationCheckHelper2(temp,comparePermuation);
        }
    }

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

    public static LinkedList intArrToLinkedList(int[] intArr){
        LinkedList myLL = new LinkedList();
        for (int i = 0; i < intArr.length; i++) {
            myLL.addFirst(intArr[i]);
        }
        return myLL;
    }

    public static void main(String[] args) {
        StackGenerability myOperations = new StackGenerability();
        myOperations.generatePermutation(5);
        for (Object i:myOperations) {
            System.out.print(i+" ");
        }
        System.out.println(myOperations.overflowCheck());

        int[] mypermutation = new int[]{0,2,1,3,4};
        System.out.println(permutationCheck(mypermutation));

        int[] mypermutation2 = new int[]{3,2,0,1,4};
        System.out.println(permutationCheck(mypermutation2));
    }
}
