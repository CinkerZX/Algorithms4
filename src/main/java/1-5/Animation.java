import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Scanner;

/**
 * The {@CODE Animation} class is for visualizing RandomGrid
 *
 * ************************* function ****************************
 * IniGrid()        Initialize the grid, the size of the grid is N, generate a sqrt(N) * sqrt(N) size grid
 * RandomGrid       first generate the random grid, and then draw the lines (Need to use function specialFuncX and
 *                  specialFuncY to calculate x0 and y0)
 * UnionFind        Check connectivity
 */
public class Animation {
    public Animation(){}

    public static void RandomGrid(){
        System.out.println("Please insert the number of nodes of the net: ");
        Scanner ms = new Scanner(System.in);
        int n = ms.nextInt();
        ms.close();
        RandomBag<RandomGridGenerator.Connection> connections = RandomGridGenerator.generate(n);

        UnionFind myGrid = new UnionFind(n);
        int p;
        int q;

        int nNodesRow = IniGrid(n);
        StdDraw.setPenRadius(0.003);
        StdDraw.setPenColor(Color.lightGray);
        for (RandomGridGenerator.Connection c: connections) {
            p = c.p;
            q = c.q;
            if (!myGrid.checkConnection(p,q)){
                myGrid.connect(p,q);
                p++;
                q++;
                StdDraw.line(specialFuncX(p, nNodesRow)*5-2,specialFuncY(p,nNodesRow)*5-2,specialFuncX(q, nNodesRow)*5-2,specialFuncY(q,nNodesRow)*5-2);
            }
        }
    }

    private static class UnionFind{
        QuickUnionUF myGrid;
        public UnionFind(int N){
            myGrid = new QuickUnionUF(N);
        }

        public void connect(int p, int q){
            myGrid.union(p,q);
        }

        public boolean checkConnection(int p, int q){
            return myGrid.connected(p,q);
        }
    }

    private static int specialFuncX(int p, int n){
        if (p % n == 0){
            return n;
        }
        else
            return p%n;
    }
    private static int specialFuncY(int p, int n){
        if (p % n == 0){
            return p/n;
        }
        else
            return p/n + 1;
    }

    public static int IniGrid(int N){
        int n = (int) Math.sqrt(N);
        if(n*n < N){n=n+1;}
        StdDraw.setCanvasSize(100*n,100*n);
        StdDraw.setXscale(1,5*n);
        StdDraw.setYscale(1,5*n);

        StdDraw.setPenRadius(0.01);
        int nNode = 1;
        int row = 1;

        for (int i = 1; i <= n; i++) {
            if(nNode==N) break;
            StdDraw.point(i*5-2,row*5-2);
            nNode++;
            if (i == n){row++; i = 0;} // start a new row
        }
        return n;
    }

    public static void main(String[] args) {
//        IniGrid(23);
        RandomGrid();
    }
}
