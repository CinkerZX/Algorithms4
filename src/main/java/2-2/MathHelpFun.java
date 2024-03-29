import java.util.concurrent.ThreadLocalRandom;

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

    static int sum(int[] arr){
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }

    public static int[] zerosArr(int len){
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = 0;
        }
        return result;
    }

    public static int[] onesArr(int len){
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = 1;
        }
        return result;
    }

    public static int generateRandomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        System.out.println(sum(a));
    }

}
