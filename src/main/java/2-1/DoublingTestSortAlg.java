import edu.princeton.cs.algs4.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * The {Code DoublingTest} aims at testing the time consumption of different sorting algorithms
 *
 * The output of will be as following:
 * Algorithm: insertion
 * N            1000    2000    4000    8000    16000   32000   64000
 * PredictTime  t1      t2      t3      t4      t5      t6      t7
 * ActualTime   T1      T2      T3      T4      T5      T6      T7
 * Rate         t1/T1   t2/T2   t3/T3   t4/T4   t5/T5   t6/T6   t7/T7
 *
 * Result:
 * 1000      	2000      	4000      	8000      	16000     	32000
 * 0.0064    	0.0097    	0.0184    	0.076     	0.26      	1.1514
 * 1         	4         	16        	64        	256       	1024
 * 0.0064    	0.0025    	0.0012    	0.0012    	0.0011    	0.0012
 */


public class DoublingTestSortAlg {
    public DoublingTestSortAlg(){} //Constructor: do nothing

    /**
     * This function calculate the actual consumed time of algorithm 'alg'
     * @param n the length of the double lists
     * @param alg the name of the sorting algorithm
     * @param trials #repeating experiments
     * @return
     */
    public static double timeRandomInput(int n, String alg, int trials)  {
        double total = 0.0;
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            total += SortCompare.time(alg,GeometricIncrements.doublesGenerator(n));
        }
        return total/trials;
    }

    public static double timeRandomInput(int n, String alg, int trials, String DataDisType)  {
        double total = 0.0;
        Double[] testData;
        if      (DataDisType.equals("Gaussian"))    testData = NonUniformDistributions.GaussianDisGenerator(n);
        else if (DataDisType.equals("Poisson"))     testData = NonUniformDistributions.PoissionDisGenerator(n);
        else if (DataDisType.equals("Geometric"))   testData = NonUniformDistributions.GeometricDisGenerator(n);
        else if (DataDisType.equals("Discrete"))    testData = NonUniformDistributions.DiscreteDisGenerator(n);
        else throw new IllegalArgumentException("Invalid data distribution type: " + DataDisType);
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            total += SortCompare.time(alg, testData);
        }
        return total/trials;
    }


    /**
     * This function calculate the predicted time of algorithm 'alg'
     * @param n the length of double lists
     * @param alg the name of the sorting algorithm
     * @return
     */
    public static double predictTime(int n, String alg){
        double predictTime;
        if      (alg.equals("Insertion"))                  predictTime = (n*n)/1000000;
//        else if (alg.equals("InsertionWithSentinel"))      InsertionWithSentinel.sort(a);
//        else if (alg.equals("InsertionWithoutExch"))       InsertionWithoutExch.sort(a);
//        else if (alg.equals("sortint"))                    Insertion.sort(convertDoubleArray(a));
//        else if (alg.equals("sortInt"))                    Insertion.sortInt(convertDoubleArray(a));
//        else if (alg.equals("InsertionX"))                 InsertionX.sort(a);
//        else if (alg.equals("BinaryInsertion"))            BinaryInsertion.sort(a);
        else if (alg.equals("Selection"))                  predictTime = (n*n)/1000000;
//        else if (alg.equals("Bubble"))          Bubble.sort(a);
        else if (alg.equals("Shell"))                      predictTime = (n*Math.log(n))/1000000;
//        else if (alg.equals("Merge"))                      Shell.sort(a);
//        else if (alg.equals("MergeX"))                     MergeX.sort(a);
//        else if (alg.equals("MergeBU"))                    MergeBU.sort(a);
//        else if (alg.equals("Quick"))                      Quick.sort(a);
//        else if (alg.equals("Quick3way"))                  Quick3way.sort(a);
//        else if (alg.equals("QuickX"))                     QuickX.sort(a);
//        else if (alg.equals("Heap"))                       Heap.sort(a);
//        else if (alg.equals("System"))                     Arrays.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return predictTime;
    }

    /**
     * This function is for printing the results in the form of table
     * @param arrayN
     * @param actualT
     * @param predictT
     * @param rate
     */
    public static void printTable(int[] arrayN, double[] actualT, double[] predictT, double[] rate){
        //TODO: printout the results in good form
        printTableHelper(arrayN);
        printTableHelper(actualT);
        printTableHelper(predictT);
        printTableHelper(rate);
    }

    public static void printTable(int[] arrayN, double[] actualT){
        //TODO: printout the results in good form
        printTableHelper(arrayN);
        printTableHelper(actualT);
    }

    public static void printTableHelper(double[] array){
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        for (int i = 0; i < array.length; i++) {
            System.out.format("%-10s", df.format(array[i]));
            System.out.print("\t");
        }
        System.out.println("");
    }

    public static void printTableHelper(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.format("%-10s", array[i]);
            System.out.print("\t");
        }
        System.out.println("");
    }

    /**
     * Run the experiment, and call other functions
     * @param Alg the name of the algorithm
     * @param numDoubling   valN = (1000, 2000, ....), the length of valN
     * @param trails        the number of trails for each N
     */
    public static void mainExp(String Alg, int numDoubling, int trails){
        double[] actualT = new double[numDoubling];
        double[] predictT = new double[numDoubling];
        int[] arrayN = new int[numDoubling];
        double[] rate = new double[numDoubling];
        int n = 1000;
        for (int i = 0; i < numDoubling; i++) {
            actualT[i] = timeRandomInput(n,Alg, trails);
            predictT[i] = predictTime(n,Alg);
            arrayN[i] = n;
            rate[i] = actualT[i]/predictT[i];
            n = n*2;
        }
        printTable(arrayN,actualT,predictT, rate);
    }

    /**
     * Run the experiment, test on test dats that obeys certain nonUniform distribution
     * By default, test n = 1000, 2000, 4000, 8000
     * @param Alg
     * @param DataDisType
     */
    public static void mainExp(String Alg, String DataDisType){
        int numDoubling = 5;
        int trails = 10;
        double[] actualT = new double[numDoubling];
        int[] arrayN = new int[numDoubling];
        int n = 1000;
        for (int i = 0; i < numDoubling; i++) {
            actualT[i] = timeRandomInput(n, Alg, trails, DataDisType);
            arrayN[i] = n;
            n = n*2;
        }
        printTable(arrayN,actualT);
    }

    public static void main(String[] args) {
        mainExp("Insertion", 6, 3);
    }
}
