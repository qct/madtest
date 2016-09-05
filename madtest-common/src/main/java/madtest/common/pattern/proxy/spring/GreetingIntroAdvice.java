package madtest.common.pattern.proxy.spring;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by alex on 2016/9/5.
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {
    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);
    }
}
