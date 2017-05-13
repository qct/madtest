package madtest.common.java8.interface_default;

/**
 * Created by qct on 2015/5/21.
 */
@FunctionalInterface
public interface Functional {

    static void staticMethod() {

    }

    void method();

    default void defaultMethod() {

    }
}
