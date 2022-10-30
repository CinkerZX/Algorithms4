import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Code @{CornerCases} aims at running sort() on difficult or pathological cases
 * Examples: already in order; in reverse order; arrays with same keys; arrays consisting of only two distinct values;
 * arrays of size 0 or 1
 *
 * Idea:
 * 1)  check the length of the array first, if == 0 or 1, return the original array object
 * 2)  check how many distinct key values does it have
 * 3)  check if they already in order, or in reverse order
 */
public class CornerCases {

    public CornerCases(){}

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        //TODO: check the length
        int n = a.length;
        if (n > 1){ // length > 1
            if (!isSorted(a)){ // not sorted
                Collections.reverse(Arrays.asList(a));
                if(!isSorted(a)){ // if is in reverse order, then already correct the order, if not, we do the following
                    if(numElement(a) == 2){ // insert sorting is faster
                        System.out.println("The array has only two keys");
                        Insertion.sort(a);
                    }else{
                        ShellSort.sort(a);
                        System.out.println("The array has more than two keys");
                    }
                }
                else{
                    //******* in reverse order
                    System.out.println("The array is in reverse order");
                }
            }
            else{
                //******* already sorted
                System.out.println("The array is ordered");
            }
        }
        else{//******* n == 0 or 1
        System.out.println("The array has 0 or 1 element");}
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i]+"    ");
        }
        System.out.println();
    }

    //Check the number of elements: return 1, 2 or 3
    // return 1 means the array has only one specific key
    // return 2 means the array has only two different keys
    // return 3 means the array has more than two different keys
    private static int numElement(Comparable[] a){
        //TODO: compare a[i] with all the elements ahead it, if the same, break, if not #elemnet++
        int nElement = 1;
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int j = 0;
            for (; j < i; j++) {
                if (a[j] == a[i]){break;}
            }
            if (j == i){nElement++;} // there is no value that is the same as a[i]
            if (nElement == 3){return 3;}
        }
        return nElement;
    }

    public static void main(String[] args) {
        // length == 0 or 1
        Comparable[] a = new Comparable[]{};
        CornerCases.sort(a);
        CornerCases.show(a);

        a = new Comparable[]{1};
        CornerCases.sort(a);
        CornerCases.show(a);

        // is sorted
        a = new Comparable[]{1, 2, 3, 4, 5};
        CornerCases.sort(a);
        CornerCases.show(a);

        // reverse sorted
        a = new Comparable[]{5, 4, 3, 2, 1};
        CornerCases.sort(a);
        CornerCases.show(a);

        // Have the same key
        a = new Comparable[]{3, 3, 3, 3, 3};
        CornerCases.sort(a);
        CornerCases.show(a);

        // Have two different keys
        a = new Comparable[]{3, 3, 2, 3, 3};
        CornerCases.sort(a);
        CornerCases.show(a);

        // Have more than two different keys
        a = new Comparable[]{1, 3, 2, 3, 3};
        CornerCases.sort(a);
        CornerCases.show(a);
    }
}
