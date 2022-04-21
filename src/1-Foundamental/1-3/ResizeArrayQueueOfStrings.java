import java.text.ParseException;
import java.util.*;

class ResizeArrayQueueOfStrings<E> extends ArrayList<E> implements Queue<E>, Iterable<E>{
    //TODO: create an fixed size array class virtually, but extend it to changeable size by implement Queue.
    //TODO: Queue has add function, we just override the add function, realizing resizing function
    private ArrayList<E> myQueue;
    private int capacity;

    public ArrayList<E> getMyQueue() {
        return myQueue;
    }

    public int getLength(){
        return myQueue.size();
    }

    public E getItem(int i){
        // return the item located at i
        return myQueue.get(i);
    }

    public ResizeArrayQueueOfStrings(int n){
        myQueue = new ArrayList<E>();
        capacity = n;
    }

    public ResizeArrayQueueOfStrings(){
        myQueue = new ArrayList<E>();
        capacity = 0;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public E[] toArraylist(){
        return (E[]) myQueue.subList(0,myQueue.size()).toArray();
    }

    @Override
    public boolean add(E e) { // add at the end of the array (first in first out)
        if (myQueue.size()<capacity){
            myQueue.add(myQueue.size(),e); // add from 0 -> size-1
        }
        else{ // already reach the capacity
            int newSize = capacity+10;
            ResizeArrayQueueOfStrings<E> newQueue = new ResizeArrayQueueOfStrings(newSize); // resize
            int i = 0;
            for (E temp : myQueue){ // put everything in
                newQueue.add(i,temp);
                i++;
            }
            newQueue.add(i,e); // add the new element e
            myQueue = newQueue;
            setCapacity(newSize);
        }
        return true;
    }

    public void add_inverse(E e) { // add at the beginning of the array
        if (myQueue.size()<capacity){
            myQueue.add(0,e);
        }
        else{ // already reach the capacity
            int newSize = capacity+10;
            ResizeArrayQueueOfStrings<E> newQueue = new ResizeArrayQueueOfStrings(newSize); // resize
            int i = 0;
            for (E temp : myQueue){ // put everything in
                newQueue.add(i,temp);
                i++;
            }
            newQueue.add(0,e);
            myQueue = newQueue;
            setCapacity(newSize);
        }
    }

    public void setItem(int i, E e){
        myQueue.set(i,e);
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() { // remove the end of the array (the head of the queue)
        // TODO: remove the head of the queue: remove the first
        if (this.size()!=0){
            myQueue = this;
        }
        E result =myQueue.get(myQueue.size()-1);
        myQueue.remove(myQueue.size()-1);
        return result;
    }

    public E remove_inverse() {
        // TODO: remove the ith of the queue
        if (this.size()!=0){
            myQueue = this;
        }
        E result =myQueue.get(0);
        myQueue.remove(0);
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
                return (i < myQueue.size());
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

    public static void main(String[] args) throws ParseException {
        ResizeArrayQueueOfStrings q = new ResizeArrayQueueOfStrings(3);
        q.add("1");
        q.add("2");
        q.add("3");
        q.add("4");
        q.add("5");
        q.add("6"); // 6 -> 5 -> 4 -> 3 -> 2 -> 1
        for (Object s : q){ // 1 2 3 4 5 6
            System.out.println(s);
        }
        q.remove(); // 5 -> 4 -> 3 -> 2 -> 1
        for (Object s : q){ // 1 2 3 4 5
            System.out.println(s);
        }

        q.remove_inverse(); // 5 -> 4 -> 3 -> 2
        for (Object s : q){ // 2 3 4 5
            System.out.println(s);
        }

        ResizeArrayQueueOfStrings q_2 = new ResizeArrayQueueOfStrings(3);
        q_2.add_inverse(1);
        q_2.add_inverse(2);
        q_2.add_inverse(3);
        q_2.add_inverse(4);
        q_2.add_inverse(5);
        q_2.add_inverse(6);// 1 -> 2 -> 3 -> 4 -> 5 -> 6
        for (Object s : q_2){ // 6 5 4 3 2 1
            System.out.println(s);
        }


    }
}
