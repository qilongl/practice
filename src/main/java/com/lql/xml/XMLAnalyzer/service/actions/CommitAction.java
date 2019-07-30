package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.util.StringHelper;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import com.lql.xml.db.DPFactory;
import com.lql.xml.db.DPService;
import net.sf.json.JSONObject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2018/1/17.
 */
public class CommitAction implements Serializable {
    private BusiConfig busiConfig;

    public CommitAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    public DPService commitCommand(Action cmd, DPService dpService) throws Exception {
        cmd.setId("COMMIT_" + StringHelper.getUUID());
        cmd.setErrorid("COMMIT_" + StringHelper.getUUID());
        try {
            dpService.commit();
            String dataSourceName = busiConfig.getDataSource();
            dpService = DPFactory.createDPService(BusiConfigCache.ctx, dataSourceName);
            dpService.startTransaction();
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
        }
        return dpService;
    }
}
