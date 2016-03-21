package madtest.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by qct on 2016/3/21.
 *
 * @author qct
 */
public class TaskExecutor {

    private static Logger logger = LoggerFactory.getLogger(TaskExecutor.class);
    static Integer i = 0;
    static Integer result = -1;

    public int execute() throws InterruptedException {
        ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
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
                logger.debug("singleThreadScheduledPool is shutdown: " + singleThreadScheduledPool.isShutdown());
                if (!singleThreadScheduledPool.isShutdown()) {
                    singleThreadScheduledPool.shutdownNow();
                    return result;
                }
            }
        }
    }
}
