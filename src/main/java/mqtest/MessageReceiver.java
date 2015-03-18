package mqtest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by qct on 2015/1/6.
 */
public class MessageReceiver implements MessageListener {

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                System.out.println(String.format("Received: %s", text));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
