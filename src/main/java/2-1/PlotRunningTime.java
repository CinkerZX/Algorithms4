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
        StdDraw.setXscale(-maxN*0.1,maxN*1.1);
        StdDraw.setYscale(-0.5,maxT*1.1);
        StdDraw.line(-maxN*0.1,0,maxN*1.1,0); // x-axis
        StdDraw.line(0,-0.5,0,maxT*1.1); // y-axis
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < arrayN.length; i++) { // 10 100 1000 10000 ... 100000000
            double x = arrayN[i];
            double y = actualT[i];
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.point(x, y);
        }
    }

    /**
     * Calculate the consumed time
     * @param Alg algorithm that want to test
     * @param sizeArray each value in the array is a length of array that want to test
     * @param trails
     */
    public static void mainExp(String Alg, int[] sizeArray, int trails){
        int nX = sizeArray.length;
        double[] actualT = new double[nX];
//        double[] predictT = new double[nX];
//        double[] rate = new double[nX];
        for (int i = 0; i < nX; i++) {
            actualT[i] = DoublingTestSortAlg.timeRandomInput(sizeArray[i],Alg, trails);
//            predictT[i] = DoublingTestSortAlg.predictTime(sizeArray[i],Alg);
//            rate[i] = actualT[i]/predictT[i];
        }
        runningTimePlot(sizeArray, actualT);
    }

    public static void main(String[] args) {
        int[] sizeArray = new int[20];
        for (int i = 0; i < 15; i++) {
            sizeArray[i] = (int) Math.pow(2,i+1);
        }
        mainExp("Insertion", sizeArray, 5);
    }
}
