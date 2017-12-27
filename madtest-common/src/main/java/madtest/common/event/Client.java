package madtest.common.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by alex on 2016/9/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/event-test.xml"})
public class Client {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testEvent() {
        applicationContext.publishEvent(new AEvent("I'm A Event!"));
    }

    @Test
    public void testConditionEvent() {
        ConditionEvent conditionEvent = new ConditionEvent("condition event");
        conditionEvent.setAwesome(true);
        applicationContext.publishEvent(conditionEvent);
    }

    @Test
    public void testTransactionEvent() {
        TransactionEvent transactionEvent = new TransactionEvent("Transaction Event");
        transactionEvent.setAwesome(true);
        applicationContext.publishEvent(transactionEvent);
    }
}
