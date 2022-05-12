import java.util.AbstractQueue;
import java.util.PriorityQueue;
import java.util.Queue;

public class CatenableQueue<Item> implements Catenable<CatenableQueue> {
    CircularLinkedList myQueue;

    public CatenableQueue(){
        myQueue = new CircularLinkedList();
    }

    public CatenableQueue(CircularLinkedList q){
        myQueue = q;
    }

    /**
     * This: 0->1->2->last1->0    queue: a->b->c->last2->a
     * Return: a->b->c->0->1->2->last1->a
     * @param queue
     * @return
     */
    @Override
    public CatenableQueue caten(CatenableQueue queue) {
        // both this and queue is empty
        if (myQueue.isEmpty() && queue.myQueue.isEmpty()){return new CatenableQueue();}
        if (myQueue.isEmpty()){return queue;}
        if (queue.myQueue.isEmpty()){return new CatenableQueue(myQueue);}

        myNode endOfThis = myQueue.last.getNext();
        myNode endOfqueue = queue.myQueue.last.getNext();
        myNode headOfqueue = queue.myQueue.goToEnd();
        headOfqueue.setNext(endOfThis); // c->0
        myQueue.last.setNext(endOfqueue); // last1->a
        return new CatenableQueue(myQueue);
    }

    public boolean add(Item item) {
        myQueue.add(item);
        return true;
    }

    public boolean offer(Item item) {
        return add(item);
    }

    /**
     * Remove the head of the queue (end of this)
     */
    public Item remove() {
        if (myQueue.isEmpty()){return null;}
        myNode endOfThis = myQueue.last.getNext();
        Item result =  (Item) endOfThis.getData();
        myQueue.last.setNext(endOfThis.getNext());
        return result;
    }

    public Item poll() {
        return remove();
    }

    public Item element() {
        if (myQueue.isEmpty()){return null;}
        myNode endOfThis = myQueue.last.getNext();
        Item result =  (Item) endOfThis.getData();
        return result;
    }

    public Item peek() {
        return element();
    }
}
