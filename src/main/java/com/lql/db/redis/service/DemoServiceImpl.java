package com.lql.db.redis.service;

import com.lql.db.redis.BaseService;
import com.lql.util.JsonUtil;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/6/24 14:05
 **/
@Service("demoService")
public class DemoServiceImpl extends BaseService implements DemoService {

    @Override
    public ResponseResult getFunctionList() {
        ResponseResult rr = new ResponseResult();
        try {
            //1、先从缓存中获取，如果缓存中存在则将结果集处理成List<Map>   (   List<String>----->List<Map>   )
            List<Map<String, Object>> result = redisClusterUtil.lrange2ListMap("sys_function", 0, 1000);
            //2、缓存中不存在则从数据库查询后返还，同时放入缓存中
            if (0 == result.size()) {
                List<Map<String, Object>> dbResult = demoDao.getFunctionList();
                String[] args = new String[dbResult.size()];
                for (int i = 0; i < dbResult.size(); i++) {
                    args[i] = JsonUtil.beanToJson(dbResult.get(i));
                }
//                log.info("sys_function 更新至缓存：{}", redisUtil.lpush("sys_function", args));
//                result = redisUtil.lrange2ListMap("sys_function", 0, 100000);
                log.info("sys_function 更新至缓存：{}", redisClusterUtil.lpush("sys_function", args));
                result = redisClusterUtil.lrange2ListMap("sys_function", 0, 100000);
            }
            rr.setResult(result);
            rr.setMsg("业务执行成功!");
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            rr.setMsg("业务执行失败! " + e.getMessage());
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
        }
        return rr;
    }

    @Override
    public ResponseResult refreshCache() {
        ResponseResult rr = new ResponseResult();
        try {
            List<Map<String, Object>> list = demoDao.getConfigList();
            Map<String, String> configMap = new HashMap<>();
            for (Map map : list) {
                String name = StringHelper.toString(map.get("NAME"));
                String value = StringHelper.toString(map.get("VALUE"));
                configMap.put(name, value);
            }
            log.info("配置数据：" + JsonUtil.beanToJson(configMap));
//            log.info("redis初始化缓存：" + redisUtil.hmset("configCache", configMap));
            log.info("redis初始化缓存：" + redisClusterUtil.hmset("configCache", configMap));
            rr.setMsg("缓存刷新成功!");
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("redis初始化缓存失败：{}", e.getMessage());
            rr.setMsg("缓存刷新失败! " + e.getMessage());
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
        }
        return rr;
    }

}
