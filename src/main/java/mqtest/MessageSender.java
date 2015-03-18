package mqtest;

import org.springframework.jms.core.JmsTemplate;

/**
 * Created by qct on 2015/1/6.
 */
public class MessageSender {
    private final JmsTemplate jmsTemplate;

    public MessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {
        jmsTemplate.convertAndSend(text);
    }
}
