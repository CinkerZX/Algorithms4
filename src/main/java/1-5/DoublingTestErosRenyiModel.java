import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.OptionalDouble;
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

    public static double[] timeConsume(int T, int N){
        //TODO: run the three experiments and return the results
        double[] runningTime = new double[3]; // quickFind, quickUnion, weightedQuickUnion

        // QuickFind
        long startTime = System.nanoTime();
        testQuickFind(N,T);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        runningTime[0] = totalTime/(1000*T);

        // QuickUnion
        startTime = System.nanoTime();
        testQuickUnion(N,T);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        runningTime[1] = totalTime/(1000*T);

        //weightedQuickUnion
        startTime = System.nanoTime();
        testWeightedQuickUnion(N,T);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        runningTime[2] = totalTime/(1000*T);

        return runningTime;
    }

    public static double[][] trailExp(int T){
        //TODO: test N = 10, 20, 30, 40.. 1500
        double[][] runingTime = new double[3][10]; // 3 rows: quickFind, quickUnion, weightedQuickUnion
        double[] oneExp = new double[3];
        for (int i = 1; i <= 10; i++) {
            oneExp = timeConsume(T, 10*i); // run one trail
            for (int j = 0; j < 3; j++) {
                runingTime[j][i-1] = oneExp[j]; // save the results
            }
        }
        return runingTime;
    }

    public static void visualize(double[][] timeConsume, int N) { // N = 1500
        //TODO: draw eachFinal & average total Final
        double[] timeConsumeQF = timeConsume[0];
        double[] timeConsumeQU = timeConsume[1];
        double[] timeConsumeWQU = timeConsume[2];
        double[] x = new double[N];
        double Yscale = Arrays.stream(timeConsumeQF).max().getAsDouble();
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(-5,N+5);
        StdDraw.setYscale(-5,Yscale+5);
        StdDraw.line(-5,0,N+5,0); // x-axis
        StdDraw.line(0,-5,0,Yscale+5); // y-axis
        StdDraw.setPenRadius(0.01);

        for (int i = 1; i <= N/10; i++) {
            x[i] = i*10;
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.point(x[i], timeConsumeQF[i-1]);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(x[i], timeConsumeQU[i-1]);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(x[i], timeConsumeWQU[i-1]);
        }
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

//        N = 500;
//        printEverything(T,N);
//        System.out.println("********************************");
//
//        N = 800;
//        printEverything(T,N);
//        System.out.println("********************************");

        double[][] timeConsume = trailExp(T);
        visualize(timeConsume,100);

    }

}
