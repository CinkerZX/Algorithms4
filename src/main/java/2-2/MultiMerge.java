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
    private int[] UnEmptySubArray;

    public MultiMerge(int k){
        K = k;
        UnEmptySubArray = MathHelpFun.onesArr(k);
    }

    private class Element{
        Comparable Value;
        int subArrayIndex;

        public Element(Comparable Value, int subArrayIndex){
            this.Value = Value;
            this.subArrayIndex = subArrayIndex;
        }
    }

    Comparator<Element> elementComparator = (e1, e2) -> e1.Value.compareTo(e2.Value);

    public void multiMerge(Comparable[] a, int[] subArrPointer, int[] subArrLen){ // ********** debug
        //Todo: merge the k ordered sub arrays
        int n = MathHelpFun.sum(subArrLen);
        Comparable[] myaux = new Comparable[n]; //copy the k sub arrays to aux[]

        MinHeap<Element> myMinHeap = new MinHeap<>(elementComparator);
        int[] subpointer = MathHelpFun.zerosArr(this.K);

        int pointerAux = 0;
        for (int i = 0; i < this.K; i++) { // repeat copying k times
            for (int j = 0; j < subArrLen[i]; j++) { // copy the ith array
                myaux[pointerAux] = a[subArrPointer[i]+j];
                pointerAux++;
            }
            // Put the k elements into the MinHeap
            Element myElement = new Element(a[subArrPointer[i]],i);
            myMinHeap.insert(myElement);
            subpointer[i]++;
        }

        // merge back to a
        int moveSubArrPointer;
        Element insertToMinHeap;
        int insertFromSubArray;

        Element minElement;
        for (int i = 0; i < n-this.K; i++) {
            minElement = myMinHeap.removeMin();
            moveSubArrPointer = minElement.subArrayIndex; // from moveSubArrPointer th sub array
            a[subArrPointer[0]+i] = minElement.Value;
            if (subpointer[moveSubArrPointer] < subArrLen[moveSubArrPointer]){ // this the moveSubArrPointer th sub array is not empty
                insertToMinHeap = new Element(myaux[subArrPointer[moveSubArrPointer]+subpointer[moveSubArrPointer]], moveSubArrPointer);
                insertFromSubArray = moveSubArrPointer;
//                subpointer[moveSubArrPointer]++; // move the pointer of this sub array
            }
            else{
                insertFromSubArray = randomSelectNonZeroInt(UnEmptySubArray);
                insertToMinHeap = new Element(myaux[subArrPointer[insertFromSubArray]+subpointer[insertFromSubArray]], insertFromSubArray);
//                subpointer[insertFromSubArray]++;
            }
            subpointer[insertFromSubArray]++;
            if (subpointer[insertFromSubArray] == subArrLen[insertFromSubArray]){UnEmptySubArray[insertFromSubArray] = 0;}
            myMinHeap.insert(insertToMinHeap);
        }
        
        // The rest k item, directly pop out from the MinHeap
        for (int i = 0; i < this.K; i++) {
            minElement = myMinHeap.removeMin();
            a[n-this.K+i] = minElement.Value;
        }
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
    public static int randomSelectNonZeroInt(int[] arr){
        int result = MathHelpFun.generateRandomInt(0,arr.length-1);
        if (arr[result] !=0){return result;}
        else return randomSelectNonZeroInt(arr);
    }

    public static void main(String[] args) {
        MultiMerge myMultiMerge = new MultiMerge(3);
        Comparable[] a = new Comparable[]{2,3,6,1,7,8,3,3,4};
        int[] subArrayPointer = new int[]{0, 3, 6};
        int[] subArrayLen = new int[]{3, 3, 3};
        myMultiMerge.multiMerge(a,subArrayPointer, subArrayLen);
        sort.printStringArray(a);
    }


}
