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

    public void multiMerge(Comparable[] a, int[] subArrPointer, int[] subArrLen){
        //Todo: merge the k ordered sub arrays
        int n = MathHelpFun.sum(subArrLen);
        Comparable[] myaux = new Comparable[n]; //copy the k sub arrays to aux[]

        MinHeap<Element> myMinHeap = new MinHeap<>(elementComparator);
        int[] subpointer = MathHelpFun.zerosArr(this.K);

        int pointerAux = 0;
        for (int i = 0; i < this.K; i++) { // repeat copying k times
            if (subArrLen[i] != 0){
                for (int j = 0; j < subArrLen[i]; j++) { // copy the ith array
                    myaux[pointerAux] = a[subArrPointer[i]+j];
                    pointerAux++;
                }
                // Put the k elements into the MinHeap, if not empty
                Element myElement = new Element(a[subArrPointer[i]],i);
                myMinHeap.insert(myElement);
                subpointer[i]++;
                if (subpointer[i] == subArrLen[i]){ // **********
                    UnEmptySubArray[i] = 0;
                }else{UnEmptySubArray[i] = 1;}
            }
            else{
                UnEmptySubArray[i] = 0;
            }
        }

        // merge back to a
        int moveSubArrPointer;
        Element insertToMinHeap;
        int insertFromSubArray;

        Element minElement;
//        for (int i = 0; i < n-this.K; i++) {
        for (int i = 0; i < n-1; i++) {
            minElement = myMinHeap.removeMin();
            moveSubArrPointer = minElement.subArrayIndex; // from moveSubArrPointer th sub array
            a[subArrPointer[0]+i] = minElement.Value;
            if (subpointer[moveSubArrPointer] < subArrLen[moveSubArrPointer]){ // this the moveSubArrPointer th sub array is not empty
                insertToMinHeap = new Element(myaux[subArrPointer[moveSubArrPointer]+subpointer[moveSubArrPointer]], moveSubArrPointer);
                insertFromSubArray = moveSubArrPointer;
            }
            else{ // insert from other UnEmptySubArray
                if (MathHelpFun.sum(UnEmptySubArray)!=0){
                    insertFromSubArray = randomSelectNonZeroInt(UnEmptySubArray);
                    insertToMinHeap = new Element(myaux[subArrPointer[insertFromSubArray]+subpointer[insertFromSubArray]], insertFromSubArray);
                }
                else{
                    break; // if all sub array has been empty
                }
            }
            subpointer[insertFromSubArray]++;
            if (subpointer[insertFromSubArray] == subArrLen[insertFromSubArray]){UnEmptySubArray[insertFromSubArray] = 0;}
            myMinHeap.insert(insertToMinHeap);
        }
        // The rest r item, directly pop out from the MinHeap (r <= k
        int r = myMinHeap.size();
        for (int i = 0; i < r; i++) {
            minElement = myMinHeap.removeMin();
            a[subArrPointer[0]+n-r+i] = minElement.Value;
        }
        updateAfterMerge(subArrPointer, subArrLen);
    }

    //Idea: breaking big problems into small problems
    // iteration: call sort[a, lo, hi], until the smallest unit a[0, 1], a[2, 3], ...
    public void sortTopDown(Comparable[] a){
        //TODO: call sortTopDown
        int[] subArrPointer = new int[this.K];
        int[] subArrLen = new int[this.K];
        devideArrayintoKparts(0, a.length-1, subArrPointer, subArrLen);
        sortTopDown(a, subArrPointer, subArrLen);
    }

//    public void multiMerge(Comparable[] a, int[] subArrPointer, int[] subArrLen){
    public void sortTopDown(Comparable[] a, int[] subArrPointer, int[] subArrLen){  // down 下来了，但递归回去的过程出了问题
        if (MathHelpFun.sum(subArrLen) == 0){return;}
        int[] subArrPointer2 = new int[this.K];
        int[] subArrLen2 = new int[this.K];
        for (int i = 0; i < this.K; i++) {
            int c = subArrLen[i];
            switch (c) {
                case 1: // can already merge
                    multiMerge(a, subArrPointer, subArrLen);
                case 0: // no element
                    break;
                default:
                    devideArrayintoKparts(subArrPointer[i], subArrPointer[i]+subArrLen[i]-1, subArrPointer2, subArrLen2);
                    sortTopDown(a, subArrPointer2, subArrLen2);
            }
        }
//        multiMerge(a, subArrPointer, subArrLen);
    }

    // HelpFunction
    public static int randomSelectNonZeroInt(int[] arr){
        int result = MathHelpFun.generateRandomInt(0,arr.length-1);
        if (arr[result] !=0){return result;}
        else return randomSelectNonZeroInt(arr);
    }

    // Update subArrLen: after merge, the
    public static void updateAfterMerge(int[] subArrPointer, int[] subArrLen){
        subArrPointer[0] = subArrPointer[0];
        subArrLen[0] = MathHelpFun.sum(subArrLen);
        for (int i = 1; i < subArrPointer.length; i++) {
            subArrPointer[i] = -1;
            subArrLen[i] = 0;
        }
    }

    public void devideArrayintoKparts(int lo, int hi, int[] subArrPointer, int[] subArrLen){ // need to amend: if hi - lo == 1, len == 1; then can already merge
        int len = hi-lo+1;
        int size = len/this.K;
        int cases = 0;
        if (lo == hi){cases = 3;}
        if (len >= this.K*this.K){cases = 0;}
        if (this.K*this.K > len & len > this.K){cases = 1;}
        if (this.K>=len & len > 0){cases = 2;}

        switch (cases){
            case 0: // we have k groups, each group have [len - len/this.K*(k-1), len/this.K] items
                for (int i = 0; i < this.K; i++) {
                    subArrPointer[i] = lo + i*(size+1);
                    if (i == this.K-1){
                        subArrLen[i] = len-(size+1)*(this.K-1);
                    }
                    else{
                        subArrLen[i] = size+1;
                    }
                }
                break;
            case 1: // we have less than size groups, and size < k
                for (int i = 0; i <this.K; i++) {
                    if (i<size){
                        subArrPointer[i] = lo + i*this.K;
                        subArrLen[i] = this.K;
                    }else{
                        if (i*this.K<len){
                            subArrPointer[i] = lo + i*this.K;
                            subArrLen[i] = len-size*this.K;
                        }
                        else{
                            subArrPointer[i] = -1;
                            subArrLen[i] = 0;
                        }
                    }
                }
                break;
            case 2: // we have less than this.K items
                for (int i = 0; i < len; i++) {
                    subArrPointer[i] = lo + i;
                    subArrLen[i] = 1;
                }
                if (this.K > len){
                    for (int i = len; i < this.K; i++) {
                        subArrPointer[i] = -1;
                        subArrLen[i] = 0;
                    }
                }
                break;
            case 3: // there are zero items left
                subArrLen = MathHelpFun.zerosArr(this.K);
                break;
        }
    }

    public static void main(String[] args) {
        MultiMerge myMultiMerge = new MultiMerge(3);
//        Comparable[] a = new Comparable[]{2,3,6,1,7,8,3,3,4};
        Comparable[] a = new Comparable[]{2,3,6,1};
        // why when there are nl+1 works, but when there are nk+2 or nk does not work, need to further debug
        sort.printStringArray(a);
        myMultiMerge.sortTopDown(a);
        sort.printStringArray(a);
    }


}
