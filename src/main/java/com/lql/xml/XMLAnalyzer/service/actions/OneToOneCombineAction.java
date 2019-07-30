//package com.lql.tools.XMLAnalyzer.service.actions;
//
//import com.cbdcloud.beans.Action;
//import com.cbdcloud.beans.UserException;
//import com.cbdcloud.xml.XMLAnalyzer.beans.BusiConfig;
//import com.cbdcloud.util.TypeUtil;
//import net.sf.json.JSONObject;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by lql on 2017/10/30.
// * params 中的list<Map>类型的结果集,列名不允许重复,不然后面结果集的列和覆盖掉前面列的值
// */
//public class OneToOneCombineAction implements Serializable {
//    private BusiConfig busiConfig;
//
//    public OneToOneCombineAction(BusiConfig busiConfig) {
//        this.busiConfig = busiConfig;
//    }
//
//    /***
//     * 一对一的组合 如:
//     * list:
//     * name
//     * jack
//     * richard
//     * nike
//     * <p>
//     * <p>
//     * list:
//     * age
//     * 11
//     * 21
//     * 33
//     * <p>
//     * 组合后的list
//     * name    age
//     * jack    11
//     * richard 21
//     * nike    33
//     *
//     * @param cmd
//     * @param inParams
//     * @return
//     */
//    public List<Map<String, Object>> oneToOneCombineCommand(Action cmd, JSONObject inParams) throws Exception {
//        List<Map<String, Object>> result = new ArrayList<>();
//        try {
//            String params = cmd.getParams();
//            String[] ps = params.split(",");
//            int unifiedSize = 0;
//            for (int i = 0; i < ps.length; i++) {
//                String p = ps[i];
//                List<Map<String, Object>> list = TypeUtil.changeToListMap(busiConfig.getParamsFromInParams_CmdResult(p, inParams));
//                if (i == 0) {
//                    unifiedSize = list.size();
//                    result.addAll(list);
//                    continue;
//                }
//                if (list.size() != unifiedSize)
//                    throw new UnsupportedOperationException("id=" + cmd.getId() + "的" + cmd.getCmdType() + "入参" + p + "大小为" + list.size() + "和期望值" + unifiedSize + "不一致!");
//                for (int j = 0; j < result.size(); j++) {
//                    Map map = result.get(j);
//                    map.putAll(list.get(j));
//                }
//            }
//        } catch (Exception ex) {
//            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
//        }
//        return result;
//    }
//
//}
