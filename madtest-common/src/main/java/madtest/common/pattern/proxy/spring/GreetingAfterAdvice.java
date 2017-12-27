package madtest.common.pattern.proxy.spring;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Created by alex on 2016/9/5.
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
        throws Throwable {
        System.out.println("spring after returning");
    }
}
