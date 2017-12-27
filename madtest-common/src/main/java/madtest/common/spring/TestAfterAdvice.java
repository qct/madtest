package madtest.common.spring;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Created by alex on 2016/10/26.
 */
public class TestAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
        throws Throwable {
        System.out
            .println("after " + target.getClass().getSimpleName() + "." + method.getName() + "()");
    }
}
