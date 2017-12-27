package qct;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

/**
 * default description.
 *
 * @author qct
 * @date 2017/12/22
 * @since 1.0
 */
@Service
public class ConcurrentService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentService.class);

    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new
        LinkedBlockingQueue<>(), new CustomizableThreadFactory("update-svc-"));

    @Autowired
    private SaveService saveService;

    @Autowired
    private UpdateService1 updateService1;

    @Autowired
    private UpdateService2 updateService2;

    @Override
    public void afterPropertiesSet() {
//        Customer c = new Customer("a", "b");
//        saveService.saveOne(c);
//
//        logger.info("ConcurrentService begin");
//
//        EXECUTOR_SERVICE.submit(() -> {
//            try {
//                Customer update = updateService2.update(c);
//                logger.debug("after update 2: {}", update);
//            } catch (Exception e) {
//                logger.error("update 2 failed");
//                e.printStackTrace();
//            }
//        });
//
//        try {
//            Customer update = updateService1.update(c);
//            logger.debug("after update 1: {}", update);
//        } catch (Exception e) {
//            logger.error("update 1 failed");
//            e.printStackTrace();
//        }
    }
}
