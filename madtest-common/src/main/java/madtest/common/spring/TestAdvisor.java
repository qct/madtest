package madtest.common.spring;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * Created by alex on 2016/10/26.
 */
public class TestAdvisor implements PointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new TestPointcut();
    }

    @Override
    public Advice getAdvice() {
        return new TestAfterAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
