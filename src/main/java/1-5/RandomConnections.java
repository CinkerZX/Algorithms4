import com.sun.jmx.snmp.EnumRowStatus;

import java.util.Random;
import java.util.Scanner;

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
        int numConnections;
        int numNodes;

        public ErdosRenyi(int N){
            numNodes = N;
            numConnections =0;
        }

        public void completeNetGenerating(){
            int p;
            int q;
            QuickUnionUF myNet = new QuickUnionUF(numNodes);
            while(myNet.count != 1){ // not complete
                p = (int) (Math.random() * (numNodes-1));
                q = (int) (Math.random() * (numNodes-1));
                if (!myNet.connected(p,q)){ // if connected
                    myNet.union(p,q);
                    numConnections++;
                }
            }
            System.out.println(numConnections);
        }
    }

    public static void count(){ // static method, don't need to instantiated RandomCOnnections object.
        System.out.println("Please input the number of nodes for the net: ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ErdosRenyi myNet = new ErdosRenyi(N);
        myNet.completeNetGenerating();
    }

    public static void main(String[] args) {
        count();
    }
}
