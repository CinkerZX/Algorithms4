import java.util.ArrayDeque;

/**
 * Aim: Bottom-up merge implementation
 * Idea: create N queues, and save them in a queue, then merge them
 */

public class BottomUpQueueMergeSort {

    public static void BottomUpQueueMergeSort(ArrayDeque<Comparable> queueA){
        ArrayDeque<ArrayDeque> nQueue = new ArrayDeque<>(queueA.size());
        while (!queueA.isEmpty()){
            ArrayDeque<Comparable> element = new ArrayDeque<>(1);
            element.add(queueA.pollFirst());
            nQueue.add(element);
        }
        ArrayDeque<ArrayDeque> result = new ArrayDeque<>(queueA.size());
        BottomUpQueueMerge(nQueue);
        MergingSortedQueue.copyQueue(nQueue.pollFirst(),queueA);
    }

    public static void BottomUpQueueMerge(ArrayDeque<ArrayDeque> Q){
        if(Q.size()>1){
            Q.addLast(MergingSortedQueue.mergeSortedQueue(Q.pollFirst(),Q.pollFirst()));
            BottomUpQueueMerge(Q);
        }
    }

//    public static void BottomUpQueueMerge(ArrayDeque<ArrayDeque> Q, ArrayDeque<Comparable> q){
//        //TODO: pop from Q and merge with q, then save the merged deque at the beginning of Q
//            Q.add(MergingSortedQueue.mergeSortedQueue(Q.pollFirst(),q));
//            BottomUpQueueMerge(Q);
//    }

    public static void main(String[] args) {
        ArrayDeque<Comparable> b = sort.generateStingQueue(6);
        MergingSortedQueue.printQueue(b);
        BottomUpQueueMergeSort(b);
        MergingSortedQueue.printQueue(b);
    }
}
