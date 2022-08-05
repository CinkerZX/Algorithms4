import com.sun.jmx.snmp.EnumRowStatus;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The {@CODE RandomConnections} has one inner class EdroRenyi
 *
 * In class ErdosRenyi, it will construct a complete network
 *
 * In the static method count()
 * ErdosRenyi class will be instantiated, and return #connections in generating the complete net
 */
public class RandomConnections{
    public RandomConnections(){};

    public static class ErdosRenyi{
        int numConnectionsQuickUnion;
        int numConnectionsQuickFind;
        int numNodes;

        public ErdosRenyi(int N){
            numNodes = N;
            numConnectionsQuickUnion = 0;
            numConnectionsQuickFind = 0;
        }

        public int completeNetGeneratingNQuickUnion(){
            int p;
            int q;
            QuickUnionUF myNet = new QuickUnionUF(numNodes);
            while(myNet.count != 1){ // not complete
                numConnectionsQuickUnion++;
                p = ThreadLocalRandom.current().nextInt(0, numNodes);
                q = ThreadLocalRandom.current().nextInt(0, numNodes);
                if (!myNet.connected(p,q)){ // if connected
                    myNet.union(p,q);
                }
            }
            return numConnectionsQuickUnion;
        }

        public int completeNetGeneratingNQuickFind(){
            int p;
            int q;
            QuickFindUF myNet = new QuickFindUF(numNodes);
            while(myNet.count() != 1){ // not complete
                numConnectionsQuickUnion++;
                p = ThreadLocalRandom.current().nextInt(0, numNodes);
                q = ThreadLocalRandom.current().nextInt(0, numNodes);
                if (!myNet.connected(p,q)){ // if connected
                    myNet.union(p,q);
                }
            }
            return numConnectionsQuickUnion;
        }

        public int completeNetGeneratingNWeightedQuickUnion(){
            int p;
            int q;
            WeightedQuickUnion myNet = new WeightedQuickUnion(numNodes);
            while(myNet.count != 1){ // not complete
                numConnectionsQuickUnion++;
                p = ThreadLocalRandom.current().nextInt(0, numNodes);
                q = ThreadLocalRandom.current().nextInt(0, numNodes);
                if (!myNet.connected(p,q)){ // if connected
                    myNet.union(p,q);
                }
            }
            return numConnectionsQuickUnion;
        }

        public void completeNetGenerating(){
            int p;
            int q;
            QuickUnionUF myNet = new QuickUnionUF(numNodes);
            while(myNet.count != 1){ // not complete
                numConnectionsQuickUnion++;
                p = ThreadLocalRandom.current().nextInt(0, numNodes);
                q = ThreadLocalRandom.current().nextInt(0, numNodes);
                if (!myNet.connected(p,q)){ // if connected
                    myNet.union(p,q);
                }
            }
            QuickFindUF myNet2 = new QuickFindUF(numNodes);
            while(myNet2.count() != 1){ // not complete
                numConnectionsQuickFind++;
                p = ThreadLocalRandom.current().nextInt(0, numNodes);
                q = ThreadLocalRandom.current().nextInt(0, numNodes);
                if (!myNet2.connected(p,q)){ // if connected
                    myNet2.union(p,q);
                }
            }
            System.out.println("The number of connecting operations for quickUnion is: " + numConnectionsQuickUnion);
            System.out.println("The number of connecting operations for quickFind is: " + numConnectionsQuickFind);
        }
    }

    public static void count(){ // static method, don't need to instantiated RandomCOnnections object.
        System.out.println("Please input the number of nodes for the net: ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();
        ErdosRenyi myNet = new ErdosRenyi(N);
        myNet.completeNetGenerating();
    }

    public static int countQuickUnion(int n){
        int N = n;
        ErdosRenyi myNet = new ErdosRenyi(N);
        return myNet.completeNetGeneratingNQuickUnion();
    }

    public static int countQuickFind(int n){
        int N = n;
        ErdosRenyi myNet = new ErdosRenyi(N);
        return myNet.completeNetGeneratingNQuickFind();
    }

    public static int countWeightedQuickUnion(int n){
        int N = n;
        ErdosRenyi myNet = new ErdosRenyi(N);
        return myNet.completeNetGeneratingNWeightedQuickUnion();
    }

    public static void main(String[] args) {
        count();
    }
}
