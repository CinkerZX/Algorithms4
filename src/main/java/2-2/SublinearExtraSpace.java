import sun.awt.image.ByteComponentRaster;

/**
 * 2.2.12 Sublinear extra space: reduce the extra space requirement to max(M, N/M)
 * Idea: separate sort and merge (no need to aux array
 * First divide the array into N/M blocks of size M, in each block, do selection sort
 * Then run through the array, merge the first block with the second, and the second with the third.
 */
public class SublinearExtraSpace {
    public static void SublinearExtraSpace(Comparable[] a, int M){
        // order blocks in a
        orderByBlocks (a, M);
        // merge blocks
    }

    public static void orderByBlocks (Comparable[] a, int M){
        int nBlocoks = (int) Math.ceil(a.length/M);
        for (int i = 0; i <= a.length/M; i++) {
            SublinearExtraSpace.orderedBlocks(a, (i-1)*M, Insertion.sortReturn(SublinearExtraSpace.subsetComparableArray(a, (i-1)*M, M)));
        }
        if (nBlocoks != a.length/M) {
            SublinearExtraSpace.orderedBlocks(a, (a.length/M)*M, Insertion.sortReturn(SublinearExtraSpace.subsetComparableArray(a, (a.length/M)*M, M)));
        }
    }

    public static void SublinearExtraSpace(Comparable[] a){
        SublinearExtraSpace(a, 5);
    }

    // help function
    public static Comparable[] subsetComparableArray(Comparable[] a, int startIndex, int len){
        Comparable[] result = new Comparable[len];
        for (int i = 0; i < len; i++) {
            result[i] = a[startIndex+i];
        }
        return result;
    }

    public static Comparable[] orderedBlocks(Comparable[] a, int startIndex, Comparable[] orderedBlock){
        for (int i = 0; i < orderedBlock.length; i++) {
            a[startIndex+i] = orderedBlock[i];
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(11/5);
        System.out.println(11%5);
    }
}
