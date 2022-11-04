import java.util.concurrent.ThreadLocalRandom;

/**
 * Code {@PartiallySortedData} aims at generating test dataset that partially sorted arrays:
 *
 * 1. 95 percent sorted, last 5 percent random values (formerSorted)
 * 2. All entries within 10 positions of their final place in the array (with10PositionOrder)
 * 3. Sorted except for 5 percent of the entries randomly dispersed throughout the array (sort5PercentRandom)
 */
public class PartiallySortedData {
    public PartiallySortedData(){} // Constructor does nothing

    /**
     * This function is for generating an array whose former 95% are sorted but the last 5% are randomly sorted
     * @param n: the size of the array
     * @return
     */
    public static Comparable[] formerSorted(int n){
        //TODO: construct the original ordered array
        Comparable[] data = NonUniformDistributions.sortedComparable(n);
        //TODO: index need to change
        double percent = 0.95;
        int nSorted = (int) Math.round(n*percent);
        int nNonSorted = n-nSorted;
        Integer[] changeIndex = new Integer[nNonSorted];
        for (int i = 0; i < nNonSorted; i++) {
            changeIndex[i] = i+nSorted;
        }
        //TODO: shuffle the index
        reorderArrar(data, changeIndex, NonUniformDistributions.shuffleIntArr(changeIndex.clone()));
        return data;
    }

    /**
     * This function is for generating an array that partially sorted, every element is within 10 position of where it should be
     * Idea: separate the array into several groups, where each group contains 10 elements, reorder inside each group
     * @param n
     * @return
     */
    public static Comparable[] with10PositionOrder(int n){
        Comparable[] data = NonUniformDistributions.sortedComparable(n);
        int numGroup = n/10;
        Integer[] changeIndex = new Integer[10];
        for (int i = 0; i < numGroup; i++) {
            for (int j = 0; j < 10; j++) {
                changeIndex[j] = i*10+j;
            }
            reorderArrar(data, changeIndex, NonUniformDistributions.shuffleIntArr(changeIndex.clone()));
        }
        int numRest = n-numGroup*10;
        if (numRest!=0){
            Integer[] changeIndexRest = new Integer[numRest];
            for (int i = 0; i < numRest; i++) {
                changeIndexRest[i] = i+numGroup*10;
            }
            reorderArrar(data, changeIndex, NonUniformDistributions.shuffleIntArr(changeIndex.clone()));
        }
        return data;
    }

    /**
     * This function is for generating a partially sorted array within witch 5 percent is sorted
     * @param n
     * @return
     */
    public static Comparable[] sort5PercentRandom(int n){
        Comparable[] data = NonUniformDistributions.sortedComparable(n);
        int nRandom = (int) Math.round(0.05*n);
        Integer[] indexArray = new Integer[nRandom];
        //TODO: select the index that needs to shuffle [0,n-1]
        for (int i = 0; i < nRandom; i++) {
            indexArray[i] = ThreadLocalRandom.current().nextInt(0, n);
        }
//        CornerCases.show(indexArray);
        reorderArrar(data, indexArray, NonUniformDistributions.shuffleIntArr(indexArray.clone()));
        return data;
    }

    //**************************** HelperFunction
    /**
     *
     * @param originalArr
     * @param changeIndex
     * @param pointedOrder
     */
    public static void reorderArrar(Comparable[] originalArr, Integer[] changeIndex, Integer[] pointedOrder){
        Comparable[] tempData = originalArr.clone();
        for (int i = 0; i < changeIndex.length; i++) {
            originalArr[changeIndex[i]] = tempData[pointedOrder[i]];
        }
    }

    public static void main(String[] args) {
        Comparable[] a = PartiallySortedData.formerSorted(50);
        CornerCases.show(a);

        a = PartiallySortedData.with10PositionOrder(50);
        CornerCases.show(a);

        a = PartiallySortedData.sort5PercentRandom(50);
        CornerCases.show(a);
    }
}
