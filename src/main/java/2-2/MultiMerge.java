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



}
