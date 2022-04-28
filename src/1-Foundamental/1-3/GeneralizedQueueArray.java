import sun.misc.Queue;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;

class GeneralizedQueueArray<Item> extends ResizeArrayQueueOfStrings<Item> implements GenralizedQueue<Item>{
    
    /**
     * Constructor: create an empty queue
     */
    public GeneralizedQueueArray(){
        super();
    }

    /**
     * Is the queue empty?
     * @return
     */
    @Override
    public boolean isEmpty(){
        if (this.getLength() == 0){return true;}
        else{return false;}
    }

    /**
     * Add an Item x
     * @param x
     */
    @Override
    public boolean insert(Item x) {
        this.add(x);
        return true;
    }


    /**
     * Delete and return the kth least recently inserted item
     * @param k
     * @return
     */
    public Item delete(int k){
        Item result = (Item) this.getItem(this.getLength()-k);
        this.removeItem(this.getLength()-k);
        return result;
    }

    public static void main(String[] args) {
        GeneralizedQueueArray q = new GeneralizedQueueArray();
        System.out.println(q.isEmpty());
        q.insert(0);
        q.insert(1);
        q.insert(2);
        q.insert(3);
        System.out.println(q.isEmpty());
        System.out.println(q.delete(3)); // 1
        for (Object o:q) {
            System.out.println(o);
        }
    }
}

