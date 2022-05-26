import java.util.Arrays;
import java.util.LinkedList;

/**
 *  The {@code TwoSumFaster}
 *  Read in N long integers and counts the number of 2-tuples that sum to exactly 0.
 *  take proportional N time in the worst case.
 *  Idea: take the opposite number of the array a, then get the intersection of a and -a
 *
 *  @author XinXinZhou
 */

public class TwoSumFaster {
    private TwoSumFaster(){} // Do not instantiate

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

    /**
     * Prints to standard output the (i, j) with {@code i < j < k < l}
     * such that {@code a[i] + a[j]== 0}
     */
    public static void printAll(int[] a){
        if (containsDuplicates(a)){throw new IllegalArgumentException("array contains duplicate integers");}
        int[] b = getOpposite(a);
        Arrays.sort(a);
        Arrays.sort(b);
        intersectionOf2Array inter = new intersectionOf2Array(a,b);
        LinkedList results = inter.intersection();
        if (results.contains(0)){results.remove(results.indexOf(0));}
        printLinkedList(results);
    }

    /**
     * Returns the number of triples (i, j) with {@code i < j}
     * such that {@code a[i] + a[j] == 0}.
     */
    public static int count(int[] a) {
        if (containsDuplicates(a)){throw new IllegalArgumentException("array contains duplicate integers");}
        int[] b = getOpposite(a);
        Arrays.sort(a);
        Arrays.sort(b);
        intersectionOf2Array inter = new intersectionOf2Array(a,b);
        LinkedList results = inter.intersection();
        if (results.contains(0)){results.remove(results.indexOf(0));}
        return results.size();
    }

    public static int[] getOpposite(int[] a){
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = (-1)*a[i];
        }
        return result;
    }

    public static void printLinkedList(LinkedList ll){
        while (!ll.isEmpty()){
            int temp = (int) ll.pollLast();
            System.out.println(temp+ " "+ (-1)*temp);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{-4,-1, -2, 0, 1,2,3, 5};
//        a = getOpposite(a);
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
        printAll(a); 
        System.out.println(count(a)); // 2
    }
}
