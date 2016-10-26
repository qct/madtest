package madtest.common.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alex on 2016/10/26.
 */
public class TestAOP {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring-read-source.xml");
        TestTarget testTarget = (TestTarget) applicationContext.getBean("testAOP");
        testTarget.test();
        System.out.println("------无敌分割线-----");
        testTarget.test2();
    }
}
