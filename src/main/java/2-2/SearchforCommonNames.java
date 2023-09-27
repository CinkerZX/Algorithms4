import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Aim: Find the common names within three name lists of length N, and return the first such name, with linearithmic time complexity
 * Idea: sort the first two lists, and then merge this two lists save the common names set
 *       if there are common names, then sort the third list, and merge the common name list into the third sorted list, and find out the common list
 *       if there is no common name, return there is no common name
 */
public class SearchforCommonNames {
    private static ArrayDeque<String> commonNameList = new ArrayDeque<String>();
    private static String firstCommonName = new String("There is no common name in these three lists.");

    public static String SearchforCommonNames(Comparable[] nameList1, Comparable[] nameList2, Comparable[] nameList3){
        sort.sortTopDown(nameList1);
        sort.sortTopDown(nameList2);
        MergingSortedQueue.copyQueue(mergeSortedArrays(nameList1,nameList2), commonNameList);
        // *************************
        if (commonNameList.size()>0){
            String[] stockArr = new String[commonNameList.size()];
            stockArr = commonNameList.toArray(stockArr);
            sort.sortTopDown(nameList3);
            MergingSortedQueue.copyQueue(mergeSortedArrays(nameList3,stockArr), commonNameList);
        }


        return firstCommonName;
    }



    // Help functions
    public static String[] generateNameArray(int len){
        //TODO: generate a name list with the length of len
        String[] namePool = {"Olivia", "Liam", "Emma", "Noah", "Ava", "Isabella", "Sophia", "Mia", "Charlotte", "Amelia",
                "Harper", "Evelyn", "Abigail", "Emily", "Elizabeth", "Sofia", "Ella", "Madison", "Scarlett", "Grace",
                "Chloe", "Penelope", "Luna", "Aria", "Hazel", "Zoe", "Lily", "Victoria", "Natalie", "Stella",
                "Avery", "Mila", "Lillian", "Claire", "Aubrey", "Layla", "Riley", "Samantha", "Nora", "Eleanor",
                "Lucas", "Jackson", "Aiden", "Sebastian", "Elijah", "Caleb", "Benjamin", "Mason", "Henry", "Ethan",
                "Daniel", "Matthew", "Jackson", "Samuel", "David", "Joseph", "Nicholas", "Andrew", "Jonathan", "Nathan"};
        String[] a = new String[len];
        Random ran = new Random();
        for (int i = 0; i < len; i++) {
            a[i]= namePool[ran.nextInt(namePool.length)];
        }
        return a;
    }

    public static ArrayDeque<String> mergeSortedArrays(Comparable[] a, Comparable[] b){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        int lo = 0;
        int mid = a.length;
        Comparable[] result = sort.concatenate(a, b);
        int hi = result.length;
        Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
        for (int k = lo; k <=hi ; k++) {
            myaux[k-lo] = result[k];
        }
        ArrayDeque<String> commonNames = new ArrayDeque<>();
        // merge back to result[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            if (i>mid)
                result[k] = myaux[j++ - lo];
            else if (j>hi)
                result[k] = myaux[i++ - lo];
            else if (sort.less(myaux[j-lo], myaux[i-lo])) result[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
            else if (myaux[j-lo] == myaux[i-lo] & myaux[j-lo] != commonNames.getLast()){ // no repeat
                commonNames.add((String) myaux[j-lo]);
                result[k] = myaux[i++ - lo];
            }
            else
                result[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
        }
        return commonNames;
    }

    public static void main(String[] args) {
        // Test Help function - generateNameArray
        String[] nameList1 = generateNameArray(5);
        sort.printStringArray(nameList1);

        // Test sort
        sort.sortTopDown(nameList1);
        sort.printStringArray(nameList1);
    }
}
