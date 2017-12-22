package madtest.web.util;

import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * Created by qct on 2015/7/8.
 */
public class SpringContextUtils {
    private static ApplicationContext APPLICATION_CONTEXT;

    public static void setContext(ApplicationContext context) {
        if (APPLICATION_CONTEXT == null) {
            APPLICATION_CONTEXT = context;
        }
    }

    public static <T extends Object> T getBean(Class<T> type) {
        checkApplicationContext();
        return APPLICATION_CONTEXT.getBean(type);
    }

    public static <T extends Object> T getBean(String beanId) {
        checkApplicationContext();
        return (T) APPLICATION_CONTEXT.getBean(beanId);
    }

    public static <T extends Object> Collection<T> getBeans(Class<T> type) {
        checkApplicationContext();
        return APPLICATION_CONTEXT.getBeansOfType(type).values();
    }

    private static void checkApplicationContext() {
        if (APPLICATION_CONTEXT == null) {
            throw new IllegalAccessError("must set ApplicatonContext first!");
        }
    }
}
