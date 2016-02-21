package madtest.common.pool;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Created by qct on 2015/5/6.
 */
public class ThreadPoolTask implements Callable<String>, Serializable {
    private static final long serialVersionUID = 0;
    private static int consumeTaskSleepTime = 2000;
    // 保存任务所需要的数据
    private Object threadPoolTaskData;

    public ThreadPoolTask(Object tasks) {
        this.threadPoolTaskData = tasks;
    }

    public synchronized String call() throws Exception {
        // 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
        System.out.println("开始执行任务：" + threadPoolTaskData);
        String result = "";
        // //便于观察，等待一段时间
        try {
            // long r = 5/0;
            for (int i = 0; i < 100000000; i++) {

            }
            result = "OK";
        } catch (Exception e) {
            e.printStackTrace();
            result = "ERROR";
        }
        threadPoolTaskData = null;
        return result;
    }
}
