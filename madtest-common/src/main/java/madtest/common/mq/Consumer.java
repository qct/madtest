package madtest.common.mq;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by qct on 2016/3/12.
 */
public class Consumer {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
    }
}
