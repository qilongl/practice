package com.lql.spring.ioc.diyioc;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/7/10 17:09
 * 模仿ioc容器
 **/
public class SimpleIoc {
    /**
     * bean容器
     */
    private Map<String, Object> beansMap = new HashMap<>();


    public SimpleIoc(String location) throws Exception {
        loadBeans(location);
    }

    /**
     * 根据bean配置文件加载bean
     */
    private void loadBeans(String location) throws Exception {
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");
                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                Object object = beanClass.newInstance();
                NodeList propertyNodes = element.getElementsByTagName("property");
                for (int j = 0; j < propertyNodes.getLength(); j++) {
                    Element propertyNode = (Element) propertyNodes.item(j);
                    String name = propertyNode.getAttribute("name");
                    String value = propertyNode.getAttribute("value");
                    String refId = propertyNode.getAttribute("ref");
                    Object refObject = getBean(refId);
                    Field field = beanClass.getDeclaredField(name);
                    field.setAccessible(true);
                    if (null != refObject) {
                        field.set(object, refObject);
                    }
                    if (null != value && value.length() > 0) {
                        field.set(object, value);
                    }
                    if (null == value && null == refObject) {
                        throw new UnsupportedOperationException("bean：" + id + "配置不正确，找不到value或引用！");
                    }
                }
                register(id, object);
            }
        }
    }

    public Object getBean(String name) {
        return beansMap.get(name);
    }

    private void register(String id, Object object) {
        beansMap.put(id, object);
    }
}
