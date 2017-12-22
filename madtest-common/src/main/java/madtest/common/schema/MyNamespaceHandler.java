package madtest.common.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by qct on 2015/7/9.
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("qct", new QctBeanDefinitionParser());
    }
}
