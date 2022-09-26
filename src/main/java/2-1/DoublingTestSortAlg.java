import edu.princeton.cs.algs4.*;

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

    /**
     * This function calculate the predicted time of algorithm 'alg'
     * @param n the length of double lists
     * @param alg the name of the sorting algorithm
     * @return
     */
    public static double predictTime(int n, String alg){
        double predictTime;
        if      (alg.equals("Insertion"))                  predictTime = n*n;
//        else if (alg.equals("InsertionWithSentinel"))      InsertionWithSentinel.sort(a);
//        else if (alg.equals("InsertionWithoutExch"))       InsertionWithoutExch.sort(a);
//        else if (alg.equals("sortint"))                    Insertion.sort(convertDoubleArray(a));
//        else if (alg.equals("sortInt"))                    Insertion.sortInt(convertDoubleArray(a));
//        else if (alg.equals("InsertionX"))                 InsertionX.sort(a);
//        else if (alg.equals("BinaryInsertion"))            BinaryInsertion.sort(a);
        else if (alg.equals("Selection"))                  predictTime = n*n;
//        else if (alg.equals("Bubble"))          Bubble.sort(a);
        else if (alg.equals("Shell"))                      predictTime = n*Math.log(n);
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

    public static void printTable(double[] actualT, double[] predictT){
        //TODO: printout the results in good form

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
//        for (int i = 0; i < ; i++) {
//
//        }
    }


}
