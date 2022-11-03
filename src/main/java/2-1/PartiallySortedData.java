/**
 * Code {@PartiallySortedData} aims at generating test dataset that partially sorted arrays:
 *
 * 1. 95 percent sorted, last 5 percent random values
 * 2. All entries within 10 positions of their final place in the array
 * 3. Sorted except for 5 percent of the entries randomly dispersed throughout the array
 */
public class PartiallySortedData {
    public PartiallySortedData(){} // Constructor does nothing

    /**
     * This function is for generating an array whose former 95% are sorted but the last 5% are randomly sorted
     * @param n: the size of the array
     * @return
     */
    public static Comparable[] formerSorted(int n){
        Comparable[] data = new Comparable[n];
        double percent = 0.95;
        int nSorted = (int) Math.round(n*percent);
        for (int i = 0; i < nSorted; i++) {
            data[i]  = i;
        }
        int nNonSorted = n-nSorted;
        Integer[] indexArray = new Integer[nNonSorted];
        for (int i = nSorted; i < n; i++) {
            indexArray[i-nSorted] = i;
        }
        Integer[] shuffledIndex = NonUniformDistributions.shuffleIntArr(indexArray);
        for (int i = 0; i < nNonSorted; i++) {
            data[shuffledIndex[i]] = i+nSorted;
        }
        return data;
    }

    //**************************** HelperFunction
    /**
     *
     * @param originalArr
     * @param changeIndex
     * @param pointedOrder
     */
    public void reorderArrar(Comparable[] originalArr, Integer[] changeIndex, Integer pointedOrder){

    }

    public static void main(String[] args) {
//        Comparable[] a = PartiallySortedData.formerSorted(50);
//        CornerCases.show(a);
    }
}
