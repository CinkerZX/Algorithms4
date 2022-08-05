import java.util.Scanner;

/**
 * The {@code ErdosRenyiModel} aims at testing the times for creating a complete network based on
 * RandomConnections algorithm is ~1/2 NlnN
 *
 * Idea:
 * Run function count(n) multiple times, calculate the average # GeneratedConnections, aveCount for n size network
 * Calculate aveCount/(1/2 NlnN) under various N
 * If the result is close to 1 => proof aveCount ~1/2 NlnN
 * 
 */
public class ErdosRenyiModel {
    public ErdosRenyiModel(){} // Constructor, do nothing

    public static double aveCount(int n){
        int time = 0;
        int numRepeat = 1000;
        for (int i = 0; i < numRepeat; i++) {
            time = time + RandomConnections.countQuickFind(n);
        }
        return (double) time/(double) numRepeat;
    }

    public static void main(String[] args) {
        int N = 20;
        System.out.println(aveCount(N)/(N*Math.log(N)/2));

        N = 30;
        System.out.println(aveCount(N)/(N*Math.log(N)/2));

        N = 200;
        System.out.println(aveCount(N)/(N*Math.log(N)/2));

        N = 500;
        System.out.println(aveCount(N)/(N*Math.log(N)/2));

        N = 600;
        System.out.println(aveCount(N)/(N*Math.log(N)/2));

        N = 700;
        System.out.println(aveCount(N)/(N*Math.log(N)/2));

        // when N is growing up, the result is close to 1, which indicate time consumption is ~1/2 NlnN
    }

}
