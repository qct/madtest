package madtest.common.pattern.proxy;

import org.springframework.stereotype.Component;

import madtest.common.pattern.proxy.spring.aspectj.Tag;

/**
 * Created by alex on 2016/8/26.
 */
@Component
public class GreetingImpl implements Greeting {
    @Tag
    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("After");
    }

    public void goodMorning(String name) {
        System.out.println("Good Morning! " + name);
    }

    public void goodNight(String name) {
        System.out.println("Good Night! " + name);
    }
}
