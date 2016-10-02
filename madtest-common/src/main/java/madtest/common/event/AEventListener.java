package madtest.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by alex on 2016/9/30.
 */
@Component
public class AEventListener implements ApplicationListener<AEvent> {
    private static final Logger logger = LoggerFactory.getLogger(AEventListener.class);

    @Async
    @Override
    public void onApplicationEvent(AEvent event) {
        logger.debug("received AEvent {}", event);
    }
}
