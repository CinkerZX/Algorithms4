
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
public class StaticSETofInts {
    private int[] a;

    /**
     * Initializes a set of integers specified by the integer array.
     * @param keys the array of integers
     * @throws IllegalArgumentException if the array contains duplicate integers
     */
    public StaticSETofInts(int[] keys) {

        // defensive copy
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i];

        // sort the integers
        Arrays.sort(a);

        // check for duplicates
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1])
                throw new IllegalArgumentException("Argument arrays contains duplicate keys.");
    }

    /**
     * Is the key in this set of integers?
     * @param key the search key
     * @return true if the set of integers contains the key; false otherwise
     */
    public boolean contains(int key) {
        return binarySearch(0,a.length,key) != -1;
    }

    public int binarySearch(int l, int r, int key){
        while(l<r){
            int mid = (l+r)/2;
            if (a[mid] < key){ // search in the right
                return binarySearch(mid, r, key);
            }
            if (a[mid] > key){
                return binarySearch(l,mid, key);
            }
            return mid;
        }
        return -1;
    }

    /**
     * Returns either the index of the search key in the sorted array
     * (if the key is in the set) or -1 (if the key is not in the set).
     * @param key the search key
     * @return the number of keys in this set less than the key (if the key is in the set)
     * or -1 (if the key is not in the set).
     */
    public int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,5,7,9,13};
        StaticSETofInts myIntArray = new StaticSETofInts(a);
        System.out.println(myIntArray.contains(3)); // true
        System.out.println(myIntArray.contains(1)); // true
        System.out.println(myIntArray.contains(13)); // true
        System.out.println(myIntArray.contains(-1)); // false
    }
}