import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * The {@CODE AnimationSelectionSort} aims at visualizing the sorting process of SelectionSort
 *
 * Idea: adding show into sort function, after each sort, call show() to exhibit the current array
 *
 */

public class AnimationSelectionSort {
    // This class should not be instantiated.
    private AnimationSelectionSort() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) throws InterruptedException {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
//            assert isSorted(a, 0, i);
            show(a);
        }
//        assert isSorted(a);
        show(a);
    }

    /**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */
    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(comparator, a[j], a[min])) min = j;
            }
            exch(a, i, min);
            assert isSorted(a, comparator, 0, i);
        }
        assert isSorted(a, comparator);
    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }


    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    public static Double[] arrayGenerator(int n){
    //TODO: Generate a random double array of length n
        Double[] result = new Double[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            result[i] = 5 * r.nextDouble();
        }
        return result;
    }

    /***************************************************************************
     * Visualization functions
     ***************************************************************************/
    public static void show(Comparable[] a) throws InterruptedException {
        //TODO: Clear canvas and create canvas, visualize array a
        StdDraw.clear();
        int width = a.length;
        StdDraw.setCanvasSize(width*10,500);
        StdDraw.setXscale(-1,2*width+1);
        StdDraw.setYscale(0,6);

        StdDraw.setPenRadius(0.015);
        StdDraw.setPenColor(Color.gray);

        for (int i = 0; i < width; i++) {
            StdDraw.line(i*2,0,i*2,(double) a[i]);
        }
        TimeUnit.SECONDS.sleep(1);
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/

    // is the array a[] sorted?
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array a[] sorted?
    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void printOut(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
    
    /**
     * Reads in a sequence of strings from standard input; selection sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Double[] a = arrayGenerator(50);
        sort(a);
    }
}
