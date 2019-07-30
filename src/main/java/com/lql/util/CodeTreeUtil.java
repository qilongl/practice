package com.lql.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lql
 * @date 2018/6/11 21:26
 * @describe
 */
@Component
public class CodeTreeUtil {

//    private static String ID = "ID";
//    private static String NAME = "NAME";
    private static String CODE = "CODE";
    private static String CHILDREN = "children";

    /**
     * 根据list<Map>中的code组装code-树
     *
     * @param list
     * @return List<Map<String, Object>>
     *
     * [{
     * ID:123,
     * NAME:AAA,
     * CODE:10
     * },
     * {
     * ID:123,
     * NAME:AAA,
     * CODE:1010
     * },
     * {
     * ID:123,
     * NAME:AAA,
     * CODE:1011
     * },
     * {
     * ID:123,
     * NAME:AAA,
     * CODE:20
     * }]
     *
     *
     * 组装后：
     * [{
     * ID:123,
     * NAME:AAA,
     * CODE:10,
     * children:[
     *          {
     *          ID:123,
     *          NAME:AAA,
     *          CODE:1010
     *          },
     *          {
     *          ID:123,
     *          NAME:AAA,
     *          CODE:1011
     *          }]
     * },
     * {
     * ID:123,
     * NAME:AAA,
     * CODE:20
     * }]
     *
     *
     *
     *
     */

    public static JSONArray change2TreeFromCode(List<Map<String, Object>> list) {
        com.alibaba.fastjson.JSONArray treeArray = new com.alibaba.fastjson.JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
//            String id = StringHelper.toString(map.get("ID"));
//            String name = map.get("NAME") + "";
//            changeMapKeyToLowerCase(map);
            if (!map.containsKey(CODE))
                throw new UnsupportedOperationException("缺少必须的列" + CODE);
            String code = StringHelper.toString(map.get(CODE));
//            String flag = map.containsKey("FLAG") ? map.get("FLAG").toString() : "";
//            String order = map.containsKey("ORDER") ? map.get("ORDER").toString() : "";
//            String storage = map.containsKey("STORAGE") ? map.get("STORAGE").toString() : "";
            JSONObject distantNode = getDistantNode(treeArray, code);
            /****
             * 自己就是根节点
             */
            if (distantNode == null) {
                JSONObject selfNode = new JSONObject();
                selfNode.putAll(map);
//                selfNode.put("id", id);
//                selfNode.put("value", code);
//                selfNode.put("label", name);
//                selfNode.put("flag", flag);
//                selfNode.put("order", order);
//                selfNode.put("storage", storage);
                selfNode.put(CHILDREN, new com.alibaba.fastjson.JSONArray());
                treeArray.add(selfNode);
                continue;
            }
            /***
             * 自己不是根节点
             */
            else {
                JSONObject closeNode = getCloserNodeCode(code, distantNode);
                JSONArray jsonArray = closeNode.getJSONArray(CHILDREN);
                JSONObject selfNode = new JSONObject();
                selfNode.putAll(map);
//                selfNode.put("id", id);
//                selfNode.put("value", code);
//                selfNode.put("label", name);
//                selfNode.put("flag", flag);
//                selfNode.put("order", order);
//                selfNode.put("storage", storage);
                selfNode.put(CHILDREN, new com.alibaba.fastjson.JSONArray());
                jsonArray.add(selfNode);
            }
            list.remove(i);
            i--;
        }
        return treeArray;
    }


    /***
     * 找到自己的根
     *
     * @param jsonArray
     * @param code
     * @return
     */
    private static JSONObject getDistantNode(JSONArray jsonArray, String code) {
        JSONObject rootNode = null;
        String distantPCode = "";
        for (int i = 1; i < code.length(); i++) {
            distantPCode = code.substring(0, i);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                if (jsonObject.getString(CODE).equals(distantPCode)) {
                    rootNode = jsonObject;
                    break;
                }
            }
        }
        return rootNode;
    }

    /***
     * 找到根上最近的父节点
     *
     * @param distantNode
     * @return
     */
    private static JSONObject getCloserNodeCode(String code, JSONObject distantNode) {
        JSONObject lastNode = distantNode;
        int startIndex = distantNode.getString(CODE).length();
        for (int i = startIndex; i < code.length(); i++) {
            String genCode = code.substring(0, i);
            JSONArray jsonArray = lastNode.getJSONArray(CHILDREN);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                if (jsonObject.getString(CODE).equals(genCode)) {
                    lastNode = jsonObject;
                }
            }
        }
        return lastNode;
    }
}
