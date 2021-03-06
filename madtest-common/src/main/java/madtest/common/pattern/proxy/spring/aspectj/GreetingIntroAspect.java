package madtest.common.pattern.proxy.spring.aspectj;

import madtest.common.pattern.proxy.spring.Apology;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Created by alex on 2016/9/5.
 */
@Aspect
@Component
public class GreetingIntroAspect {

    @DeclareParents(value = "madtest.common.pattern.proxy.GreetingImpl", defaultImpl = ApologyImpl.class)
    private Apology apology;
}
