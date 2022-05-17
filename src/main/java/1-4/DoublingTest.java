import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;

import static edu.princeton.cs.algs4.StdDraw.*;

/**
 *  The {@code DoublingTest} class provides a client for measuring
 *  the running time of a method using a doubling test.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DoublingTest {
    private static final int MAXIMUM_INTEGER = 1000000;

    // This class should not be instantiated.
    private DoublingTest() { }

    private static boolean containsDuplicates(int[] a, int n) { //n is the index of the last filled element of a
        for (int i = 0; i < n; i++)
            if (a[n] == a[i]) return true;
        return false;
    }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     *   with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
            while (containsDuplicates(a,i)){
                a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
            }
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    /**
     * Draw the plot
     * @param X: for arrays of size 250, 500, 1000, 2000, and so forth.
     * @param Y: the consumed time
     * @param maxY: the scale of Y axis
     */
    public static void DrawPlot(LinkedList X, LinkedList Y, int maxY){
//        setCanvasSize((int) Math.log((Double) X.peekLast()), maxY);
        Double x;
        Double y;
        setXscale(Math.log((Double) X.peekFirst())-1, Math.log((Double) X.peekLast())+1);
        setYscale((Double) Y.peekFirst()-1, (Double) Y.peekLast()+1.0);
        while(!X.isEmpty()){
            DrawPlotHelper(Math.log((Double) X.pollFirst()),(Double) Y.pollFirst());
        }
    }

    public static void DrawPlotHelper(double X, double Y){
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(X, Y);
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        long limitTime = 6;
        // save the x and y of points into a linked list
        LinkedList<Double> x = new LinkedList<>();
        LinkedList<Double> y = new LinkedList<>();

        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            if (time>limitTime){break;}
            StdOut.printf("%7d %7.1f\n", n, time);
            x.add(Double.valueOf(n));
            y.add(time);
        }
        DrawPlot(x,y, (int) limitTime);
    }
}
