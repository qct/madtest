package madtest.common.thread;

/**
 * Created by qct on 2015/5/5.
 */
public class PrinterTest {
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyThreadPrinter t1 = new MyThreadPrinter("A", c, a);
        MyThreadPrinter t2 = new MyThreadPrinter("B", a, b);
        MyThreadPrinter t3 = new MyThreadPrinter("C", b, c);

        new Thread(t1).start();
        Thread.sleep(100);
        new Thread(t2).start();
        Thread.sleep(100);
        new Thread(t3).start();
        Thread.sleep(100);
    }
}
