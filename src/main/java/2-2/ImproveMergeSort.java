/**
 * Improve merge sort
 * Idea1: insertion sort performs better on small sub-arrays. Switching to insertion sort when a.length[] <= 15 can
 *        improve the running time of mergesort by 10 to 15%
 *
 * Idea2: check if already sorted before merge
 *
 * Idea3:
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


    // Idea: Eliminate the copy to the auxiliary
    // given array => sort => sorted auxiliary array

    // sorted auxiliary array => sort => given array

    public static void mergeNoAux(Comparable[] a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
//        Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
//        for (int k = lo; k <=hi ; k++) {
//            myaux[k-lo] = a[k];
//        }
        // merge back to a[lo, hi]
//        int i = lo, j = mid+1;
//        for (int k = lo; k <= hi ; k++) {
//            if (i>mid) // a[0, 1] is sorted, and a[0] < a[1]
//                // i = k = lo = 1, aux[a[1], a[0], a[2], a[3]]
//                // a[0] = aux[1]
//                a[k] = myaux[j++ - lo];
//            else if (j>hi) // a[2,3] is sorted, and a[2] < a[3]
//                // j = 3, hi = 2, k = 2, i = 2, aux[a[0], a[1], a[3], a[2]];
//                // a[2] = aux[3]
//                a[k] = myaux[i++ - lo];
//            else if (less(myaux[j-lo], myaux[i-lo])) a[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
//            else
//                a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
//        }
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
