/**
 * Code {@TestSortAlgNonUniformDis} aims at testing different sort algorithms with specific test data set
 *
 * Algs: Insertion, Selection, Shell
 * Data Distribution: Gaussian, Poisson, Geometric, Discrete
 *
 * For such dataset, since certain number with higher probability to appear
 * for example, for Poisson distribution with lambda = 1, most are 1, 2, 3, 4 are less and less
 * Thus the data can look like following: 3 1 1 1 2 1 1 4 1 1 1 1 1
 * As can be seen, the dataset is partially sorted, thus shellsort performs better than insert sorting,
 * and selection who needs lots of compare and move operation, performs the worst
 *
 * ****** Data obey Gaussian distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0.001     	0.0011    	0.0016    	0.0051    	0.0223
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0021    	0.0042    	0.0145    	0.0616    	0.2483
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0004    	0.0002    	0.0004    	0.0002    	0.0007
 *
 * ****** Data obey Poisson distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0005    	0.0004    	0.001     	0.0036    	0.0152
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0019    	0.005     	0.0233    	0.0836    	0.3675
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0002    	0.0002    	0.0004    	0.0004    	0.0007
 *
 * ****** Data obey Geometric distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0.0002    	0.0009    	0.0036    	0.0146
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0013    	0.0066    	0.0216    	0.0862    	0.3612
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0001    	0         	0.0002    	0.0004    	0.0007
 *
 * ****** Data obey Discrete distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0         	0.0008    	0.0031    	0.0106
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0014    	0.0059    	0.0241    	0.088     	0.3552
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0         	0.0002    	0.0002    	0.0005
 *
 * ****** Data obey Half0Half1 distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0004    	0.0007    	0.0011    	0.0037    	0.014
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0024    	0.0056    	0.0239    	0.0852    	0.3441
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0002    	0.0002    	0.0004    	0.0004    	0.0005
 *
 * ****** Data obey Half0HalfRestAdd1 distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0.0004    	0.0013    	0.0049    	0.0194
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0016    	0.0061    	0.0236    	0.0959    	0.3829
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0         	0.0001    	0.0002    	0.0004
 *
 * ****** Data obey Half0HalfRandom distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0001    	0.0004    	0.0014    	0.0053    	0.022
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0016    	0.006     	0.0234    	0.0906    	0.3668
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0.0001    	0.0001    	0.0002    	0.0005
 *
 */
public class TestSortAlgNonUniformDis {
    public TestSortAlgNonUniformDis(){} // Constructor do nothing

    //******** Helper function
    public static void printDisInfo(String Dis){
        System.out.println("****** Data obey " + Dis + " distribution ***** ");
    }
    public static void printAlgInfo(String Alg){
        System.out.println("Time take for " + Alg + ":");
    }

    public static Comparable[] int2Comparable(int[] data){
        int n = data.length;
        Comparable[] result = new Comparable[n];
        for (int i = 0; i < n; i++) {
            result[i] = data[i];
        }
        return result;
    }

    public static Comparable[] Double2Comparable(Double[] data){
        int n = data.length;
        Comparable[] result = new Comparable[n];
        for (int i = 0; i < n; i++) {
            result[i] = data[i];
        }
        return result;
    }

    public static void main(String[] args) {
        String[] dataDisTypes = new String[]{"Gaussian", "Poisson", "Geometric", "Discrete", "Half0Half1", "Half0HalfRestAdd1", "Half0HalfRandom"};
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
