package madtest.common.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import madtest.common.concurrent.ExecutorServiceUtil;

/**
 * <p>Created by Damon.Q on 2017/10/4.
 */
public class QueueTest {

    private static final BlockingQueue<Runnable> QUEUE = new LinkedBlockingDeque<>();

    private static ExecutorService executorService = new ThreadPoolExecutor(2, 3, 10L, TimeUnit.MINUTES, QUEUE,
        new ThreadFactoryBuilder().setNameFormat("test-pool-%d").build(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("active count: " + executor.getActiveCount());
            System.out.println("task count: " + executor.getTaskCount());
            System.out.println("completed task count: " + executor.getCompletedTaskCount());
            System.out.println("queue size: " + executor.getQueue().size());
        }
    });

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> {
                System.out.println("queue size: " + QUEUE.size());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

        ExecutorServiceUtil.shutdownAndAwaitTermination(executorService);
    }
}
