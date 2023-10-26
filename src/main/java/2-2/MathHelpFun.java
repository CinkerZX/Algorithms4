public class MathHelpFun {
    static long max(long mat[][], int i, int j){
        // Handling the base cases
        if (j == mat[0].length && i < mat.length) {
            // Changing the row and column index
            j = 0;
            ++i;
        }

        // Generic case
        if (i == mat.length) {
            // Simply return
            return 0;
        }

        // By far if we reach here then
        // return the max element
        return Math.max(mat[i][j], max(mat, i, j + 1));
    }

    static long max(long mat[][]){
        return max(mat, 0, 0);
    }

    static int max(int arr[]){
        int result = 0;
        if (arr.length == 0){
            System.out.println("Empty array!");
        }
        else{
            result = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > result){
                    result = arr[i];
                }
            }
        }
        return result;
    }


}
