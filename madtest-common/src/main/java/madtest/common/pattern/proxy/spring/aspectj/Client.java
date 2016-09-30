package madtest.common.pattern.proxy.spring.aspectj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import madtest.common.pattern.proxy.GreetingImpl;
import madtest.common.pattern.proxy.spring.Apology;

/**
 * Created by alex on 2016/9/5.
 */
public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-aop-aspectj.xml");
        GreetingImpl greeting = (GreetingImpl) context.getBean("greetingImpl");
        greeting.sayHello("qct");
        greeting.goodMorning("qct");
        greeting.goodNight("qct");

        Apology apology = (Apology)greeting;
        apology.saySorry("Jack");
    }
}
