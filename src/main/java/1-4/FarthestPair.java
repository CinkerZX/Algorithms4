import java.util.Arrays;

/**
 * The {@Code FarthestPair}
 * Read in N long double array, and find the farthest pair (has the largest difference)
 * take linearithmic time in the worst case.
 *
 * Idea: sort the array, then just need to compare the a[i] with a[i+1]
 */
public class FarthestPair {
    private FarthestPair(){} // Do not instantiate

    public static double[] farthestPair(double[] a){
        Arrays.sort(a);
        double[] result = new double[2];
        result[0] = a[0];
        result[1] = a[a.length-1];
        return result;
    }

    public static void main(String[] args) {
        double[] myArray = new double[]{-3,-2,4,1,2,10.4,10.5,0.1,0};
        double[] fp = farthestPair(myArray);
        for (int i = 0; i < fp.length; i++) {
            System.out.print(fp[i]+" ");
        }
    }
}







