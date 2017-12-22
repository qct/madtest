package madtest.common.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qct on 2015/5/5.
 */
public class MyPooledObjectFactory extends BasePooledObjectFactory<SimpleThread> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SimpleThread create() throws Exception {
        SimpleThread simpleThread = new SimpleThread();
//        simpleThread.start();
        logger.debug("创建线程:" + simpleThread.getName());
        return simpleThread;
    }

    @Override
    public PooledObject<SimpleThread> wrap(SimpleThread obj) {
        return new DefaultPooledObject<SimpleThread>(obj);
    }

}
