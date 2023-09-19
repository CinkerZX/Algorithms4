import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * Aim: take advantages of order in the array
 * Idea: find a sorted subarray, then find the next, them merge them
 */

public class NaturalMergeSort {
    public static void NaturalMergeSort(Comparable[] a){
        // Convert array to ArrayDequeue
        ArrayDeque<Comparable> queueQ = ComArray2ArrayQueue(a);
        ArrayDeque<Comparable> orderedArray1 = new ArrayDeque<>();
        orderedArray1.add(queueQ.pollFirst());
        findSortedArray(queueQ,orderedArray1);
        ArrayDeque<Comparable> orderedArray2 = new ArrayDeque<>();
        NaturalMergeSort(orderedArray1,orderedArray2, queueQ, a);
    }

    public static void NaturalMergeSort(LinkedList<Comparable> a){
        // Convert array to ArrayDequeue
//        ArrayDeque<Comparable> queueQ = ComArray2ArrayQueue(a);
        LinkedList<Comparable> queueQ = (LinkedList<Comparable>) a.clone();
        LinkedList<Comparable> orderedArray1 = new LinkedList<>();
        orderedArray1.add(queueQ.pollFirst());
        findSortedArray(queueQ,orderedArray1);
        LinkedList<Comparable> orderedArray2 = new LinkedList<>();
        NaturalMergeSort(orderedArray1,orderedArray2, queueQ, a);
    }

    private static void NaturalMergeSort(LinkedList<Comparable> array1, LinkedList<Comparable> array2, LinkedList<Comparable> motherArray, LinkedList<Comparable> A) {
        if(!motherArray.isEmpty()){
            array2.add(motherArray.pollFirst()); // ArrayDeque<Comparable>
            if (!motherArray.isEmpty()){
                findSortedArray(motherArray,array2); // ArrayDeque<Comparable>
            }
            array1 = MergingSortedQueue.mergeSortedQueue(array1,array2);
            array2.clear();
            NaturalMergeSort(array1, array2, motherArray, A);
        }
        else{ArrayQueue2ComArray(array1, A);}
    }

    public static void NaturalMergeSort(ArrayDeque<Comparable> array1, ArrayDeque<Comparable> array2, ArrayDeque<Comparable> motherArray, Comparable[] A){
        if(!motherArray.isEmpty()){
            array2.add(motherArray.pollFirst()); // ArrayDeque<Comparable>
            if (!motherArray.isEmpty()){
                findSortedArray(motherArray,array2); // ArrayDeque<Comparable>
            }
            array1 = MergingSortedQueue.mergeSortedQueue(array1,array2);
            array2.clear();
            NaturalMergeSort(array1, array2, motherArray, A);
        }
        else{ArrayQueue2ComArray(array1, A);}
    }


    // find sorted array -- ArrayDeque
    public static void findSortedArray(ArrayDeque<Comparable> a, ArrayDeque<Comparable> result){
        while(a.size() > 0){// at least there are two elements
            if (sort.less(result.getLast(), a.getFirst()) || result.getLast()==a.getFirst()){
                result.add(a.pollFirst());
                findSortedArray(a,result);
            }
            else{break;}
        }
    }

    // find sorted array -- LinkedList
    public static void findSortedArray(LinkedList<Comparable> a, LinkedList<Comparable> result){
        while(a.size() > 0){// at least there are two elements
            if (sort.less(result.getLast(), a.getFirst()) || result.getLast()==a.getFirst()){
                result.add(a.pollFirst());
                findSortedArray(a,result);
            }
            else{break;}
        }
    }

    // help function
    public static Comparable[] ArrayQueue2ComArray(ArrayDeque<Comparable> queue){
        //TODO: convert ArrayQueue to an array, and return the array
        ArrayDeque<Comparable> queueCopy = queue.clone();
        Comparable[] resultArray = new Comparable[queueCopy.size()];
        int i = 0;
        while (!queueCopy.isEmpty()){
            resultArray[i] = queueCopy.pollFirst();
            i++;
        }
        return resultArray;
    }

    public static Comparable[] ArrayQueue2ComArray(ArrayDeque<Comparable> queue, Comparable[] resultArray){
        //TODO: convert ArrayQueue to an array, and return the array
        ArrayDeque<Comparable> queueCopy = queue.clone();
        int i = 0;
        while (!queueCopy.isEmpty()){
            resultArray[i] = queueCopy.pollFirst();
            i++;
        }
        return resultArray;
    }

    public static LinkedList<Comparable> ArrayQueue2ComArray(LinkedList<Comparable> queue, LinkedList<Comparable> resultArray){
        //TODO: convert ArrayQueue to an array, and return the array
        LinkedList<Comparable> queueCopy = (LinkedList<Comparable>) queue.clone();
        resultArray.clear();
        while (!queueCopy.isEmpty()){
            resultArray.add(queueCopy.pollFirst());
        }
        return resultArray;
    }

    public static ArrayDeque<Comparable> ComArray2ArrayQueue(Comparable[] Array){
        //TODO: convert an array to an ArrayQueue, and return the ArrayQueue
        Comparable[] myArray = Array.clone();
        ArrayDeque<Comparable> queue = new ArrayDeque<>();
        for (int i = 0; i < myArray.length; i++) {
            queue.add(myArray[i]);
        }
        return queue;
    }

    public static void main(String[] args) {
        Comparable[] a = sort.generateStringArray(5);
        sort.printStringArray(a);
        NaturalMergeSort(a);
        sort.printStringArray(a);

        LinkedList<Comparable> b = sort.generateStringLinkedList(5);
        sort.printStringLinkedList(b);
        NaturalMergeSort(b);
        sort.printStringLinkedList(b);

    }
}
