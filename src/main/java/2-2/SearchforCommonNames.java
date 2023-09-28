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
        commonNameList = mergeSortedArrays(nameList1,nameList2);
        if (commonNameList.size()>0){ // If there are common names between nameList1 and nameList2
            String[] stockArr = new String[commonNameList.size()];
            stockArr = commonNameList.toArray(stockArr);
            sort.sortTopDown(nameList3);
            commonNameList = mergeSortedArrays(nameList3,stockArr); // Get the common names
        }
        // TODO: get the first occurred common name
        if (commonNameList.size()!=0){
            firstCommonName = commonNameList.getLast();
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
        int mid = a.length-1;
        Comparable[] result = sort.concatenate(a, b);
        int hi = result.length-1;
        Comparable[] myaux = new Comparable[hi+1]; //copy a[lo, hi] to myaux[]
        for (int k = 0; k < result.length ; k++) {
            myaux[k] = result[k];
        }
        ArrayDeque<String> commonNames = new ArrayDeque<>();
        // merge back to result[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            if (i>mid)
                result[k] = myaux[j++];
            else if (j>hi)
                result[k] = myaux[i++];
            else if (sort.less(myaux[j], myaux[i])) result[k] = myaux[j++]; // if the smallest on the right size is smaller
            else if (i != j & myaux[j] == myaux[i] & (commonNames.size() == 0 || myaux[j] != commonNames.getLast())){ // no repeat
                commonNames.add((String) myaux[j]);
                result[k] = myaux[i++];
            }
            else
                result[k] = myaux[i++]; // the smallest on the left is smaller. Do aux[i] first, and then i++
        }
        return commonNames;
    }

    public static void main(String[] args) {
        // Test Help function - generateNameArray
        String[] nameList1 = generateNameArray(30);
//        sort.printStringArray(nameList1);
        String[] nameList2 = generateNameArray(30);
//        sort.printStringArray(nameList2);
        String[] nameList3 = generateNameArray(30);
//        sort.printStringArray(nameList3);

        // Test sort
//        sort.sortTopDown(nameList1);
//        sort.printStringArray(nameList1);

        // Test SearchforCommonNames
//        String[] nameList1 = new String[]{"Alice", "Bob", "Tim"};
//        String[] nameList2 = new String[]{"Bob", "Bob"};
//        String[] nameList3 = new String[]{"Bob", "Alice"};
        System.out.println(SearchforCommonNames(nameList1, nameList2, nameList3));
        System.out.println("List 1: ");
        sort.printStringArray(nameList1);
        System.out.println("List 2: ");
        sort.printStringArray(nameList2);
        System.out.println("List 3: ");
        sort.printStringArray(nameList3);
    }
}
