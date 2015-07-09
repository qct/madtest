package madtest.common.thread;

/**
 * Created by qct on 2015/5/5.
 */
public class MyThreadPrinter implements Runnable {
    private String name;
    private Object prev;
    private Object self;

    public MyThreadPrinter(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }
    @Override
    public void run() {
        int count = 10;
        while(count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.println(this.name);
                    count--;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
