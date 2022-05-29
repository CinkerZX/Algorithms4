import java.util.Arrays;

/**
 * The {@Code ClosetPair}
 * Read in N long double array, and find the closest pair (has the smallest difference)
 * take linearithmic time in the worst case.
 *
 * Idea: sort the array, then just need to compare the a[i] with a[i+1]
 */
public class ClosestPair {
    private ClosestPair(){} // Do not instantiate

    public static double[] diffCalculator(double[] sortedA){
        int n = sortedA.length-1;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = Math.abs(sortedA[i]-sortedA[i+1]);
        }
        return result;
    }

    public static int closestPairIndex(double[] diff){
        int index = 0;
        double temp = diff[0];
        for (int i = 1; i < diff.length; i++) {
            if (temp>diff[i]){ // Find smaller difference;
                temp = diff[i];
                index = i;
            }
        }
        return index;
    }

    public static double[] closestPair(double[] a){
        Arrays.sort(a);
        double[] result = new double[2];
        int aimIndex = closestPairIndex(diffCalculator(a));
        result[0] = a[aimIndex];
        result[1] = a[aimIndex+1];
        return result;
    }

    public static void main(String[] args) {
        double[] myArray = new double[]{-3,-2,4,1,2,10.4,10.5,0.1,0};
        double[] closestPair = closestPair(myArray);
        for (int i = 0; i < closestPair.length; i++) {
            System.out.print(closestPair[i]+" ");
        }
    }
}







