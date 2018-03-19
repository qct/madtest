package madtest.common.jama;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

/**
 * default description.
 *
 * @author qct
 * @date 2018/1/25
 * @since 1.0
 */
public class MatrixTest {

    public static void main(String[] args) {
        Matrix dense = DenseMatrix.Factory.zeros(4, 4);
        System.out.println(dense);
    }
}
