import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

/**
 *
 */
public class PlotRunningTime {
    public PlotRunningTime(){} // Constructor do nothing

    /**
     * Draw the running time with n (n is the size of the ordered array
     * @param arrayN array of n
     * @param actualT the average time that consume to order the array
     */
    public static void runningTimePlot(int[] arrayN, double[] actualT){
        int maxN = Arrays.stream(arrayN).max().getAsInt();
        double maxT = Arrays.stream(actualT).max().getAsDouble();
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(-1,maxN+5);
        StdDraw.setYscale(-0.5,maxT+5);
        StdDraw.line(-1,0,maxN+5,0); // x-axis
        StdDraw.line(0,-0.5,0,maxT+5); // y-axis
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < arrayN.length; i++) { // 10 100 1000 10000 ... 100000000
            double x = arrayN[i];
            double y = actualT[i];
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.point(x, y);
        }
    }
}
