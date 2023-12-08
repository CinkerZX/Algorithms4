import javax.swing.*;
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

    public void sortTopDown(Comparable[] a, int[] subArrPointer, int[] subArrLen){
        if (MathHelpFun.sum(subArrLen) == 0){return;}
        int[] subArrPointer2 = new int[this.K];
        int[] subArrLen2 = new int[this.K];

        // if ordered, then merge, if not ordered, then dive into it
        for (int i = 0; i <= this.K; i++) {
            if (checkIfMerge(a, subArrPointer, subArrLen)){
                multiMerge(a, subArrPointer, subArrLen);
                break;
            }
            else{// dive into each of the not ordered one
                devideArrayintoKparts(subArrPointer[i], subArrPointer[i]+subArrLen[i]-1, subArrPointer2, subArrLen2);
                sortTopDown(a, subArrPointer2, subArrLen2);
            }
            // 有两种情况需要merge，第一种情况是每一个小组都已经ordered了，所以需要一个check是否order的函数，第二种情况是，只有1个
//            int c = subArrLen[i];
//            switch (c) {
//                case 1: // can already merge
//                    multiMerge(a, subArrPointer, subArrLen);
//                    break;
//                case 0: // no element
//                    break;
//                default:
//                    devideArrayintoKparts(subArrPointer[i], subArrPointer[i]+subArrLen[i]-1, subArrPointer2, subArrLen2);
//                    sortTopDown(a, subArrPointer2, subArrLen2);
//                    break;
//            }
        }
    }

    // HelpFunction
    public static int randomSelectNonZeroInt(int[] arr){
        int result = MathHelpFun.generateRandomInt(0,arr.length-1);
        if (arr[result] !=0){return result;}
        else return randomSelectNonZeroInt(arr);
    }

    // Update subArrLen: after merge
    public static void updateAfterMerge(int[] subArrPointer, int[] subArrLen){
        subArrPointer[0] = subArrPointer[0];
        subArrLen[0] = MathHelpFun.sum(subArrLen);
        for (int i = 1; i < subArrPointer.length; i++) {
            subArrPointer[i] = -1;
            subArrLen[i] = 0;
        }
    }

    // Check if need to merge bottom up. Idea: if subArrLen, except for the first one, the rest are zero, then should break
    public static boolean checkIfnonZeroAllZero(int[] subArrLen){
        return (MathHelpFun.sum(subArrLen) - subArrLen[0] == 0);
    }

    // Check if the arrar is ordered.
    public static boolean checkIfOrdered(Comparable[] a, int startIndex, int length){
        if (length == 1){return true;}
        else{
            for (int i = 0; i < length-1; i++) {
                if (!sort.less(a[startIndex+i],a[startIndex+i+1])){
                    return false;
                }
            }
        }
        return true;
    }

    //Check if the k parts of a can be merged. Idea: if each part if ordered, then can be merged
    public static boolean checkIfMerge(Comparable[] a, int[] subArrPointer, int[] subArrLen){
        for (int i = 0; i < subArrPointer.length; i++) { // check each of the k parts
            if(!checkIfOrdered(a, subArrPointer[i], subArrLen[i])){// if there is one that not ordered
                return false;
            }
        }
        return true;
    }

    public void devideArrayintoKparts(int lo, int hi, int[] subArrPointer, int[] subArrLen){ // need to amend: if hi - lo == 1, len == 1; then can already merge
        int len = hi-lo+1;
        int size = len/this.K;
        int cases = 0;
        if (lo == hi){cases = 3;}
        if (len >= this.K*this.K){cases = 0;}
        if (this.K*this.K > len & len > this.K){cases = 1;}
        if (this.K>=len & len > 0){cases = 2;}

        switch (cases){ // ************************************************
            case 0: // we have k groups, each group have [len - len/this.K*(k-1), len/this.K] items
                for (int i = 0; i < this.K; i++) {
//                    subArrPointer[i] = lo + i*(size+1);
                    subArrPointer[i] = lo + i*size;
                    if (i == this.K-1){
//                        subArrLen[i] = len-(size+1)*(this.K-1);
                        subArrLen[i] = len-(size)*(this.K-1);
                    }
                    else{
//                        subArrLen[i] = size+1;
                        subArrLen[i] = size;
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
        Comparable[] a = new Comparable[]{3,2,1,0,2,6,8,5,3,1};
        // why when there are nl+1 works, but when there are nk+2 or nk does not work, need to further debug
        sort.printStringArray(a);
        myMultiMerge.sortTopDown(a);
        sort.printStringArray(a);
    }


}
