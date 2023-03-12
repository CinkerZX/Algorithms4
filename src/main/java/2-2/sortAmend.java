import java.util.Random;

public class sortAmend {

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
        for (int k = lo; k <=hi ; k++) {
            myaux[k-lo] = a[k];
        }
        // merge back to a[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            if (i>mid) // a[0, 1] is sorted, and a[0] < a[1]
                // i = k = lo = 1, aux[a[1], a[0], a[2], a[3]]
                // a[0] = aux[1]
                a[k] = myaux[j++ - lo];
            else if (j>hi) // a[2,3] is sorted, and a[2] < a[3]
                // j = 3, hi = 2, k = 2, i = 2, aux[a[0], a[1], a[3], a[2]];
                // a[2] = aux[3]
                a[k] = myaux[i++ - lo];
            else if (sort.less(myaux[j-lo], myaux[i-lo])) a[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
            else
                a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
        }
    }

    //Idea: breaking big problems into small problems
    // iteration: call sort[a, lo, hi], until the smallest unit a[0, 1], a[2, 3], ...
    public static void sortTopDown(Comparable[] a){
        //TODO: call sortTopDown
        Comparable[] aux = new Comparable[a.length]; //construct aux
        sortTopDown(a, 0, a.length-1, aux);
    }
    public static void sortTopDown(Comparable[] a, int lo, int hi, Comparable[] aux){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        sortTopDown(a, lo, mid, aux);
        sortTopDown(a, mid+1, hi, aux);
        //TODO: merge from the bottom to up
        merge(a, lo, mid, hi, aux);
        sort.printStringArray(a); // for test
    }

    // Idea: building small solutions into larger ones
    public static void sortBottomUp(Comparable[] a){
        //TODO: use two for loop to merge
        int N = a.length;
        Comparable[] aux = new Comparable[N]; //construct aux
        for (int i = 1; i < N; i = i+i) { // i-- subarray size
            for (int lo = 0; lo < N-i ; lo += i+i) {
                merge(a, lo, lo+i-1, Math.min(lo+i-1+i, N-1), aux); // hi -- the mid+i, if it out of index, hi = N-1
                sort.printStringArray(a); // for test
            }
        }
    }

    public static void main(String[] args) {
        // Test of merge
//        String[] a = new String[]{"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
//        sort.merge(a, 0, 4, 9);
//        sort.printStringArray(a);

        // Test of sortTopdown
        String[] a = new String[]{"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
//        sortTopDown(a);

        //Test of sortBottomUp
        sortBottomUp(a);

    }

}
