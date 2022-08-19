import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;

/**
 * The {@CODE ShellSort} is an implementation of ShellSort algorithm
 *
 * function sort is a static method, which doesn't need any instantiation
 */
public class ShellSort{
    public static void sort(Comparable[] a){
        //TODO: Sort a[] in an ascending order
        int N = a.length;
        int h = 1;
        while(h<N/3) h = 3*h+1; // h = 1, 4, 13, 40, 121, ....  depends on the length of a
        while(h >= 1){//TODO: Start from the largest h
            // h-sort the array
            for (int i = h; i < N; i++) {
                //TODO: insert a[i] among a[i-h], a[i-2*h], a[i-3*h]...
                for (int j = i; j >= h && less(a[j],a[j-h]); j -= h) { // i = 13
                    each(a, j, j-h);
                }
                h = h/3;
            }
        }
    }

    public static boolean less(Comparable a, Comparable b){
        if(a.compareTo(b) < 0){
            return true;
        }
        else
            return false;
    }

    public static void each(Comparable[] a, int i, int j){

    }
}
