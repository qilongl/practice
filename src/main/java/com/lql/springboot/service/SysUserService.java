package com.lql.springboot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lql.springboot.dbutils.service.DataSourceUtil;
import com.lql.util.JsonValueFilter;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.lql.util.ResponseResult.RESULT_STATUS_CODE_SUCCESS;

/**
 * @Author lql
 * @Date 2018/7/1 20:41
 */
@Service
public class SysUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 登录
     *
     * @return
     */
    public String login(String jsonParams) {
        String sql = "SELECT SU.* FROM Sys_User su \n" +
                "WHERE su.is_delete=0\n" +
                "     AND LOWER(su.account)=LOWER(TRIM(?))\n" +
                "     AND su.password=?";
//        String sql = "SELECT SU.*, SA.OBJID||'.'||SA.ATTACH_TYPE AS PATH FROM Sys_User su \n" +
//                "LEFT JOIN SYS_ATTACH SA\n" +
//                "ON SU.OBJID = SA.FK_OBJID\n" +
//                "AND SA.IS_DELETE=0" +
//                "AND SA.TYPE=10  \n" +
//                "WHERE su.is_delete=0\n" +
//                "     AND LOWER(su.account)=LOWER(TRIM(?))\n" +
//                "     AND su.password=?";
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        ResponseResult rs = new ResponseResult();
        JSONObject json = JSON.parseObject(jsonParams);
        String account = json.getString("account");
        String password = json.getString("password");
        List list = new ArrayList<>();
        list.add(account);
        list.add(password);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, list.toArray());
            if (result.size() > 0) {
                Map map = result.get(0);
                map.put("TOKEN", UUID.randomUUID().toString().replace("-", ""));
                rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
                rs.setMsg("登录成功!");
                rs.setResult(JSON.toJSONString(map, JsonValueFilter.changeNullToString()));
            } else if (result.size() == 0) {
                rs.setStatusCode(ResponseResult.LOGIN_STATUS_CODE_ACCOUNT_NO_OR_WRONG_PASSWORD);
                rs.setMsg("账号或密码错误!");
                rs.setResult("账号或密码错误!");
            }
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }

    /**
     * 修改密码
     *
     * @return
     */
    public String changePassword(String jsonParams) {
        String sql = "SELECT SU.*, SA.OBJID||'.'||SA.ATTACH_TYPE AS PATH FROM Sys_User su \n" +
                "LEFT JOIN SYS_ATTACH SA\n" +
                "ON SU.OBJID = SA.FK_OBJID\n" +
                "AND SA.IS_DELETE=0" +
                "AND SA.TYPE=10  \n" +
                "WHERE su.is_delete=0\n" +
                "     AND LOWER(su.account)=LOWER(TRIM(?))\n" +
                "     AND su.password=?";
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        ResponseResult rs = new ResponseResult();
        JSONObject json = JSON.parseObject(jsonParams);
        String account = json.getString("account");
        String password = json.getString("password");
        List list = new ArrayList<>();
        list.add(account);
        list.add(password);
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, list.toArray());
            if (result.size() > 0) {
                Map map = result.get(0);
                map.put("TOKEN", UUID.randomUUID().toString().replace("-", ""));
                rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
                rs.setMsg("登录成功!");
                rs.setResult(JSON.toJSONString(map, JsonValueFilter.changeNullToString()));
            } else if (result.size() == 0) {
                rs.setStatusCode(ResponseResult.LOGIN_STATUS_CODE_ACCOUNT_NO_OR_WRONG_PASSWORD);
                rs.setMsg("账号或密码错误!");
                rs.setResult("账号或密码错误!");
            }
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }


    /**
     * 上传头像
     *
     * @return
     */
    public String changeUserPhoto(String jsonParams) {
        String sql1 = "UPDATE SYS_ATTACH SA SET SA.IS_DELETE = 1 ,sa.upd_psn = ? ,sa.upd_time = SYSDATE" +
                "  WHERE SA.FK_OBJID = ?";
        String sql2 = "UPDATE SYS_ATTACH sa SET sa.fk_objid = ?,sa.upd_psn = ? ,sa.upd_time = SYSDATE\n" +
                "  WHERE sa.objid = ? \n" +
                "  AND sa.is_delete=0";
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        ResponseResult rs = new ResponseResult();
        JSONObject json = JSON.parseObject(jsonParams);
        JSONObject data = json.getJSONObject("data");
        JSONObject info = json.getJSONObject("info");
        String objId = data.getString("OBJID");//附件OBJID
        String fk_objId = info.getString("OBJID");//用户OBJID
        String objName = info.getString("OBJNAME");//用户名
        List list1 = new ArrayList<>();
        list1.add(fk_objId);
        list1.add(fk_objId);
        List list2 = new ArrayList<>();
        list2.add(fk_objId);
        list2.add(fk_objId);
        list2.add(objId);
        try {
            int result1 = jdbcTemplate.update(sql1, list1.toArray());
            int result2 = jdbcTemplate.update(sql2, list2.toArray());
            if (result2 > 0 && result1 >= 0) {
                rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
                rs.setMsg("业务执行成功!");
                rs.setResult("头像更新成功!");
            } else {
                rs.setStatusCode(ResponseResult.LOGIN_STATUS_CODE_ACCOUNT_NO_OR_WRONG_PASSWORD);
                rs.setMsg("业务执行失败!");
                rs.setResult("头像更新失败!");
            }
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }
}
