/******************************************************************************
 *  Compilation:  javac StaticSetOfInts.java
 *  Execution:    none
 *  Dependencies: StdOut.java
 *
 *  Data type to store a set of integers.
 *
 ******************************************************************************/
import java.util.Arrays;

/**
 *  The {@code StaticSETofInts} class represents a set of integers.
 *  It supports searching for a given integer is in the set. It accomplishes
 *  this by keeping the set of integers in a sorted array and using
 *  binary search to find the given integer.
 *  <p>
 *  The <em>rank</em> and <em>contains</em> operations take
 *  logarithmic time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class ModifiedStaticSETofInts {
    private int[] a;

    /**
     * Initializes a set of integers specified by the integer array.
     * @param keys the array of integers
     * @throws IllegalArgumentException if the array contains duplicate integers
     */
    public ModifiedStaticSETofInts(int[] keys) {
        // defensive copy
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i];

        // sort the integers
        Arrays.sort(a);
    }

    /**
     * Is the key in this set of integers?
     * @param key the search key
     * @return true if the set of integers contains the key; false otherwise
     */
    public boolean contains(int key) {
        return rank(key) != -1;
    }

    /**
     * Returns either the smallest index of the search key in the sorted array
     * (if the key is in the set) or -1 (if the key is not in the set).
     * @param key the search key
     * @return the smallest index of keys in a (if the key is in the set)
     * or -1 (if the key is not in the set).
     */
    public int rank(int key) {
        return ModifiedBinarySearch.smallestindexOf(a,key);
    }

    public int howMany(int key){
        return ModifiedBinarySearch.largestIndexOf(a,key)-ModifiedBinarySearch.smallestindexOf(a,key);
    }
}