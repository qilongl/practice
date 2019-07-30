//package com.lql.xml.XMLAnalyzer.service.actions;
//
//import com.lql.xml.XMLAnalyzer.beans.*;
//import com.lql.xml.XMLAnalyzer.util.TypeUtil;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//
//import javax.xml.namespace.QName;
//import java.io.Serializable;
//import java.util.*;
//
///**
// * Created by lql on 2017/5/25.
// */
//public class ServiceAction implements Serializable {
//    private BusiConfig busiConfig;
//
//    public ServiceAction(BusiConfig busiConfig) {
//        this.busiConfig = busiConfig;
//    }
//
//    /***
//     * WebService 命令执行
//     *
//     * @param cmd
//     * @param inParam
//     * @return
//     * @throws Exception
//     */
//    public Object serviceCommand(Action cmd, JSONObject inParam) throws Exception {
//        Object result = null;
//        try {
//            Document document = DocumentHelper.parseText(cmd.getContent());
//            Element rootElement = document.getRootElement();
//            String urlName = rootElement.attributeValue("url");
//            String methodName = rootElement.attributeValue("method");
//            Map includeMap = new HashMap();
//            List<Element> list = rootElement.elements();
//            for (int i = 0; i < list.size(); i++) {
//                Element element = list.get(i);
//                String inParamName = element.getName();// 入参名称
//                //TODO统一规定入参使用params属性下面的属性值
//                String valueName = element.attributeValue("value");// 引用的入参名称
//                includeMap.put(inParamName, valueName);
//            }
//            List<Map<String, Object>> cmdResultParam = null;
//            // 参数来源：入参,取出参数
//            if (busiConfig.getParametersMap().containsKey(cmd.getParams())) {
//                Parameters parametersModel = busiConfig.getParametersMap().get(cmd.getParams());
//                if (parametersModel.islist()) {// 批量调用
//                    JSONArray jsonArray = inParam.getJSONArray(cmd.getParams());
//                    for (int i = 0; i < jsonArray.size(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        result = takeService(jsonObject, parametersModel, urlName, methodName, includeMap);
//                    }
//                } else {//单次调用
//                    JSONObject jsonObject = inParam.getJSONObject(cmd.getParams());
//                    result = takeService(jsonObject, parametersModel, urlName, methodName, includeMap);
//                }
//            }
//            // 参数来源：命令结果集
//            else if (null != (cmdResultParam = TypeUtil.changeToListMap(busiConfig.getParamFromCmdResult(cmd.getParams())))) {
//                if (cmd.isList())// 批次调用
//                {
//                    for (int i = 0; i < cmdResultParam.size(); i++) {
//                        Map map = cmdResultParam.get(i);
//                        result = takeService(map, urlName, methodName, includeMap);
//                    }
//                } else// 单次调用
//                {
//                    Map map = cmdResultParam.get(0);
//                    result = takeService(map, urlName, methodName, includeMap);
//                }
//            } else {
//                throw new UnsupportedOperationException(cmd.getId() + "-" + cmd.getParams() + "参数未定义!");
//            }
//        } catch (Exception ex) {
//            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
//        }
//        return result;
//    }
//
//    /***
//     * 参数来源是结果集
//     *
//     * @param map
//     * @param urlName
//     * @param methodName
//     * @param includeMap
//     * @return
//     * @throws Exception
//     */
//    public Object takeService(Map map, String urlName, String methodName, Map<String, String> includeMap) throws Exception {
//        String urlValue = map.get(urlName).toString();
//        String methodValue = map.get(methodName).toString();
//        List<Map<String, String>> paramList = new ArrayList<>();
//        List<QName> typeList = new ArrayList<>();
//        Iterator it = includeMap.keySet().iterator();
//        while (it.hasNext()) {
//            String paramName = it.next().toString();
//            String includeName = includeMap.get(paramName).toString();
//            String paramValue = map.get(includeName).toString();
//            Map paramMap = new HashMap();
//            paramMap.put("paramName", paramName);
//            paramMap.put("paramValue", paramValue);
//            paramList.add(paramMap);
//            typeList.add(org.apache.axis.encoding.XMLType.XSD_STRING);
//        }
//        Object resultObj = WebServiceUtil.service(urlValue, methodValue, paramList);
////        Object resultObj = service(urlValue, methodValue, paramList, typeList, org.apache.axis.encoding.XMLType.XSD_STRING);
//        return resultObj;
//    }
//
//    /***
//     * 参数来源是参数集
//     *
//     * @param jsonObject
//     * @param parametersModel
//     * @param urlName
//     * @param methodName
//     * @param includeMap
//     * @return
//     * @throws Exception
//     */
//    public Object takeService(JSONObject jsonObject, Parameters parametersModel, String urlName, String methodName, Map<String, String> includeMap) throws Exception {
//        Parameter urlModel = parametersModel.getParameterMap().get(urlName);
//        Parameter methodModel = parametersModel.getParameterMap().get(methodName);
//        String urlValue = jsonObject.getString("url");
//        String methodValue = jsonObject.getString("method");
//        if (null == urlValue || "".equals(urlValue)) {
//            if (urlModel.isRequired())
//                throw new NullPointerException(urlModel.getName() + "是必填字段，不可以为空！");
//            urlValue = urlModel.getDefaultValue();
//        }
//        if (null == methodValue || "".equals(methodValue)) {
//            if (methodModel.isRequired())
//                throw new NullPointerException(methodModel.getName() + "是必填字段，不可以为空！");
//            methodValue = methodModel.getDefaultValue();
//        }
//        Iterator it = includeMap.keySet().iterator();
//        List<Map<String, String>> paramList = new ArrayList<>();
//        List<QName> typeList = new ArrayList<>();
//        while (it.hasNext()) {
//            String inName = it.next().toString();// service入参名称
//            String pName = includeMap.get(inName).toString();//引用的入参名称
//            Parameter pNameModel = parametersModel.getParameterMap().get(pName);
//            String pValue = jsonObject.getString(pName);
//            if (null == pValue || "".equals(pValue)) pValue = pNameModel.getDefaultValue();
//            Map map = new HashMap();
//            map.put("paramName", inName);
//            map.put("paramValue", pValue);
//            paramList.add(map);
//            typeList.add(org.apache.axis.encoding.XMLType.XSD_STRING);
//        }
//        Object resultObj = WebServiceUtil.service(urlValue, methodValue, paramList);
////        Object resultObj = service(urlValue, methodValue, paramList, typeList, org.apache.axis.encoding.XMLType.XSD_STRING);
//        return resultObj;
//    }
//}
