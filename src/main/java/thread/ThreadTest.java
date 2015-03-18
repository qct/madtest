package thread;

/**
 * Created by qct on 2015/3/15.
 */
public class ThreadTest {
    public static void main(String[] args) {
        Count c = new Count();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }
}


