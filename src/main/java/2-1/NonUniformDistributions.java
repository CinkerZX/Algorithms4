
import com.sun.deploy.net.proxy.WFirefoxProxyConfig;
import edu.princeton.cs.algs4.StdOut;

import java.util.concurrent.ThreadLocalRandom;
/**
 * Code @{NonUniformDistribution} aims at generating test data that with special distribution for the sorting algs
 *
 * 1. Gaussian
 * 2. Poisson
 * 3. Geometric
 * 4. Discrete
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

    public static void main(String[] args) {
        Comparable[] a = NonUniformDistributions.GaussianDisGenerator(10);
        CornerCases.show(a);

        a = NonUniformDistributions.PoissionDisGenerator(20);
        CornerCases.show(a);

        a = NonUniformDistributions.GeometricDisGenerator(20);
        CornerCases.show(a);

        a = NonUniformDistributions.DiscreteDisGenerator(20);
        CornerCases.show(a);


    }
}
