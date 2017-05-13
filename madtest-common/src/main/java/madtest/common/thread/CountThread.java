package madtest.common.thread;

/**
 * Created by qct on 2015/3/15.
 */
public class CountThread extends Thread {

    int total;

    @Override
    public void run() {
//        int count = 0;
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                total += i;
            }
            notify();
        }
//        System.out.println("haha:" + ++count);
    }
}
