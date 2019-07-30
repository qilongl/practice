package com.lql.xml.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.lql.xml.db.DPService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/11/03.
 * Update by lql on 2018/06/12.
 */
@Component
@Service
public class TokenCloudService {


    public List<Map<String, Object>> getTokenInfoByAccount(DPService dpService, String account) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            String dbTypeName = ((DruidDataSource) dpService.getDataSource()).getDbType();
            String sql = "";
            switch (dbTypeName.toUpperCase()) {
                case "ORACLE":
                    sql = "select account, token_key, status, end_time, tolerant_sec ,check_ciphertext\n" +
                            "  from SYS_TOKEN_CLOUD t\n" +
                            " where t.account = ?\n" +
                            "   and t.status = 0\n" +
                            "   and sysdate < t.end_time\n";
                    break;
                case "MYSQL":
                    sql = "SELECT ACCOUNT, TOKEN_KEY, STATUS, END_TIME, TOLERANT_SEC ,CHECK_CIPHERTEXT\n" +
                            "  from SYS_TOKEN_CLOUD t\n" +
                            " where t.account = ?\n" +
                            "   and t.status = 0\n" +
                            "   and NOW() < t.end_time\n";
                    break;
                case "SQLSERVER":
                    throw new UnsupportedOperationException("暂不支持该SQLSERVER!");
                default:
                    break;
            }
//        String sql = "select account, token_key, status, end_time, tolerant_sec ,check_ciphertext\n" +
//                "  from SYS_TOKEN_CLOUD t\n" +
//                " where t.account = ?\n" +
//                "   and t.status = 0\n" +
//                "   and sysdate < t.end_time\n";
            List list = new ArrayList<>();
            list.add(account);
            result = dpService.select(sql, list);
            //最后操作时间；
//        String sqlForUp = "update sys_token_cloud d set d.last_time = sysdate where d.account=? and d.status=0 and sysdate<d.end_time";
//        List list2 = new ArrayList<>();
//        list2.add(account);
//        int num_b = dpService.update(sqlForUp, list2);
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }


}
