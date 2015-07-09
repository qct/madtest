package madtest.common.pattern.singleton;

/**
 * Created by qct on 2015/7/9.
 */
public class SingletonTest {
    public static void main(String[] args) {
        EasySingleton s1 = EasySingleton.INSTANCE;
        EagerSingleton s2 = EagerSingleton.getInstance();
        NestedClassSingleton s3 = NestedClassSingleton.getInstance();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
