import edu.princeton.cs.algs4.StdDraw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * The {@CODE visShellsortNumComparebyArraySize} class aims at testing the hypothesis that #Compares / size(array)
 * is a small constant
 *
 * Idea: calculate E(#Compares) of random Double[] with size n
 * n = 10^2, 10^3, 10^4, 10^5, ....
 * Then calculate the results #Compares / n
 * Visualize the dots plot
 *
 * ------------------------------------------------------------------------------------
 *
 */
public class visShellsortNumComparesbyArraySize {

    public static BigDecimal rateNumComparewithN(Comparable[] a){
        BigDecimal result = new BigDecimal(ShellSort.sortNumCompare(a));
        BigDecimal n = new BigDecimal(a.length);
        return result.divide(n);
    }

    public static Comparable[] randomArrayGenerator(int n){
        Random random = new Random();
        Comparable[] result = new Double[n];
        for (int i = 0; i < n; i++) {
            result[i] = random.nextDouble();
        }
        return result;
    }

    public static void trails(){
        int n = 1;
        int powerTimes = 8;
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(-1,powerTimes);
        StdDraw.setYscale(-0.5,2);
        StdDraw.line(-1,0,powerTimes,0); // x-axis
        StdDraw.line(0,-0.5,0,2); // y-axis
        StdDraw.setPenRadius(0.01);

        for (int i = 0; i < powerTimes; i++) { // 10 100 1000 10000 ... 100000000
            n = n*10;
            double x = i;
            BigDecimal scale = new BigDecimal(n);
            double y = rateNumComparewithN(randomArrayGenerator(n)).divide(scale).doubleValue();
            System.out.println(y);
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.point(x, y);
        }
    }

    public static void main(String[] args) {
        Comparable[] a = randomArrayGenerator(10);
        BigDecimal n = new BigDecimal(a.length);
        System.out.println(rateNumComparewithN(a).divide(n));

        a = randomArrayGenerator(100);
        n = new BigDecimal(a.length);
        System.out.println(rateNumComparewithN(a).divide(n));

        a = randomArrayGenerator(1000);
        n = new BigDecimal(a.length);
        System.out.println(rateNumComparewithN(a).divide(n));

        a = randomArrayGenerator(10000);
        n = new BigDecimal(a.length);
        System.out.println(rateNumComparewithN(a).divide(n));

        a = randomArrayGenerator(100000);
        n = new BigDecimal(a.length);
        System.out.println(rateNumComparewithN(a).divide(n));

//        trails();
    }

}
