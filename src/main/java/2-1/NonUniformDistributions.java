
import java.util.concurrent.ThreadLocalRandom;
/**
 * Code @{NonUniformDistribution} aims at generating test data that with special distribution for the sorting algs
 *
 * 1. Gaussian
 * 2. Poisson
 * 3. Geometric
 * 4. Discrete
 *
 */
public class NonUniformDistributions {
    public NonUniformDistributions(){}// Constructor, do nothing

    /**
     * This function is for generating test data obeys the Gaussian distribution
     * @param n, the length of the test data
     * @return
     */
    public static Comparable[] GaussianDisGnerator(int n){
        //TODO: generate a random double within [-5, 5], and another random number within [0,1]
        Comparable x;
        double p;
        Comparable[] data = new Comparable[n];
        int i = 0;
        while(i<n){
            x= ThreadLocalRandom.current().nextDouble(-5, 5);
            p = ThreadLocalRandom.current().nextDouble(0, 1);
            if (checkGaussian(p,x)){data[i] = x; i++;}
        }
        return data;
    }

    /**
     * This function is for generating test data obeys the Poisson distribution
     * @param n, the length of the test data
     * @param lambda, parameters for possion distribution
     * @return
     */
    public static Comparable[] PoissionDisGnerator(int n, double lambda){
        //TODO: generate a random double within [-5, 5], and another random number within [0,1]
        Comparable x;
        double p;
        Comparable[] data = new Comparable[n];
        int i = 0;
        while(i<n){
            x= ThreadLocalRandom.current().nextInt(0, 20);
            p = ThreadLocalRandom.current().nextDouble(0, 1);
            if (checkPoisson(p,x, lambda)){data[i] = x; i++;}
        }
        return data;
    }

    //************* Helper functions
    public static boolean checkGaussian(double P, Comparable X){
        double fx = Math.exp(-Math.pow((Double) X,2)/2)/Math.sqrt(2*Math.PI);
        return P < fx;
    }

    public static boolean checkPoisson(double P, Comparable X, double LAMBDA){
        int x = (int) X;
        double fx = Math.pow(LAMBDA, x)*Math.exp(-1*LAMBDA)/factorize(x);
        return P < fx;
    }

    public static int factorize(int k){
        if(k == 0){return 1;}
        else{return k*factorize(k-1);}
    }

    public static void main(String[] args) {
        Comparable[] a = NonUniformDistributions.GaussianDisGnerator(10);
        CornerCases.show(a);

        a = NonUniformDistributions.PoissionDisGnerator(20, 4);
        CornerCases.show(a);


    }
}
