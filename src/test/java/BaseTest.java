import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.sql.DataSource;

/**
 * 测试基类
 * Created by qct on 2015/2/11.
 */
@ContextConfiguration(locations = {"/applicationContext-threadpool.xml"})
public class BaseTest extends AbstractJUnit4SpringContextTests {

    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    DataSource dataSource;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
