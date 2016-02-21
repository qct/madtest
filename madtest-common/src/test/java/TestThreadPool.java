import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;

import madtest.common.pool.StartTaskThread;

/**
 * Created by qct on 2015/5/6.
 */
public class TestThreadPool extends BaseTest {

    private static int produceTaskSleepTime = 4000;
    private static int produceTaskMaxNumber = 10;

    @Autowired
    DataSource dataSource;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }


    @Test
    public void testThreadPoolExecutor() {
        for (int i = 1; i <= produceTaskMaxNumber; i++) {
            try {
                Thread.sleep(produceTaskSleepTime);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            new Thread(new StartTaskThread(threadPoolTaskExecutor, i)).start();
        }

    }
}
