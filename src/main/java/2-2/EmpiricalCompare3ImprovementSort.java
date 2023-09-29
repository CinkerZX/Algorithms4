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

    // repeart timeConofMergeInsert for several times

    public static void main(String[] args) {

    }
}
