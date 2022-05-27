import java.util.Arrays;
import java.util.LinkedList;

/**
 * The {@code intersectionOf2ArrayFlexible} class represents two set of sorted integers a, b with different size
 *  It supports prints all elements in b that appear in a, in sorted order.
 *  The intersection operation take proportional max(a.size(), b.size()) time in the worst case.
 *  Example: a = [1,2,3,4] b = [1,3,3,5,7,9] Result: 1,3,3
 *  Note: a is not duplicated, b can have duplicates
 *  @author Xin Zhou
 */
public class intersectionOf2ArrayFlexible {
    private int[] a;
    private int[] b;

    /**
     * Initializes two set of integers specified by the integer array.
     */
    public intersectionOf2ArrayFlexible(int[] A, int[] B) {
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
                default: // a[i] == b[j]
                    if (result.isEmpty()){result.addFirst(a[i]);}
                    if (!result.isEmpty() && a[i] != result.peekLast()){result.addFirst(a[i]);}
                    while (j<b.length-1 && b[j] == b[j + 1]) {
                        result.addFirst(a[i]);
                        j++;
                    }
                    i++;
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
                    while (j<b.length-1 && b[j] == b[j + 1]) {
                        result.addFirst(a[i]);
                        j++;
                    }
                    i++;
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
        int[] a = new int[]{1,3,5,7,9};
        int[] b = new int[]{-1,0,3,5,5,6,9,9,9,9};
        intersectionOf2ArrayFlexible inter = new intersectionOf2ArrayFlexible(a,b);
        inter.intersectionPrint(); // 3, 5,5,9,9,9,9
    }
}
