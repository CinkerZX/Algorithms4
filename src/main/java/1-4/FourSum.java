import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 *  The {@code FourSum}
 *  A program with N^4 running time. Read in N long integers
 *  and counts the number of 4-tuples that sum to exactly 0.
 *
 *  @author Xin
 */
public class FourSum {
    private FourSum(){} // Do not instantiate

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

    /**
     * Prints to standard output the (i, j, k, l) with {@code i < j < k < l}
     * such that {@code a[i] + a[j] + a[k] + a[l] == 0}.
     *
     * @param a the array of integers
     * @throws IllegalArgumentException if the array contains duplicate integers
     */
    public static void printAll(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    int l = Arrays.binarySearch(a, -(a[i] + a[j] + a[k]));
                    if (l > k) StdOut.println(a[i] + " " + a[j] + " " + a[k] + " " + a[l]);
                }
            }
        }
    }

    /**
     * Returns the number of triples (i, j, k, l) with {@code i < j < k < l}
     * such that {@code a[i] + a[j] + a[k] + a[l] == 0}.
     */
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    int l = Arrays.binarySearch(a, -(a[i] + a[j] + a[k]));
                    if (l > k) count++;
                }
            }
        }
        return count;
    }

    /**
     * Reads in a sequence of distinct integers from a file, specified as a command-line argument;
     * counts the number of triples sum to exactly zero; prints out the time to perform
     * the computation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)  {
        int[] a = new int[]{-3, -2, -1, 0, 1, 2, 3, 4, 5, 6};
        printAll(a);
        int count = count(a);
        StdOut.println(count);
    }
}
