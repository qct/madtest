package madtest.common.redis;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * Created by qct on 2016/3/15.
 */
@ContextConfiguration(locations = {"/META-INF/spring/application-redis.xml",
    "/META-INF/spring/application-common.xml"})
public class JedisUtilTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private JedisUtil jedisUtil;

    @Test
    public void testSet() throws Exception {
        jedisUtil.set("qct", "123");
    }

    @Test
    public void testGet() throws Exception {
        System.out.println(jedisUtil.get("qct", "000"));
    }
}