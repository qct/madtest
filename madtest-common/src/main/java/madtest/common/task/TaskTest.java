package madtest.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by quchentao on 16/3/20.
 */
public class TaskTest {

    private static Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    public static void main(String[] args) throws InterruptedException {
        TaskExecutor taskExecutor = new TaskExecutor();
        int result = taskExecutor.execute();
        logger.debug("main result: " + result);
    }
}
