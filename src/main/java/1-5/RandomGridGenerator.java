import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The {@CODE RandomGridGenerator} aims at generating random net
 *
 * -------------------------------------------------------------
 * Methods
 * RandomGrid: generate an N-by-N grid
 * The elements for the grid are the pair of nodes, (p,q), which is of Connection class
 *
 * generate(int N): return the N-by-N matrix
 *
 * main(): take N from command line, call Generate(N), and print out the connections by iterating through the returned matrix
 */
public class RandomGridGenerator {

    public RandomGridGenerator(){}

    public static RandomBag generate(int N){
        //TODO: generate an N*N Connections
        int p;
        int q;
        RandomBag<Connection> myConnections = new RandomBag();
        for (int i = 0; i < N*N; i++) {
            p = ThreadLocalRandom.current().nextInt(0, N);
            q = ThreadLocalRandom.current().nextInt(0, N);
            myConnections.add(new Connection(p,q));
        }
        return myConnections;
    }

    // Connection class
    private static class Connection{
        int p;
        int q;

        public Connection(int p, int q){
            this.p = p;
            this.q = q;
        }

        public String printConnection(){
            return "("+this.p+", "+this.q+")";
        }
    }

    public static void main(String[] args) {
        // TODO: Call generator, and print the array in N-by-N matrix format by iterator
        System.out.println("Please insert the number of nodes of the net: ");
        Scanner ms = new Scanner(System.in);
        int n = ms.nextInt();
        ms.close();
        RandomBag<Connection> connections = generate(n);
        int curCol = 0;
        for (Connection c: connections) {
            System.out.print(c.printConnection() + "\t");
            if (curCol == n-1){
                System.out.println("");
                curCol=0;
            }
            else{
                curCol++;
            }
        }
    }
}
