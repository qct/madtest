package qct.practice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * <p>Created by qdd on 2022/6/12.
 */
public class TestMyThreadPoolExecutor {

    @Test
    void name() {
        ThreadPoolExecutor exec = new MyThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        exec.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": I'm task1");
        });
        exec.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": I'm task2");
        });
    }
}

class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private final ThreadLocal<Integer> tasks = new ThreadLocal<>();

    public MyThreadPoolExecutor(
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        Integer init = tasks.get();
        if (init == null) {
            tasks.set(1);
        } else {
            tasks.set(tasks.get() + 1);
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("Executed tasks: " + tasks.get());
    }
}
