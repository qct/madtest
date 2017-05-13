package madtest.common.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import org.junit.Test;

/**
 * Created by qct on 2016/3/9.
 */
public class AtomicReferenceTest {

    AtomicReference<Long> objectAtomicReference = new AtomicReference<>(0L);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Long time = System.currentTimeMillis();
            Long expect = objectAtomicReference.get();

            //dead loop
//            long time = System.currentTimeMillis();
//            long expect = objectAtomicReference.get();
            while (!objectAtomicReference.compareAndSet(expect, time)) {
                expect = objectAtomicReference.get();
            }
            System.out.println(objectAtomicReference.get());
            Thread.sleep(1000);
        }
    }
}
