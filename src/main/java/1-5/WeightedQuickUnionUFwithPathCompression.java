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
public class WeightedQuickUnionUFwithPathCompression extends WeightedQuickUnion {

    public WeightedQuickUnionUFwithPathCompression(int n) {
        super(n);
    }

    public void unionCompression(int p, int q) {
        //TODO: change the root of all nodes between p to P, and q to Q with the new root
        this.union(p,q);
        int newRoot = find(p);
        rootCompre(p, newRoot);
        rootCompre(q, newRoot);
    }

    public void rootCompre(int p, int Root){
        //TODO: change the nodes between p(exclude) to P(include) with the new parent Root
        Stack<Integer> nodes = new Stack<>();
        nodes = rootCompreHelper(p, nodes);
        while(!nodes.isEmpty()){
            super.getParent()[nodes.pop()] = Root;
        }
    }

    public Stack<Integer> rootCompreHelper(int p, Stack<Integer> nStack){
        //TODO: return the nodes between p(exclude) to P(include)
        if (super.getParent()[p] == p){
            nStack.push(p);
            return nStack;
        }
        else {
            nStack.push(super.getParent()[p]);
            rootCompreHelper(super.getParent()[p],nStack);
        }
        return nStack;
    }

    public static void main(String[] args) {
    }
}
