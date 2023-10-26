import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Aim0: Empirically compare the performance of the three improvements on sorting
 * Find the optimal threshold of 'small' of ImproveMergeSort.ImpSortBottomUpInsert(Comparable[] a, int cutoff)
 *
 * Idea:
 * Comparable[], cutoff -> emp_function -> time consumption
 * cutoff[], time consumption[] -> vis_function -> line plot
 *
 * Aim1: Empirically compare the performance of the three improvements on sorting
 * 1) using insertion for small sub array
 * 2) test weather the array is already in order
 * 3) eliminate the copy to the aux
 *
 * Idea:
 *
 * Aim2: Empirically compare the performance of sort.merge() with faster merge
 * N, Amend_i -> emp_function_2 -> time consumption
 * N[], time consumption[] ->  vis_function -> line plot
 *
 */
public class EmpiricalCompare3ImprovementSort {
    static int repeat = 5;
    static int[] x = new int[]{5000, 10000, 50000, 100000, 200000};
    static int[] bestCutoff = new int[]{13, 13, 12, 11, 7};

    //****************** Amend1 Combine Merge with Insertion *******************
    public static long timeConofMergeInsert(Comparable[] a, int cutoff){
        // TODO: calculate the consumed time for soring array "a" with threshold = cutoff
        long time = 0;
        // Start measuring execution time
        long startTime = System.nanoTime();
        ImproveMergeSort.ImpSortBottomUpInsert(a, cutoff);
        // Stop measuring execution time
        long endTime = System.nanoTime();
        time = (endTime - startTime)/10000; // the unit of time is ms-3
        return time;
    }

    //****************** Amend2 Test whether is already in order *******************
    public static long timeConofMergeOrdering(Comparable[] a){
        // TODO: calculate the consumed time for soring array "a" check if the array is already ordered
        long time = 0;
        // Start measuring execution time
        long startTime = System.nanoTime();
        ImproveMergeSort.removeRedundantOrderingMerge(a);
        // Stop measuring execution time
        long endTime = System.nanoTime();
        time = (endTime - startTime)/10000; // the unit of time is ms-3
        return time;
    }

    //****************** Amend3 eliminate the copy to the aux *******************
    public static long timeConofMergeElimiAux(Comparable[] a){
        // TODO: calculate the consumed time for soring array "a" check if the array is already ordered
        long time = 0;
        // Start measuring execution time
        long startTime = System.nanoTime();
        ImproveMergeSort.ImpSortBottomUpNoCopy(a);
        // Stop measuring execution time
        long endTime = System.nanoTime();
        time = (endTime - startTime)/10000000; // the unit of time is ms
        return time;
    }

    // repeat timeConofMergeInsert for repeat times
    public static long[] optimalCutofMergeInsert(int len, int cutoff){
        //TODO: run the sorting for arrays that of length len for multiple (repeat) times
        long[] times = new long[repeat];
        for (int i = 0; i < repeat; i++) {
            times[i] = timeConofMergeInsert(sort.generateStringArray(len), cutoff);
        }
        return times;
    }

    // try cutoff = [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
    public static long[] optimalCutofMergeInsert(int len){
        // TODO: test the times consumption of different cutoffs
        int[] cutoffToTest = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        long[] averageTimeofCutoff = new long[cutoffToTest.length];
        for (int i = 0; i < cutoffToTest.length; i++) {
            averageTimeofCutoff[i] = mean(optimalCutofMergeInsert(len,cutoffToTest[i]));
        }
        return averageTimeofCutoff;
    }

    // Compare the consumed time for these three improved algorithms
    public static long[][] averageTimeMain(){
        long[][] result = new long[3][x.length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < 3; j++) {
                result[j][i] = mean(repeatSortwithAlgs(i, j));
            }
        }
        return result;
    }

    public static long[] repeatSortwithAlgs(int lengthArray, int algorithm){
        //TODO: run the sorting for arrays that of length len for multiple (repeat) times
        long[] times = new long[repeat];

        switch (algorithm){
            case 1:
                for (int i = 0; i < repeat; i++) {
                    times[i] = timeConofMergeInsert(sort.generateStringArray(x[lengthArray]), bestCutoff[lengthArray]);
                }
                break;
            case 2:
                for (int i = 0; i < repeat; i++) {
                    times[i] = timeConofMergeOrdering(sort.generateStringArray(x[lengthArray]));
                }
                break;
            default:
                for (int i = 0; i < repeat; i++) {
                    times[i] = timeConofMergeElimiAux(sort.generateStringArray(x[lengthArray]));
                }
        }
        return times;
    }

    // help function
    public static long mean(long[] m) {
        long sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += (double)m[i]/ (double)m.length;
        }
        return sum;
    }

    public static void main(String[] args) {
//        // TODO: Find the optimal threshold of 'small' of ImproveMergeSort.ImpSortBottomUpInsert(Comparable[] a, int cutoff)
//        long[] time;
//        time = optimalCutofMergeInsert(10000); // 13
//        sort.printLongArray(time);
//        time = optimalCutofMergeInsert(100000); // 11
//        sort.printLongArray(time);
//        time = optimalCutofMergeInsert(500000); // 7
//        sort.printLongArray(time);

        // TODO: Empirically compare the performance of the three improvements on sorting
        long[][] times = averageTimeMain();
        String[] mystr = new String[]{"Insertion", "No repeatOrder", "No aux"};
        for (int i = 0; i < times.length; i++) {
            System.out.println(mystr[i]);
            for (int j = 0; j < times[i].length; j++) {
                System.out.println(times[i][j] + "    ");
            }
        }
//        long[][] times = new long[3][5];
//        times[0] = new long[]{0, 3, 46, 163, 774};
//        times[1] = new long[]{213, 1182, 1039, 2583, 5705};
//        times[1] = new long[]{266, 260, 1408, 2026, 5042};
        List<String> legends = new ArrayList<>();
        legends.add(mystr[0]);
        legends.add(mystr[1]);
        legends.add(mystr[2]);
        LineDrawer.drawLines(x, times, legends, 4);
    }
}
