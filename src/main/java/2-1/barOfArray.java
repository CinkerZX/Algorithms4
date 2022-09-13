import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class barOfArray {
    //
    private barOfArray(){}
    /***************************************************************************
     * Visualization functions
     ***************************************************************************/
    public static void show(Comparable[] a) throws InterruptedException {
        //TODO: Clear canvas and create canvas, visualize array a
        StdDraw.clear();
        int width = a.length;
        StdDraw.setCanvasSize(width*10,500);
        StdDraw.setXscale(-1,2*width+1);
        StdDraw.setYscale(0,6);

        StdDraw.setPenRadius(0.015);
        StdDraw.setPenColor(Color.gray);

        for (int i = 0; i < width; i++) {
            StdDraw.line(i*2,0,i*2,(double) a[i]);
        }
        TimeUnit.SECONDS.sleep(1);
    }

    public static Double[] arrayGenerator(int n){
        //TODO: Generate a random double array of length n
        Double[] result = new Double[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            result[i] = 5 * r.nextDouble();
        }
        return result;
    }
}
