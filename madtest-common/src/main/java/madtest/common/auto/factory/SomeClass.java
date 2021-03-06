package madtest.common.auto.factory;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

/**
 * default description.
 *
 * @author qct
 * @date 2017/12/26
 * @since 1.0
 */
//@AutoFactory
final class SomeClass {

    private final String providedDepA;
    private final String depB;

    SomeClass(String providedDepA, String depB) {
        this.providedDepA = providedDepA;
        this.depB = depB;
    }
}
