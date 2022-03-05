import com.sun.deploy.util.StringUtils;
import com.sun.xml.internal.bind.v2.TODO;
import edu.princeton.cs.algs4.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.lang.Math.random;

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
        //TODO: returns an array of length M whose ith entry is the number of times the integer i appeared in the argument array
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

    //1.1.31
    public static void randomNet() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the number of nodes N: ");
        int N = input.nextInt();
        System.out.println("Please input the probability of connection p which is between 0 and 1): ");
        double p = input.nextDouble();
        input.close();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Circle(N,p));
        f.pack();
        f.setVisible(true);
    }

    public static class Circle extends JPanel{
        //TODO: draw the net
        private final int SIZE = 256;
        private int a = SIZE / 2;
        private int b = a;
        private int r = 4 * SIZE / 5;
        private int n;
        private double p;

        public Circle(int n, double p){
            super(true);
            if (n<50){
                this.setPreferredSize(new Dimension(SIZE, SIZE));
            }
            else{this.setPreferredSize(new Dimension(n*5, n*5));}
            this.n = n;
            this.p = p;
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);
            a = getWidth()/2;
            b = getHeight()/2;
            int m = Math.min(a,b);
            r=4*m/5;
//            g2d.drawOval(a - r, b - r, 2 * r, 2 * r);
            int r2 = 3;
            int[][] nodeXY = new int[n][2];
            for (int i = 0; i < n; i++) {
                double t=2*Math.PI *i/n;
                int x = (int) Math.round(a + r*Math.cos(t));
                int y = (int) Math.round(b + r*Math.sin(t));
                g2d.fillOval(x-r2,y-r2,2*r2,2*r2);
                nodeXY[i][0]=x;
                nodeXY[i][1]=y;
            }
            g2d.setColor(Color.gray);
            for (int i = 0; i < n ; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (random() < p) {
                        g2d.drawLine(nodeXY[i][0], nodeXY[i][1], nodeXY[j][0], nodeXY[j][1]);
                    }
                }
            }
        }

    }

    //1.1.31
    public static void countHistogram(double[] stream) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the number of intervals N: ");
        int N = input.nextInt();
        System.out.println("Please initiate double l: ");
        double l = input.nextDouble();
        System.out.println("Please initiate double r: ");
        double r = input.nextDouble();
        input.close();
        Arrays.sort(stream);
        int[] countResult = new int[N];
        Arrays.fill(countResult,0);
        double delta = (r-l)/N;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < stream.length; j++) {
                if (stream[j]>l+i*delta){
                    if (stream[j]<l+(i+1)*delta){countResult[i]++;}
                    else{i++;j--;}
                }
            }
        }
        JFrame frame = new JFrame();
        frame.add(new histogram(countResult));
        frame.pack();
        frame.setVisible(true);
    }

    public static class histogram extends JPanel{
        //TODO: draw the histogram
        private int[] countResult;
        private int max;
        private final int SIZE = 500;


        public histogram(int[] countR) {
            this.countResult = countR;
            max = Arrays.stream(countR).max().getAsInt();
            this.setPreferredSize(new Dimension(SIZE, SIZE));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int barWidth = SIZE / countResult.length;
            for (int i = 0; i < countResult.length; i++) {
                int barHeight = (int)((countResult[i] / (double)max) * (getHeight()-50));
                Rectangle rect = new Rectangle(i * barWidth, getHeight() - barHeight, barWidth, barHeight);
                g2d.setColor(Color.gray);
                g2d.fill(rect);
                g2d.setColor(Color.BLACK);
                g2d.draw(rect);
            }
            g2d.dispose();
        }
    }

    //1.1.35
    public static double[] diceSimulation(int N){
        int[] arr = new int[N];
//        double n = (double)Math.ceil(N);
        int Min = 1;
        int Max = 6;
        for (int i = 0; i < N; i++) {
            Random rand = new Random();
//            arr[i] = rand.nextInt((Max - Min) + 1) + Min + rand.nextInt((Max - Min) + 1) + Min - 2*Min;
            arr[i] = rand.nextInt((Max - Min) + 1) + rand.nextInt((Max - Min) + 1); //2-12 -> 0-10
        }
        double[] fre = IntStream.of(countFre(arr,11)).mapToDouble(d -> (double) Math.ceil(d)).toArray();
        double[] prodis = DoubleStream.of(fre).map(d->d/(double) Math.ceil(N)).toArray();
        return prodis;
    }

    public static double[] disStandard(){
        int SIDES=6;
        double[] dist = new double[2*SIDES+1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1 ; j <=SIDES ; j++) {
                dist[i+j] +=1.0;
            }
        }
        double[] dist_2 = new double[11];
        for (int i = 2; i <= 2*SIDES ; i++) {
            dist_2[i-2] = dist[i] / 36.0;
        }
        return dist_2;
    }

    public static double maxDiffer(double[] a, double[] b){
        //TODO: max(a[i] - b[i])
        double maxDif = 0;
        double dif = 0;
        for (int i = 0; i < a.length; i++) {
            dif = Math.abs(a[i]-b[i]);
            if (dif > maxDif){
                maxDif = dif;
            }
        }
        return maxDif;
    }

    //1.1.36
    public static void shuffle(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // Exchange a[i] with random element in a[i..N-1]
            int r = i+ StdRandom.uniform(N-i);
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    // Input: N(times of do shuffle), M(the length of array)
    public static void ShuffleTest(int N, int M){
        // TODO: count the times that each item shuffled to the other positions
        double[] a = new double[M];
        int[][] result = new int[M][M];
        while(N>0){
            for (int i = 0; i < M; i++) {
                a[i] = i;
            }
            shuffle(a);
            for (int i = 0; i < M; i++) {
                result[(int) a[i]][i]++;
            }
            N--;
        }
        for (int i = 0; i < M; i++) { //*****************
            for (int j = 0; j < M; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

    //1.1.37
    public static void badShuffle(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // Exchange a[i] with random element in a[0..N-1]
            int r = StdRandom.uniform(N);
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    public static void badShuffleTest(int N, int M){
        // TODO: count the times that each item shuffled to the other positions
        double[] a = new double[M];
        int[][] result = new int[M][M];
        while(N>0){
            for (int i = 0; i < M; i++) {
                a[i] = i;
            }
            badShuffle(a);
            for (int i = 0; i < M; i++) {
                result[(int) a[i]][i]++;
            }
            N--;
        }
        for (int i = 0; i < M; i++) { //*****************
            for (int j = 0; j < M; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

    //1.1.38
//    public static void BruteForceSearch() {
//        int[] whitelist = In.readInts()
//    }
    public static int bruteRank(int key, int[] a){
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
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

        System.out.println("*** 1.1.31 ***");
        randomNet();

        System.out.println("*** 1.1.32 ***");
        int N = 1000;
        double[] stream = new double[N];
        for (int i = 0; i <N; i++) {
            stream[i] = random();
        }
        countHistogram(stream);


        System.out.println("*** 1.1.34 ***");
        System.out.println("Answer: all needs to save all the values from standard input.");

        System.out.println("*** 1.1.35 ***");
        //TODO: Find the smallest N that makes the error of probability (compared with standard ones) within 0.001
        System.out.println(maxDiffer(disStandard(), diceSimulation(200000))); // 8.766666666666645E-4

        System.out.println("*** 1.1.36 ***");
        ShuffleTest(20000,4); // very close to N/M
//        4946 5101 5032 4921
//        4982 4950 4997 5071
//        5084 4970 4929 5017
//        4988 4979 5042 4991

        System.out.println("*** 1.1.37 ***");
        badShuffleTest(20000,4); // very close to N/M
//        4992 4984 4955 5069
//        5849 4482 4757 4912
//        4920 5588 4526 4966
//        4239 4946 5762 5053
        */
        System.out.println("*** 1.1.38 ***");

    }
}
