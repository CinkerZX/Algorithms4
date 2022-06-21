import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.Stack;

public class BirthdayProblem {
    private int N;

    public BirthdayProblem(int n){ // Constructor
        N = n;
    }

    public int generateIntNormdis(){
        return StdRandom.uniform(N-1);
    }

    public int leastRepeatNum(){
        Stack<Integer> myRandomList = new Stack<>();
        int temp = generateIntNormdis();
        int leastTime = 0;
        while(myRandomList.isEmpty() || !myRandomList.contains(temp)){
            myRandomList.push(temp);
            leastTime++;
            temp = generateIntNormdis();
        }
        return leastTime;
    }

    public double experimentsResults1(){
        int expTime = 10000*N;
        int sumofResults = 0;
        for (int i = 0; i < expTime; i++) {
            sumofResults = sumofResults+leastRepeatNum();
        }
        return sumofResults/expTime;
    }

    public double experimentsResults2(){
        int expTime = 5000*N;
        int sumofResults = 0;
        for (int i = 0; i < expTime; i++) {
            sumofResults = sumofResults+leastRepeatNum();
        }
        return sumofResults/expTime;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        BirthdayProblem myBP = new BirthdayProblem(n);
        // if E(repeatTime)~sqrt(pi*N/2), then E(repeatTime)/sqrt(pi*N/2) will converge to a stable constant
        // when take enough samples. Accordingly, if the following check is true, it means the constant exists => ~sqrt(pi*N/2)
        System.out.println(myBP.experimentsResults1()/Math.sqrt(Math.PI*n/2) == myBP.experimentsResults2()/Math.sqrt(Math.PI*n/2));
    }
}
