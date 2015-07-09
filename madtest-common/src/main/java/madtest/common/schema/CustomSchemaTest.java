package madtest.common.schema;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by qct on 2015/7/9.
 */
public class CustomSchemaTest {
    public static void main(String[] args) {
        CustomSchemaTest test = new CustomSchemaTest();
        test.testSchema();
    }

    void testSchema() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("custom-schema.xml");
        Qct q = (Qct) ctx.getBean("qctConfig");
        System.out.println(q.getId());
        System.out.println(q.getName());
        System.out.println(q.getAge());
    }
}
