package com.lql.xml.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carrotsearch.sizeof.RamUsageEstimator;
import com.lql.xml.beans.UserDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lql on 2018/10/10 15:34
 **/
@RestController
@CrossOrigin
public class test {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("1",123);
        map.put("2",123);
        map.put("3",123);
        map.put("4",123);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        System.out.println(jsonObject);
    }
}
