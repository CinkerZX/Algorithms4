import java.text.ParseException;
import java.util.*;

class ResizeArrayQueueOfStrings<E> extends ArrayList<E> implements Queue<E>, Iterable<E>{
    //TODO: create an fixed size array class virtually, but extend it to changeable size by implement Queue.
    //TODO: Queue has add function, we just override the add function, realizing resizing function
    private ArrayList<E> myQueue;
    private int length; // the num of elements
    private int capacity;

    public ResizeArrayQueueOfStrings(int n){
        myQueue = new ArrayList<E>(n);
        length = 0;
        capacity = n;
    }

    public int getLength() {
        return length;
    }

    public E[] toArraylist(){
        return (E[]) myQueue.subList(0,length).toArray();
    }

    @Override
    public boolean add(E e) {
        if (length<capacity){
            myQueue.add(length,e); // add from 0 -> size-1
            length++;
        }
        else{ // already reach the capacity
            int newSize = capacity+10;
            ResizeArrayQueueOfStrings<E> newQueue = new ResizeArrayQueueOfStrings(newSize); // resize
            int i = 0;
            for (E temp : myQueue){ // put everything in
                newQueue.add(i,temp);
                i++;
            }
            newQueue.add(length,e); // add the new element e
            myQueue = newQueue;
        }
        return true;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        // TODO: remove the head of the queue: remove the [0]
        E result =myQueue.get(0);
        if (length>1){
            for (int i = 0; i < length-1; i++) {
                myQueue.add(i,myQueue.get(i+1));
            }
            myQueue.remove(length-1); // remove the last one, cause all are moved one place forward
            length--;
        }
        if (length==1){
            length--;
        }
        return result;
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E element() {
        return myQueue.get(0);
    }

    @Override
    public E peek() {
        return myQueue.get(0);
    }

    @Override
    public Iterator<E> iterator(){ // Define my own iterator, necessary if want to use for each to print the result
        Iterator<E> iter = new Iterator<E>() {
            private int i=0;

            @Override // Override the original result
            public boolean hasNext(){
                return (i < length);
            }

            @Override
            public E next(){
                E temp;
                temp = (E) myQueue.get(i);
                i++;
                return temp;
            }
        };
        return iter;
    }

    public static void main(String[] args) throws ParseException, InterruptedException {
        ResizeArrayQueueOfStrings q = new ResizeArrayQueueOfStrings(3);
        q.add("One");
        q.add("Two");
        q.add("Now it's full. ");
        q.add("Resize. ");
        for (Object s : q){
            System.out.println(s);
        }
    }
}
