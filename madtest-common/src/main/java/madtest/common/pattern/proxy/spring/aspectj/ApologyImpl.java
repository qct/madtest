package madtest.common.pattern.proxy.spring.aspectj;

import madtest.common.pattern.proxy.spring.Apology;

/**
 * Created by alex on 2016/9/5.
 */
public class ApologyImpl implements Apology {
    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }
}
