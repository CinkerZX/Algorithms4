import java.util.LinkedList;

/**
 * The {@Code LocalMinimumMatrix}
 * Read N-by-N array a[] of N^2 distinct integers, and find a local minimum a[i-1][j] > a[i][j]
 *                                                                          a[i][j-1] > a[i][j] < a[i][j+1]
 *                                                                          a[i+1][j] > a[i][j]
 * The running time is proportional to N in the worst case.
 * Idea: examine the middle value of a[N/2][N/2], and its horizontal neighbors
 *       if it is local minimum, compare with its vertical neighbors
 *          if it is local minimum, stop
 *          else search in the half with the smaller vertical neighbor
 *       otherwise search in the half with the smaller horizontal neighbor
 *       Until researching search the same element.
 */

public class LocalMinimumMatrix {
    public LocalMinimumMatrix(){} // Do nothing in the constructor

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }

    public static int ifLocalMinimumHV(int row, int col, int[][] ma){
        //TODO: check if ma[row][col] is local minimal
        return LocalMinimumArray.ifLocalMinimum(row,getColumn(col,ma));
    }

    public static int[] getColumn(int col, int[][] ma){
        int n = ma[0].length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = ma[i][col];
        }
        return result;
    }

    public static int[] findLocalMinimum(int[][] a){
        int n = a[0].length;
        LinkedList checked = new LinkedList();
        for (int i = 0; i < n; i++) {
            if (containsDuplicates(a[i])){throw new IllegalArgumentException("array contains duplicate integers");}
        }
        int start = n/2; // start from the central line
        return findLocalMinimumHelper(0, a, checked);
    }

    public static int[] findLocalMinimumHelper(int row, int[][] matrix, LinkedList checked){ //checked save already checked col
        //TODO: (1) find the minimal of the matrix[row], if cannot, row = row+1, until row = N, return [-1,-1]
        // (2) If find the local minimal of that line, check if it is also vertical minimal (use function: ifLocalMinimumHV)
        // If yes: end
        // if no: continue find the local minimal findLocalMinimumHelper(newRow, int[][] matrix, LinkedList checked)
        // newRow is generated by ifLocalMinimumHV
        int newRow;
        if(!checked.contains(row) && checked.size()<=matrix[0].length){
            checked.add(row);
            int rowMinimum_col = LocalMinimumArray.findLocalMinimum(matrix[row]); // (1)
            if (rowMinimum_col == -1){ //(1) cannot find, search in newRow
                newRow = row+1;
                return findLocalMinimumHelper(newRow, matrix, checked);
            }
            else{//(2) find the rowMinimim_col
                newRow = ifLocalMinimumHV(row,rowMinimum_col,matrix);
                if (newRow == 0 ){return new int[]{row, rowMinimum_col};} // find the local minimal!
                if (newRow == -1){ // already search to the edge, and cannot find
                    newRow = row+1;
                    return findLocalMinimumHelper(newRow, matrix, checked); // start from the new row
                }
            }
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        int[][] mymatrix1 = new int[][]{
                {9,8,7},
                {5,3,4},
                {-2,20,-10}};

        int[] index = findLocalMinimum(mymatrix1); // [][]
        System.out.println("["+index[0]+"] ,["+index[1]+"]"); //[1,1]

        int[][] mymatrix2 = new int[][]{
                {5,3,4},
                {7,8,9},
                {-2,20,-10}};

        int[] index2 = findLocalMinimum(mymatrix2); // [][]
        System.out.println("["+index2[0]+"] ,["+index2[1]+"]"); //[-1,-1]
    }

}
