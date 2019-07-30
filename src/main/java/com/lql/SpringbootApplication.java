package com.lql;

//import com.lql.redis.RedisUtil;

import com.lql.db.redis.service.DemoService;
import com.lql.db.redis.util.RedisClusterUtil;
import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import com.lql.xml.beans.SysProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling//开启定时任务
public class SpringbootApplication {
    public static ApplicationContext ctx;
    private static Logger logger = Logger.getLogger(SpringbootApplication.class);

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(initMethod = "init")
    public RedisClusterUtil redisClusterUtil() {
        return new RedisClusterUtil();
    }


    public static ApplicationContext start() throws Exception {
        ctx = SpringApplication.run(SpringbootApplication.class);
        /**
         * 加载配置文件
         */
        SysProperties.init();
        /**
         * 初始化业务组件
         */
        BusiConfigCache busiConfigCache = (BusiConfigCache) ctx.getBean("busiConfigCache");
        busiConfigCache.init(ctx);

        /**
         * 初始化缓存
         */
        DemoService demoService = (DemoService) ctx.getBean("demoService");
        demoService.refreshCache();

        logger.info("============================ServiceCentreApplication 启动完成===================================");
        return ctx;
    }

    public static void main(String[] args) throws Exception {
        start();
    }
}
