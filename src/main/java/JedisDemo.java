import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * imooc redis
 * https://www.imooc.com/learn/839
 *
 * @author dhl
 */
public class JedisDemo {

    /**
     * k - value
     */
    @Test
    public void test1() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("name", "imooc");
        String value = jedis.get("company");
        System.out.println(value);
        jedis.close();
    }

    /**
     * jedis config
     */
    @Test
    public void test2() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);
        // 获得核心对象
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
             jedis.set("name","张三");
            String value = jedis.get("name");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    /**
     * key - map
     */
    @Test
    public void redisByHm(){
        Jedis jedis = new Jedis("localhost", 6379);
        Map<String,String> map = new HashMap<String, String>();
        map.put("username","charlie");
        map.put("age","12");
        jedis.hmset("map",map);
        List list = jedis.hmget("map","username","age");
        System.out.println(list);
        jedis.close();
    }


}
