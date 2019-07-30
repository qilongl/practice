//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.cbdcloud.UtilCloudApplication;
//import com.cbdcloud.beans.ResponseResult;
//import com.cbdcloud.tools.XMLAnalyzer.beans.BusiConfig;
//import com.cbdcloud.tools.XMLAnalyzer.service.impl.BusiConfigCache;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by fu on 2017/12/27.
// * 对需要重新发起流程的数据对象进行批量重新发起
// */
//public class ScheduleStartWorkflow {
//
//
//    private final static String ISSUBMIT = "isSubmit";
//    private static Logger logger = LoggerFactory.getLogger(ScheduleStartWorkflow.class);
//
//    public List<Map<String, Object>> build(List<Map<String, Object>> schedulevar, List<Map<String, Object>> userInfo) throws Exception {
//
//        //重新发起流程
//        List<Map<String, Object>> rs = new ArrayList<>();
//        ArrayList<Map<String, String>> instancelist = new ArrayList<>();
//        String userId = (String) userInfo.get(0).get("userId");
//        String userName = (String) userInfo.get(0).get("userName");
//
//        for (int i = 0; i < schedulevar.size(); i++) {
//            Map<String, Object> map = schedulevar.get(i);
//            String key = (String) map.get(ISSUBMIT);
//            if ("1".equals(key)) {
//                String instanceId = (String) map.get("instanceId");
//                String objId = (String) map.get("id");
//                HashMap<String, String> resubmap = new HashMap<>();
//                resubmap.put("instanceId", instanceId);
//                resubmap.put("userId", userId);
//                resubmap.put("userName", userName);
//                resubmap.put("objId", objId);
//                instancelist.add(resubmap);
//            }
//        }
//        if (instancelist.size() != 0) {
//            RestTemplate restTemplate = (RestTemplate) BusiConfigCache.ctx.getBean("restTemplate");
//            for (int j = 0; j < instancelist.size(); j++) {
//                MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//                param.add("instanceId", instancelist.get(j).get("instanceId"));
//                param.add("userId", instancelist.get(j).get("userId"));
//                param.add("userName", instancelist.get(j).get("userName"));
//                param.add("objId", instancelist.get(j).get("objId"));
//                param.add("jsonParams", "");
//                String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/reSubmit", param, String.class);
//                ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {});
//                HashMap hashMap = new HashMap();
//                hashMap.put("result", rr);
//                rs.add(hashMap);
//            }
//
//        }
//        return rs;
//    }
//
//}
