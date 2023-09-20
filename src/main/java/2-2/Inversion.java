/**
 * Aim: calculate the number of inversions of a given array
 * For example, [3, 4, 1, 2] there are [3, 1] [4, 1] [3, 2] [4, 2] four inversions
 * Idea: amend the merge sort algorithm to count the accumulated swap times
 */
public class Inversion {
    static int numInversion = 0;
    public static int inveresionCal(Comparable[] a){
        sortTopDownCalInversion(a);
        return numInversion;
    }

    public static void mergeCalInversion(Comparable[] a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
        for (int k = lo; k <=hi ; k++) {
            myaux[k-lo] = a[k];
        }
        // merge back to a[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            if (i>mid)
                a[k] = myaux[j++ - lo];
            else if (j>hi){
                a[k] = myaux[i++ - lo];
                numInversion++;}
            else if (sort.less(myaux[j-lo], myaux[i-lo])){
                a[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
                numInversion++;
                System.out.println("inversion");
            }
            else
                a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
        }
    }

    public static void sortTopDownCalInversion(Comparable[] a){
        //TODO: call sortTopDown
        sortTopDownCalInversion(a, 0, a.length-1);
    }
    public static void sortTopDownCalInversion(Comparable[] a, int lo, int hi){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        sortTopDownCalInversion(a, lo, mid);
        sortTopDownCalInversion(a, mid+1, hi);
        //TODO: merge from the bottom to up
        mergeCalInversion(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        Comparable[] a = sort.generateStringArray(5);
        sort.printStringArray(a);
        int number = inveresionCal(a);
        sort.printStringArray(a);
        System.out.println("The number of inversion of this array is "+ number+".");
    }

}
