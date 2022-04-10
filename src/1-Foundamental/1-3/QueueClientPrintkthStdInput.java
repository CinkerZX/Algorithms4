import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
//import sun.misc.Queue;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class QueueClientPrintkthStdInput extends Queue<String> {

    Queue<String> myQueue;
    int point;
    int length;

    public QueueClientPrintkthStdInput(int k){
        myQueue = new Queue<String>();
        Scanner input = new Scanner(System.in);
        System.out.println("Please input at least "+ k + " strings as following format: String1 (enter) String2 (enter) ...");
        System.out.println("Finish by entering #");
        int i = 0;
        while(input.hasNext()){
            String line = input.nextLine();
            if (line.equals("#")){
                break;
            }
            myQueue.enqueue(line);
            i++;
        }
        point = k;
        length = i;
    }

    public String kthStringFromLast(){
        int i = 0;
        while(i<length-point){
            myQueue.dequeue();
            i++;
        }
        return myQueue.dequeue();
    }

    public static void main(String[] args){
        QueueClientPrintkthStdInput myQ = new QueueClientPrintkthStdInput(Integer.valueOf(args[0]));
        System.out.println(myQ.kthStringFromLast());
    }
}
