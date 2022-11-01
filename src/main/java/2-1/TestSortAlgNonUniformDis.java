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
 * 0.0013    	0.0016    	0.0022    	0.0097    	0.0378
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0047    	0.004     	0.0147    	0.0635    	0.3064
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0004    	0.0002    	0.0004    	0.0007    	0.001
 *
 * ****** Data obey Poisson distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0005    	0.0004    	0.001     	0.0046    	0.0177
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0022    	0.0051    	0.0214    	0.0879    	0.3864
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0001    	0.0002    	0.0004    	0.0004    	0.0008
 *
 * ****** Data obey Geometric distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0001    	0.0004    	0.0013    	0.0045    	0.0186
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0015    	0.005     	0.0236    	0.0971    	0.4167
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0.0001    	0.0002    	0.0004    	0.0008
 *
 * ****** Data obey Discrete distribution *****
 * Time take for Insertion:
 * 1000      	2000      	4000      	8000      	16000
 * 0         	0.0004    	0.0008    	0.0033    	0.0154
 * Time take for Selection:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0015    	0.0071    	0.0244    	0.0962    	0.3837
 * Time take for Shell:
 * 1000      	2000      	4000      	8000      	16000
 * 0.0001    	0         	0.0001    	0.0004    	0.0007
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


    public static void main(String[] args) {
        String[] dataDisTypes = new String[]{"Gaussian", "Poisson", "Geometric", "Discrete"};
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
