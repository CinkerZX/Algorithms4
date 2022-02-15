import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class exercise1_1 {
    // 1.1.3
    public static boolean intArrayContent(String[] input) {
        for(int i = 1; i < input.length; i++) {
            if(Integer.valueOf(input[0]) != Integer.valueOf(input[i]))
                return false;
        }
        return true;
    }
    // 1.1.5
    public static boolean defDomainBeta(double x, double y){
        if(x<1 && x>0 && y<1 && y>0){
            return true;
        }
        return false;
    }

    // 1.1.6
    public static void Fibonacci(){
        int f = 0;
        int g = 1;
        for (int i = 0; i < 15; i++) {
            System.out.print(f+" ");
            f=f+g;
            g=f-g;
        }
    }

    //1.1.7
    public static void Formating(){
        double t=9.0;
        while (Math.abs(t-9.0/t)> .001)
            t=(9.0/t+t)/2.0;
        StdOut.printf("%.5f\n",t); // five positions after the decimal, new line

        int sum =0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        StdOut.println(sum);

        sum =0;
        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        StdOut.println(sum);
    }

    //1.1.9
    public static String NumtoBinary(int N){
        String s = "";
        for (int n = N; n > 0 ; n /=2) {
            s = n%2 + s;
        }
        return s;
    }

    //1.1.11
    public static ArrayList<String[]> booArrtoStrArr(ArrayList<boolean[]> booArr){
        ArrayList<String[]> strArr= new ArrayList<String[]>();
        for (boolean[] bA : booArr) {
            strArr.add(booArrtoStrArrHelper(bA));
        }
        return strArr;
    }
    public static String[] booArrtoStrArrHelper(boolean[] b){
        String[] s = new String[2];
        System.out.println("b[0]"+b[0]);
        if (b[0]){
            s[0] = "*";
        }
        else{s[0]= " ";}
        System.out.println("b[1]"+b[1]);
        if (b[1]){
            s[1] = "*";
        }
        else{s[1]=" ";}
        return s;
    }

    //1.1.12
    public static void forLoopAssign(){
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i]=9-i;
        }
        for (int i = 0; i < 10; i++) {
            a[i]=a[a[i]];
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(a[i]+" ");
        }
    }

    //1.1.13
    public static Double[][] transMatrix(Double[][] m0){
        int nrow = m0[0].length;
        int ncol = m0.length;
        Double[][] m1 = new Double[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                m1[i][j] = m0[j][i];
                System.out.print(m1[i][j]+" ");
            }
            System.out.print("\n");
        }
        return m1;
    }

    //1.1.14
    public static int intLn(int n){
        int result=0;
        for (int i = 1; i <= n;) {
            i = i*2;
            result ++;
        }
        return result-1;
    }

    //1.1.15
    public static int[] countFre(int[] arr, int M){
        int[] result = new int[M];
        Arrays.fill(result, 0);
        ArrayList<Integer> arr_2 = new ArrayList<Integer>();
        for (int i : arr) {
            arr_2.add(i);
        }
        for (int i = 0; i < arr_2.size();) {
            i=0;
            for (int j = 0; j < M; j++) {
                if(arr_2.get(i)==j){
                    result[j] ++;
                    arr_2.remove(i);
                    break;
                }
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        // 1.1.1
        System.out.println(7);
        System.out.println(200.0000002D);
        System.out.println(true);

        // 1.1.2
        Double a = (1+2.236)/2; // double a : primitive type, is not an object
        System.out.println(a);
        System.out.println(a.getClass().getName());
        Double b = 1+2+3+4.0;
        System.out.println(b);
        System.out.println(b.getClass().getName());
        Boolean c = (4.1>=4);
        System.out.println(c);
        System.out.println(c.getClass().getName());
        String d = 1+2+"3";
        System.out.println(d);
        System.out.println(d.getClass().getName());

        //1.1.3
        System.out.println(intArrayContent(args));

        //1.1.5
        System.out.println(defDomainBeta(0.5,0.1));

        //1.1.6
        System.out.println("*** 1.1.6 ***");
        Fibonacci();

        //1.1.7
        System.out.println("*** 1.1.7 ***");
        Formating();

        //1.1.8
        System.out.println("*** 1.1.8 ***");
        System.out.println((char) ('a'+4));

        //1.1.9
        System.out.println(NumtoBinary(10));
        
        //1.1.11
        System.out.println("*** 1.1.11 ***");
        ArrayList<boolean[]> booArray = new ArrayList<boolean[]>();
        booArray.add(0,new boolean[]{false, true});
        booArray.add(1,new boolean[]{true, false});
        ArrayList<String[]> strArray = booArrtoStrArr(booArray);
        for (String[] sa : strArray) {
            System.out.println(sa[0]+"  "+sa[1]);
        }

        //1.1.12
        System.out.println("*** 1.1.12 ***");
        forLoopAssign();
        System.out.printf("\n");

        //1.1.13
        System.out.println("*** 1.1.13 ***");
        Double[][] m1 = new Double[][]{{1.0, 1.0, 1.0, 1.0}, {2.0, 2.0, 2.0, 2.0}, {3.0, 3.0, 3.0, 3.0}};
        transMatrix(m1);

        //1.1.14
        System.out.println("*** 1.1.14 ***");
        System.out.println(intLn(10));

        //1.1.15
        System.out.println("*** 1.1.15 ***");
        int[] array = new int[]{5,5,4,4,4,4,3,3,3,2,2,1,10};
        for (int i : countFre(array,6)) {
            System.out.print(i+" ");
        }
    }
}
