package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/9/12.
 */
public class DeleteFileAction implements Serializable {
    private BusiConfig busiConfig;

    public static final String PATH = "PATH";
    public static final String FLAG = "FLAG";

    public DeleteFileAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    public List<Map<String, Object>> deleteFileCommand(Action cmd, JSONObject inParams) throws Exception{
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            List<Map<String, Object>> list = TypeUtil.changeToListMap(busiConfig.getParamsFromInParams_CmdResult(cmd.getParams(), inParams));
            for (int i = 0; i < list.size(); i++) {
                boolean flag;
                Map<String, Object> map = list.get(i);
                String filePath = map.get(PATH).toString();
                try {
                    File file = new File(filePath);
                    file.delete();
                    flag = true;
                } catch (Exception ex) {
                    flag = false;
                }
                Map<String, Object> map1 = new HashMap();
                map1.put(PATH, filePath);
                map1.put(FLAG, flag);
                result.add(map1);
            }
        } catch (Exception ex) {
            throw new UserException(cmd.getId(),cmd.getErrorid(), "", ex);
        }
        return result;
    }

}
