package com.lql.springboot.dbutils.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.lql.SpringbootApplication;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author lql
 * @Date 2017/12/15
 */
@Service
public class DataSourceUtil {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceUtil.class);

    ApplicationContext ctx;

    private static final String DEFAULT_DATASOURCE = "dataSource";

    public static final String dbConfig = "db.properties";
    private static String dataSource_URL = "datasource.url";
    private static String dataSource_USERNAME = "datasource.username";
    private static String dataSource_PASSWORD = "datasource.password";
    private static String dataSource_DRIVER_CLASS_NAME = "datasource.driver-class-name";
    private static String dataSource_TEST_SQL = "datasource.testsql";


    @Bean(name = "dataSource")
    @Primary
    public DruidDataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        Configuration configuration = new PropertiesConfiguration(dbConfig);
        dataSource.setUrl(configuration.getString(dataSource_URL));
        dataSource.setUsername(configuration.getString(dataSource_USERNAME));//用户名
        dataSource.setPassword(configuration.getString(dataSource_PASSWORD));//密码
        dataSource.setDriverClassName(configuration.getString(dataSource_DRIVER_CLASS_NAME));
        dataSource.setInitialSize(10);//初始化时建立物理连接的个数
        dataSource.setMaxActive(50);//最大连接数
        dataSource.setMinIdle(5);
        dataSource.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒
        //是否启用PSCache,对游标的效率提升大
        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(100);
        //检查的语句
        dataSource.setValidationQuery(configuration.getString(dataSource_TEST_SQL));
        //申请时是否检查连接有效
        dataSource.setTestOnBorrow(false);
        //归还时是否检查连接有效
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        //连接池中最小的连接数小于minIdle时执行keepAlive操作,长连接
        dataSource.setKeepAlive(false);
        //连接闲置时间超过该值,执行有效性检查和关闭物理连接
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(300000);
        try {
            //开启Druid的监控统计功能，mergeStat代替stat表示sql合并,wall表示防御SQL注入攻击
//            dataSource.setFilters("mergeStat,wall,log4j");
            dataSource.setFilters("mergeStat,slf4j");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setName("dataSource");
        return dataSource;
    }

    /**
     * druid监控
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams

        //白名单 (没有配置或者为空，则允许所有访问)
//		servletRegistrationBean.addInitParameter("allow","192.168.1.88,127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
//		servletRegistrationBean.addInitParameter("deny","192.168.1.80");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据(禁用HTML页面上的“Reset All”功能)
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;
    }

    /**
     * 从容器中获取默认的数据源
     * @param ctx
     * @return
     */
    public static DruidDataSource getDataSource(ApplicationContext ctx) {
        DataSource dataSource1 = (DataSource) ctx.getBean(DEFAULT_DATASOURCE);
        return (DruidDataSource) dataSource1;
    }

    /**
     * 获取JdbcTemplate
     * @return
     */
    public static JdbcTemplate getJdbcTemplate() {
        DataSource dataSource = DataSourceUtil.getDataSource(SpringbootApplication.ctx);
        return new JdbcTemplate(dataSource);
    }

}
