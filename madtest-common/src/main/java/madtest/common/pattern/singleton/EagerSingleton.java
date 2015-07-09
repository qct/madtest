package madtest.common.pattern.singleton;

/**
 * 饿汉式 static final field
 * Created by qct on 2015/7/9.
 */
public class EagerSingleton {
    //类加载时就初始化
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
