package mqtest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by qct on 2015/1/6.
 */
public class ReceiverApp {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }
}
