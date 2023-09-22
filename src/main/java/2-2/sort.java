import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class sort {
//    private static Comparable[] aux;
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
        for (int k = lo; k <=hi ; k++) {
            myaux[k-lo] = a[k];
        }
        // merge back to a[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            if (i>mid) // a[0, 1] is sorted, and a[0] < a[1]
                // i = k = lo = 1, aux[a[1], a[0], a[2], a[3]]
                // a[0] = aux[1]
                a[k] = myaux[j++ - lo];
            else if (j>hi) // a[2,3] is sorted, and a[2] < a[3]
                // j = 3, hi = 2, k = 2, i = 2, aux[a[0], a[1], a[3], a[2]];
                // a[2] = aux[3]
                a[k] = myaux[i++ - lo];
            else if (less(myaux[j-lo], myaux[i-lo])) a[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
            else
                a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
        }
    }

    // Idea: compare a[lo : mid] with a[End : mid+1], to reduce compare times
    public static void fasterMerge(Comparable[] a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        LinkedList<Comparable> aux = new LinkedList<>();
        for (int k = lo; k <= mid ; k++) {
            aux.add(a[k]);
        }
        LinkedList<Comparable> myaux = new LinkedList<>();
        for (int k = 0; k < (hi-lo+1)/2 ; k++) {
            myaux.add(a[hi-k]);
        }
        LinkedList<Comparable> orderResult = new LinkedList<>();
        // merge myaux[End, mid+1] back to a[lo, hi]
        int j = 0;
        while (orderResult.size() <= hi) {
            if (myaux.size()==0){// add the rest of aux to orderResult
                while(aux.size()!=0){orderResult.addLast(aux.removeFirst());}
                break;
            }
            if (aux.size()==0){// add the rest of myaux to orderResult
                while(myaux.size()!=0){
                    orderResult.addLast(myaux.removeLast());
                }
                break;
            }
            if (less(myaux.get(j),aux.getFirst())){// aux[i]>myaux[j]: move myaux[end:j] to the head of a
                int sizeMyaux = myaux.size();
                for (int i = 0; i < sizeMyaux-j; i++) { orderResult.addLast(myaux.removeLast());}
                orderResult.addLast(aux.removeFirst());
                j = 0;
            }
            else{
                if (j==myaux.size()-1){ // aux[0] is the smallest one
                    orderResult.addLast(aux.removeFirst());
                }else{j++;}
            }
        }
        for (int i = lo; i <= hi; i++) {
            a[i] = orderResult.remove(); //remove orderResult[0], [1], ....[end]
        }
        printStringArray(a);
    }

    // Idea: when the length of the array is small, use Insertion sort alg, which performs better
    public static void mergeInsert(Comparable[] a, int lo, int mid, int hi){
        int threshold = 15;
        if (a.length < threshold){
            Insertion.sort(a);
        }
        else{
            //Todo: merge array a[lo, mid] and a[mid+1, hi]
            Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
            for (int k = lo; k <=hi ; k++) {
                myaux[k-lo] = a[k];
            }
            // merge back to a[lo, hi]
            int i = lo, j = mid+1;
            for (int k = lo; k <= hi ; k++) {
                if (i>mid)
                    a[k] = myaux[j++ - lo];
                else if (j>hi)
                    a[k] = myaux[i++ - lo];
                else if (less(myaux[j-lo], myaux[i-lo])) a[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
                else
                    a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
            }
        }
    }

    // Idea: check if a[mid] <= a[mid + 1], if already in order, don't need to compare
    public static void mergeCheckOrder(Comparable[] a, int lo, int mid, int hi){
        if (less(a[mid+1], a[mid])){
            //Todo: merge array a[lo, mid] and a[mid+1, hi]
            Comparable[] myaux = new Comparable[hi-lo+1]; //copy a[lo, hi] to aux[]
            for (int k = lo; k <=hi ; k++) {
                myaux[k-lo] = a[k];
            }
            // merge back to a[lo, hi]
            int i = lo, j = mid+1;
            for (int k = lo; k <= hi ; k++) {
                if (i>mid)
                    a[k] = myaux[j++ - lo];
                else if (j>hi)
                    a[k] = myaux[i++ - lo];
                else if (less(myaux[j-lo], myaux[i-lo])) a[k] = myaux[j++ - lo]; // if the smallest on the right size is smaller
                else
                    a[k] = myaux[i++ - lo]; // the smallest on the left is smaller. Do aux[i] first, and then i++
            }
        }
    }

    //Idea: breaking big problems into small problems
    // iteration: call sort[a, lo, hi], until the smallest unit a[0, 1], a[2, 3], ...
    public static void sortTopDown(Comparable[] a){
        //TODO: call sortTopDown
        sortTopDown(a, 0, a.length-1);
    }
    public static void sortTopDown(Comparable[] a, int lo, int hi){
        //TODO: sort from the Top to Bottom
        if (hi <=lo) return;
        int mid = lo + (hi-lo)/2;
        sortTopDown(a, lo, mid);
        sortTopDown(a, mid+1, hi);
        //TODO: merge from the bottom to up
        merge(a, lo, mid, hi);
//        printStringArray(a); // for test
    }



    // Idea: building small solutions into larger ones
    public static void sortBottomUp(Comparable[] a){
        //TODO: use two for loop to merge
        int N = a.length;
        for (int i = 1; i < N; i = i+i) { // i-- subarray size
            for (int lo = 0; lo < N-i ; lo += i+i) {
                merge(a, lo, lo+i-1, Math.min(lo+i-1+i, N-1)); // hi -- the mid+i, if it out of index, hi = N-1
                printStringArray(a); // for test
            }
        }
    }

    //Help fun
    public static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b)<0) // a<b
            return true;
        else
            return false;
    }

    public static void printStringArray(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+"     ");
        }
        System.out.println("");
    }

    public static void printIntArray(Integer[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+"     ");
        }
        System.out.println("");
    }


    public static void printStringLinkedList(LinkedList<Comparable> a){
        LinkedList<Comparable> aCopy = (LinkedList<Comparable>) a.clone();
        while (!aCopy.isEmpty()){System.out.print(aCopy.pollFirst()+"     ");}
        System.out.println("");
    }

    public static String[] generateStringArray(int len){
        //TODO: generate an int array with the length of len
        String[] a = new String[len];
        Random ran = new Random();
        for (int i = 0; i < len; i++) {
            a[i]= String. valueOf((char)(ran.nextInt(26) + 'a'));
        }
        return a;
    }

    public static LinkedList<Comparable> generateStringLinkedList(int len){
        //TODO: generate an int array with the length of len
        LinkedList<Comparable> a = new LinkedList<Comparable>();
        Random ran = new Random();
        for (int i = 0; i < len; i++) {
            a.add(String. valueOf((char)(ran.nextInt(26) + 'a')));
        }
        return a;
    }

    public static ArrayDeque generateStingQueue(int len){
        //TODO: generate an int array with the length of len
        ArrayDeque<Comparable> a = new ArrayDeque(len);
        Random ran = new Random();
        while(a.size()<len){
            a.add(String.valueOf((char)(ran.nextInt(26) + 'a')));
        }
        return a;
    }

    public static void main(String[] args) {
        // Test of merge
        String[] a = new String[]{"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
        sort.merge(a, 0, 4, 9);

        // Test of sortTopdown
        a = new String[]{"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sortTopDown(a);

        // Test of sortBottomUp
        a = generateStringArray(10);
        sortBottomUp(a);

        // Test of fasterMerge
        a = new String[]{"M", "R", "T", "W", "E", "J", "L", "P"};
        fasterMerge(a, 0, 3, 7);

        a = new String[]{"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
        fasterMerge(a, 0, 4, 9);

    }

}
