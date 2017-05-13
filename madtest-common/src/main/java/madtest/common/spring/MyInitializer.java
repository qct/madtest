package madtest.common.spring;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Damon.Q on 2017/2/3.
 */
public class MyInitializer implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MyInitializer.class);

    // first called
    @PostConstruct
    public void postConstruct() {
        logger.debug("postConstruct called...");
    }

    // second called
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("afterPropertiesSet called...");
    }

    // third called
    public void init() {
        logger.debug("init called...");
    }
}
