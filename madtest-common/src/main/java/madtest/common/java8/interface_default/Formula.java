package madtest.common.java8.interface_default;

/**
 * Created by qct on 2015/5/21.
 */
public interface Formula {

    /**
     *
     * @param a
     * @return
     */
    double calculate(int a);

    /**
     *
     * @param a
     * @return
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
