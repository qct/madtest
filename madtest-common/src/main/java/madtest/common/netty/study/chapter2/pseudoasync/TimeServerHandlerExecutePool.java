package madtest.common.netty.study.chapter2.pseudoasync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by quchentao on 15/10/25.
 */
public class TimeServerHandlerExecutePool {

    private ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize,
            120l, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(queueSize));
    }

    /**
     * @param task
     */
    public void execute(Runnable task) {
        System.out.println("execute pool execute a task: " + task.toString());
        executor.execute(task);
    }
}
