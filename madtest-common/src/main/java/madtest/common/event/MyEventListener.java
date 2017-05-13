package madtest.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Created by alex on 2016/9/30.
 */
@Component
public class MyEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MyEventListener.class);

    @Async
    @EventListener
    public void onApplicationEvent(AEvent event) {
        logger.info("received AEvent {}", event);
    }

    @EventListener(condition = "#event.awesome")
    public void conditionEvent(ConditionEvent event) {
        logger.info("condition event, awesome = true!!!");
    }

    @TransactionalEventListener(condition = "#transactionEvent.awesome", phase = TransactionPhase.BEFORE_COMMIT)
    public void transactionEvent(TransactionEvent transactionEvent) {
        logger.info("I'm transaction event!!!");
    }
}
