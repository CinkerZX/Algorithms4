import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Stack;

/**
 * The {@CODE WeightedQuickUnionUFwithPathCompression} class extends the WeightedUnion class by adding path compression
 *
 * Idea:
 * Original: union(int p, int q)        TODO: get p.size and q.size, if p.size < q.size, P=Q
 * (Let P, Q be the root of the two trees)
 * Amended:  ubion(int p, int q)        TODO: update the parent of all the nodes between p to P, and q to Q, to the common root Q
 * Note that path compression doesn't change the size of the tree, but only the level of the tree
 */
public class WeightedQuickUnionUFwithPathCompression extends WeightedQuickUnionUF {

    private int[] parent;
    private int[] size;
    private int count;

    public WeightedQuickUnionUFwithPathCompression(int n) {
        super(n);
        this.count = n;
        this.parent = new int[n];
        this.size = new int[n];

        for(int i = 0; i < n; ++i) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int rootP = this.find(p);
        int rootQ = this.find(q);
        if (rootP != rootQ) {
            int[] var10000;
            if (this.size[rootP] < this.size[rootQ]) {
                this.parent[rootP] = rootQ;
                var10000 = this.size;
                var10000[rootQ] += this.size[rootP];
                //TODO: change the root of all nodes between p to P, and q to Q with Q
                rootCompre(p, rootQ);
                rootCompre(q, rootQ);
            } else {
                this.parent[rootQ] = rootP;
                var10000 = this.size;
                var10000[rootP] += this.size[rootQ];
                //TODO: change the root of all nodes between p to P, and q to Q with P
                rootCompre(p, rootP);
                rootCompre(q, rootP);
            }
            --this.count;
        }
    }

    public void rootCompre(int p, int Root){
        //TODO: change the nodes between p(exclude) to P(include) with the new parent Root
        Stack<Integer> nodes = new Stack<>();
        nodes = rootCompreHelper(p, nodes);
        while(!nodes.isEmpty()){
            this.parent[nodes.pop()] = Root;
        }
    }

    public Stack<Integer> rootCompreHelper(int p, Stack<Integer> nStack){
        //TODO: return the nodes between p(exclude) to P(include)
        if (this.parent[p] == p){
            nStack.push(p);
            return nStack;
        }
        else {
            nStack.push(this.parent[p]);
            rootCompreHelper(this.parent[p],nStack);
        }
        return nStack;
    }

    public static void main(String[] args) {

    }
}
