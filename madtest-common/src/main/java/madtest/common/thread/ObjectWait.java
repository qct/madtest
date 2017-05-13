package madtest.common.thread;

/**
 * Created by alex on 2016/10/26.
 */
public class ObjectWait {

    private volatile static boolean lock;

    public static void main(String[] args) throws InterruptedException {
        final Object object = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("等待被通知！");
                try {
                    synchronized (object) {
                        while (!lock) {
                            System.out.println("thread 1 waiting");
                            object.wait();
                            System.out.println("thread 1 continue");
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("已被通知");
            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {

                System.out.println("马上开始通知！");
                synchronized (object) {
                    object.notify();
                    lock = true;
                }
                System.out.println("已通知");
            }
        });

        thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        Thread.sleep(10000);
    }
}
