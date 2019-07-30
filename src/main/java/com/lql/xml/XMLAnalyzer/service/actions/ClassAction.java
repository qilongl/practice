package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/7/13.
 */
public class ClassAction implements Serializable {
    private BusiConfig busiConfig;

    public ClassAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    /****
     * 导入类的命令执行
     *
     * @return
     */
    public Object toolClassCommand(Action cmd, JSONObject inParams) throws Exception {
        Object result = null;
        try {
            String content = cmd.getContent();
            Document document = DocumentHelper.parseText(content);
            Element rootElement = document.getRootElement();
            List<String> methodParNameList = new ArrayList<>();// 方法参数名
            List<Object> inParNameList = new ArrayList<>();    // 方法实际值
            List<Element> elementList = rootElement.elements();
            for (int i = 0; i < elementList.size(); i++) {
                Element element = elementList.get(i);
                String methodParmeterName = element.getName();
                String inParamName = element.attributeValue("params");// 参数名称
                Object inparameter = busiConfig.getParamFromInParams(inParamName, inParams);
                if (null == inparameter)
                    inparameter = busiConfig.getParamFromCmdResult(inParamName);
                if (null == inparameter)
                    throw new UnsupportedOperationException("您配置的入参未定义！");
                methodParNameList.add(methodParmeterName);
                inParNameList.add(inparameter);
            }
            Class<?> cls = Class.forName(cmd.getClasspath());//加载指定类，注意一定要带上类的包名
            Object obj = cls.newInstance();//初始化一个实例
            Method method = null;
            //根据入参的个数分别反射
            switch (methodParNameList.size()) {
                case 0:
                    method = cls.getMethod(cmd.getMethod());
                    result = method.invoke(obj);
                    break;
                case 1:
                    method = cls.getMethod(cmd.getMethod(), List.class);
                    result = method.invoke(obj, inParNameList.get(0));
                    break;
                case 2:
                    method = cls.getMethod(cmd.getMethod(), List.class, List.class);
                    result = method.invoke(obj, inParNameList.get(0), inParNameList.get(1));
                    break;
                case 3:
                    method = cls.getMethod(cmd.getMethod(), List.class, List.class, List.class);
                    result = method.invoke(obj, inParNameList.get(0), inParNameList.get(1), inParNameList.get(2));
                    break;
                case 4:
                    method = cls.getMethod(cmd.getMethod(), List.class, List.class, List.class, List.class);
                    result = method.invoke(obj, inParNameList.get(0), inParNameList.get(1), inParNameList.get(2), inParNameList.get(3));
                    break;
                case 5:
                    method = cls.getMethod(cmd.getMethod(), List.class, List.class, List.class, List.class, List.class);
                    result = method.invoke(obj, inParNameList.get(0), inParNameList.get(1), inParNameList.get(2), inParNameList.get(3), inParNameList.get(4));
                    break;
                case 6:
                    method = cls.getMethod(cmd.getMethod(), List.class, List.class, List.class, List.class, List.class, List.class);
                    result = method.invoke(obj, inParNameList.get(0), inParNameList.get(1), inParNameList.get(2), inParNameList.get(3), inParNameList.get(4), inParNameList.get(5));
                    break;
                case 7:
                    method = cls.getMethod(cmd.getMethod(), List.class, List.class, List.class, List.class, List.class, List.class, List.class);
                    result = method.invoke(obj, inParNameList.get(0), inParNameList.get(1), inParNameList.get(2), inParNameList.get(3), inParNameList.get(4), inParNameList.get(5), inParNameList.get(6));
                    break;
                default:
                    throw new UnsupportedOperationException("暂时不支超过7个参数的入参方法！");
            }
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
        }
        return result;
    }

    public static void main(String args[]) {
        List<Map<String, Object>> list = new ArrayList<>();
        Object object = list;
        System.out.println(object instanceof List);
    }

}
