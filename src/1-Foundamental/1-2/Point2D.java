import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Point2D {
    //TODO: generate points in a unit square
    private final double x;
    private final double y;

    // Constructor
    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY() {
        return y;
    }

    public double distanTo(Point2D point){
        return Math.sqrt(Math.pow(Math.abs(this.x - point.getX()),2)+Math.pow(Math.abs(this.y - point.getY()),2));
//        System.out.println(dis);
    }

    public void draw(){
        StdDraw.point(this.getX(), this.getY());
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Point2D[] points = new Point2D[N];
        double dis = 1;
        for (int i = 0; i < N; i++) {
            Random rand1 = new Random();
            Random rand2 = new Random();
            points[i] = new Point2D(rand1.nextDouble(),rand2.nextDouble());
        }
        double temp;
        for (int i = 0; i <N ; i++) {
//            points[i].draw();
            for (int j = i+1; j < N; j++) {
                temp = points[i].distanTo(points[j]);
                if (temp<dis){dis = temp;}
            }
        }
        System.out.println("The smallest distance is: ");
        StdOut.printf("%.2f",dis);
    }
}
