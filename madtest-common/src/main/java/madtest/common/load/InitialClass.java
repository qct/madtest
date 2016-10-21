package madtest.common.load;

/**
 * Created by alex on 2016/10/20.
 */
public class InitialClass {
    public static int i;

    static {
        i = 1000;
        System.out.println("InitialClass is init");
    }
}
