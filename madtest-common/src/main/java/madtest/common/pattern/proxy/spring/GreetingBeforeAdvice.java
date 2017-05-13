package madtest.common.pattern.proxy.spring;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * Created by alex on 2016/9/5.
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("spring before");
    }
}
