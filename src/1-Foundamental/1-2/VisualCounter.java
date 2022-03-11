import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;


public class VisualCounter {
    private int curCount; // \in [-max, max]
    private int curN; // \in [0, N]
    private int N; // the max num of operations
    private int max; // the max absolute value for the counter

    public VisualCounter(int n, int m){
        this.curCount = 0;
        this.N = n;
        this.max = m;
        this.curN = 0;
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(-max,max);
    }

    public void increament() throws IllegalStateException{
        if (this.curN == N) throw new IllegalStateException("Already reach the maximum operation limit!");
        if (this.curCount == max) throw new IllegalStateException("Reach the maximum counting number!");
        this.curCount++;
        this.curN++;
        DrawCount();
    }

    public void decreament() throws IllegalStateException{
        if (this.curN == N) throw new IllegalStateException("Already reach the maximum operation limit!");
        if (this.curCount == -max) throw new IllegalStateException("Reach the maximum counting number!");
        this.curCount--;
        this.curN++;
        DrawCount();
    }

    private void DrawCount(){
        double x = (this.curN-1)*1 +.5;
        double y = this.curCount/2.0;
        double halfWidth = .5;
        double halfHeight = this.curCount/2.0;
        StdDraw.rectangle(x,y,halfWidth,halfHeight);
    }

    public static void main(String[] args) {
        VisualCounter vc = new VisualCounter(4, 3);
        vc.increament();
        vc.increament();
        vc.increament();
        vc.decreament();
        vc.decreament();
    }
    
    
}
