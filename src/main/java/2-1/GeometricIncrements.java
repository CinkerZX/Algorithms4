import edu.princeton.cs.algs4.*;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * The {@Code GeometricIncrements} aims at finding the best 3 values of t that determines the increment sequence
 * 1, t, t^2, t^3, ... for shellsort wen N = 10^6
 *
 * Print the best 3 values of t, and the corresponding increment sequences
 *
 * Test t: 2, 3, 4, ..., 10. The best three are the following:
 *
 * The first t is 2
 *
 * 1   2   4   8   16   32   64   128   256   512   1024   2048   4096   8192   16384   32768   65536   131072   262144   524288
 * The second t is 3
 *
 * 1   3   9   27   81   243   729   2187   6561   19683   59049   177147   531441
 * The third t is 4
 *
 * 1   4   16   64   256   1024   4096   16384   65536   262144
 */

public class GeometricIncrements {
    public GeometricIncrements(){} // constructor, do nothing

    /**
     * This function is for constructing the increment sequence based on t
     * @param t
     * @return
     */
    public static int[] incrementSeq(int t){
        LinkedList<Integer> sequence = new LinkedList();
        for (int i = 0;  Math.pow(t,i)<Math.pow(10,6) ; i++) { //**********************************
            sequence.add((int) Math.pow(t,i));
        }
        int[] result = new int[sequence.size()];
        for (int i = 0; i < sequence.size(); i++) {
            result[i] = sequence.get(i);
        }
        return result;
    }

    /**
     * This function is to calculate the time cost for shellSort with pointed sequence
     * @param seq the pointed sequence
     * @param trials the number of trails
     * @return
     */
    public static double timeRandomInput(int[] seq, int trials)  {
        double total = 0.0;
        int n = (int) Math.pow(10,6);
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
            total += time(seq, a);
        }
        return total;
    }

    public static double time(int[] seq, Double[] a) {
        Stopwatch sw = new Stopwatch();
        Shell.sort(a, seq);
        return sw.elapsedTime();
    }

    /**
     * try t and record the corresponding time in a double[]: t = 2, 3, 4, 5, ....
     * @param scaleT The largest value of t that will try
     * @param trails # repeated experiment under the same setting
     * @return
     */
    public static Double[] experiment(int scaleT, int trails){
        Double[] timeCost = new Double[scaleT];
        int[] seq;
        for (int i = 1; i <= scaleT; i++) {
            seq = incrementSeq(i+1);
            timeCost[i-1] = timeRandomInput(seq, trails);
        }
        return timeCost;
    }

    /**
     * Return the best 3 values of t
     * @param expResults the running time, id is the corresponding t
     * @return
     */
    public static int[] best3t(Double[] expResults){
        //TODO: order expResults first, and then find the position of the smallest 3 values
        int[] result = new int[3];
        Double[] temp = expResults.clone();
        Selection.sort(expResults);
        for (int i = 0; i < 3; i++) {
            result[i] = findInArray(temp, expResults[i]);
        }
        return result;
    }

    // helping function: find the location of the double values
    public static int findInArray(Double[] array, Double x){
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == x){ result = i+2;} // t = 2,3,4,5
        }
        return result;
    }

    /**
     * This function is to print the sequence with known t
     * @param t
     */
    public static void printSeq(int t){
        System.out.println("");
        int[] seq = incrementSeq(t);
        for (int i = 0; i < seq.length; i++) {
            System.out.print(seq[i]+"   ");
        }
        System.out.println("");
    }

    /**
     * This is the main function to run the experiment
     * @param scaleT t = 2,3,4,5 ... scaleT >= 4
     * @param trails
     */
    public static void expMain(int scaleT, int trails){
        Double[] time = experiment(scaleT,trails);
        int[] bestTs = best3t(time);
        String[] order = new String[]{"first", "second", "third"};
        //TODO: printout the best t and the corresponding sequence
        for (int i = 0; i < 3; i++) {
            System.out.println("The "+order[i]+ " t is "+bestTs[i]);
            printSeq(bestTs[i]);
        }
    }


    public static void main(String[] args) {
//        //*********** Test ************
//        Double[] a = new Double[]{1.0,5.0,2.0,3.0};
//        Double[] temp = a.clone();
//        Selection.sort(a);
//        System.out.println("********* ordered a ********");
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i]+ "  ");
//        }
//        System.out.println("");
//        System.out.println("********* original a ********");
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(temp[i]+ "  ");
//        }
//        System.out.println(a[3]==temp[1]);

        expMain(10, 10);

    }

}
