package madtest.common.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Created by qct on 2015/5/5.
 */
public class PoolMain {
    static GenericObjectPool<SimpleThread> pool = new GenericObjectPool<>(new MyPooledObjectFactory());

    static {
        pool.setMaxTotal(2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            try {
                SimpleThread t1 = pool.borrowObject();
                t1.setIsRunning(true);
                t1.setPool(pool);
                t1.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
