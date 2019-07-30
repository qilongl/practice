package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lql on 2017/10/16.
 */
public class RestAction implements Serializable {
    private BusiConfig busiConfig;

    public RestAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    /***
     * 发送rest请求
     *
     * @param cmd
     * @return
     * @throws Exception
     */
    public String restCommand(Action cmd) throws Exception {
        String rs;
        try {
            Document document = DocumentHelper.parseText(cmd.getContent());
            Element rootElement = document.getRootElement();
            String urlName = rootElement.attributeValue("url");
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            List<Element> list = rootElement.elements();
            for (int i = 0; i < list.size(); i++) {
                Element element = list.get(i);
                String inParamName = element.getName();//入参名称
                String valueName = element.attributeValue("value");//引用的入参名称
                param.add(inParamName, busiConfig.getValue(cmd, valueName));
            }
            /**
             * 从spring中获取restTemplate
             */
            RestTemplate restTemplate = (RestTemplate) BusiConfigCache.ctx.getBean("restTemplate");
            rs = restTemplate.postForObject(urlName, param, String.class);
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
        }
        return rs;
    }
}
