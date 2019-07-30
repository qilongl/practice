package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.util.ExpressionUtil;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lql on 2017/10/11.
 * if 条件判断语句
 */
public class IfAction implements Serializable {
    private BusiConfig busiConfig;

    public IfAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    public boolean ifCommand(Action cmd, JSONObject inParams) throws Exception {
        try {
            String expression = cmd.getTest();
            boolean flag = ExpressionUtil.calExpression(expression, busiConfig, inParams, null);
            //表达式成立
            if (flag) {
                String cmdStr = cmd.getContent();
                Document document = DocumentHelper.parseText(cmdStr);
                Element rootElement = document.getRootElement();
                List<Element> elementList = rootElement.elements();
                int index = busiConfig.getCmdList().indexOf(cmd);
                busiConfig.getCmdList().remove(index);
                for (int i = 0; i < elementList.size(); i++) {
                    Element element = elementList.get(i);
                    busiConfig.getCmdList().add(index + i, new Action(element));
                }
                if (elementList.size() > 0)
                    return true;
            }
        } catch (Exception ex) {
            throw new UserException(cmd.getId(),cmd.getErrorid(), "", ex);
        }
        return false;
    }

    public static void main(String args[]) {
        List<Object> list = new ArrayList<>();
        int[] a = {1};
        int[] b = {2};
        int[] c = {3};
        int[] d = {4};
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        int[] x = {0};
        System.out.println(list.indexOf(b));
        list.add(1, x);
        System.out.println(list.size());
    }
}
