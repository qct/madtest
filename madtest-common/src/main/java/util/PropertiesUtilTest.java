package util;

/**
 * Created by qct on 2015/7/7.
 */
public class PropertiesUtilTest {
    public static void main(String[] args) {
        PropertiesUtil.appendProperties("log4j.properties");
        String key = PropertiesUtil.getProperty("mongoClients");
        System.out.println(key);
    }
}
