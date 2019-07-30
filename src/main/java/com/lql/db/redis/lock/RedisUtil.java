//package com.lql.redis;
//
//import com.lql.util.JsonUtil;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Value;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//
//public class RedisUtil {
//
//    private static RedisUtil redisUtil;
//
//    private static Log log = LogFactory.getLog(RedisUtil.class);
//
//
//    @Value("${easipass.oauth.appCode:complex}")
//    private String app_Code;
//
//    private JedisCluster jedisCluster;
//
//    @Value("${spring.redis.cluster.nodes}")
//    private String redisNodes;
//
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private Integer maxActive;
//
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private Integer maxIdle;
//
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private Integer minIdle;
//
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private Integer maxWait;
//
//    @Value("${spring.redis.timeout}")
//    private Integer timeout;
//
////    @Resource(name = "tCrCommConfigService")
////    private TCrCommConfigService tCrCommConfigService;
//
//
//    public static void setEx(String key, String value, int second) {
//        redisUtil.jedisCluster.setex(redisUtil.app_Code + ":" + key, second, value);
//    }
//
//    public static void set(String key, String value) {
//        redisUtil.jedisCluster.set(redisUtil.app_Code + ":" + key, value);
//    }
//
//    public static void hmset(String key, Map map) {
//        redisUtil.jedisCluster.hmset(redisUtil.app_Code + ":" + key, map);
//    }
//
//    public static String get(String key) {
//        return redisUtil.jedisCluster.get(redisUtil.app_Code + ":" + key);
//    }
//
//    public static String hmget(String key, String field) {
//        List<String> list = redisUtil.jedisCluster.hmget(redisUtil.app_Code + ":" + key, field);
//        return list.size() > 0 ? list.get(0) : null;
//    }
//
//    public static Map<String, String> hgetAll(String key) {
//        return redisUtil.jedisCluster.hgetAll(redisUtil.app_Code + ":" + key);
//    }
//
//    public static void remove(String key) {
//        redisUtil.jedisCluster.del(redisUtil.app_Code + ":" + key);
//    }
//
//    public static void expireAt(String key, int second) {
//        redisUtil.jedisCluster.expireAt(key, second);
//    }
//
//
//    /**
//     * 初始化
//     */
//    public void init() {
//        try {
//            log.info("------------------redisNodes:" + redisNodes);
//            HashSet haps = new HashSet();
////            String redisIp = commConfigService.findValueByConfigName("RadisIp");
////            String[] ipArray = redisIp.split(",");
//            String[] ipArray = redisNodes.split(",");
//            for (String s : ipArray) {
//                String[] portArray = s.split(":");
//                HostAndPort hap = new HostAndPort(portArray[0], Integer.parseInt(portArray[1]));
//                haps.add(hap);
//            }
//            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//            genericObjectPoolConfig.setMaxIdle(maxIdle);
//            genericObjectPoolConfig.setMaxTotal(maxActive);
//            genericObjectPoolConfig.setMinIdle(minIdle);
//            genericObjectPoolConfig.setMaxWaitMillis(maxWait);
//            redisUtil = this;
//            redisUtil.jedisCluster = new JedisCluster(haps, timeout, 6, genericObjectPoolConfig);
//            log.info("redisUtil初始化...");
//
//            refreshCommconfigCache();
//        } catch (Exception e) {
//            log.error("---------redis初始化错误");
//            log.error(e.getMessage(), e);
//        }
//    }
//
//
//    public static String getConfig(String key) {
//        String value = hmget("CommConfigCache", key);
//        if (null == value) {
//            redisUtil.refreshCommconfigCache();
//            value = hmget("CommConfigCache", key);
//        }
//        return value;
//    }
//
//    public static String getDefaultConfig(String key, String defaultVale) {
//        String value = getConfig(key);
//        return null != value ? value : defaultVale;
//    }
//
//    public static Long getDefaultLongConfig(String key, Long defaultVale) {
//        String value = getConfig(key);
//        return null != value ? Long.valueOf(value) : defaultVale;
//    }
//
//
//    /**
//     * 常用参数缓存
//     */
//    public void refreshCommconfigCache() {
//        System.out.println("暂时没有缓存");
////        List<TCrCommConfigEntity> configList = tCrCommConfigService.loadConfig();
////        log.info("------------------refreshCommconfigCache:"+ JsonUtil.beanToJson(configList));
////        Map<String , String> map = new HashMap<>();
////        for (TCrCommConfigEntity config : configList) {
////            String value = config.getConfigValue()==null?"":config.getConfigValue();
////            map.put(config.getConfigKey(),value);
////        }
////        hmset("CommConfigCache",map);
//    }
//}
