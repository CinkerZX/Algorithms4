/**
 * Code {@TestPartiallySortedData} aims at testing different sort algorithms with specific test data set
 *
 * Algs: Insertion, Selection, Shell
 * Data Distribution:
 * 1. 95 percent sorted, last 5 percent random values (formerSorted)
 * 2. All entries within 10 positions of their final place in the array (with10PositionOrder)
 * 3. Sorted except for 5 percent of the entries randomly dispersed throughout the array (sort5PercentRandom)
 *
 * Results: For "formerSorted",
 * Insertion, the former 95% are sorted, thus for these elements, each of them just need to compare with the former one
 * Shell, it has more comparing operations.
 * (But if the array is like this: [5, 6, 7, ..., 99, 2, 1, 4, 3], then the selection performs better)
 *
 * For with10PositionOrder, the array has order between groups, namely, the max element in group_i is less than the minimum element in group_(i+1),
 * There is no need for changing elements between groups, thus Selection performs definitely better than Shell.
 *
 * For sort5PercentRandom, since there has need to change elements between groups, thus, Shell is more dominated
 *
 * Whatever kinds of arrays here is partially sorted, thus Selection is less dominated compare with the other two algorithms.
 *
 * ****** Data obey Gaussian distribution *****
 * ****** Partially sorted data "formerSorted" *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0002    	0.0001    	0.0002    	0.0001    	0.0004
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0022    	0.0043    	0.0157    	0.0659    	0.2659
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0004    	0.0001    	0.0004    	0.0005    	0.0005
 *
 * ****** Partially sorted data "with10PositionOrder" *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0.0002    	0         	0         	0.0001
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0013    	0.0046    	0.0157    	0.0726    	0.2657
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0.0002    	0.0004    	0.0004    	0.0005
 *
 * ****** Partially sorted data "sort5PercentRandom" *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0002    	0.0002    	0.0007    	0.0013    	0.0022
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0021    	0.0066    	0.0294    	0.1179    	0.4603
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0001    	0.0002    	0.0004    	0.0005    	0.0007
 *
 */
public class TestPartiallySortedData {
    public TestPartiallySortedData(){} // Constructor do nothing

    //******** Helper function
    public static void printDisInfo(String Dis){
        System.out.println("****** Partially sorted data \"" + Dis + "\" ***** ");
    }
    public static void printAlgInfo(String Alg){
        System.out.println("Time take for " + Alg + ":");
    }

    public static void main(String[] args) {
        String[] dataDisTypes = new String[]{"formerSorted", "with10PositionOrder", "sort5PercentRandom"};
        String[] algs = new String[]{"Insertion", "Selection", "Shell"};
        // Gaussian
        for (int i = 0; i < dataDisTypes.length; i++) {
            printDisInfo(dataDisTypes[i]);
            for (int j = 0; j < algs.length; j++) {
                printAlgInfo(algs[j]);
                DoublingTestSortAlg.mainExp(algs[j], dataDisTypes[i]);
            }
            System.out.println("");
        }
    }

}
