/**
 * Improve merge sort
 * Idea1: insertion sort performs better on small sub-arrays. Switching to insertion sort when a.length[] <= 15 can
 *        improve the running time of mergesort by 10 to 15%
 *
 * Idea2: check if already sorted before merge
 */

public class ImproveMergeSort {
    //****************** Amend1 Combine Merge with Insertion *******************
    public static void ImpSortTopDownInsert(Comparable[] a){
        //TODO: call sortTopDown
        ImpSortTopDownInsert(a, 0, a.length-1);
    }
    public static void ImpSortTopDownInsert(Comparable[] a, int lo, int hi){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        ImpSortTopDownInsert(a, lo, mid);
        ImpSortTopDownInsert(a, mid+1, hi);
        //TODO: merge from the bottom to up
        sort.mergeInsert(a, lo, mid, hi);
    }

    // Idea: building small solutions into larger ones
    public static void ImpSortBottomUpInsert(Comparable[] a){
        //TODO: use two for loop to merge
        int N = a.length;
        for (int i = 1; i < N; i = i+i) { // i-- subarray size
            for (int lo = 0; lo < N-i ; lo += i+i) {
                sort.mergeInsert(a, lo, lo+i-1, Math.min(lo+i-1+i, N-1)); // hi -- the mid+i, if it out of index, hi = N-1
            }
        }
    }

    //****************** Amend2 check if already sorted before merge *******************
    public static void ImpSortTopDownCheckOrder(Comparable[] a){
        //TODO: call sortTopDown
        ImpSortTopDownCheckOrder(a, 0, a.length-1);
    }
    public static void ImpSortTopDownCheckOrder(Comparable[] a, int lo, int hi){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        ImpSortTopDownCheckOrder(a, lo, mid);
        ImpSortTopDownCheckOrder(a, mid+1, hi);
        //TODO: merge from the bottom to up
        sort.mergeCheckOrder(a, lo, mid, hi);
    }

    // Idea: building small solutions into larger ones
    public static void ImpSortBottomUpCheckOrder(Comparable[] a){
        //TODO: use two for loop to merge
        int N = a.length;
        for (int i = 1; i < N; i = i+i) { // i-- subarray size
            for (int lo = 0; lo < N-i ; lo += i+i) {
                sort.mergeInsert(a, lo, lo+i-1, Math.min(lo+i-1+i, N-1)); // hi -- the mid+i, if it out of index, hi = N-1
            }
        }
    }



    public static void main(String[] args) {
        // Test of Amend1
        String[] a = new String[]{"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
        sort.mergeInsert(a, 0, 4, 9);
        System.out.println("Merge combine with Insertion");
        sort.printStringArray(a);

        // Test of sortTopdown
        a = new String[]{"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        ImpSortTopDownInsert(a);
        sort.printStringArray(a);

        // Test of sortBottomUp
        a = sort.generateStringArray(10);
        ImpSortBottomUpInsert(a);
        sort.printStringArray(a);

        //Test of Amend2
        a = new String[]{"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
        sort.mergeCheckOrder(a, 0, 4, 9);
        System.out.println("Check if ordered before merge");
        sort.printStringArray(a);

        // Test of sortTopdown
        a = new String[]{"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        ImpSortTopDownCheckOrder(a);
        sort.printStringArray(a);

        // Test of sortBottomUp
        a = sort.generateStringArray(10);
        ImpSortBottomUpCheckOrder(a);
        sort.printStringArray(a);
    }

}
