package com.lql.spring.transactionmanager;

import com.alibaba.druid.pool.DruidDataSource;
import com.lql.util.PropertiesUtil;
import org.apache.commons.configuration.Configuration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/7/29 10:43
 * 测试：从配置类中加载bean到容器，从容器获取加载的bean
 **/
@org.springframework.context.annotation.Configuration
public class DataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    public static final String DEFAULT_DATASOURCE = "dataSource";// 系统资源默认使用的数据源
    public static final String dbConfig = "db.properties";

    private final String dataSource_URL = "datasource.url";
    private final String dataSource_USERNAME = "datasource.username";
    private final String dataSource_PASSWORD = "datasource.password";
    private final String dataSource_DRIVER_CLASS_NAME = "datasource.driver-class-name";
    private final String dataSource_TEST_SQL = "datasource.testsql";


    /**
     * 从容器中加载
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select 1 as num from dual");
        System.out.println(result);
    }


    /**
     * 注入数据源连接池
     *
     * @return
     */
    @Bean(name = DEFAULT_DATASOURCE)
    @Primary
    public DruidDataSource dataSource() throws Exception {
        logger.info("============================" + DEFAULT_DATASOURCE + "初始化===================================");
        DruidDataSource dataSource = new DruidDataSource();
        Configuration configuration = PropertiesUtil.getDirConfig(dbConfig);
        dataSource.setUrl(configuration.getString(dataSource_URL));
        dataSource.setUsername(configuration.getString(dataSource_USERNAME));//用户名
        dataSource.setPassword(configuration.getString(dataSource_PASSWORD));//密码
        dataSource.setDriverClassName(configuration.getString(dataSource_DRIVER_CLASS_NAME));
        dataSource.setInitialSize(30);
        dataSource.setMaxActive(500);
        dataSource.setMinIdle(5);
        dataSource.setMaxWait(60000);
        //是否启用PSCache,对游标的效率提升大
        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(100);
        //检查的语句
        dataSource.setValidationQuery(configuration.getString(dataSource_TEST_SQL));
        //申请时是否检查连接有效
        dataSource.setTestOnBorrow(true);
        //归还时是否检查连接有效
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        //连接池中最小的连接数小于minIdle时执行keepAlive操作,长连接
        dataSource.setKeepAlive(false);
        //连接闲置时间超过该值,执行有效性检查和关闭物理连接
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setRemoveAbandoned(true);

        //回收连接的超时时间30分钟
        dataSource.setRemoveAbandonedTimeout(1800);
        //执行查询的超时时间5分钟
        dataSource.setQueryTimeout(60 * 5);
        //执行事务的超时时间20分钟
        dataSource.setTransactionQueryTimeout(60 * 20);
        try {
            //开启Druid的监控统计功能，mergeStat代替stat表示sql合并,wall表示防御SQL注入攻击
//            dataSource.setFilters("mergeStat,wall,log4j");
//            dataSource.setFilters("mergeStat,log4j2");//SpringBoot2.x 版本
            dataSource.setFilters("mergeStat,log4j");//SpringBoot1.x 版本
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setName(DEFAULT_DATASOURCE);
        return dataSource;
    }
}
