//import sun.misc.Queue;

import edu.princeton.cs.algs4.Queue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

class Josephus<Item> extends Queue<Item> {
    Queue oriQueue;
    private int N=0;
    private int M=0;

    public void setN(int n) {
        N = n;
    }

    public void setM(int m) {
        M = m;
    }

    public Josephus(){
        oriQueue = new Queue();
    }

    /**
     *
     * @param
     */
    public void elimiOrder(){
        Josephus myQueue = elimiOrderHelper_ConstructOriginQueue();
        Queue<Item> result = new Queue<>();
        Item temp = null;
        int i = 0;
        while(!myQueue.isEmpty()){
            temp = (Item)myQueue.dequeue();
            if (i<M-1){
                myQueue.enqueue(temp);
                i++;
            }
            else{
                result.enqueue(temp);
                i = 0;
            }
        }
        while(!result.isEmpty()){
            System.out.print(result.dequeue()+" ");
        }
    }

    /**
     * Construct the original queue
     * @return
     */
    public Josephus<Item> elimiOrderHelper_ConstructOriginQueue() {
        Josephus jQueue = new Josephus();
        for (int i = 0; i < N; i++) {
            jQueue.enqueue(i);
        }
        return jQueue;
    }

    public static void main(String[] args) {
        Josephus j = new Josephus();
        j.setN(Integer.valueOf(args[0]));
        j.setM(Integer.valueOf(args[1]));
        j.elimiOrder();
    }
}
