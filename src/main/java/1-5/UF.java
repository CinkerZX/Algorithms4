public interface UF {
    // add connection between p and q (p and q are two nodes)
    void union(int p, int q);

    // component indentifier for p (result is in [0, N-1]. N is the size of the network)
    int find(int p);

    // return true if p and q are in the component
    boolean connected(int p, int q);

    // number of components
    int count();

}
