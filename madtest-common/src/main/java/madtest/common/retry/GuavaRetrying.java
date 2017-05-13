package madtest.common.retry;

import com.github.rholder.retry.BlockStrategies;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Created by Damon.Q on 2017/3/22.
 */
public class GuavaRetrying {

    private static final Logger logger = LoggerFactory.getLogger(GuavaRetrying.class);

    public static void main(String[] args) {
        Callable<Void> myCallable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                logger.debug("I'm executing...");
                return null;
            }
        };
        Retryer<Void> retryer = RetryerBuilder.<Void>newBuilder()
            .retryIfResult(result -> true)
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
            .withBlockStrategy(BlockStrategies.threadSleepStrategy())
            .build();

        logger.debug("I'm caller...");
        try {
            retryer.call(myCallable);
        } catch (ExecutionException e) {
        } catch (RetryException e) {
        }
        logger.debug("retrying finished...");
    }

    static void executorRetrying() {
        ExecutorService retryExecutor = Executors.newSingleThreadExecutor();
        retryExecutor.submit(() -> {
            Callable<Void> myCallable = () -> {
                System.out.println("do something...");
                return null;
            };
            try {
                RetryerBuilder.<Void>newBuilder().build().call(myCallable);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (RetryException e) {
                e.printStackTrace();
            }
        });
    }
}
