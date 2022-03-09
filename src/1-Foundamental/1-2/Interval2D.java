import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Random;
import java.util.Scanner;

public class Interval2D implements Interval{
    private Interval1D x; // interval on X
    private Interval1D y; // interval on Y

    public Interval2D(Interval1D horizontal , Interval1D vertical){
        this.x = horizontal;
        this.y = vertical;
    }

    public Interval1D getX() {
        return x;
    }

    public Interval1D getY() {
        return y;
    }

    public Interval2D intersect(Interval2D scale){
        Interval1D labelX = new Interval1D(0,0);
        Interval2D label = new Interval2D(labelX,labelX);
        try{
            return new Interval2D(x.intersect(scale.getX()),this.y.intersect(scale.getY()));
        }
        catch (Exception IllegalArgumentException){
            return label;
        }
    }

    @Override
    public void printInterval() {

    }

    public boolean equals(Interval2D scale){
        if (this.x.equals(scale.getX()) && this.y.equals(scale.getY())){return true;}
        return false;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);

        Interval2D temp;
        Interval2D[] interval2Ds = new Interval2D[N];
        Interval1D interval = new Interval1D(0,0);
        Interval2D interval0 = new Interval2D(interval,interval);

        //TODO: generate N 2D intervals
        double width;
        double height;
        double left;
        double bottom;
        for (int i = 0; i < N; i++) {
            Random rand1 = new Random(); // width
            Random rand1x = new Random(); // start point
            Random rand2 = new Random();
            Random rand2y = new Random();
            width = min+rand1.nextDouble()*(max-min);
            left = rand1x.nextDouble();
            height = min+rand2.nextDouble()*(max-min);
            bottom = rand2y.nextDouble();
            interval2Ds[i] = new Interval2D(new Interval1D(left,left+width),new Interval1D(bottom,bottom+height));
            StdDraw.filledRectangle(left,bottom,width/2,height/2);
        }

        int numIntersectIntervalPairs = 0;
        int numSameIntervalPaires = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                temp = interval2Ds[i].intersect(interval2Ds[j]);
                if (!temp.equals(interval0)){
                    numIntersectIntervalPairs++;
                }
                if (interval2Ds[i].equals(interval2Ds[j])){
                    numSameIntervalPaires++;
                }
            }
        }

        System.out.println("The number of pairs of intervals that intersect: "+numIntersectIntervalPairs);
        System.out.println("The number of pairs of intervals that contained each other: "+numSameIntervalPaires);
    }

}