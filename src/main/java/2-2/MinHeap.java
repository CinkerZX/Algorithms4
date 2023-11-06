import java.util.ArrayList;
import java.util.Comparator;

/**
 * Min heap: a binary tree, whose root is the smallest item of the entire tree;
 * every parent node is bigger than its kid nodes
 *
 */
public class MinHeap<T> {
    private ArrayList<T> heap;
    private Comparator<T> comparator;

    public MinHeap(){
        this.heap = new ArrayList<>();
        this.comparator = null; // Use natural order
    }

    public MinHeap(Comparator<T> comparator){
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }


    public void insert(T value){
        // Insert value into the heap
        heap.add(value);
        int currentIndex = heap.size()-1;
        int parentIndex = (currentIndex - 1)/2;

        while (currentIndex > 0 && comparator.compare(heap.get(currentIndex), heap.get(parentIndex))<0){ // if the added item is smaller than the parent
            //TODO: exchange the value of current position and of the parent position
            T temp = heap.get(currentIndex);
            heap.set(currentIndex, heap.get(parentIndex));
            heap.set(parentIndex, temp);

            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1)/2;
        }
    }

    public T removeMin(){
        // Remove the root of the heap
        if (heap.isEmpty()){
            throw new IllegalArgumentException("Heap is empty.");
        }

        T min = heap.get(0);

        T lastValue = heap.remove(heap.size()-1);

        heap.set(0, lastValue);
        heapifyDown(0);
        return min;
    }

    public void heapifyDown(int index){
        int leftChild = 2*index + 1;
        int rightChild = 2*index + 2;
        int smallest = index;

        if (leftChild < heap.size() && comparator.compare(heap.get(leftChild), heap.get(smallest))<0){
            smallest = leftChild;
        }


        if (rightChild < heap.size() && comparator.compare(heap.get(rightChild), heap.get(smallest))<0){
            smallest = rightChild;
        }

        if (smallest != index) {
            // Swap the current node with the smallest child
            T temp = heap.get(index);
            heap.set(index, heap.get(smallest));
            heap.set(smallest, temp);
            // Recursively heapify down
            heapifyDown(smallest);
        }
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
