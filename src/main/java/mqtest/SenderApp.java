package mqtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by qct on 2015/1/6.
 */
public class SenderApp {
    public static void main(String[] args) throws IOException {
        MessageSender sender = getMessageSender();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();

        while (StringUtils.hasText(text)) {
            System.out.println(String.format("send message: %s", text));
            sender.send(text);
            text = br.readLine();
        }
    }

    public static MessageSender getMessageSender() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        return (MessageSender) context.getBean("messageSender");
    }
}
