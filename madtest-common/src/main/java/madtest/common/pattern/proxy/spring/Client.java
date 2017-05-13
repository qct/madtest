package madtest.common.pattern.proxy.spring;

import madtest.common.pattern.proxy.Greeting;
import madtest.common.pattern.proxy.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * https://my.oschina.net/huangyong/blog/161338
 *
 * Created by alex on 2016/9/5.
 */
public class Client {

    public static void main(String[] args) {
        // 1.before and after advice
        ProxyFactory proxyFactory = new ProxyFactory();// 创建代理工厂
        proxyFactory.setTarget(new GreetingImpl());// 射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAdvice());// 添加前置增强
        proxyFactory.addAdvice(new GreetingAfterAdvice());// 添加后置增强

        Greeting greeting = (Greeting) proxyFactory.getProxy();// 从代理工厂中获取代理
        greeting.sayHello("Jack");// 调用代理的方法

        // 2.around advice
        ProxyFactory proxyFactory2 = new ProxyFactory();// 创建代理工厂
        proxyFactory2.setTarget(new GreetingImpl());// 射入目标类对象
        proxyFactory2.addAdvice(new GreetingAroundAdvice());// 添加环绕增强

        Greeting greeting2 = (Greeting) proxyFactory2.getProxy();// 从代理工厂中获取代理
        greeting2.sayHello("Alex");// 调用代理的方法

        // 3.spring configuration
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "META-INF/spring/spring-aop.xml");
        Greeting greeting3 = (Greeting) context.getBean("greetingProxy");
        greeting3.sayHello("qct");

        // 4.throw advice
//        Greeting greeting4 = (Greeting) context.getBean("greetingProxyThrow");
//        greeting4.sayHello("throw qct");

        // 5.introduction advice
        GreetingImpl greetingImpl = (GreetingImpl) context
            .getBean("greetingProxyIntro"); // 注意：转型为目标类，而并非它的 Greeting 接口
        Apology apology = (Apology) greetingImpl; // 将目标类强制向上转型为 Apology 接口（这是引入增强给我们带来的特性，也就是“接口动态实现”功能）
        apology.saySorry("Jack, introduction advice");

        // 6.advisor
        GreetingImpl greeting5 = (GreetingImpl) context.getBean("greetingProxy2");
        greeting5.sayHello("advisor hello, qct");
        greeting5.goodMorning("advisor good morning, qct");
        greeting5.goodNight("advisor good night, qct");
    }
}
