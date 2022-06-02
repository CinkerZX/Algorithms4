import java.util.LinkedList;

/**
 * The {@Code BitonicSearch}
 * Read a bitonic array (composed by an increasing sequence of ints followed immediately by a decreasing sequence)
 * of N distinct int values, and determine if a given int is in the array.
 *
 * The program take ~3lgN compares in the worst case
 *
 * Idea: Find the maximum in this array first: take ~lgN time in the worst case
 *       Binary search in each side: take ~lgN time in each side
 */
public class BitonicSearch {
    public BitonicSearch(){} // Do nothing in the constructor

    public static int findMaximum(int[] a){
        //TODO: find the index of the maximum of this bitonic array
        return findMaximumHelper(a.length/2, a);
    }

    public static int findMaximumHelper(int i, int[] a){
        int n = a.length;
        if (a[i] > a[i+1] && a[i] > a[i-1]){ // find the maximum one
            return i;
        }
        if (a[i] < a[i+1]){ // at the left
            return findMaximumHelper(i+n/2, a);
        }
        if (a[i] > a[i+1]){ // at the right
            return findMaximumHelper(i/2, a);
        }
        return -1;
    }

    public static boolean ifExists(int x, int[] a){
        int maxIndex = findMaximum(a);
        return ifExistsHelper(x,maxIndex,a);
    }

    public static boolean ifExistsHelper(int x, int maxIindex, int[] a){
        //TODO: Find x in the left half of a
        LinkedList alreadyChecked = new LinkedList();
        if (a[maxIindex] != x) {
            if (binarySearch(x, maxIindex / 2, maxIindex, a, alreadyChecked) == -1 && binarySearch(x, (maxIindex + a.length) / 2, maxIindex, a, alreadyChecked) == -1) {
                return false;
            }
        }
        return true;
    }

    public static int binarySearch(int x, int checkIndex, int maxIndex, int[] a, LinkedList checked){
        if(checked.contains(checkIndex)){return -1;}
        else{
            checked.add(checkIndex);
            if (checkIndex <= maxIndex){ // search in the left side of a
                if (x == a[checkIndex]){return checkIndex;}
                if (x < a[checkIndex]) {return binarySearch(x, checkIndex/2, maxIndex,a, checked);}
                if (x > a[checkIndex]) {return binarySearch(x,(checkIndex+maxIndex)/2, maxIndex,a, checked);}
            }
            else{ // search in the right side of a
                if (x == a[checkIndex]){return checkIndex;}
                if (x < a[checkIndex]) {return binarySearch(x, (a.length+checkIndex)/2, maxIndex,a, checked);}
                if (x > a[checkIndex]) {return binarySearch(x,(checkIndex+maxIndex)/2, maxIndex,a, checked);}
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,4,7,9,5,3,0};
        System.out.println(findMaximum(a)); // 3

        System.out.println(ifExists(3, a)); // true
        ifExists(9, a);
        System.out.println(ifExists(9, a)); // true
        System.out.println(ifExists(10, a)); // false
        System.out.println(ifExists(1, a)); // true
        System.out.println(ifExists(0, a)); // true
    }
}
