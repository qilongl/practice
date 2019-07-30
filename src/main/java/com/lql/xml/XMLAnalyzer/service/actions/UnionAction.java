package com.lql.xml.XMLAnalyzer.service.actions;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/8/15.
 */
public class UnionAction implements Serializable {
    private BusiConfig busiConfig;

    public UnionAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    /****
     * 结果集合并
     *
     * @param cmd
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> unionCommand(Action cmd) throws Exception {
        String dataset = cmd.getDataset();
        List<Map<String, Object>> allData = new ArrayList<>();
        try {
            String[] datasetList = dataset.split(",");
            for (int i = 0; i < datasetList.length; i++) {
                String datasetName = datasetList[i];
                List<Map<String, Object>> result = TypeUtil.changeToListMap(busiConfig.getParamFromCmdResult(datasetName));
                allData.addAll(result);
            }
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
        }
        return allData;
    }

    public static void main(String args[])
    {
        System.out.println("a,bc,d".split(",")[1]);
    }
}
