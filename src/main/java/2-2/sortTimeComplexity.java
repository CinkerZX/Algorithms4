import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class sortTimeComplexity {
    // no constructor
    private static int ac; // ac := access time
    private static Comparable[] aux;

    private static void mergeAC(Comparable[] a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
        for (int k = lo; k <=hi ; k++) {
            myaux[k-lo] = a[k];
            ac = ac+2;
        }
        // merge back to a[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            ac = ac+2;
            if (i>mid)
                a[k] = myaux[j++ - lo];
            else if (j>hi)
                a[k] = myaux[i++ - lo];
            else if (ShellSort.less(myaux[j-lo], myaux[i-lo])){
                a[k] = myaux[j++ - lo];
                ac = ac+2;
            }
            else
                a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
        }
    }

    // iteration: call sort[a, lo, hi], until the smallest unit a[0, 1], a[2, 3], ...
    private static void sortTopDownAC(Comparable[] a){
        //TODO: call sortTopDown
        ac = 0; //init
        aux = new Comparable[a.length]; //construct aux
        sortTopDownAC(a, 0, a.length-1);
    }
    private static void sortTopDownAC(Comparable[] a, int lo, int hi){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        sortTopDownAC(a, lo, mid);
        sortTopDownAC(a, mid+1, hi);
        //TODO: merge from the bottom to up
        mergeAC(a, lo, mid, hi);
    }

    // Idea: building small solutions into larger ones
    private static void sortBottomUpAC(Comparable[] a){
        //TODO: use two for loop to merge
        int N = a.length;
        ac = 0;
        aux = new Comparable[N]; //construct aux
        for (int i = 1; i < N; i = i+i) { // i-- subarray size
            for (int lo = 0; lo < N-i ; lo += i+i) {
                mergeAC(a, lo, lo+i-1, Math.min(lo+i-1+i, N-1)); // hi -- the mid+i, if it out of index, hi = N-1
            }
        }
    }

    // Generate a array of function f(x), x = 1, 2, ..., n
    public static double[] funcArrayGen(int n){
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = 6*(i+1)*Math.log(i+1) / Math.log(2);
        }
        return arr;
    }

    // Visualize
    public static void linePlot(int[] a, int[] b, double[] c) throws InterruptedException {
        //TODO: Clear canvas and create canvas, visualize array a
        StdDraw.clear();
        int width = a.length;
        double maxA = Arrays.stream(a).max().getAsInt();
        double maxB = Arrays.stream(b).max().getAsInt();
        double maxC = Arrays.stream(c).max().getAsDouble();
        double[] max = new double[]{maxA, maxB, maxC};
        double height = Arrays.stream(max).max().getAsDouble();

        StdDraw.setCanvasSize(300, 280);
        StdDraw.setXscale(-5,5*width+5);
        StdDraw.setYscale(-1,height/(3*width)+1);
        StdDraw.setPenRadius(0.005);

        StdDraw.setPenColor(Color.red);
        for (int i = 0; i < width; i++) {
            StdDraw.point(5*(i+1),a[i]/(3*width));
        }

        StdDraw.setPenColor(Color.blue);
        for (int i = 0; i < width; i++) {
            StdDraw.point(5*(i+1),b[i]/(3*width));
        }

        StdDraw.setPenColor(Color.black);
        for (int i = 0; i < width; i++) {
            StdDraw.point(5*(i+1),c[i]/(3*width));
        }
    }

    private static class AC_saved{
        public static int[] ac_savedTopDown;
        public static int[] ac_savedBottomUp;

        public static void runExperiment(int n){
            // TODO: sort the array whose size is N, N = 1, 2, 3, ..., n
            ac_savedTopDown = new int[n];
            ac_savedBottomUp = new int[n];
            for (int i = 0; i < n; i++) {
                String[] a = sort.generateStringArray(i+1);
                sortTimeComplexity.sortTopDownAC(a);
                ac_savedTopDown[i] = ac;

                a = sort.generateStringArray(i+1);
                sortTimeComplexity.sortBottomUpAC(a);
                ac_savedBottomUp[i] = ac;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Test
//        String[] a = sort.generateStringArray(10);
//        sortTimeComplexity.sortTopDownAC(a);
//      //sortTimeComplexity.sortBottomUpAC(a);
//        System.out.println(ac);

//        a = sort.generateStringArray(5);
//        sortTimeComplexity.sortTopDownAC(a);
//        sortTimeComplexity.sortBottomUpAC(a);
//        System.out.println(ac);

          // Test
//        AC_saved.runExperiment(10);
//        int[] bu = AC_saved.ac_savedBottomUp;
//        int[] td = AC_saved.ac_savedTopDown;
//        System.out.println(Arrays.toString(bu));
//        // [0, 8, 22, 38, 54, 74, 96, 116, 148, 166]
//        System.out.println(Arrays.toString(td));
//        // [0, 10, 24, 36, 56, 76, 92, 106, 140, 154]
        AC_saved.runExperiment(512);
        int[] bu = AC_saved.ac_savedBottomUp;
        System.out.println(Arrays.toString(bu));
        int[] td = AC_saved.ac_savedTopDown;
        System.out.println(Arrays.toString(td));
        double[] upperbound = funcArrayGen(512);
        linePlot(td, bu, upperbound);

    }

}
