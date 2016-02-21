package madtest.common.pattern.singleton;

/**
 * 静态内部类 static nested class Created by qct on 2015/7/9.
 */
public class NestedClassSingleton {
    private NestedClassSingleton() {
    }

    public static final NestedClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final NestedClassSingleton INSTANCE = new NestedClassSingleton();
    }
}
