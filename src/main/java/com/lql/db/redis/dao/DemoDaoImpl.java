package com.lql.db.redis.dao;

import com.lql.db.redis.BaseDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/6/24 16:05
 **/
@Repository("demoDao")
@Component
public class DemoDaoImpl extends BaseDao implements DemoDao {

    @Override
    public List<Map<String, Object>> getConfigList() {
        String sql = "select * from sys_config";
        return baseSql(sql);
    }

    @Override
    public List<Map<String, Object>> getFunctionList() {
        String sql = "select * from sys_function";
        return baseSql(sql);
    }
}
