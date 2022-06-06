import edu.princeton.cs.algs4.BinarySearch;

import java.util.LinkedList;

/**
 * The {@Code BinarySearchwithAddSubract} class represents a set of increasing integers.
 *  It supports determine whether a given integer is in the set. It accomplishes
 *  this by keeping the set of integers in a sorted array and using
 *  binary search to find the given integer.
 *
 *  Idea: generate a series of Fibonacci number (grow exponentially): F1, F2, ..., Fn
 *  search interval [i, i+Fk], if
 *  Take logarithmic time in the worst case.
 *
 *  @author XinXin
 */
public class BinarySearchwithAddSubtract {
    private LinkedList<Integer> fibonacci = new LinkedList<>();
    private int[] set;

    public BinarySearchwithAddSubtract(int[] a){
        //TODO: Generate the Fibonacci series
        set = a;
        int x = 0;
        int y = 1;
        int temp;
        fibonacci.addFirst(x);
        fibonacci.addFirst(y);
        while(y < a.length){
            temp = y;
            y = x+y;
            fibonacci.addFirst(y);
            x = temp;
        }
        fibonacci.remove();
    }

    public LinkedList<Integer> getFibonacci() {
        return fibonacci;
    }

    public boolean ifContain(int x){
        //TODO: determine the initial searching interval
        LinkedList<Integer> fiboCopy = (LinkedList<Integer>) fibonacci.clone();
        int i;
        int F = fiboCopy.peekFirst(); // the last num in fibo series
        if (x < set[F]){ // if contain x, x must be in [0, F]
            i = 0;
        }
        else{ // x must be in [set.length-F -1, set.length-F + F -1]
            i = set.length-F-1;
        }
        fiboCopy.removeFirst();
        return ifContainHelper(x, i, F+i, fiboCopy);
    }

    public boolean ifContainHelper(int x, int l, int r, LinkedList<Integer> fibo){
        int k1;
        int k2;
        while(fibo.size()>=2){
            k1 = fibo.removeFirst(); // Fk-1
            k2 = fibo.removeFirst(); //Fk-2
            int s = set[l+k2];
            if (s == x){return true;}
            if (s > x){ // if contains, must be in [l, l+k2]
                return ifContainHelper(x,l, l+k2, fibo);
            }
            return ifContainHelper(x,l+k1,l+k1+k2, fibo); // if contains, must be in [l+k2, r]
        }
        if (set[l] == x){return true;}
        return false;
    }

    public void printFibo(){
        while(!fibonacci.isEmpty()){
            System.out.print(fibonacci.removeLast()+" ");
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,10,11};
        BinarySearchwithAddSubtract myBS = new BinarySearchwithAddSubtract(a);
        myBS.printFibo();
    }
}
