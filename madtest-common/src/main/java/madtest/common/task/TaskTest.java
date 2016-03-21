package madtest.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Soundbank;

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
