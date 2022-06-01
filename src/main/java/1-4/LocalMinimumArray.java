import java.util.LinkedList;

/**
 * The {@Code LocalMinimal}
 * Read in N long distinct integers, and find a local minimum pair (a[i-1] < a[i] < a[i+1])
 * take ~2logN times compares in the worst case.
 *
 * Idea: examine the middle value of a[N/2], and it's neighbor
 *       if it is local minimum, stop
 *       otherwise search in the half with the smaller neighbor
 *       Until researching search the same element.
 */


public class LocalMinimumArray {
    public LocalMinimumArray(){} // Do nothing in the constructor

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

    public static int ifLocalMinimum(int i, int[] a){
        if (i == 0){ // At the left edge, cannot find in the left part of a.length/2
            return (a.length/2+a.length)/2;
        }
        if (i == a.length-1){return -1;}
        if (a[i] < a[i+1] && a[i] < a[i-1]){ // Find the minimal
            return 0;
        }
        if (a[i-1] < a[i+1]){
            if (i/2 == 0){return (a.length/2+a.length)/2;}
            return i/2;
        } // return the middle with the smaller neighbor
        return (a.length-1+i)/2;
    }

    public static int findLocalMinimum(int[] a){
        LinkedList checked = new LinkedList();
        if (containsDuplicates(a)){throw new IllegalArgumentException("array contains duplicate integers");}
        int start = a.length/2;
        return findLocalMinimumHelper(start, a, checked);
    }

    public static int findLocalMinimumHelper(int i, int[] a, LinkedList indexes){
        indexes.add(i);
        int newStart = ifLocalMinimum(i, a);
        if (!indexes.contains(newStart)){
            if (newStart == 0){return i;}
            while(newStart != 0){
                if (newStart == -1){return -1;}
                return findLocalMinimumHelper(newStart,a, indexes);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-3, -2, -1, 0, 1, 2, 3, 4};
        System.out.println(findLocalMinimum(a)); //-1

        int[] b = new int[]{-3, -2, -1, 0, 1, 2, 3, 4,5};
        System.out.println(findLocalMinimum(b)); //-1

        int[] c = new int[]{-3, -2, -1, 0, 1, 2, 3, -5,10};
        findLocalMinimum(c); //-5
        System.out.println(c[findLocalMinimum(c)]);
    }
}










