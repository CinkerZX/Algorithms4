import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;

import java.math.BigInteger;

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
                for (int j = i; j >= h && less(a[j],a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }

    public static BigInteger sortNumCompare(Comparable[] a){
        BigInteger numComprea = new BigInteger("0");
        //TODO: Sort a[] in an ascending order
        int N = a.length;
        int h = 1;
        while(h<N/3) h = 3*h+1; // h = 1, 4, 13, 40, 121, ....  depends on the length of a
        while(h >= 1){//TODO: Start from the largest h
            // h-sort the array
            for (int i = h; i < N; i++) {
                //TODO: insert a[i] among a[i-h], a[i-2*h], a[i-3*h]...
                for (int j = i; j >= h; j -= h) {
                    if(less(a[j],a[j-h])){
                        exch(a, j, j-h);
                    }
                    numComprea = numComprea.add(BigInteger.ONE); // count the num of compare
                }
            }
            h = h/3;
        }
        return numComprea;
    }

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) <= 0;
    }

    public static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
