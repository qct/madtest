package thread;

/**
 * Created by qct on 2015/3/15.
 */
public class Count implements Runnable {
    @Override
    public  void run() {
//        int count = 0;
        synchronized(this) {
            for(int i=0;i<10;i++) {
                System.out.println(Thread.currentThread().getName()+ ": " + i);
            }
        }

//        System.out.println("haha:" + ++count);
    }
}
