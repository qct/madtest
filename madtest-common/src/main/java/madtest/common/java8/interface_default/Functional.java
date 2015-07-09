package madtest.common.java8.interface_default;

/**
 * Created by qct on 2015/5/21.
 */
@FunctionalInterface
public interface Functional {
    void method();
    default void defaultMethod() {

    }

    static void staticMethod() {

    }
}
