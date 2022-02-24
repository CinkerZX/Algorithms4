import com.sun.deploy.util.StringUtils;
import com.sun.xml.internal.bind.v2.TODO;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Scanner;

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

    //1.1.16
    public static String exR1(int n){
        if (n<=0) return "";
        return exR1(n-3)+n+exR1(n-2)+n;
    }

    //1.1.20
    public static int recurFactorial(int N){
        // TODO: 2022-02-16 N!
        if (N==1) return 1;
        else return N * recurFactorial(N-1);
    }

    //1.1.21
    public static void regulateOutput() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input as following format: NAME INT1 INT2");
        System.out.println("Finish by entering END");
        ArrayList<String[]> lines = new ArrayList<String[]>();
        int i = 0;
        while (input.hasNext()){
            String line = input.nextLine();
            String[] splited = line.split("\\s+");
            if (splited.length!=3){
                break;
            }
            lines.add(i,splited);
            i++;
        }
        StdOut.printf("%20s%10s%10s%10s", "Name", "Success", "Hit", "Rate");
        System.out.print("\n");
        for (String[] s : lines) {
            Double rate = ((double) Integer.parseInt(s[1]))/Integer.parseInt(s[2]);
            DecimalFormat df = new DecimalFormat("#.###");
            StdOut.printf("%20s%10s%10s%10s",s[0],s[1],s[2],df.format(rate));
            System.out.print("\n");
        }
    }

    //1.1.22
    public static int rank(int key, int[] a){
        int dep = 0;
        return rank(key, a, 0, a.length-1, dep);
    }

    public static int rank(int key, int[] a, int lo, int hi, int depth){
        //TODO: binary search, recursive
        if (lo > hi ){return -1;}
        int mid = lo + (hi-lo)/2;
        System.out.println(Collections.nCopies(depth, " ") + "lo: " +lo+";    hi: "+hi);
        if (key<a[mid]) return rank(key, a, lo, mid-1,depth++);
        else if(key>a[mid]) return rank(key, a, mid+1, hi, depth++);
        else return mid;
    }

    //1.1.24
    public static void Euclid() throws IOException {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String[] ints = line.split("\\s+");
        if (ints.length!=2){
            System.out.println("Please insert two integers.");
        }
        Euclid(Integer.parseInt(ints[0]),Integer.parseInt(ints[1]));
    }
    public static int Euclid(int p, int q){
        System.out.println("The value of 'p' is: "+p+". The value of 'q' is: "+q+".");
        if (q==0) {
            System.out.println("The largest common devisor is: "+p);
            return p;
        }
        int r=p % q;
        return Euclid(q,r);
    }

    //1.1.27
    public static double binomial(int N, int k, double p){
        //TODO: construct a (N+1)*(N+1) matrix b. b[N][k] = (1-p)b[N-1][k]+pb[N-1][K-1]
        double[][] b = new double[N+1][k+1];
        //initialize i=0; j=0;
        b[0][0]=1;
        for (int i = 1; i <= k; i++) {
            b[0][i] = 0;
        }
        for (int i = 1; i <= N; i++) { //nrow
            for (int j = 0; j <= k; j++) { //ncol
                if (j==0) {b[i][j] = (1-p)*b[i-1][j];}
                else
                    {b[i][j] = (1-p)*b[i-1][j]+p*b[i-1][j-1];}
            }
        }
        return b[N][k];
    }

    //1.1.28
    public static int[] removeDuplicate(int[] a){
        ArrayList arr = new ArrayList();
        for (int i = 0; i < a.length; i++) {
            arr.add(i,a[i]);
        }
        for (int i = 0; i < arr.size()-1; i++) {
            if (arr.get(i)==arr.get(i+1)){arr.remove(i+1); i--;}
        }
        int[] result = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = (int)arr.get(i);
        }
        return result;
    }

    //1.1.29
    public static int rank_2(int key, int[] a){
        //TODO: return the number of element that smaller than the key
        int n=0;
        while (key > a[n] && n < a.length){n++;}
        return n;
    }

    public static int count(int key, int[] a){
        //TODO: return the number of element that equals to the key
        int n=0;
        for (int i = 0; i < a.length; i++) {
            if (a[i]==key){n++;}
            if (a[i]>key){break;}
        }
        return n;
    }

    //1.1.30
    public static boolean[][] ifPrime(int N){
        boolean[][] result = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j]=relativePrime(i+1,j+1);
            }
        }
        return result;
    }

    public static boolean relativePrime(int p, int q){
        if (q==0) {
            if (p==1 || p==0){return false;}
            return true;
        }
        int r=p % q;
        return relativePrime(q,r);
    }

    public static void main(String[] args) throws IOException {
      /*// 1.1.1
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

        //1.1.16
        System.out.println("*** 1.1.16 ***");
        System.out.println(exR1(6));

        //1.1.17
        System.out.println("*** 1.1.17 ***");
        System.out.println("(1)Calculate a(2^0+x^1+ ... +2^n), where 2^(n+1)>b>2^n");
        System.out.println("(2)Calculate a^1+a^2+ ... +a^n, where 2^(n+1)>b>2^n");

        //1.1.18
        System.out.println("*** 1.1.18 ***");

        //1.1.20
        System.out.println("*** 1.1.20 ***");
        System.out.println(Math.log(recurFactorial(10)));

        //1.1.21
        System.out.println("*** 1.1.21 ***");
        regulateOutput();

        //1.1.22
        System.out.println("*** 1.1.22 ***");
        int[] binarySearch = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println("The location is at: "+rank(9,binarySearch));

        //1.1.23
        // Can hardly understand the requirement of the question, didn't define what is "whitelist" or "blacklist"

        //1.1.24
        System.out.println("*** 1.1.24 ***");
        System.out.println("Please insert two integers.");
        Euclid();

        //1.1.27
        System.out.println("*** 1.1.27 ***");
        System.out.println(binomial(4, 1, 0.1));

        //1.1.28
        System.out.println("*** 1.1.28 ***");
        int[] whitelist = new int[]{0,0,1,1,1,1,1,1,2,2,3,3,3,5,5,10};
        int[] whitellist_2 = removeDuplicate(whitelist);
        for (int i = 0; i < whitellist_2.length; i++) {
            System.out.print(whitellist_2[i]+" ");
        }
        System.out.print('\n');

        //1.1.29
        System.out.println("*** 1.1.29 ***");
        int k = 2;
        System.out.println("The number of element smaller than "+k+" is: "+rank_2(k,whitelist));
        System.out.println("The number of element equals to "+k+" is: "+count(k,whitelist));

        //1.1.30
        System.out.println("*** 1.1.30 ***");
        int N = 10;
        boolean[][] matri = ifPrime(N);
        for (int i = 0; i < N; i++) {
            if (i!=0){System.out.print("\n");StdOut.printf("%5s",i+1);}
            for (int j = 0; j < N; j++) {
                if (i==0 && j==0){
                    System.out.print("\n");
                    StdOut.printf("%5s",i);
                    for (int k = 0; k < N; k++) {
                        StdOut.printf("%8s",k+1);
                    }
                    System.out.print("\n");
                    StdOut.printf("%5s",i+1);
                }
                StdOut.printf("%8s",matri[i][j]);
            }
        }
        System.out.println("\n");

       */
    }
}
