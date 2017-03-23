package madtest.common.retry;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Created by Damon.Q on 2017/3/11.
 */
public class RetryableOperationTest {

    public static void main(String[] args) {
        RetryableOperation<Integer> intRetry = RetryableOperation.create(new Callable<Integer>() {
            AtomicInteger count = new AtomicInteger(0);
            @Override
            public Integer call() throws Exception {
                System.out.println("callable retrying, " + count.incrementAndGet() + " time...");
                throw new NullPointerException();
            }
        });

        try {
            intRetry.retry(3, NullPointerException.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RetryableOperation<?> runnableRetry = RetryableOperation.create(new Runnable() {
            AtomicInteger count = new AtomicInteger(0);

            @Override
            public void run() {
                System.out.println("runnable retrying, " + count.incrementAndGet() + " time...");
                throw new NullPointerException();
            }
        }).withExponentialBackoff();
        try {
            runnableRetry.retry(5, NullPointerException.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
