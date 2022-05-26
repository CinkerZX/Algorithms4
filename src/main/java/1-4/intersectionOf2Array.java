import java.util.Arrays;
import java.util.LinkedList;

/**
 * The {@code intersectionOf2Array} class represents two set of sorted integers(N).
 *  It supports prints all elements that appear in both arrays, in sorted order.
 *  The intersection operation take proportional N time in the worst case.
 *
 *  @author Xin Zhou
 */
public class intersectionOf2Array {
    private int[] a;
    private int[] b;

    /**
     * Initializes two set of integers specified by the integer array.
     */
    public intersectionOf2Array(int[] A, int[] B) {
        // defensive copy
        a = new int[A.length];
        for (int i = 0; i < A.length; i++)
            a[i] = A[i];
        b = new int[B.length];
        for (int i = 0; i < B.length; i++)
            b[i] = B[i];
        // sort the integers
        Arrays.sort(a);
        Arrays.sort(b);
    }

    public void intersectionPrint(){
        LinkedList<Integer> result = new LinkedList<Integer>();
        int temp = 0;
        int i = 0;
        int j = 0;
        while(i<a.length && j<b.length){
            temp = Integer.signum(a[i]-b[j]);
            switch (temp){
                case -1: // a[i] < b[j]
                    i++;
                    break;
                case 1:
                    j++;
                    break;
                default:
                    if (result.isEmpty()){result.addFirst(a[i]);}
                    if (!result.isEmpty() && a[i] != result.peekLast()){result.addFirst(a[i]);}
                    i++;
                    j++;
                    break;
            }
        }
        intersectionPrint(result);
    }

    public LinkedList intersection(){
        LinkedList<Integer> result = new LinkedList<Integer>();
        int temp = 0;
        int i = 0;
        int j = 0;
        while(i<a.length && j<b.length){
            temp = Integer.signum(a[i]-b[j]);
            switch (temp){
                case -1: // a[i] < b[j]
                    i++;
                    break;
                case 1:
                    j++;
                    break;
                default:
                    if (result.isEmpty()){result.addFirst(a[i]);}
                    if (!result.isEmpty() && a[i] != result.peekLast()){result.addFirst(a[i]);}
                    i++;
                    j++;
                    break;
            }
        }
        return result;
    }

    public void intersectionPrint(LinkedList num){
        while(!num.isEmpty()){
            System.out.print(num.pollLast()+" ");
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,3,5,7,9,9,10};
        int[] b = new int[]{-1,0,5,5,6,9,9};
        intersectionOf2Array inter = new intersectionOf2Array(a,b);
        inter.intersectionPrint(); // 5,9,9
    }
}
