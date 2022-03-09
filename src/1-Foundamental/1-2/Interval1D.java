import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Scanner;

public class Interval1D implements Interval{
    private double l; // left boundary
    private double r; // right boundary

    public Interval1D(double left, double right) throws IllegalArgumentException{
        if (left> right){throw new IllegalArgumentException("The left boundary should be less or equal to the right one.");}
        this.l = left;
        this.r = right;
    }

    public double getL() {
        return l;
    }

    public double getR() {
        return r;
    }

    public Interval1D intersect(Interval1D scale){
        Interval1D label = new Interval1D(0,0);
        double newL = Math.max(this.l,scale.getL());
        double newR = Math.min(this.r,scale.getR());
        try {
            return new Interval1D(newL, newR);
        }
        catch (Exception IllegalArgumentException){
            System.out.println("There is no interval");
            return label;
        }
    }

    public void printInterval(){
        String s = "["+this.l+", "+this.r+"]";
        System.out.print(s);;
    }

    public boolean equals(Interval1D scale){
        if (this.l == scale.getL() && this.r == scale.getR()){return true;}
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input N interval as the following format: 0.1 0.3");
        int N = Integer.parseInt(args[0]);
        Interval1D[] intervals = new Interval1D[N];
        for (int i = 0; i < N; i++) {
            System.out.println("Please input interval"+(i+1)+": ");
            String line = input.nextLine();
            String[] splited = line.split(" ");
            intervals[i] = new Interval1D(Double.parseDouble(splited[0]),Double.parseDouble(splited[1]));
        }
        Interval1D temp;
        Interval1D interval = new Interval1D(0,0);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                temp = intervals[i].intersect(intervals[j]);
                if (!temp.equals(interval)){
                    intervals[i].printInterval();
                    System.out.print("   ");
                    intervals[j].printInterval();
                    StdOut.printf("\n");
                }
            }
        }
    }
}
