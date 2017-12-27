package madtest.common.auto.service;

import com.google.auto.service.AutoService;

/**
 * default description.
 *
 * @author qct
 * @date 2017/12/26
 * @since 1.0
 */
@AutoService(IMyProcessor.class)
public class MyProcessor implements IMyProcessor {

    @Override
    public void test() {

    }
}
