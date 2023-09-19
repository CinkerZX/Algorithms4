import java.util.LinkedList;

/**
 * Aim: shuffling a LinkedList
 * Idea: divide-and-conquer
 * Based on merge sort, when merging, instead of comparing, but randomly choose to exchange or not
 */
public class LinkedListShuffling {
    // Merge linkedList randomly
    public static void mergeRandomly(LinkedList<Comparable> a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        LinkedList<Comparable> myaux = new LinkedList<Comparable>();
        for (int k = lo; k <=hi ; k++) {
            myaux.add(a.get(k));
        }
        int i = lo, j = mid+1; // i, j are the pointer of a and aux
        for (int k = lo; k <= hi ; k++) { // k is the index moving in a
            if (i>mid)
                a.set(k,myaux.get(j++ - lo));
            else if (j>hi)
                a.set(k,myaux.get(i++ - lo));
            else if (Math.random()>0.5){ // the probability of swap
                a.set(k, myaux.get(j++ - lo));
            }
            else
                a.set(k, myaux.get(i++ - lo));
        }
    }

    //Idea: breaking big problems into small problems
    //iteration: call sort[a, lo, hi], until the smallest unit a[0, 1], a[2, 3], ...
    public static void shufflingTopDown(LinkedList<Comparable> a){
        //TODO: call sortTopDown
        shufflingTopDown(a, 0, a.size()-1);
    }
    public static void shufflingTopDown(LinkedList<Comparable> a, int lo, int hi){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        shufflingTopDown(a, lo, mid);
        shufflingTopDown(a, mid+1, hi);
        //TODO: merge from the bottom to up
        mergeRandomly(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        LinkedList<Comparable> a = sort.generateStringLinkedList(5);
//        System.out.println("Original LinkedList: ");
//        sort.printStringLinkedList(a);
        NaturalMergeSort.NaturalMergeSort(a);
        System.out.println("The sorted LinkedList: ");
        sort.printStringLinkedList(a);

        shufflingTopDown(a);
        System.out.println("Shuffled LinkedList: ");
        sort.printStringLinkedList(a);
        shufflingTopDown(a);
        System.out.println("Shuffled LinkedList: ");
        sort.printStringLinkedList(a);
        shufflingTopDown(a);
        System.out.println("Shuffled LinkedList: ");
        sort.printStringLinkedList(a);
    }
}
