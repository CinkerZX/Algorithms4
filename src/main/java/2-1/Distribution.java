import com.sun.javafx.logging.JFRInputEvent;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * The {@Code Distribution} aims at visualize the average running times of a series of repeated sorting
 *
 * Idea: an infinite loop running sort()
 *
 * Visualize: 1) meaning time, 2) the distribution of time
 */
public class Distribution {
    private double meanT;
    private LinkedList timeA;
    public int times;

    public Distribution(){
        meanT = 0;
        timeA = new LinkedList();
        times = 0;
    }

    public double getMeanT() {
        return meanT;
    }

    public void updateMeanT(double t) {
        this.meanT = (this.meanT*times+t)/(times+1);
        times = times+1;
    }

    public LinkedList getTimeA() {
        return timeA;
    }

    public void updateTimeA(double timeA) {
        this.timeA.add(timeA);
    }

    /**
     * Calculate the consumed time
     * @param Alg algorithm that want to test
     * @param size the length of the array
     */
    public void mainExp(String Alg, int size) throws InterruptedException {
        //TODO: infinite loop, draw the time distribution every 3 seconds
        Distribution distribution = new Distribution();
        double t = 0;
        long startTime = System.currentTimeMillis(); //fetch starting time
        while(true){
            while(false||(System.currentTimeMillis()-startTime)<3)
            {
                t = DoublingTestSortAlg.timeRandomInput(size,Alg, 1);
                distribution.updateMeanT(t);
                distribution.updateTimeA(t);
            }
            distributionVisualize(this.getTimeA(), this.times);
//            TimeUnit.SECONDS.sleep(2);
            StdDraw.clear();
            startTime = System.currentTimeMillis(); // update time
        }

    }

//    public static void meanTimeVisualize(double aveT, int T){
//        int maxN = 20000;
//        StdDraw.setCanvasSize(1000,1000);
//        StdDraw.setXscale(-1,maxN+5);
//        StdDraw.setYscale(-0.5,aveT*2);
//        StdDraw.line(-1,0,maxN+5,0); // x-axis
//        StdDraw.line(0,-0.5,0,aveT*2); // y-axis
//        StdDraw.setPenRadius(0.01);
//        StdDraw.setPenColor(StdDraw.GRAY);
//        StdDraw.point(T,aveT);
//    }

    public static void distributionVisualize(LinkedList times, int T) throws InterruptedException {
        //TODO: draw the results every 3 seconds
        calDistribution(linkedList2Double(times, T), T);
    }

    // distributionVisualize Helper functions
    public static void calDistribution(Double[] times, int T) throws InterruptedException {
        //TODO: sort -> get the largest and smallest -> divide scales -> calculate the distribution of each scale
        Insertion.sort(times);
        double minT = times[0];
        double maxT = times[T-1];
        double interval = (maxT - minT)/5;
        double[] freq = new double[5]; // 5 scales
        for (int i = 0; i < T; i++) {
            if (minT <= times[i] &&  times[i]< minT+interval) freq[0] += 1;
            else if(minT+interval <= times[i] &&  times[i]< minT+2*interval) freq[1] += 1;
            else if(minT+2*interval <= times[i] &&  times[i]< minT+3*interval) freq[2] += 1;
            else if(minT+3*interval <= times[i] &&  times[i]< minT+4*interval) freq[3] += 1;
            else freq[4] += 1;
        }
        drawPanel(minT, maxT,0 ,1);
        drawBarPlot(freq,interval,minT, T);
    }

    public static void drawPanel(double minX, double maxX, double minY, double maxY){
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(-minX*0.1,maxX*1.1);
        StdDraw.setYscale(-minY*0.1,maxY*1.1);
        StdDraw.line(-minX*0.1,0,minX*1.1,0); // x-axis
        StdDraw.line(0,-minY*0.1,0,maxY*1.1); // y-axis
    }

    public static void drawBarPlot(double[] freq, double interval, double minT, int T) throws InterruptedException {
        //TODO: calculate rate and draw bar plot
        double left = minT;
        for (int i = 0; i < freq.length; i++) {
            StdDraw.rectangle(left+interval/2, freq[i]/(2*T), 0.45*interval, freq[i]/T);
            left = left+interval;
        }
//        TimeUnit.SECONDS.sleep(2);
//        StdDraw.clear();
    }

    public static Double[] linkedList2Double(LinkedList times, int T){
        //TODO: convert the linked list to double array
        Double[] result = new Double[T];
        for (int i = 0; i < T; i++) {
            result[i] = (Double) times.removeFirst();
        }
        return result;
    }

    public static void main(String[] args) {
        Distribution exp = new Distribution();

    }

}
