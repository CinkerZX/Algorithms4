import java.util.LinkedList;

/**
 * The {@CODE DynamicGrowth} realizing weightedQuickUnion without knowing the #nodes in advance by linked list
 *
 * Idea:
 * Linked list parent: save the id of node's parent
 * Linked list size: save the size of the tree that one node belongs to
 * Linked list count: save the number of current trees
 *
 *
 */

public class DynamicGrowth implements UF{
    private LinkedList<Integer> parent;
    private LinkedList<Integer> size;
    public int count;

    public LinkedList<Integer> getParent() {
        return parent;
    }

    public LinkedList<Integer> getSize() {
        return size;
    }

    public DynamicGrowth(){
        parent = new LinkedList<>();
        size = new LinkedList<>();
        count = 0;
    }

    public int newSite(){
        //TODO: return the size of the current net
        return parent.size();
    }

    private void updateNet(int p, int q){
        //TODO: extend the current nodes into the needed size
        int newSize = p+Math.abs(p-q)+1; // #nodes (6)
        int curSize = parent.size(); //2 (0,1) -> (0,1,2,3,4,5)
        if (newSize > curSize){ // need to extend
            for (int i = curSize; i < newSize; i++) {
                parent.addLast(i);
                size.addLast(i);
            }
            count = count+newSize-curSize;
        }
    }

    @Override
    public void union(int p, int q) {
        updateNet(p,q);
        int rootP = this.find(p);
        int rootQ = this.find(q);
        if (rootP != rootQ) {
            LinkedList var10000;
            if (this.size.get(rootP) < this.size.get(rootQ)) {
                this.parent.set(rootP,rootQ);
                var10000 = this.size;
                int sizeP = (int) var10000.get(rootP);
                int sizeQ = (int) var10000.get(rootQ);
                var10000.set(rootQ, sizeP+sizeQ);
            } else {
                this.parent.set(rootQ,rootP);
                var10000 = this.size;
                int sizeP = (int) var10000.get(rootP);
                int sizeQ = (int) var10000.get(rootQ);
                var10000.set(rootP, sizeP+sizeQ);
            }
            --this.count;
        }
    }

    @Override
    public int find(int p) {
        this.validate(p);

        while(p != this.parent.get(p)) {
            p = this.parent.get(p);
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    private void validate(int p) {
        int n = this.parent.size();
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    @Override
    public int count() {
        return this.count;
    }

    public static void main(String[] args) {

    }
}
