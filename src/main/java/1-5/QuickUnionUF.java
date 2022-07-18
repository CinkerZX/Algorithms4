/**
 * The {@Code QuickUnionUF} class will realize the UF class, which describes a net
 *
 * Idea: use pa[] to save each node's parents id. For root nodes, the id is its position [0,N-1]
 *       when do union operation, combine trees
 * ---------------------------------------------------------------------------------------------
 * void union(int p, int q)          add connection between p and q (p and q are two nodes)
 * int traceRoot(int p)              return the root of node p
 * int find(int p)                   component indentifier for p (result is in [0, N-1]. N is the size of the network)
 * boolean connected(int p, int q)   return true if p and q are in the component
 * int count();                      number of components
 *
 */
public class QuickUnionUF implements UF{
    public int[] pa;  // Here the variables are public, so that subClass can inherit them by "extends"
    public int count; // number of component

    public QuickUnionUF(int n){ // Constructor.
        //TODO: Ini pa[] = [0 1 2 .... n-1]
        pa = new int[n];
        for (int i = 0; i < n; i++) {
            pa[i] = i;
        }
        count = n;
    }

    @Override
    public void union(int p, int q) {
        int N = pa.length;
        if (p>N || q>N || p<0 || q<0){throw new IllegalArgumentException("Both p, q should be in [0, N-1].");}
        //TODO: change the root of p to the root of q
        pa[traceRoot(p)] = traceRoot(q);
        count--;
    }

    public int traceRoot(int p){
        //TODO return the root of p
        if (pa[p] == p){return p;}
        else{return traceRoot(pa[p]);}
    }

    @Override
    public int find(int p) {
        // return the component(root) of p
        return traceRoot(p);
    }

    @Override
    public boolean connected(int p, int q) {
        // check if p and q are of the same component
        if (traceRoot(p) == traceRoot(q)){return true;}
        return false;
    }

    @Override
    public int count() {
        // return the number of roots
        return count;
    }
}
