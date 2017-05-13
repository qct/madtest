package madtest.common.pattern.proxy.spring.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by alex on 2016/9/5.
 */
@Aspect
@Component
public class GreetingAnnotationAspect {

    @Around("@annotation(madtest.common.pattern.proxy.spring.aspectj.Tag)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }

    private void after() {
        System.out.println("after aspectj");
    }

    private void before() {
        System.out.println("before aspectj");
    }
}
