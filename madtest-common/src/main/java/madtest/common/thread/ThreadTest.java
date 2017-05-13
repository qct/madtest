package madtest.common.thread;

/**
 * Created by qct on 2015/3/15.
 */
public class ThreadTest {

    public static void main(String[] args) {
        CountThread b = new CountThread();
        b.start();
        synchronized (b) {
            System.out.println("Waiting for b to complete...");
            try {
                b.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Total is: " + b.total);
        }
    }
}


