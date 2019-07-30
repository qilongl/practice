package com.lql.db.redis.util;

import com.lql.util.JsonUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.*;

/**
 * Created by StrangeDragon on 2019/6/24 10:09
 * 单机模式
 **/
@Data
@Configuration
public class RedisUtil {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Jedis jedis;//非切片客户端连接
    private JedisPool jedisPool;//非切片连接池

    @Value("${spring.redis.host}")
    private String serverIp;
    @Value("${spring.redis.port}")
    private int port;


    /**
     * 单机模式
     *
     * @return
     */
    @Bean
    public RedisUtil redisSingleInit() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);//最大连接数
        config.setMaxIdle(5);//最大空闲连接（意思是连接池中最多会保持5个空闲连接）
        config.setMaxWaitMillis(10000);//最大超时  表示获取的redis都是可用的
        config.setTestOnBorrow(false);//为true 时，标识获取的每一个redis都是测试可用的
        jedisPool = new JedisPool(config, serverIp, port);
        jedis = jedisPool.getResource();
        return this;//等价于 return new RedisUtil();
    }

    public String hmset(String key, Map<String, String> hash) {
        return jedis.hmset(key, hash);
    }

    public Long lpush(String key, String... strings) {
        return jedis.lpush(key, strings);
    }

    public List<String> lrange(String key, long start, long end) {
        return jedis.lrange(key, start, end);
    }

    /**
     * 从redis中获取list类型转换为List<Map>
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Map<String, Object>> lrange2ListMap(String key, long start, long end) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<String> list = lrange(key, start, end);
        log.info("redis 中获取的数据：{}", JsonUtil.beanToJson(list));
        for (String str : list) {
            try {
                result.add(JsonUtil.jsonToBean(str, Map.class));
            } catch (Exception e) {
                throw new UnsupportedOperationException("转换失败！");
            }
        }
        return result;
    }
}
