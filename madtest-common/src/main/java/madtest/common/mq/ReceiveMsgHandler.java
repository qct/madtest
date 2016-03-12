package madtest.common.mq;

/**
 * Created by qct on 2016/3/12.
 */
public class ReceiveMsgHandler {
    public void handleMessage(String text) {
        System.out.println("Received: " + text);
    }
}
