import java.util.Stack;

/**
 * The {@CODE QuickUnionUFwithPathCompression} class extends the QuickUnionUF class by adding path compression
 *
 * Idea: inherit all the methods but override the method union
 * Original: union(int p, int q)        TODO: hook P tree up to Q tree
 * (Let P, Q be the root of the two trees)
 * Amended:  ubion(int p, int q)        TODO: update the parent of all the nodes between p to P, and q to Q, to the common root Q
 */
public class QuickUnionUFwithPathCompression extends QuickUnionUF{

    public QuickUnionUFwithPathCompression(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        int N = pa.length;
        if (p>N || q>N || p<0 || q<0){throw new IllegalArgumentException("Both p, q should be in [0, N-1].");}
        //TODO: change the root of all nodes between p to P, and q to Q with Q
        int newRoot = traceRoot(q);
        rootCompre(p, newRoot);
        rootCompre(q, newRoot);
        count--;
    }

    public void rootCompre(int p, int Root){
        //TODO: change the nodes between p(exclude) to P(include) with the new parent Root
        Stack<Integer> nodes = new Stack<>();
        nodes = rootCompreHelper(p, nodes);
        while(!nodes.isEmpty()){
            pa[nodes.pop()] = Root;
        }
    }

    public Stack<Integer> rootCompreHelper(int p, Stack<Integer> nStack){
        //TODO: return the nodes between p(exclude) to P(include)
        if (pa[p] == p){
            nStack.push(p);
            return nStack;
        }
        else {
            nStack.push(pa[p]);
            rootCompreHelper(pa[p],nStack);
        }
        return nStack;
    }

    public static void main(String[] args) {
        QuickUnionUFwithPathCompression myQuickUnion = new QuickUnionUFwithPathCompression(7);
        myQuickUnion.union(1,2);
        myQuickUnion.union(3,4);
        myQuickUnion.union(5,6);
        myQuickUnion.union(2,3);
        myQuickUnion.union(4,6);
    }
}
