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
            mixedOperations.push((Integer) operations.get(i));
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
        //generate the operation order by the input permutation
        LinkedList opeOrders = new LinkedList();
        LinkedList permutations = intArrToLinkedList(permutation);
        opeOrders = permutationCheckHelper(opeOrders, permutations);
        //generate a mixedOperation, then check
        StackGenerability recuntrrustOpe = new StackGenerability(opeOrders);
        return recuntrrustOpe.overflowCheck();
    }

    // Based on the permutation, generate the int[] that need to put into the opeOrders
    public static LinkedList permutationCheckHelper(LinkedList opeOrder, LinkedList permutation){
        int lenPermutaion = permutation.size();
        int[] ope;
        if (permutation.size() == 0){return opeOrder;}
        while(lenPermutaion > 0){
            if (lenPermutaion == 1){
                ope = new int[]{-1, 1};
                opeOrder = permutationCheckHelper2(ope, opeOrder);
                permutation.remove();
                return opeOrder;
            }
            int i = 0;
            int temp1 = (int) permutation.pollLast();
            int temp2 = (int) permutation.pollLast();
            while (temp1<temp2 && permutation.size()>=1){
                i++;
                temp1 = temp2;
                temp2 = (int) permutation.pollLast();
            }
            ope = permutationCheckHelper3(i);
            opeOrder = permutationCheckHelper2(ope, opeOrder);
            permutationCheckHelper(opeOrder, permutation);
        }
        return opeOrder;
    }
    // Insert the operation order into the linkedlist
    public static LinkedList permutationCheckHelper2(int[] ope, LinkedList opeOrder){
        for (int i = 0; i < ope.length; i++) {
            opeOrder.addFirst(ope[ope.length-1-i]);
        }
        return opeOrder;
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

    public static int[] permutationCheckHelper3(int i){
        int[] result = new int[2*i];
        for (int j = 0; j < i; j++) {
            result[j] = 1;
            result[2*i-1-j] = -1;
        }
        for (int s:result) {
            System.out.println(s);
        }
        return result;
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

        permutationCheckHelper3(3);

    }

}
