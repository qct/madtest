package madtest.persist;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qct on 2015/8/26.
 */
public class JdbcTemplateDemo {


    public static void testDruid() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/druid.xml");
        JdbcTemplate template = (JdbcTemplate) context.getBean("jdbcTemplate");
        template.query("select user,host from mysql.user;", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                System.out.println("user:" + resultSet.getString("user") + ", host:" + resultSet.getString("host"));
            }
        });
    }

    public static void main(String[] args) {
        testDruid();
    }
}
