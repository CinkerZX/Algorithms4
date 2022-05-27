import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *  The {@code ThreeSumFaster}
 *  Read in N long integers and counts the number of 3-tuples that sum to exactly 0.
 *  take proportional N^2 time in the worst case.
 *  Idea: take the opposite number of the array a, then get the intersection of a and -a
 *
 *  @author XinXinZhou
 */

public class ThreeSumFaster {
    private ThreeSumFaster(){} // Do not instantiate

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

    /**
     * Returns the number of triples (i, j) with {@code i < j < l}
     * such that {@code a[i] + a[j] +a[l]== 0}.
     */
    public static int count3sum(int[] a) {
        if (containsDuplicates(a)){throw new IllegalArgumentException("array contains duplicate integers");}
        int[] b = getOpposite(a);
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length;
        int[] sumOfIJ = new int[n*(n-1)/2]; // n-1 + n-2 + ... + 1 = (1+n-1)(n-1)/2 = n(n-1)/2
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                sumOfIJ[temp] = a[i]+a[j];
                temp++;
            }
        }
        Arrays.sort(sumOfIJ);
        intersectionOf2ArrayFlexible inter = new intersectionOf2ArrayFlexible(b,sumOfIJ);
        LinkedList results = inter.intersection();
        return results.size()/3;
    }

    public static int[] getOpposite(int[] a){
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = (-1)*a[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-2, -1, 0, 1,2,3};
        System.out.println(count3sum(a)); // -2 -1 3; -2 0 2; -1 0 1; #3

        int[] b = new int[]{-5, -1, 0, 1,2,3};
        System.out.println(count3sum(b)); // -5 2 3; -1 0 1; #2

        int[] c = new int[]{-5, -1, 0, 1,2,3,5,6};
        System.out.println(count3sum(c)); // -5 2 3; -5,0,5; -5,-1,6; -1 0 1; #4
    }
}
