import java.util.ArrayList;

/**
 * Min heap: a binary tree, whose root is the smallest item of the entire tree;
 * every parent node is bigger than its kid nodes
 *
 */
public class MinHeap {
    private ArrayList<Comparable> heap;

    public MinHeap(){
        heap = new ArrayList<>();
    }

    public void insert(Comparable value){
        // Insert value into the heap
        heap.add(value);
        int currentIndex = heap.size()-1;
        int parentIndex = (currentIndex - 1)/2;

        while (currentIndex > 0 && sort.less(heap.get(currentIndex), heap.get(parentIndex))){ // if the added item is smaller than the parent
            //TODO: exchange the value of current position and of the parent position
            Comparable temp = heap.get(currentIndex);
            heap.set(currentIndex, heap.get(parentIndex));
            heap.set(parentIndex, temp);

            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1)/2;
        }
    }

    public Comparable removeMin(){
        // Remove the root of the heap
        if (heap.isEmpty()){
            throw new IllegalArgumentException("Heap is empty.");
        }

        Comparable min = heap.get(0);

        Comparable lastValue = heap.remove(heap.size()-1);

        heap.set(0, lastValue);
        heapifyDown(0);
        return min;
    }

    public void heapifyDown(int index){
        int leftChild = 2*index + 1;
        int rightChild = 2*index + 2;
        int smallest = index;

        if (leftChild < heap.size() && sort.less(heap.get(leftChild), heap.get(smallest))){
            smallest = leftChild;
        }

        if (rightChild < heap.size() && sort.less(heap.get(rightChild), heap.get(smallest))){
            smallest = rightChild;
        }

        if (smallest != index) {
            // Swap the current node with the smallest child
            Comparable temp = heap.get(index);
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
