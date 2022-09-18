import edu.princeton.cs.algs4.StdOut;

import javax.swing.text.html.ObjectView;
import java.util.Arrays;
import java.util.Comparator;

/**
 * The {@Code InsertionWithoutExch} implement insertion alg without using exch()
 *
 * Idea: add one position on the right side of the original array, sort from right to left:
 *       eg: 2 3 1 5 4 _
 *       put the larger one on the right empty place
 *       if the larger one is the one with smaller index, then move the smaller item one position left
 *       eg: 2 3 1 4 _ 5
 *           2 3 1 _ 4 5
 *           2 1 _ 3 4 5
 *           1 _ 2 3 4 5 until reaching index 0, move to position 1, and return array 1:n
 */
public class InsertionWithoutExch {
    // This class should not be instantiated.
    public InsertionWithoutExch() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] temp = addOnePoition(a);
        for (int i = n-1; i > 0; i--) {
            if (less(temp[i],temp[i-1])){// if the a[i-1] > a[i]
                temp[i+1] = temp[i-1];
                temp[i-1] = temp[i];
            }
            else{
                temp[i+1] = temp[i];
            }
        }
        copyTemptoArray(a,temp);
        assert isSorted(a);
    }

    /**
     * Rearranges the subarray a[lo..hi) in ascending order, using the natural order.
     * @param a the array to be sorted
     * @param lo left endpoint (inclusive)
     * @param hi right endpoint (exclusive)
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        int n = a.length;
        Comparable[] temp = addOnePoition(a);
        for (int i = hi-1; i > lo; i--) {
            if (less(temp[i],temp[i-1])){// if the a[i-1] > a[i]
                temp[i+1] = temp[i-1];
                temp[i-1] = temp[i];
            }
            else{
                temp[i+1] = temp[i];
            }
        }
        copyTemptoArray(a,temp);
        assert isSorted(a, lo, hi);
    }

    /**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */
    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        Object[] temp = addOnePoition(a);
        for (int i = n-1; i > 0; i--) {
            if (less(temp[i],temp[i-1], comparator)){// if the a[i-1] > a[i]
                temp[i+1] = temp[i-1];
                temp[i-1] = temp[i];
            }
            else{
                temp[i+1] = temp[i];
            }
        }
        copyTemptoArray(a,temp);
        assert isSorted(a, comparator);
    }

    /**
     * Rearranges the subarray a[lo..hi) in ascending order, using a comparator.
     * @param a the array
     * @param lo left endpoint (inclusive)
     * @param hi right endpoint (exclusive)
     * @param comparator the comparator specifying the order
     */
    public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
        int n = a.length;
        Object[] temp = addOnePoition(a);
        for (int i = n-1; i > 0; i--) {
            if (less(temp[i],temp[i-1], comparator)){// if the a[i-1] > a[i]
                temp[i+1] = temp[i-1];
                temp[i-1] = temp[i];
            }
            else{
                temp[i+1] = temp[i];
            }
        }
        a = Arrays.copyOfRange(temp, 1, n+1);
        assert isSorted(a, lo, hi, comparator);
    }


    // return a permutation that gives the elements in a[] in ascending order
    // do not change the original array a[]
    /**
     * Returns a permutation that gives the elements in the array in ascending order.
     * @param a the array
     * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
     *    ..., {@code a[p[n-1]]} are in ascending order
     */
    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] temp = IndexAddOnePoition(a);
        for (int i = n-1; i > 0; i--) {
            if (less(temp[i],temp[i-1])){// if the a[i-1] > a[i]
                temp[i+1] = temp[i-1];
                temp[i-1] = temp[i];
            }
            else{
                temp[i+1] = temp[i];
            }
        }
        return Arrays.copyOfRange(temp, 1, n+1);
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Object v, Object w, Comparator comparator) {
        return comparator.compare(v, w) < 0;
    }

    // construct an array with one addition position at the end
    private static Comparable[] addOnePoition(Comparable[] a){
        int n = a.length;
        Comparable[] temp = new Comparable[n+1];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        return temp;
    }

    private static Object[] addOnePoition(Object[] a){
        int n = a.length;
        Object[] temp = new Object[n+1];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        return temp;
    }

    private static int[] IndexAddOnePoition(Comparable[] a){
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++)
            index[i] = i;
        return index;
    }

    private static void copyTemptoArray(Comparable[] a, Comparable[] temp){
        for (int i = 0; i < a.length; i++) {
            a[i] = temp[i+1];
        }
    }

    private static void copyTemptoArray(Object[] a, Object[] temp){
        for (int i = 0; i < a.length; i++) {
            a[i] = temp[i+1];
        }
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length);
    }

    // is the array a[lo..hi) sorted
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, 0, a.length, comparator);
    }

    // is the array a[lo..hi) sorted
    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i-1], comparator)) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     *  % java SortCompare Insertion InsertionWithoutExch 10 100
     *  For 10 random Doubles
     *      Insertion is 0 times faster than InsertionWithSentinel
     *
     *  % java SortCompare Insertion InsertionWithoutExch 100 100
     *  For 500 random Doubles
     *     Insertion is 0.1 times faster than InsertionWithSentinel
     *
     *  % java SortCompare Insertion InsertionWithoutExch 1000 100
     *  For 1000 random Doubles
     *     Insertion is 0.1 times faster than InsertionWithSentinel
     *
     *  % java SortCompare Insertion InsertionWithoutExch 5000 100
     *     Insertion is 0 times faster than InsertionWithSentinel
     *
     * @param args
     */
    public static void main(String[] args) {
        //************************* Test *******************************
//        Comparable[] a = {2,3,1,5,4};
//        InsertionWithoutExch.sort(a);
//        show(a);
        //**************** Compare with Insertion **********************
        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int trials = Integer.parseInt(args[3]);
        double time1, time2;
        if (args.length == 5 && args[4].equals("sorted")) {
            time1 = SortCompare.timeSortedInput(alg1, n, trials);   // Total for alg1.
            time2 = SortCompare.timeSortedInput(alg2, n, trials);   // Total for alg2.
        }
        else {
            time1 = SortCompare.timeRandomInput(alg1, n, trials);   // Total for alg1.
            time2 = SortCompare.timeRandomInput(alg2, n, trials);   // Total for alg2.
        }

        StdOut.printf("For %d random Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", time2/time1, alg2);
    }
}
