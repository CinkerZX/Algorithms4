import edu.princeton.cs.algs4.StdOut;

/**
 * The {@Code ShellVSInsertion} aims at comparing the performance of Shellsort and insertion sort by SortCompare
 *
 * Results:
 * For 128 random Doubles
 *     Insertion is 1.0 times faster than Shell
 * For 256 random Doubles
 *     Insertion is Infinity times faster than Shell
 * For 512 random Doubles
 *     Insertion is 4.0 times faster than Shell
 * For 1024 random Doubles
 *     Insertion is 4.0 times faster than Shell
 * For 2048 random Doubles
 *     Insertion is Infinity times faster than Shell
 * For 4096 random Doubles
 *     Insertion is 8.0 times faster than Shell
 * For 8192 random Doubles
 *     Insertion is 16.0 times faster than Shell
 * For 16384 random Doubles
 *     Insertion is 7.5 times faster than Shell
 * For 32768 random Doubles
 *     Insertion is 8.7 times faster than Shell
 * For 65536 random Doubles
 *     Insertion is 8.3 times faster than Shell
 * For 131072 random Doubles
 *     Insertion is 9.8 times faster than Shell
 * For 262144 random Doubles
 *     Insertion is 9.4 times faster than Shell
 * For 524288 random Doubles
 *     Insertion is 13.6 times faster than Shell
 * For 1048576 random Doubles
 *     Insertion is 14.9 times faster than Shell
 * For 2097152 random Doubles
 *     Insertion is 14.1 times faster than Shell
 * For 4194304 random Doubles
 *     Insertion is 17.8 times faster than Shell
 * For 8388608 random Doubles
 *     Insertion is 16.6 times faster than Shell
 * For 16777216 random Doubles
 *     Insertion is 18.0 times faster than Shell
 */

public class ShellVSInsertion {
    public ShellVSInsertion(){}

    public static void main(String[] args) {
        int[] n = new int[20];
        n[0]=128;
        for (int i = 1; i < n.length; i++) {
            n[i] = 2*n[i-1];
        }
        String alg1 = "Insertion";
        String alg2 = "Shell";
        int trials = 100;
        double time1, time2;

        for (int i = 0; i < n.length; i++) {
            time1 = SortCompare.timeSortedInput(alg1, n[i], trials);   // Total for alg1.
            time2 = SortCompare.timeSortedInput(alg2, n[i], trials);   // Total for alg2.
            StdOut.printf("For %d random Doubles\n    %s is", n[i], alg1);
            StdOut.printf(" %.1f times faster than %s\n", time2 / time1, alg2);
        }
    }
}

