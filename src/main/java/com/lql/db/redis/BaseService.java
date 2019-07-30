package com.lql.db.redis;

import com.lql.db.redis.dao.DemoDao;
import com.lql.db.redis.util.RedisClusterUtil;
import com.lql.db.redis.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by StrangeDragon on 2019/6/24 14:08
 **/
public class BaseService {
    public final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    public RedisClusterUtil redisClusterUtil;

    @Autowired
    public DemoDao demoDao;
}
