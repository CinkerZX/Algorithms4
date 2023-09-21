/**
 * Aim: return an int[] called perm, such that perm[i] is the index of the ith smallest entry in the array.
 * Idea: similar to the inversion,java
 * Instead of changing the value of the array, but changing their index's position in perm
 */
public class IndirectSort {
    static int[] perm;

    public static int[] IndirectSort(Comparable[] a){
        int len = a.length;
        perm = new int[len];
        for (int i = 0; i < len; i++) {
            perm[i] = i;
        }
        sortTopDownIndirectSort(a);
        return perm;
    }

    // lo, mid, hi are the index of perm
    public static void mergeCalInversion(Comparable[] a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
        for (int k = lo; k <=hi ; k++) {
            myaux[k-lo] = a[perm[k]];
        }
        // merge back to a[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            if (i>mid)
//                a[k] = myaux[j++ - lo];
                perm[k] = j++ - lo;
            else if (j>hi){
//                a[k] = myaux[i++ - lo];
                perm[k] = i++ - lo;}
            else if (sort.less(myaux[j-lo], myaux[i-lo])){
//                a[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
                perm[k] = j++ - lo;
            }
            else
//                a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
                i++;
        }
    }

    public static void sortTopDownIndirectSort(Comparable[] a){
        //TODO: call sortTopDown
        sortTopDownIndirectSort(a, 0, a.length-1);
    }

    public static void sortTopDownIndirectSort(Comparable[] a, int lo, int hi){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        sortTopDownIndirectSort(a, lo, mid);
        sortTopDownIndirectSort(a, mid+1, hi);
        //TODO: merge from the bottom to up
        mergeCalInversion(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        Comparable[] a = sort.generateStringArray(3);
        sort.printStringArray(a);
        IndirectSort(a);
        System.out.println("The ordered index is: ");
        sort.printIntArray(perm);
    }
}
