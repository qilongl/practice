package com.lql.db.redis.service;

import com.lql.db.redis.BaseService;
import com.lql.util.ResponseResult;

import java.util.List;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/6/24 14:03
 **/
public interface DemoService {

    public ResponseResult getFunctionList();

    public ResponseResult refreshCache();
}
