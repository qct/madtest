package madtest.common.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qct on 2016/3/21.
 *
 * @author qct
 */
public class TaskExecutor {

    static Integer i = 0;
    static Integer result = -1;
    private static Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    public int execute() throws InterruptedException {
        ScheduledExecutorService singleThreadScheduledPool = Executors
            .newSingleThreadScheduledExecutor();
        singleThreadScheduledPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                logger.debug("i: {}", i++);
                result = i;
                logger.debug("processing result: " + result);
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);

        while (true) {
            Thread.sleep(1000);
            logger.debug("while result: " + result);
            if (result >= 5) {
                logger.debug("singleThreadScheduledPool is shutdown: " + singleThreadScheduledPool
                    .isShutdown());
                if (!singleThreadScheduledPool.isShutdown()) {
                    singleThreadScheduledPool.shutdownNow();
                    return result;
                }
            }
        }
    }
}
