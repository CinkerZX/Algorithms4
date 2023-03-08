public class sortTimeComplexity {
    // no constructor
    private static int ac; // ac := access time
    private static Comparable[] aux;

    private static void mergeAC(Comparable[] a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
        for (int k = lo; k <=hi ; k++) {
            myaux[k-lo] = a[k];
            ac = ac+2;
        }
        // merge back to a[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            ac = ac+2;
            if (i>mid)
                a[k] = myaux[j++ - lo];
            else if (j>hi)
                a[k] = myaux[i++ - lo];
            else if (ShellSort.less(myaux[j-lo], myaux[i-lo])){
                a[k] = myaux[j++ - lo];
                ac = ac+2;
            }
            else
                a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
        }
    }

    // iteration: call sort[a, lo, hi], until the smallest unit a[0, 1], a[2, 3], ...
    private static void sortTopDownAC(Comparable[] a){
        //TODO: call sortTopDown
        ac = 0; //init
        aux = new Comparable[a.length]; //construct aux
        sortTopDownAC(a, 0, a.length-1);
    }
    private static void sortTopDownAC(Comparable[] a, int lo, int hi){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        sortTopDownAC(a, lo, mid);
        sortTopDownAC(a, mid+1, hi);
        //TODO: merge from the bottom to up
        mergeAC(a, lo, mid, hi);
    }

    // Idea: building small solutions into larger ones
    private static void sortBottomUpAC(Comparable[] a){
        //TODO: use two for loop to merge
        int N = a.length;
        ac = 0;
        aux = new Comparable[N]; //construct aux
        for (int i = 1; i < N; i = i+i) { // i-- subarray size
            for (int lo = 0; lo < N-i ; lo += i+i) {
                mergeAC(a, lo, lo+i-1, Math.min(lo+i-1+i, N-1)); // hi -- the mid+i, if it out of index, hi = N-1
            }
        }
    }

    private static class AC_saved{
        public static int[] ac_savedTopDown;
        public static int[] ac_savedBottomUp;

        public static void runExperiment(int n){
            // TODO: sort the array whose size is N, N = 1, 2, 3, ..., n
            ac_savedTopDown = new int[n];
            ac_savedBottomUp = new int[n];
            for (int i = 0; i < n; i++) {
                String[] a = sort.generateStringArray(i+1);
                sortTimeComplexity.sortTopDownAC(a);
                ac_savedTopDown[i] = ac;

                a = sort.generateStringArray(i+1);
                sortTimeComplexity.sortBottomUpAC(a);
                ac_savedBottomUp[i] = ac;
            }
        }
    }

    public static void main(String[] args) {
        // Test
        String[] a = sort.generateStringArray(10);
//        sortTimeComplexity.sortTopDownAC(a);
        sortTimeComplexity.sortBottomUpAC(a);
        System.out.println(ac);

        a = sort.generateStringArray(5);
//        sortTimeComplexity.sortTopDownAC(a);
        sortTimeComplexity.sortBottomUpAC(a);
        System.out.println(ac);


    }

}
