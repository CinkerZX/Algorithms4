import edu.princeton.cs.algs4.StdOut;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * The {@Code DoublingTestErosRenyiModel} takes int T from command line
 * Perform T trails
 *
 * Use RandomConnections to generate a complete network with size N
 * For each N, print out the following:
 *      1. the value of N
 *      2. average number of connections that processed (aveCount)
 *      3. runing time / aveCount
 */

public class DoublingTestErosRenyiModel {
    public DoublingTestErosRenyiModel(){}// Constructor do nothing

    public static double testQuickUnion(int N, int T){
        int time = 0;
        for (int i = 0; i < T; i++) {
            time = time + RandomConnections.countQuickUnion(N);
        }
        return (double) time/(double) T;
    }

    public static double testQuickFind(int N, int T){
        int time = 0;
        for (int i = 0; i < T; i++) {
            time = time + RandomConnections.countQuickFind(N);
        }
        return (double) time/(double) T;
    }

    public static double testWeightedQuickUnion(int N, int T){
        int time = 0;
        for (int i = 0; i < T; i++) {
            time = time + RandomConnections.countWeightedQuickUnion(N);
        }
        return (double) time/(double) T;
    }

    public static void printEverything(int T, int N){
        //TODO: run the three experiments and print out the results
        System.out.println(N); // the value of N
        DecimalFormat df = new DecimalFormat("0.00");

        // QuickFind
        long startTime = System.nanoTime();
        double aveCountQuickFind = testQuickFind(N,T);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("QuickFind");
        System.out.println(aveCountQuickFind);
        System.out.println(df.format(totalTime/(T*aveCountQuickFind)));
        System.out.println("");

        // QuickUnion
        startTime = System.nanoTime();
        double aveCountQuickUnion= testQuickUnion(N,T);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("QuickUnion");
        System.out.println(aveCountQuickUnion);
        System.out.println(df.format(totalTime/(T*aveCountQuickUnion)));
        System.out.println("");

        //weightedQuickUnion
        startTime = System.nanoTime();
        double aveCountWeightedQuickUnion= testWeightedQuickUnion(N,T);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("WeightedQuickUnion");
        System.out.println(aveCountWeightedQuickUnion);
        System.out.println(df.format(totalTime/(T*aveCountWeightedQuickUnion)));
    }

    public static void main(String[] args) {
        int T = 0;
        try {
            T = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + args[0] + " must be an integer.");
            System.exit(1);
        }
        int N = 10;
//        printEverything(T,N);
//        System.out.println("********************************");
//        N = 20;
//        printEverything(T,N);
//        System.out.println("********************************");
//        N = 40;
//        printEverything(T,N);
//        System.out.println("********************************");
//        N = 80;
//        printEverything(T,N);
//        System.out.println("********************************");
//        N = 160;
//        printEverything(T,N);
//        System.out.println("********************************");
//        N = 320;
//        printEverything(T,N);
//        System.out.println("********************************");
//
//        N = 640;
//        printEverything(T,N);
//        System.out.println("********************************");

        N = 500;
        printEverything(T,N);
        System.out.println("********************************");

        N = 800;
        printEverything(T,N);
        System.out.println("********************************");

        N = 1000;
        printEverything(T,N);
        System.out.println("********************************");

        N = 1200;
        printEverything(T,N);
        System.out.println("********************************");

        N = 1400;
        printEverything(T,N);
        System.out.println("********************************");

    }

}
