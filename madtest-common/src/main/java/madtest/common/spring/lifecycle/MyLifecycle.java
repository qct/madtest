package madtest.common.spring.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * <p>Created by qct on 2017/4/27.
 */
@Component
public class MyLifecycle implements SmartLifecycle {

    private boolean isRunning = false;

    @Override
    public boolean isAutoStartup() {
        System.out.println("check isAutoStartup...");
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        System.out.println("I'm stopping");
        runnable.run();
    }

    @Override
    public void start() {
        System.out.println("I'm starting");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("I'm stopping");
    }

    @Override
    public boolean isRunning() {
        System.out.println("check isRunning: " + isRunning);
        return isRunning;
    }

    @Override
    public int getPhase() {
        return 10;
    }
}
