package qct;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ConcurrencyApplicationTest {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentService.class);

    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new
        LinkedBlockingQueue<>(), new CustomizableThreadFactory("update-svc-"));

    @Autowired
    private SaveService saveService;

    @Autowired
    private UpdateService1 updateService1;

    @Autowired
    private UpdateService2 updateService2;

    @Test
    public void testConcurrency() {
        Customer c = new Customer("a", "b");
        saveService.saveOne(c);

        logger.info("ConcurrentService begin");

        EXECUTOR_SERVICE.submit(() -> {
            try {
                Customer update = updateService2.update(c);
                logger.debug("after update 2: {}", update);
            } catch (Exception e) {
                logger.error("update 2 failed");
                e.printStackTrace();
            }
        });

        try {
            Customer update = updateService1.update(c);
            logger.debug("after update 1: {}", update);
        } catch (Exception e) {
            logger.error("update 1 failed");
            e.printStackTrace();
        }

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
