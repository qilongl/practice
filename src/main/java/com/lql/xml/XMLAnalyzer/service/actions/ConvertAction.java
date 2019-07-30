package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.util.StringHelper;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.db.DPService;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/9/21.
 */
public class ConvertAction implements Serializable {
    private BusiConfig busiConfig;
    private String METHOD = "exec";

    public ConvertAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    public Object convertCommand(Action cmd, JSONObject inParams, Map<String, List<Map<String, byte[]>>> fileMap, DPService dpService) throws Exception {
        Object object;
        try {
            //创建类加载器
            Class<?> cls = Class.forName(cmd.getClasspath());//加载指定类，注意一定要带上类的包名
            Object obj = cls.newInstance();//初始化一个实例
            if (StringHelper.isNotNull(cmd.getMethod())) {
                METHOD = cmd.getMethod();
            }
            String params = "";
            if (StringHelper.isNotNull(cmd.getParams())) {
                params = cmd.getParams();
                Object result = busiConfig.getParamsFromInParams_CmdResult(params, inParams);
                Method method = cls.getMethod(METHOD, Action.class, JSONObject.class, Map.class,Object.class,DPService.class);
                object = method.invoke(obj, cmd, inParams, fileMap, result, dpService);
            } else {
                Method method = cls.getMethod(METHOD, Action.class, JSONObject.class, Map.class,Object.class, DPService.class);
                object = method.invoke(obj, cmd, inParams, fileMap, null, dpService);
            }
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
        }
        return object;
    }

}
