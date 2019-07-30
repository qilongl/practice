package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;
import com.lql.util.StringHelper;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/11/30.
 * 自定义异常标签，可自定义异常信息
 */
public class ErrorAction implements Serializable {
    private BusiConfig busiConfig;

    public ErrorAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    public List<Map<String, Object>> errorCommand(Action cmd, JSONObject inParams) throws Exception {
        String msg = cmd.getMsg();
        try {
            if (msg.contains("#{")) {
                String tag = msg.substring(msg.indexOf("#{") + 2, msg.indexOf("}"));
                String obj = tag.substring(0, tag.indexOf("."));
                String prop = tag.substring(tag.indexOf(".") + 1, tag.length());
                List<Map<String, Object>> result = TypeUtil.changeToListMap(busiConfig.getParamsFromInParams_CmdResult(obj, inParams));
                if (result.size() == 0)
                    throw new UnsupportedOperationException("配置的参数来源结果集:" + obj + "内容为空！");
                Map<String, Object> map = result.get(0);
                String content = StringHelper.toString(map.get(prop));
                msg = msg.replace("#{" + tag + "}", content);
            }
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
        }
        throw new UserException(cmd.getId(), cmd.getErrorid(), msg, null);
    }
}
