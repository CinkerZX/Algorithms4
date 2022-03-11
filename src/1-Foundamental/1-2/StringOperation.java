import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class StringOperation {
    // 1.2.6
    public static boolean ifCircularRotate(String s, String t){
        if((s.substring(s.indexOf(t.charAt(0)), s.length())+s.substring(0,s.indexOf(t.charAt(0)))).equals(t)){
            return true;
        }
        return false;
    }

    // 1.2.7
    public static String mystery(String s){
        int N = s.length();
        if (N <= 1 ) return s;
        String a = s.substring(0,N/2);
        String b = s.substring(N/2,N);
        return mystery(a)+mystery(b);
    }

    //1.2.9
    public static int rank(int key, int[] a, AtomicInteger c){
        int dep = 0;
        return rank(key, a, 0, a.length-1, dep, c);
    }

    public static int rank(int key, int[] a, int lo, int hi, int depth, AtomicInteger c){
        //TODO: binary search, recursive
        if (lo > hi ){return -1;}
        int mid = lo + (hi-lo)/2;
        System.out.println(Collections.nCopies(depth, " ") + "lo: " +lo+";    hi: "+hi);
        c.set(c.intValue()+1);
        if (key<a[mid]) return rank(key, a, lo, mid-1,depth++, c);
        else if(key>a[mid]) return rank(key, a, mid+1, hi, depth++, c);
        else return mid;
    }

    public static void main(String[] args) {
        System.out.println("*** 1.2.4 ***");
        String string1 = "hello";
        String string2 = string1; // -> object "hello"
        string1 = "world"; // -> object "world"
        StdOut.println(string1);
        StdOut.println(string2);

        System.out.println("*** 1.2.5 ***");
        String s = "Hello world";
        s.toUpperCase();
        s.substring(6,11);
        StdOut.println(s);
        StdOut.println(s.substring(6,11).toUpperCase()); //ending index is exclusive

        System.out.println("*** 1.2.7 ***");
        System.out.println(mystery(s));

        System.out.println("*** 1.2.9 ***");
        AtomicInteger Counter = new AtomicInteger(0);
        int[] binarySearch = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println("The location is at: "+rank(9,binarySearch, Counter));
        System.out.println("The total number of compare operation is "+Counter+".");
    }
}
