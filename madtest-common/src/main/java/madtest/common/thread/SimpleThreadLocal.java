package madtest.common.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qct on 2016/3/6.
 */
public class SimpleThreadLocal {
    private Map valueMap = Collections.synchronizedMap(new HashMap());

    public void set(Object newValue) {
        valueMap.put(Thread.currentThread(), newValue);//1.键为线程对象，值为本线程的变量副本
    }

    public Object get() {
        Thread currentThread = Thread.currentThread();
        Object o = valueMap.get(currentThread);//2.返回本线程对应的变量
        if (o == null && !valueMap.containsKey(currentThread)) {//3.如果在Map中不存在，放到Map中保存起来。
            o = initialValue();
            valueMap.put(currentThread, o);
        }
        return o;
    }

    public void remove() {
        valueMap.remove(Thread.currentThread());
    }

    public Object initialValue() {
        return null;
    }
}
