package com.lql.xml.db;

import com.lql.SpringbootApplication;
import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import com.lql.xml.beans.SysProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

/**
 * Created by lql on 2017/8/29.
 */
public class DPFactory {
    /***
     * 创建DPService,使用默认的数据源
     *
     * @return
     */
    public static DPService createDPService() {
        DataSource dataSource = (DataSource) SpringbootApplication.ctx.getBean(SysProperties.DEFAULT_DATASOURCE);
        DPService dpService = new DPService(dataSource);
        return dpService;
    }

    /***
     * 创建DPService,使用默认的数据源
     *
     * @return
     */
    public static DPService createDPService(ApplicationContext ctx) {
        DataSource dataSource = (DataSource) ctx.getBean(SysProperties.DEFAULT_DATASOURCE);
        DPService dpService = new DPService(dataSource);
        return dpService;
    }

    /***
     * 创建DPService,使用默认的容器，指定数据源
     *
     * @return
     */
    public static DPService createDPService(String dataSourceName) {
        DataSource dataSource = (DataSource) BusiConfigCache.ctx.getBean(dataSourceName);
        DPService dpService = new DPService(dataSource);
        return dpService;
    }

    /***
     * 使用指定的数据源
     *
     * @param dataSourceName 数据源名称
     * @return
     */
    public static DPService createDPService(ApplicationContext ctx, String dataSourceName) {
        DataSource dataSource = (DataSource) ctx.getBean(dataSourceName);
        DPService dpService = new DPService(dataSource);
        return dpService;
    }
}
