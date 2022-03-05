import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MatrixTest {
//    private Matrix test = new Matrix();
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testdot(){
        double[] x = new double[]{1,2,3,4};
        double[] y = new double[]{3,2,1,0};
        double a = 10;
        assertEquals(a,Matrix.dot(x,y),0.001);
//        Assert.assertThrows
        exception.expect(IllegalArgumentException.class);
        Matrix.dot(x,new double[]{1,2});
    }

    @Test
    public void testTranspose(){
        double[][] m = new double[][]{{1,1,1,1},{2,2,2,2},{3,3,3,3}};
        assertEquals(4,Matrix.transpose(m).length,0.001);
        assertEquals(3,Matrix.transpose(m)[0].length);
    }

    @Test
    public void testMult(){
        double[][] m = new double[][]{{1,1,1,1},{2,2,2,2},{3,3,3,3}};
        double[][] m_2  = Matrix.transpose(m);
        double[][] result = new double[][]{{4,8,12},{8,16,24},{12,24,36}};
        // matrix-matrix-product
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m_2[0].length; j++) {
                assertEquals(result[i][j],Matrix.mult(m,m_2)[i][j],0.001);
            }
        }

        double[] y = new double[]{0,1,0,1};
        double[] result_2 = new double[]{2,4,6};
        for (int i = 0; i < m.length; i++) {
            assertEquals(result_2[i],Matrix.mult(m,y)[i],0.001); // matrix-vector product
            assertEquals(result_2[i],Matrix.mult(y,m_2)[i],0.001); // vector-matrix product
        }
    }
}
