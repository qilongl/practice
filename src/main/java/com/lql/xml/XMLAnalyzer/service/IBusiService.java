package com.lql.xml.XMLAnalyzer.service;

import com.lql.xml.XMLAnalyzer.service.impl.BusiService;
import com.lql.util.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/5/25.
 */
@FeignClient(value = "service-expand", fallback = BusiService.class)
public interface IBusiService {
    @RequestMapping(value = "/extandService", method = {RequestMethod.GET, RequestMethod.POST})
    ResponseResult exec(String jsonparam, Map<String, List<Map<String, byte[]>>> fileMap);
}
