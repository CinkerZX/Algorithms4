import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

class CopyQueue<Item> extends Queue<Item>{
    Queue myQueue;

    public CopyQueue(){
        myQueue = new Queue();
    }

    public CopyQueue(CopyQueue q){
        myQueue = new Queue();
        int i = 0;
        final int n = q.myQueue.size();
        while(i<n){
            Item temp = (Item) q.myQueue.dequeue();
            myQueue.enqueue(temp);
            q.enqueue(temp);
            i++;
        }
    }

    public CopyQueue(Queue q){
        myQueue = new Queue();
        int i = 0;
        final int n = q.size();
        while(i<n){
            Item temp = (Item) q.dequeue();
            myQueue.enqueue(temp);
            q.enqueue(temp);
            i++;
        }
    }

    @Override
    public boolean isEmpty(){
        return myQueue.isEmpty();
    }

    public void add(Item i){
        myQueue.enqueue(i);
    }

//    @Override
//    public Iterator<Item> iterator() {
//        return new Iterator<Item>() {
//            CopyQueue<Item> names = new CopyQueue(myQueue);
//
//            @Override
//            public boolean hasNext() {
//                return !names.isEmpty();
//            }
//
//            @Override
//            public Item next() {
//                return names.dequeue();
//            }
//        };
//    }

    public static void main(String[] args) {
        CopyQueue myCQ = new CopyQueue();
        myCQ.add(0);
        myCQ.add(1);
        myCQ.add(2);
        myCQ.add(3);
        for (Object o:myCQ.myQueue) {
            System.out.print(o+" ");
        }

        System.out.println("******2******");
        CopyQueue Q2 = new CopyQueue(myCQ);
        for (Object o:Q2.myQueue) {
            System.out.print(o+" ");
        }
    }
}
