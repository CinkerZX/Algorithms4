import edu.princeton.cs.algs4.StdDraw;

/**
 * The {@Code AmortizedCostPlotsQuickUnion} visualize the average cost of array access operations for QuickUnionUF
 *
 * For QuickUnionUF: visualize average find function
 *
 * Idea:
 *      a. add var 'total' to record the times of accessing id array
 *      b. amending the raceRoot function by updating var total (function 'find' call 'traceRoot')
 *      c. function 'visualizeCostQuickUnion' to call function 'trailExe' and visualize the amortized cost
 *      d. function 'trailExe' does the following multiplu times:
 *                             1) constructs net by execute one union operation
 *                             2) executes the find operation
 *                             3) record 'total' in each union operation
 *         Note: var 'total' needs to be initialized after each find operation
 *      e. function 'visualize' is static, can be reused for class AmortizedCostPlotsQuickFind also
 */

public class AmortizedCostPlotsQuickUnion extends QuickUnionUF{
    public int total;

    public AmortizedCostPlotsQuickUnion(int n) {
        super(n);
        total = 0;
    }

    @Override
    public int traceRoot(int p){
        this.total++;
        //TODO return the root of p
        if (pa[p] == p){return p;}
        else{return traceRoot(pa[p]);}
    }

    public int[] trailExe(int numTrails){
        int[] eachFinal = new int[numTrails];
        int p; //p,q,findI in [0, N-1]
        int q;
        int findI;
        int numNodes = this.pa.length;
        for (int i = 0; i < numTrails; i++) {
            p = (int) ((Math.random() * (numNodes)));
            q = (int) ((Math.random() * (numNodes)));
            this.union(p,q);
            findI = (int) ((Math.random() * (numNodes)));
            this.find(findI);
            eachFinal[i] = this.total;
            this.total =0; // after each operation initialize again
        }
        return eachFinal;
    }

    public void visualizeCostQuickUnion(int numTrails){
        int[] eachFinal = this.trailExe(numTrails);
        AmortizedCostPlotsQuickFind.visualize(eachFinal,numTrails,100);
    }

    public static void main(String[] args) {
        AmortizedCostPlotsQuickUnion myVisulaQuickUnion = new AmortizedCostPlotsQuickUnion(625);
        myVisulaQuickUnion.visualizeCostQuickUnion(900);
    }
}
