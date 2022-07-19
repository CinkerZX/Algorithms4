import java.util.Arrays;

/**
 * The {@Code WeightedQuickUnionbyHeight} realize UF class
 *
 * Idea: use pa[] to save each node's parents id. For root nodes, the id is its position [0,N-1]
 *        when do union operation, combine trees
 *  ---------------------------------------------------------------------------------------------
 *  void union(int p, int q)          keep track of tree's height, and always hook the shorter tree to the taller tree
 *  int traceRoot(int p)              return the root of node p
 *  int find(int p)                   component indentifier for p (result is in [0, N-1]. N is the size of the network)
 *  boolean connected(int p, int q)   return true if p and q are in the component
 *  int count();                      number of components
 */
public class WeightedQuickUnionbyHeight extends WeightedQuickUnion {

    public WeightedQuickUnionbyHeight(int n){
        super(n); // size records the height of the tree
    }

    @Override
    public void union(int p, int q) {
        int rootP = this.find(p);
        int rootQ = this.find(q);
        if (rootP != rootQ) {
            int[] var10000;
            var10000 = this.getSize();
            int newHeight = 0;
            if (this.getSize()[rootP] < this.getSize()[rootQ]) { // tree p is shorter than q
                this.getParent()[rootP] = rootQ; // hook p to q
                //TODO: change the height of p to the height of q
                if (this.getSize()[rootQ] == 1){
                    ++this.getSize()[rootQ];
                }
                //TODO: update the height of all nodes on the tree
                unionHelper(rootQ);
//                var10000[rootP] = this.getSize()[rootQ];
            } else {
                this.getParent()[rootQ] = rootP; // hook q to p
                if (this.getSize()[rootP] == this.getSize()[rootQ]){
                    this.getSize()[rootP] = this.getSize()[rootP]+1;
                }
                unionHelper(rootP);
//                var10000[rootQ] = this.getSize()[rootP];
            }
            --this.count;
        }
    }

    public void unionHelper(int root){
        int height = this.getSize()[root];
        for (int i = 0; i < this.getParent().length; i++) {
            if (this.getParent()[i] == root && i != root){
                this.getSize()[i] = height;
                unionHelper(i);
            }
        }
    }
}
