import java.util.HashSet;

/**
 * The {@Code QuickFindUF} class will realize the UF class, which describes a net
 *
 * Idea: use id[] to save each node's component.
 *       when do union operation, change all the values in id[] that share the same component
 * ---------------------------------------------------------------------------------------------
 * void union(int p, int q)          add connection between p and q (p and q are two nodes)
 * int traceRoot(int p)              return the root of node p
 * int find(int p)                   component indentifier for p (result is in [0, N-1]. N is the size of the network)
 * boolean connected(int p, int q)   return true if p and q are in the component
 * int count();                      number of components
 *
 */

public class QuickFindUF implements UF{
    private int[] id;

    public QuickFindUF(int n){
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int N = id.length;
        if (p>N || q>N || p<0 || q<0){throw new IllegalArgumentException("Both p, q should be in [0, N-1].");}
        //TODO: update the component of q to the component of p
        int comOfP = id[p];
        int comOfQ = id[q];
        if (comOfP != comOfQ){
            for (int i = 0; i < N; i++) {
                if (id[i] == comOfP){
                    id[i] = comOfQ;
                }
            }
        }
    }

    @Override
    public int find(int p) {
        // return the component(root) of p
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        // check if p and q are of the same component
        return id[p] == id[q];
    }

    @Override
    public int count() {
        // return the number of roots
        return distinctNumofArray(id);
    }

    public int distinctNumofArray(int[] arr){
        // count the number of distinct numbers in the array
        HashSet h = new HashSet();
        for (int i = 0; i < id.length; i++) {
            h.add(id[i]);
        }
        return h.size();
    }
}
