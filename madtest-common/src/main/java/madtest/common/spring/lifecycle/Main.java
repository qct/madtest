package madtest.common.spring.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>Created by qct on 2017/4/27.
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        configApplicationContext.start();
        configApplicationContext.close();
    }
}
