package madtest.common.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Created by qct on 2015/5/5.
 */
public class SimpleThread extends Thread {
    private boolean isRunning;
    private GenericObjectPool pool;

    public SimpleThread() {
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!isRunning) {
                    this.wait();
                } else {
                    System.out.println(this.getName() + "开始处理");
                    sleep(2000);
                    System.out.println(this.getName() + "结束处理");
                    setIsRunning(false);
                    try {
                        pool.returnObject(this);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("我被Interrupted了，所以此线程将被关闭");
            return;
        }

    }

    public boolean isRunning() {
        return isRunning;
    }

    public synchronized void setIsRunning(boolean flag) {
        isRunning = flag;
        if (flag) {
            this.notify();
        }
    }

    public GenericObjectPool getPool() {
        return pool;
    }

    public void setPool(GenericObjectPool pool) {
        this.pool = pool;
    }
}
