package com.lql.db.redis;

import com.lql.xml.db.DPFactory;
import com.lql.xml.db.DPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/6/24 16:06
 **/
public class BaseDao {
    public final Logger log = LoggerFactory.getLogger(this.getClass());

    protected List<Map<String, Object>> baseSql(String sql) {
        DPService dpService = DPFactory.createDPService();
        return dpService.select(sql);
    }
}
