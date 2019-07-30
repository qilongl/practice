package com.lql.db.redis.util;

import com.lql.util.JsonUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * Created by StrangeDragon on 2019/7/4 14:50
 * 集群模式
 **/
public class RedisClusterUtil {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private JedisCluster jedisCluster;

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    public void init() {
        log.info("redisNodes:{}", nodes);
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        String[] nodeArr = nodes.split(",");
        for (String str : nodeArr) {
            String host = str.split(":")[0];
            int port = Integer.parseInt(str.split(":")[1]);
            HostAndPort hostAndPort = new HostAndPort(host, port);
            hostAndPorts.add(hostAndPort);
        }
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(500);//最大连接数
        config.setMaxIdle(200);//最大空闲连接（意思是连接池中最多会保持200个空闲连接）
        config.setMaxWaitMillis(10000);//最大超时  表示获取的redis都是可用的
        config.setMinIdle(50);//最小空闲连接（意思是连接池中最少会保持50个空闲连接）
        config.setTestOnBorrow(false);//为true 时，标识获取的每一个redis都是测试可用的
        jedisCluster = new JedisCluster(hostAndPorts, config);
    }

    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public String hmset(String key, Map<String, String> map) {
        return jedisCluster.hmset(key, map);
    }

    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    public Long lpush(String key, String... strings) {
        return jedisCluster.lpush(key, strings);
    }

    public List<String> lrange(String key, long start, long end) {
        return jedisCluster.lrange(key, start, end);
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
