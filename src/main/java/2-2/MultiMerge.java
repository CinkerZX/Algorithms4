import java.util.Comparator;

/**
 * In contrast to two merge, the array is divided into k sub-arrays, and these k parts will be merged iteratively
 *
 * Idea: maintain minHeap with the length of k
 * This heap is composed by the smallest element from the k sub-arrays
 * The head of the heap will be inserted into the ordered big array, and the pointer of the sub-array where the removed element came from will move
 *
 * To locate the sub-array where the removed element came from, we need a special data structure (Element) which saves both the value and sub-array index;
 * it is this structure that saved in the MinHeap.
 */
public class MultiMerge {
    private int K; // k-merge

    public MultiMerge(int k){K = k;}

    private class Element{
        Comparable Value;
        int subArrayIndex;

        public Element(Comparable Value, int subArrayIndex){
            this.Value = Value;
            this.subArrayIndex = subArrayIndex;
        }
    }

    Comparator<Element> elementComparator = (e1, e2) -> e1.Value.compareTo(e2.Value);

    public void multiMerge(Comparable[] a, int[] subArrPointer, int[] subArrLen){
        //Todo: merge the k ordered sub arrays
        int n = MathHelpFun.sum(subArrLen);
        Comparable[] myaux = new Comparable[n]; //copy the k sub arrays to aux[]

        MinHeap<Element> myMinHeap = new MinHeap<>(elementComparator);
        int[] subpointer = zerosArr(this.K);

        for (int i = 0; i < this.K; i++) { // repeat copying k times
            int pointerAux = 0;
            for (int j = 0; j < subArrLen[i]; j++) { // copy the ith array
                myaux[pointerAux] = a[subArrPointer[i]+j];
                pointerAux++;
            }
            // Put the k elements into the MinHeap
            Element myElement = new Element(a[subArrPointer[i]],subpointer[i]);
            myMinHeap.insert(myElement);
            subpointer[i]++;
        }

        // merge back to a
        int moveSubArrPointer;
        Element insertToMinHeap;

        // ************ Also need to maintain an array that save for the sub arrays that still have values that not being ordered
        for (int i = 0; i < n; i++) {
            Element minElement = myMinHeap.removeMin();
            moveSubArrPointer = minElement.subArrayIndex; // from moveSubArrPointer's sub array
            a[subArrPointer[0]+i] = minElement.Value;
            subpointer[moveSubArrPointer]++; // move the pointer of this sub array
            if (subpointer[moveSubArrPointer] < subArrLen[moveSubArrPointer]){ // this sub array is empty
                insertToMinHeap = new Element(a[subArrPointer[moveSubArrPointer]+subpointer[moveSubArrPointer]], moveSubArrPointer);
            }
            else //select randomly one value from the rest arrays which is not empty
        }
//
//        // *********** maintain the minHeap and insert elements in myaux into a
//
//
//        int i = lo, j = mid+1;
//        for (int k = lo; k <= hi ; k++) {
//            if (i>mid) // a[0, 1] is sorted, and a[0] < a[1]
//                // i = k = lo = 1, aux[a[1], a[0], a[2], a[3]]
//                // a[0] = aux[1]
//                a[k] = myaux[j++ - lo];
//            else if (j>hi) // a[2,3] is sorted, and a[2] < a[3]
//                // j = 3, hi = 2, k = 2, i = 2, aux[a[0], a[1], a[3], a[2]];
//                // a[2] = aux[3]
//                a[k] = myaux[i++ - lo];
//            else if (less(myaux[j-lo], myaux[i-lo])) a[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
//            else
//                a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
//        }
    }

//    //Idea: breaking big problems into small problems
//    // iteration: call sort[a, lo, hi], until the smallest unit a[0, 1], a[2, 3], ...
//    public static void sortTopDown(Comparable[] a){
//        //TODO: call sortTopDown
//        sortTopDown(a, 0, a.length-1);
//    }
//    public static void sortTopDown(Comparable[] a, int lo, int hi){
//        //TODO: sort from the Top to Bottom
//        if (hi <=lo) return;
//        int mid = lo + (hi-lo)/2;
//        sortTopDown(a, lo, mid);
//        sortTopDown(a, mid+1, hi);
//        //TODO: merge from the bottom to up
//        merge(a, lo, mid, hi);
////        printStringArray(a); // for test
//    }

//    // Idea: building small solutions into larger ones
//    public static void sortBottomUp(Comparable[] a){
//        //TODO: use two for loop to merge
//        int N = a.length;
//        for (int i = 1; i < N; i = i+i) { // i-- subarray size
//            for (int lo = 0; lo < N-i ; lo += i+i) {
//                merge(a, lo, lo+i-1, Math.min(lo+i-1+i, N-1)); // hi -- the mid+i, if it out of index, hi = N-1
//                printStringArray(a); // for test
//            }
//        }
//    }

    // HelpFunction
    public static int[] zerosArr(int len){
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = 0;
        }
        return result;
    }

    public static void main(String[] args) {

    }


}
