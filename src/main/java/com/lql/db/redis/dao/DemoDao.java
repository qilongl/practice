package com.lql.db.redis.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/6/24 16:04
 **/
public interface DemoDao {
    List<Map<String, Object>> getConfigList();
    List<Map<String, Object>> getFunctionList();
}
