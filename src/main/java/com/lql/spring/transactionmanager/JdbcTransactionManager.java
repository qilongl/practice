package com.lql.spring.transactionmanager;

import com.alibaba.druid.pool.DruidDataSource;
import com.lql.util.PropertiesUtil;
import lombok.Data;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/7/29 10:42
 * jdbc 事务管理器
 **/
@Data
public class JdbcTransactionManager {
    public static final String DEFAULT_DATASOURCE = "dataSource";// 系统资源默认使用的数据源
    public static final String dbConfig = "db.properties";

    private static final String dataSource_URL = "datasource.url";
    private static final String dataSource_USERNAME = "datasource.username";
    private static final String dataSource_PASSWORD = "datasource.password";
    private static final String dataSource_DRIVER_CLASS_NAME = "datasource.driver-class-name";
    private static final String dataSource_TEST_SQL = "datasource.testsql";

    private static Logger logger = LoggerFactory.getLogger(JdbcTransactionManager.class);

    private static DataSource dataSource;//这里为了方便单元测试，保证数据源单例
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    private DataSourceTransactionManager transactionManager;
    private DefaultTransactionDefinition definition;
    private TransactionStatus transactionStatus;

    private boolean isStartTransAction = false;


    public JdbcTransactionManager() throws Exception {
        if (null == dataSource) {
            this.dataSource = dataSource();
        }
    }

    public JdbcTransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int update(String sql) {
        if (null == jdbcTemplate.getDataSource())
            jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate.update(sql);
    }

    public List<Map<String, Object>> select(String sql) {
        if (null == jdbcTemplate.getDataSource())
            jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate.queryForList(sql);
    }


    /**
     * 开启一个事务
     *
     * @throws Exception
     */
    public void startTransaction() throws Exception {
        transactionManager = new DataSourceTransactionManager(dataSource);
        definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);//传播行为：嵌套事务
        transactionStatus = transactionManager.getTransaction(definition);
        isStartTransAction = true;
        logger.info("===事务开启！");
    }

    /**
     * 事务提交
     */
    public void commit() {
        if (isStartTransAction) {
            if (!transactionStatus.isCompleted() && !transactionStatus.isRollbackOnly()) {
                transactionManager.commit(transactionStatus);
                logger.info("===事务提交！");
            } else {
                String logInfo = "错误的使用commit,事务是否完成的状态:" + transactionStatus.isCompleted() + ",事务是否已经被标记回滚:" + transactionStatus.isRollbackOnly();
                logger.error(logInfo);
                throw new UnsupportedOperationException(logInfo);
            }
            destory();
        }
    }

    /**
     * 事务回滚
     */
    public void rollback() {
        if (isStartTransAction) {
            if (!transactionStatus.isCompleted() && !transactionStatus.isRollbackOnly()) {
                logger.info("事务回滚!");
                transactionManager.rollback(transactionStatus);
            } else {
                logger.info("错误的使用rollback,事务完成状态:" + transactionStatus.isCompleted() + ",事务是否已被标记回滚:" + transactionStatus.isRollbackOnly());
            }
            destory();
        }
    }

    /**
     * 释放资源
     */
    public void destory() {
        isStartTransAction = false;
        transactionManager = null;
        definition = null;
        transactionStatus = null;
        jdbcTemplate = null;
    }

    public static DruidDataSource dataSource() throws Exception {
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
