package madtest.common.pattern.proxy.spring;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by alex on 2016/9/5.
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("spring after returning");
    }
}
