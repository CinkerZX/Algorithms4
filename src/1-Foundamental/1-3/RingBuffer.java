import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

class RingBuffer<Item> implements GenralizedQueue<Item>{
    Item[] myRB;
    private int Reader; // The next unread element in the buffer
    private int Writer; // The next available slot to insert
    private int Capacity; // The size of the
    private int nElement; // The number of elements that saved in the buffer now

    public RingBuffer(int n){
        myRB = (Item[]) new Object[n]; // Pay attention to the initialization, Object[n] then cast to Item[]
        Reader = 0;
        Writer = -1;
        Capacity = n;
    }

    @Override
    public boolean isEmpty() {
        return (nElement == 0);
    }

    @Override
    public boolean insert(Item x) {
        if (nElement<Capacity){ // has available slot to insert
            int nextWritePointer = Writer+1;
            myRB[nextWritePointer % Capacity] = x;
            Writer++;
            nElement++;
            return true;
        }
        return false;
    }

    @Override
    public Item delete(int k) {
        return delete();
    }

    public Item delete(){
        if (nElement>0){
            Item result = myRB[Reader % Capacity];
//            Reader++;
            nElement--;
            return result;
        }
        return null;
    }

    /**
     * Just print out but not delete
     * @return
     */
    public Item poll(){
        if (nElement>0){
            Item result = myRB[Reader % Capacity];
            Reader++;
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        RingBuffer myRingBuffer = new RingBuffer(3);
        System.out.println(myRingBuffer.isEmpty()); // true
        myRingBuffer.insert("Log0");
        myRingBuffer.insert("Log1");
        myRingBuffer.insert("Log2"); // 2 1 0
        System.out.println(myRingBuffer.isEmpty()); // flase
        myRingBuffer.insert("Log3");
        System.out.println(myRingBuffer.poll()); // 0
        myRingBuffer.insert("Log3"); // 2 1 0
        System.out.println(myRingBuffer.poll()); // 1
        System.out.println(myRingBuffer.poll()); // 2
        System.out.println(myRingBuffer.poll()); // 0
        myRingBuffer.delete(); // 2 1
        System.out.println(myRingBuffer.poll()); // 1
        myRingBuffer.insert("Log3"); // 3 2 1
        System.out.println(myRingBuffer.poll()); // 2
    }
}
