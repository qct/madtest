package madtest.common.task;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by quchentao on 16/3/20.
 */
public class TaskTest implements Runnable {
    public static int i = 0;

    public static void main(String[] args) {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(
                new TaskTest(), 1000, 2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        if (i >= 5) return;
        System.out.println(i++);
    }
}
