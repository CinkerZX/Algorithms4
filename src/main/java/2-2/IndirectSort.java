import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.List;

/**
 * Aim: return an int[] called perm, such that perm[i] is the index of the ith smallest entry in the array.
 * Idea: similar to the inversion,java
 * Instead of changing the value of the array, but changing their index's position in perm
 */
public class IndirectSort {
    static Integer[] perm;

    public static Integer[] IndirectSort(Comparable[] a){
        int len = a.length;
        perm = new Integer[len];
        for (int i = 0; i < len; i++) {
            perm[i] = i;
        }
        sortTopDownIndirectSort(a);
        return perm;
    }

    // lo, mid, hi are the index of perm
    public static void mergeCalInversion(Comparable[] a, int lo, int mid, int hi){
        //Todo: sort the index of a
        Integer[] myaux = new Integer[hi-lo+1]; //copy perm[] to aux[]
        for (int k = lo; k <=hi ; k++) {
            myaux[k-lo] = perm[k];
        }
        // merge back to a[lo, hi]
        int i = Arrays.asList(myaux).indexOf(lo), j = Arrays.asList(myaux).indexOf(mid+1); // i, j are the index of aux
        int indexOfPerm;
        for (int k = lo; k <= hi ; k++) {
            sort.printIntArray(perm);
            indexOfPerm = Arrays.asList(myaux).indexOf(k)+lo;
            if (i == -1){
                perm[indexOfPerm] = myaux[j];
                j = Arrays.asList(myaux).indexOf(myaux[j]+1);
            }
            else if (j == -1 || myaux[j]>hi){
                perm[indexOfPerm] = myaux[i];
                i = Arrays.asList(myaux).indexOf(myaux[i]+1);
            }
            else if (sort.less(a[j+lo], a[i])){
                perm[indexOfPerm] = myaux[j];
                j = Arrays.asList(myaux).indexOf(myaux[j]+1); // *******
                if(j!= -1){i = Arrays.asList(myaux).indexOf(perm[indexOfPerm]);}
            }
            else {
                perm[indexOfPerm] = myaux[i];
                i = Arrays.asList(myaux).indexOf(myaux[i] + 1);
                if(i == j){i = Arrays.asList(myaux).indexOf(myaux[i]-1);
                    System.out.println("k: "+ indexOfPerm); } //***********
            }
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
        Comparable[] a = new Comparable[]{"e", "c", "d","c"};
        sort.printStringArray(a);
        IndirectSort(a);
        System.out.println("The ordered index is: ");
        sort.printIntArray(perm);
//        Integer[] x = new Integer[]{1,2,3};
//        System.out.println(Arrays.asList(x).indexOf(1));
    }
}
