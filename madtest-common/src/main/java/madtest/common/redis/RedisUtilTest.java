package madtest.common.redis;

/**
 * Created by qct on 2015/7/7.
 */
public class RedisUtilTest {
    public static void main(String[] args) {
        RedisUtil.set("storage_file_md5_hash", "f39ee6f453314be30a210f3e328475af");
        String s = RedisUtil.get("storage_file_md5_hash");
        System.out.println(s);
    }
}
