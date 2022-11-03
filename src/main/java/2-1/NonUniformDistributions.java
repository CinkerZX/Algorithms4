
import com.sun.deploy.net.proxy.WFirefoxProxyConfig;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Code @{NonUniformDistribution} aims at generating test data that with special distribution for the sorting algs
 *
 * 1. Gaussian
 * 2. Poisson
 * 3. Geometric
 * 4. Discrete
 * 5. Half 0, half 1
 * 6. Half 0, half the remainder 1, half the remainder 2, and so forth
 * 7. Half 0, half random int values
 */
public class NonUniformDistributions {
    public NonUniformDistributions(){}// Constructor, do nothing

    /**
     * This function is for generating test data in [-5, 5] obeys the Gaussian distribution
     * @param n, the length of the test data
     * @return
     */
    public static Double[] GaussianDisGenerator(int n){
        //TODO: generate a random double within [-5, 5], and another random number within [0,1]
        double x;
        double p;
        Double[] data = new Double[n];
        int i = 0;
        while(i<n){
            x = ThreadLocalRandom.current().nextDouble(-5, 5);
            p = ThreadLocalRandom.current().nextDouble(0, 1);
            if (checkGaussian(p,x)){data[i] = x; i++;}
        }
        return data;
    }

    /**
     * This function is for generating test data in [0,10] obeys the Poisson distribution (lambda = 1)
     * @param n, the length of the test data
     * @return
     */
    public static Double[] PoissionDisGenerator(int n){
        //TODO: generate a random double within [0, 10], and another random number within [0,1]
        double x;
        double p;
        Double[] data = new Double[n];
        int i = 0;
        while(i<n){
            x = ThreadLocalRandom.current().nextInt(0, 11);
            p = ThreadLocalRandom.current().nextDouble(0, 1);
            if (checkPoisson(p,x)){data[i] = x; i++;}
        }
        return data;
    }

    /**
     * This function is for generating test data in [0,5] obeys the Geometric distribution (p = 0.5)
     * @param n
     * @return
     */
    public static Double[] GeometricDisGenerator(int n){
        //TODO: generate a random double within [0, 5], and another random number within [0,1]
        double x;
        double p;
        Double[] data = new Double[n];
        int i = 0;
        while(i<n){
            x = ThreadLocalRandom.current().nextInt(0, 6);
            p = ThreadLocalRandom.current().nextDouble(0, 1);
            if (checkGeometric(p,x)){data[i] = x; i++;}
        }
        return data;
    }

    /**
     * This function is for generating test data in Bernoulli distribution, x in {0,1}, p = 0.5
     * @param n
     * @return
     */
    public static Double[] DiscreteDisGenerator(int n){
        //TODO: generate a random double within [0, 5], and another random number within [0,1]
        double x;
        double p;
        Double[] data = new Double[n];
        int i = 0;
        while(i<n){
            x= ThreadLocalRandom.current().nextInt(0, 2);
            p = ThreadLocalRandom.current().nextDouble(0, 1);
            if (p < 0.5){data[i] = x; i++;}
        }
        return data;
    }

    /**
     * This function is for generating test data in random order, with half 0, and half 1
     * @param n
     * @return
     */
    public static int[] Half0Half1(int n){
        //TODO: generate an array data = [0,0,0,..., 1,1,1,...], and shuffle the array element by generate a random index array
        int[] data = new int[n];
        Integer[] intArray = randomOrderInt(n);
        for (int i = 0; i < n/2; i++) {
            data[intArray[i]] = 0;
        }
        for (int i = n/2; i < n; i++) {
            data[intArray[i]] = 1;
        }
        return data;
    }

    /**
     * This function is for generating test data in random order, with half 0, the rest of the half 1, the rest of the half 2..
     * @param n
     * @return
     */
    public static int[] Half0HalfRestAdd1(int n){
        //TODO: generate an array data = [0,0,0,..., 1,1,1,...], and shuffle the array element by generate a random index array
        Integer[] intArray = randomOrderInt(n);
        int[] data = Half0HalfRestAdd1Helper(0, n, new int[n], 0);
        return reorderArrayWithOrder(data,intArray);
    }

    /** This function is for generating test data in random order, with half 0, and the rest of the half is random int
     *
     * @param n
     * @return
     */
    public static int[] Half0HalfRandom(int n){
        //TODO: Construct the array, then reorder
        int[] data = new int[n];
        for (int i = 0; i < n/2; i++) {
            data[i] = 0;
        }
        Random random = new Random();
        for (int i = n/2; i < n; i++) {
            data[i] = random.nextInt(100);
        }
        Integer[] intArray = randomOrderInt(n);
        return reorderArrayWithOrder(data, intArray);
    }

    //************* Helper functions
    public static boolean checkGaussian(double P, double X){
        ////TODO: calculate f(x), by default, assume mu = 0, var = 1
        double fx = Math.exp(-Math.pow(X,2)/2)/Math.sqrt(2*Math.PI);
        return P < fx;
    }

    public static boolean checkPoisson(double P, double X){
        //TODO: calculate f(x), by default, assume lambda = 1
        int x = (int) X;
        double fx = Math.pow(1, x)*Math.exp(-1)/factorize(x);
        return P < fx;
    }

    public static boolean checkGeometric(double P, double X){
        //TODO: calculate f(x), by default, assume p = 0.5
        int x = (int) X;
        double fx = Math.pow(0.5, x+1);
        return P < fx;
    }

    public static double factorize(int k){
        if(k == 0){return 1;}
        else{return k*factorize(k-1);}
    }

    /**
     * This function is for generating an array composed by {1,2,3,...., n} in random order
     * @param n
     * @return
     */
    public static Integer[] randomOrderInt(int n){
        Integer[] intArray = new Integer[n];
        for (int i = 0; i < n; i++) {
            intArray[i] = i;
        }
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        return intList.toArray(intArray);
    }

    /**
     * This function is for reorder an array with a appointed order
     * @param original: the original array
     * @param order: the appointed order
     * @return
     */
    public static int[] reorderArrayWithOrder(int[] original, Integer[] order){
        int n = original.length;
        int[] reorderedArray = new int[n];
        for (int i = 0; i < n; i++) {
            reorderedArray[i] = original[order[i]];
        }
        return reorderedArray;
    }

    public static int[] Half0HalfRestAdd1Helper(int star, int numEmpty, int[] arr, int val){
        if (numEmpty/2 != 0){
            for (int i = star; i < star+numEmpty/2; i++) {
                arr[i] = val;
            }
            star += numEmpty/2;
            numEmpty = arr.length - star;
            val += 1;
            return Half0HalfRestAdd1Helper(star, numEmpty,arr,val);
        }
        if (numEmpty == 1){
            arr[arr.length-1] = val;
            return arr;
        }
        return arr;
    }



    public static void main(String[] args) {
        Comparable[] a = NonUniformDistributions.GaussianDisGenerator(10);
        CornerCases.show(a);

//        a = NonUniformDistributions.PoissionDisGenerator(20);
//        CornerCases.show(a);
//
//        a = NonUniformDistributions.GeometricDisGenerator(20);
//        CornerCases.show(a);
//
//        a = NonUniformDistributions.DiscreteDisGenerator(20);
//        CornerCases.show(a);

        int[] b;
        b = NonUniformDistributions.Half0Half1(20);
        CornerCases.show(b);

        b = NonUniformDistributions.Half0HalfRestAdd1(20);
        CornerCases.show(b);

        b = NonUniformDistributions.Half0HalfRandom(20);
        CornerCases.show(b);
    }
}
