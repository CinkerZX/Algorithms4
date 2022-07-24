import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

/**
 * The {@Code AmortizedCostPlots} visualize the average cost of array access operations for QuickFindUF / QuickUnionUF
 *
 * For QuickFindUF: visualize average union function
 *
 * For QuickUnionUF: visualize average find function
 */
public class AmortizedCostPlotsQuickFind extends QuickFindUF {
    public int total;

    public AmortizedCostPlotsQuickFind(int n) {
        super(n);
        total = 0;
    }

    @Override
    public void union(int p, int q) {
        int N = this.getId().length;
        total++;
        if (p > N || q > N || p < 0 || q < 0) {
            throw new IllegalArgumentException("Both p, q should be in [0, N-1].");
        }
        //TODO: update the component of q to the component of p
        int comOfP = this.getId()[p];
        total++;
        int comOfQ = this.getId()[q];
        total++;
        if (comOfP != comOfQ) {
            for (int i = 0; i < N; i++) {
                total++;
                if (this.getId()[i] == comOfP) {
                    this.getId()[i] = comOfQ;
                    total++;
                }
            }
        }
    }

    public static void visualize(int[] eachFinal, int operationTime) { // N is the total test time
        //TODO: draw eachFinal & average total Final
//        double[] accuAverage = new double[operationTime];
        int accuFinal = 0;
        double[] x = new double[operationTime];
        StdDraw.setCanvasSize(1000,800);
        StdDraw.setXscale(-5,operationTime+5);
        StdDraw.setYscale(-5,1300);
        StdDraw.line(-5,0,operationTime+5,0); // x-axis
        StdDraw.line(0,-5,0,1300); // y-axis
        StdDraw.setPenRadius(0.01);

        for (int i = 0; i < operationTime; i++) {
            x[i] = i+1;
            accuFinal = accuFinal + eachFinal[i];
//            accuAverage[i] = accuFinal / x[i];
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.point(x[i], eachFinal[i]);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(x[i], accuFinal / x[i]);
        }
    }

    public int[] trailExe(int numTrails){
        int[] eachFinal = new int[numTrails];
        int p; //p, q in [0, N-1]
        int q;
        int numNodes = this.getId().length;
        for (int i = 0; i < numTrails; i++) {
            p = (int) ((Math.random() * (numNodes)));
            q = (int) ((Math.random() * (numNodes)));
            this.union(p,q);
            eachFinal[i] = this.total;
            this.total =0; // after each operation initialize again
        }
        return eachFinal;
    }

    public void visualizeCostQuickFind(int numTrails){
        int[] eachFinal = this.trailExe(numTrails);
        this.visualize(eachFinal,numTrails);
    }

    public static void main(String[] args) {
        AmortizedCostPlotsQuickFind myVisulaQuickFind = new AmortizedCostPlotsQuickFind(625);
        myVisulaQuickFind.visualizeCostQuickFind(900);
    }
}
