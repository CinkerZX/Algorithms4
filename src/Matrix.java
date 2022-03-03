public class Matrix {
    public static double dot(double[] x, double[] y) throws IllegalArgumentException{
        //TODO: dot product
        if (x.length != y.length){
            throw new IllegalArgumentException("The length of two arrays need to be the same for dot production operation.");
        }
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            result += x[i]*y[i];
        }
        return result;
    }

    public static double[][] mult(double[][] a, double[][] b) throws IllegalArgumentException{
        //TODO: matrix matrix product
        int nrow = a.length;
        int ncol = b[0].length;
        double[][] result = new double[nrow][ncol];
        double[][] b_trans = transpose(b);
        if (a[0].length != b.length){
            throw new IllegalArgumentException("The number of columns of the first matrix needs to be the same as the number of the rows of the second matrix for matrix product operation.");
        }
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                result[i][j] = dot(a[i],b_trans[j]); //a[i] is A[i][], b_trans[j] is B[][j]
            }
        }
        return result;
    }

    public static double[][] transpose(double[][] a){
        //TODO: transpose a matrix
        int nrow = a.length;
        int ncol = a[0].length;
        double[][] result = new double[ncol][nrow];
        for (int i = 0; i < ncol; i++) {
            for (int j = 0; j < nrow; j++) {
                result[i][j] = a[j][i];
            }
        }
        return result;
    }

    public static double[] mult(double[][] a, double[] x) throws IllegalArgumentException{
        //TODO: matrxi-vector product
        if (a[0].length != x.length){throw new IllegalArgumentException("The number of columns of matrix needs to be the same as the length of the vector for matrix-vector product operation.");}
        int nrow = a.length;
        double[] result = new double[nrow];
        for (int i = 0; i < a.length; i++) {
            result[i] = dot(a[i],x);
        }
        return result;
    }

    public static  double[] mult(double[] y, double[][] a) throws IllegalArgumentException{
        if (y.length != a.length){throw new IllegalArgumentException("The length of the vector need to be the same as the number of rows of matrix A for vector-matrix product operation.");}
        return mult(transpose(a),y);
    }
}
