package redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by qct on 2015/7/7.
 */
public class RedisUtilTest {
    public static void main(String[] args) {
        String s = RedisUtil.getHSet("storage_file_md5_hash", "f39ee6f453314be30a210f3e328475af");
        System.out.println(s);
    }
}
