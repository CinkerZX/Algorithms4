import sun.misc.Queue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class MoveToFront<Item> implements Iterable<Item>{
    LinkedList<Item> myLL;

    public MoveToFront(){
        myLL = new LinkedList<>();
    }

    public boolean add(Item e){
        for (int i = 0; i < myLL.size(); i++) {
            if (myLL.get(i).equals(e)){
                myLL.remove(i);
                myLL.addFirst(e);
                return true;
            }
        }
        myLL.addFirst(e);
        return true;
    }


    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < myLL.size();
            }

            @Override
            public Item next() {
                i++;
                return myLL.get(i-1);
            }
        };
    }

    public static void main(String[] args) {
        MoveToFront myMF = new MoveToFront();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input the sequence of characters: i a f w f s a f e f ...");
        System.out.println("Finish by entering #");
        while (scan.hasNext()){
            String charSequence = scan.nextLine();
            if (charSequence.equals("#")){
                break;
            }
            String[] chars = charSequence.split(" ");
            for (String c:chars) {
                myMF.add(c);
            }
        }
        for (Object o:myMF) {
            System.out.print(o+" ");
        }
    }
}
