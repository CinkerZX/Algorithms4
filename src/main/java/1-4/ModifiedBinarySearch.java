/**
 *  The {@code BinarySearch} class provides a static method for binary
 *  searching for an integer in a sorted array of integers.
 */

public class ModifiedBinarySearch {
    private ModifiedBinarySearch(){}

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param  a the array of integers, must be sorted in ascending order
     * @param  key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int smallestindexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return smallestindexOfHelper(a, mid);
        }
        return -1;
    }

    public static int smallestindexOfHelper(int[] a, int index){
        int lo = 0;
        int hi = index; //a[hi] == key
        while (lo < hi){
            // Smallest key is in a[lo, ..., hi]
            int loCandidate = lo + (hi-lo) / 2;
            if      (a[loCandidate]<a[hi])    lo = loCandidate+1;
            else if (a[loCandidate] == a[hi]) hi = loCandidate;
            else return lo;
        }
        return lo;
    }

    public static int largestIndexOf(int[] a, int key) {
        int lo = smallestindexOf(a,key);
        if (lo==-1){return -1;}
        return largestindexOfHelper(a, lo);
    }

    public static int largestindexOfHelper(int[] a, int index){
        int lo = index;
        int hi = a.length-1; //a[hi] == key
        while (lo < hi){
            // Largest key is in a[lo, ..., hi]
            int loCandidate = lo + (hi-lo) / 2;
            if (hi-lo==1){ // loCandidate = lo
                if (a[loCandidate]==a[hi]) return hi;
                return lo;
            }
            if (a[loCandidate]>a[lo]) hi = loCandidate-1;
            else if (a[loCandidate]==a[lo])    lo = loCandidate;
            else return lo;
        }
        return hi;
    }


    public static void main(String[] args) {

    }
}
