package madtest.common.redis;

import com.alibaba.fastjson.JSON;
import madtest.common.util.PropConfig;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public class RedisUtil {

    private static final PropertiesConfiguration REDIS_CONFIG = PropConfig
        .getConfig("redis.properties");
    private static Logger log = LoggerFactory.getLogger(RedisUtil.class);
    private static JedisPool jedisPool = null;

    /**
     * 初始化jedisPool
     */
    private static void initialPool() {
        String host = REDIS_CONFIG.getString("redis.master.host", "localhost");
        int port = REDIS_CONFIG.getInt("redis.master.port", 6379);

        try {
            JedisPoolConfig config = new JedisPoolConfig();

            config.setMaxTotal(REDIS_CONFIG.getInt("redis.pool.max.total", config.getMaxTotal()));
            config.setMaxIdle(REDIS_CONFIG.getInt("redis.pool.max.idle", config.getMaxIdle()));
            config.setMaxWaitMillis(
                REDIS_CONFIG.getLong("redis.pool.max.wait.millis", config.getMaxWaitMillis()));
            config.setTestOnBorrow(false);
            int timeout = REDIS_CONFIG.getInt("redis.pool.connection.timeout", 2000);

            jedisPool = new JedisPool(config, host, port, timeout);
        } catch (Exception e) {
            log.error("initialPool-failed:" + host + ":" + port, e);
        }
    }

    /**
     * 获得jedis客户端
     */
    public synchronized static Jedis getJedisClient() {
        if (jedisPool == null) {
            initialPool();
        }

        // double check
        if (jedisPool == null) {
            log.error("getJedisInstance-no-instance");
            return null;
        }

        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            log.error("getJedisInstance-failed", e);
            return null;
        }
    }

    /**
     * 释放jedis到pool中
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
//            jedisPool.returnResource(jedis);
            jedis.close();
        }
    }

    /**
     * set key value <p>
     * <pre>
     * 将字符串值 value 关联到 key
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型
     * </pre>
     */
    public static void set(String key, String value) {
        Jedis jedis = getJedisClient();
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            log.error("set-string-failed:" + key + "," + value, e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * setnx key value <p>
     * <pre>
     * 将 key 的值设为 value ，当且仅当 key 不存在
     * 若给定的 key 已经存在，则 SETNX 不做任何动作
     * 不存在则保存成功返回1，存在或者异常则失败返回0
     * </pre>
     */
    public static long setnx(String key, String value) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("setnx-string-failed:" + key + "," + value, e);
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * setex key seconds value <p>
     * <pre>
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)
     * 如果 key 已经存在， SETEX 命令将覆写旧值
     * </pre>
     */
    public static void setex(String key, String value, int seconds) {
        Jedis jedis = getJedisClient();
        try {
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error("setex-string-failed:" + key + "," + value + "," + seconds, e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * setnx + setex key seconds value <p>
     * <pre>
     * 如果不存在key就添加并设置生存时间，单位秒
     * 如果存在key，就不做任何操作
     * 这两个动作是原子操作
     * </pre>
     */
    public static long setnxex(String key, String value, int seconds) {
        Jedis jedis = getJedisClient();
        try {

            return 0;
        } catch (Exception e) {
            log.error("setnxex-string-failed:" + key + "," + value, e);
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * get key <p>
     * <pre>
     * 返回 key 所关联的字符串值
     * 如果 key 不存在那么返回 null
     * 假如 key 储存的值不是字符串类型返回 null
     * </pre>
     */
    public static String get(String key) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            log.error("get-string-failed:" + key, e);
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * DEL key <p>
     * <pre>
     * 删除给定的一个key，返回删除成功的个数， 也就是说，如果返回0表示删除失败，1表示删除成功
     * </pre>
     */
    public static long del(String key) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.del(key);
        } catch (Exception e) {
            log.error("delete-failed:" + key, e);
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * EXISTS key <p>
     * <pre>
     * 检查给定 key 是否存在
     * </pre>
     */
    public static boolean exists(String key) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("exists-failed:" + key, e);
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * ttl key <p>
     * <pre>
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)
     * </pre>
     */
    public static long ttl(String key) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("ttl-failed:" + key, e);
            return -1;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * INCR key <p>
     * <pre>
     * 将 key 中储存的数字值增一
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作（返回1）
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么就抛出异常
     * </pre>
     */
    public static long incr(String key) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.incr(key);
        } catch (JedisException e) {
            log.error("incr-failed:" + key, e);
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * INCRBY key increment <p>
     * <pre>
     * 将 key 所储存的值加上增量 increment 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * </pre>
     */
    public static long incrBy(String key, long increment) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.incrBy(key, increment);
        } catch (JedisException e) {
            log.error("incrBy-failed:" + key, e);
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * DECR key <p>
     * <pre>
     * 将 key 中储存的数字值减一
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么就抛出异常
     * </pre>
     */
    public static long decr(String key) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.decr(key);
        } catch (JedisException e) {
            log.error("decr-failed:" + key, e);
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * DECRBY key decrement <p>
     * <pre>
     * 将 key 所储存的值减去减量 decrement 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * </pre>
     */
    public static long decrBy(String key, long decrement) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.decrBy(key, decrement);
        } catch (JedisException e) {
            log.error("decrBy-failed:" + key, e);
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * set key object <p>
     * <pre>
     * 使用fastjson序列化成String保存
     * </pre>
     */
    public static void setObject(String key, Object object) {
        set(key, JSON.toJSONString(object));
    }

    /**
     * get key <p>
     * <pre>
     * 获得object序列化的值再使用fastjson进行反序列化，进而返回clazz类型的对象
     * </pre>
     */
    public static <T> T getObject(String key, Class<T> clazz) {
        return JSON.parseObject(get(key), clazz);
    }

    /**
     * 获得HashSet对象
     *
     * @param domain 域名
     * @param key 键值
     * @return Json String or String value
     */
    public static String getHSet(String domain, String key) {
        Jedis jedis = getJedisClient();
        try {
            return jedis.hget(domain, key);
        } catch (Exception e) {
            log.error("hget-failed:" + key, e);
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

}