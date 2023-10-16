import javax.swing.*;

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
    static int repeat = 10;

    public static long timeConofMergeInsert(Comparable[] a, int cutoff){
        // TODO: calculate the consumed time for soring array "a" with threshold = cutoff
        long time = 0;
        // Start measuring execution time
        long startTime = System.nanoTime();
        ImproveMergeSort.ImpSortBottomUpInsert(a, cutoff);
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

    // help function
    public static long mean(long[] m) {
        long sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += (double)m[i]/ (double)m.length;
        }
        return sum;
    }

    public static void main(String[] args) {
//        LineDrawer.drawLines(x,y);
    }
}
