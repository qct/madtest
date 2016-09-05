package madtest.common.pattern.proxy.spring;

import org.springframework.stereotype.Component;

import madtest.common.pattern.proxy.Greeting;

/**
 * Created by alex on 2016/9/5.
 */
@Component
public class GreetingImplThrow implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);

        throw new RuntimeException("Error"); // 故意抛出一个异常，看看异常信息能否被拦截到
    }
}
