package com.lql.xml.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lql.util.CheckTokenUtil;
import com.lql.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lql on 2017/8/4.
 * Update by lql on 2017/11/03.
 * Update by lql on 2017/11/16.
 * <p>
 * token 和 权限 服务
 */
@Service
public class CentreService {
    @Autowired
    RestTemplate restTemplate;
    private static Logger logger = LoggerFactory.getLogger(CentreService.class);

    /**
     * 工作流
     */
//    public ResponseResult getMyWorkflow(String userId, String requestName, String categoryId, String startTime, String endTime, String wfTypeId,
//                                        String status, String prop, String order, String pageNum, String pageSize) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("requestName", requestName);
//        param.add("categoryId", categoryId);
//        param.add("startTime", startTime);
//        param.add("endTime", endTime);
//        param.add("wfTypeId", wfTypeId);
//        param.add("status", status);
//        param.add("prop", prop);
//        param.add("order", order);
//        param.add("pageSize", pageSize);
//        param.add("pageNum", pageNum);
//        String result = restTemplate.postForObject("http://service-centre/workflow/node/request/getMyWorkflow", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    /***
//     * 待办列表
//     *
//     * @param userId
//     * @return
//     */
//    public ResponseResult pendingList(String userId, String workFlowId, String crtPsn, String startTime, String endTime, String requestName, String nodeName,
//                                      String prop, String order,
//                                      String pageSize, String pageNum) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("workFlowId", workFlowId);
//        param.add("crtPsn", crtPsn);
//        param.add("startTime", startTime);
//        param.add("endTime", endTime);
//        param.add("requestName", requestName);
//        param.add("prop", prop);
//        param.add("order", order);
//        param.add("pageSize", pageSize);
//        param.add("pageNum", pageNum);
//        param.add("nodeName", nodeName);
//        String result = restTemplate.postForObject("http://service-centre/workflow/node/request/pendingList", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult checkedList(String userId, String workFlowId, String crtPsn, String startTime, String endTime, String requestName, String prop, String order, String pageSize, String pageNum) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("workFlowId", workFlowId);
//        param.add("crtPsn", crtPsn);
//        param.add("startTime", startTime);
//        param.add("endTime", endTime);
//        param.add("requestName", requestName);
//        param.add("prop", prop);
//        param.add("order", order);
//        param.add("pageSize", pageSize);
//        param.add("pageNum", pageNum);
//        String result = restTemplate.postForObject("http://service-centre/workflow/node/request/checkedList", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult readList(String userId, String workFlowId, String crtPsn, String startTime, String endTime, String requestName,
//                                   String prop, String order, String pageSize, String pageNum) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("workFlowId", workFlowId);
//        param.add("crtPsn", crtPsn);
//        param.add("startTime", startTime);
//        param.add("endTime", endTime);
//        param.add("requestName", requestName);
//        param.add("prop", prop);
//        param.add("order", order);
//        param.add("pageSize", pageSize);
//        param.add("pageNum", pageNum);
//        String result = restTemplate.postForObject("http://service-centre/workflow/node/request/readList", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult toReadList(String userId, String workFlowId, String crtPsn, String startTime, String endTime, String requestName, String prop, String order, String pageSize, String pageNum) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("workFlowId", workFlowId);
//        param.add("crtPsn", crtPsn);
//        param.add("startTime", startTime);
//        param.add("endTime", endTime);
//        param.add("requestName", requestName);
//        param.add("prop", prop);
//        param.add("order", order);
//        param.add("pageSize", pageSize);
//        param.add("pageNum", pageNum);
//        String result = restTemplate.postForObject("http://service-centre/workflow/node/request/toReadList", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult uptToReaded(String userId, String taskId, String userName) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("taskId", taskId);
//        param.add("userName", userName);
//        String result = restTemplate.postForObject("http://service-centre/workflow/node/request/uptToReaded", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult getInstanceCheckLog(String instanceId, String notifyIsOpen) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("instanceId", instanceId);
//        param.add("notifyIsOpen", notifyIsOpen);
//        String result = restTemplate.postForObject("http://service-centre/workflow/node/request/getInstanceCheckLog", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult getInstanceListCheckLog(String instanceIdList) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("instanceIdList", instanceIdList);
//        String result = restTemplate.postForObject("http://service-centre/workflow/node/request/getInstanceListCheckLog", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult findBntGroup(String taskId, String userId, String instanceId) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("taskId", taskId);
//        param.add("userId", userId);
//        param.add("instanceId", instanceId);
//        String result = restTemplate.postForObject("http://service-centre/workflow/workflow/request/restFindBntGroup", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult submit(String fkWorkflowId, String requestName, String crtType, String objId, String crtPsn, String crtPsnName, String jsonParams) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("fkWorkflowId", fkWorkflowId);
//        param.add("requestName", requestName);
//        param.add("crtType", crtType);
//        param.add("objId", objId);
//        param.add("crtPsn", crtPsn);
//        param.add("crtPsnName", crtPsnName);
//        param.add("jsonParams", jsonParams);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/submit", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult reSubmit(String instanceId, String userId, String userName, String jsonParams, String objId) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("instanceId", instanceId);
//        param.add("userId", userId);
//        param.add("userName", userName);
//        param.add("objId", objId);
//        param.add("jsonParams", jsonParams);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/reSubmit", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult submitList(String wfListStr) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("wfListJson", wfListStr);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/submitList", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult reject(String objId, String userId, String userName, String taskId, String remark, String jsonParams) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("objId", objId);
//        param.add("userId", userId);
//        param.add("userName", userName);
//        param.add("taskId", taskId);
//        param.add("remark", remark);
//        param.add("jsonParams", jsonParams);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/reject", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult rejectList(String jsonStr) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("jsonStr", jsonStr);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/rejectList", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult save(String fkWorkflowId, String requestName, String crtType, String crtPsn, String crtPsnName, String jsonParams) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("fkWorkflowId", fkWorkflowId);
//        param.add("requestName", requestName);
//        param.add("crtType", crtType);
//        param.add("crtPsn", crtPsn);
//        param.add("crtPsnName", crtPsnName);
//        param.add("jsonParams", jsonParams);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/save", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult findAllWfType() {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        String result = restTemplate.postForObject("http://service-centre/workflow/workflow/request/findAllWfType", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult pass(String taskId, String userId, String userName, String objId, String remark, String jsonParams) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("taskId", taskId);
//        param.add("userId", userId);
//        param.add("userName", userName);
//        param.add("objId", objId);
//        param.add("remark", remark);
//        param.add("jsonParams", jsonParams);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/pass", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult takeback(String userId, String instanceId, String userName, String remark, String jsonParams) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("instanceId", instanceId);
//        param.add("userId", userId);
//        param.add("userName", userName);
//        param.add("remark", remark);
//        param.add("jsonParams", jsonParams);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/takeback", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult passList(String jsonStr) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("jsonStr", jsonStr);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/passList", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult realTimeWorkFlow(String requestName, String crtPsn, String crtBeginTime, String crtEndTime, String approver, String curtStatus, String isCanceled, String fkWorkFlowId, String prop, String order, String pageSize, String pageNum) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("requestName", requestName);
//        param.add("crtPsn", crtPsn);
//        param.add("crtBeginTime", crtBeginTime);
//        param.add("crtEndTime", crtEndTime);
//        param.add("approver", approver);
//        param.add("curtStatus", curtStatus);
//        param.add("isCanceled", isCanceled);
//        param.add("fkWorkFlowId", fkWorkFlowId);
//        param.add("prop", prop);
//        param.add("order", order);
//        param.add("pageSize", pageSize);
//        param.add("pageNum", pageNum);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/realTimeWorkFlow", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult cancelInstance(String userId, String userName, String instanceId) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("userName", userName);
//        param.add("instanceId", instanceId);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/cancelInstance", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//    public ResponseResult cancelInstanceForAdmin(String userId, String userName, String instanceId) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("userName", userName);
//        param.add("instanceId", instanceId);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/cancelInstanceForAdmin", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//    public ResponseResult suspendInstance(String taskId, String userId, String userName, String remark, String jsonParams) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("taskId", taskId);
//        param.add("userName", userName);
//        param.add("remark", remark);
//        param.add("jsonParams", jsonParams);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/suspend", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult suspendInstanceForAdmin(String instanceId, String userId, String userName, String remark) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("instanceId", instanceId);
//        param.add("userName", userName);
//        param.add("remark", remark);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/suspendForAdmin", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult redirectToNode(String userId, String objId, String userName, String instanceId, String nowNodeId, String targetNodeId, String jsonParams) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("objId", objId);
//        param.add("userName", userName);
//        param.add("instanceId", instanceId);
//        param.add("nowNodeId", nowNodeId);
//        param.add("targetNodeId", targetNodeId);
//        param.add("jsonParams", jsonParams);
//        String result = restTemplate.postForObject("http://service-centre/workflow/instance/request/redirectToNode", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }
//
//    public ResponseResult secondNodeSubmited(String userId, String instanceId) {
//        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//        param.add("userId", userId);
//        param.add("instanceId", instanceId);
//        String result = restTemplate.postForObject("http://service-centre/workflow/request/isSecondNodeSubmited", param, String.class);
//        ResponseResult rr = JSON.parseObject(result, new TypeReference<ResponseResult>() {
//        });
//        return rr;
//    }

    /***
     * token 登录
     *
     * @param token
     * @return 根据返回的数据, 构建好 ResponseResult
     * @throws Exception
     */
    public ResponseResult verifyToken(String token) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("token", token);
        String result = restTemplate.postForObject("http://service-centre/verify", param, String.class);
        ResponseResult responseResult = JSON.parseObject(result, new TypeReference<ResponseResult>() {
        });
        return responseResult;
    }

    /***
     * 用户名和密码登录
     *
     * @param account
     * @param password
     * @return 根据返回的数据, 构建好 ResponseResult
     * @throws Exception
     */
    public ResponseResult loginByAccount(String account, String password, String termId, String termType) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("account", account);
        param.add("password", password);
        param.add("termId", termId);
        param.add("termType", termType);
        String result = restTemplate.postForObject("http://service-centre/loginByAccount", param, String.class);
        ResponseResult responseResult = JSON.parseObject(result, new TypeReference<ResponseResult>() {
        });
        return responseResult;
    }

    //token入参安全校验

    /**
     *  urlParamToken 不为空时校验get请求
     *  jsonParamToken 不为空时校验post请求
     * @param urlParamToken
     * @param jsonParamToken
     * @return
     */
    public ResponseResult checkTokenInfoByAccount(String urlParamToken, String jsonParamToken) {
        String dataString = "";
        JSONObject dataObject = null;
        JSONObject tokenObject = null;
        if (!urlParamToken.equals("")) {
            String[] tokenParamsArray = urlParamToken.split("&&");
            dataString = tokenParamsArray[0];
            String tokenUrl = tokenParamsArray[1];
            String[] tokenArray = tokenUrl.split("_");
            tokenObject = new JSONObject();
            tokenObject.put("account", tokenArray[0]);
            tokenObject.put("datetime", tokenArray[1]);
            tokenObject.put("ciphertext", tokenArray[2]);
        } else {
            // 解析出data
            JSONObject jsonParamObject = JSONObject.parseObject(jsonParamToken);
            dataObject = jsonParamObject.getJSONObject("data");
            tokenObject = jsonParamObject.getJSONObject("token");
        }

        //请求后台存储的账户token信息
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("account", tokenObject.get("account"));
        String result = restTemplate.postForObject("http://localhost:8888/getTokenInfoByAccount", param, String.class);
        JSONObject dbInfoObject = JSONObject.parseObject(result);
        CheckTokenUtil checkTokenUtil = new CheckTokenUtil();
        ResponseResult checkTokenResult = checkTokenUtil.checkToken(dataString, dataObject, tokenObject, dbInfoObject);
        return checkTokenResult;
    }
}
