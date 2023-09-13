import sun.awt.image.ByteComponentRaster;

/**
 * 2.2.12 Sublinear extra space: reduce the extra space requirement to max(M, N/M)
 * Idea: separate sort and merge (no need to aux array
 * First divide the array into N/M blocks of size M, in each block, do selection sort
 * Then run through the array, merge the first block with the second, and the second with the third.
 */
public class SublinearExtraSpace {

    public static void SublinearExtraSpace(Comparable[] a){
        SublinearExtraSpace(a, 5);
    }

    public static void SublinearExtraSpace(Comparable[] a, int M){
        // order blocks in a
        orderByBlocks (a, M);
        // merge blocks
        SublinearExtraSpaceMerge(a,M);
    }

    public static void orderByBlocks (Comparable[] a, int M){
        for (int i = 1; i <= a.length/M; i++) {
            SublinearExtraSpace.orderedBlocks(a, (i-1)*M, Insertion.sortReturn(SublinearExtraSpace.subsetComparableArray(a, (i-1)*M, M)));
        }
        if (a.length%M != 0) {
            SublinearExtraSpace.orderedBlocks(a, (a.length/M)*M, Insertion.sortReturn(SublinearExtraSpace.subsetComparableArray(a, (a.length/M)*M, a.length - (a.length/M)*M)));
        }
    }

    public static void SublinearExtraSpaceMerge(Comparable[] a, int M){
        // TODO: merge the first block with the second block, and then merge with the third, ....
        for (int i = 2; i <= a.length/M; i++) {
            sort.merge(a, 0, (i-1)*M-1, (i-1)*M-1+M);
        }
        if (a.length%M != 0) {
            sort.merge(a, 0, (a.length/M)*M-1, a.length-1);
        }
    }

    // help function
    public static Comparable[] subsetComparableArray(Comparable[] a, int startIndex, int len){
        // TODO: order the length of
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
        // Test of removeRedundantOrderingMerge
        String[] a = sort.generateStringArray(13);
        System.out.println("Check removeRedundantOrderingMerge");
        sort.printStringArray(a);
        SublinearExtraSpace(a);
        sort.printStringArray(a);

        a = sort.generateStringArray(20);
        System.out.println("Check removeRedundantOrderingMerge");
        sort.printStringArray(a);
        SublinearExtraSpace(a);
        sort.printStringArray(a);
    }
}
