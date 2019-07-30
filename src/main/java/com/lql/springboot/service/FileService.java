package com.lql.springboot.service;

import com.alibaba.fastjson.JSON;
import com.lql.springboot.beans.Attach;
import com.lql.springboot.dbutils.service.DataSourceUtil;
import com.lql.util.JsonValueFilter;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lql.util.ResponseResult.RESULT_STATUS_CODE_SUCCESS;

/**
 * @Author lql
 * @Date 2018/7/1 13:38
 */
@Service
public class FileService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 附件上传
     *
     * @return
     */
    public String attachUpload(Attach attach) {
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        ResponseResult rs = new ResponseResult();
        String sql = "INSERT INTO SYS_ATTACH\n" +
                "  (ID,\n" +
                "   OBJID,\n" +
                "   OBJNAME,\n" +
                "   ATTACH_SIZE,\n" +
                "   ATTACH_TYPE,\n" +
                "   CRT_TIME,\n" +
                "   UPD_TIME,\n" +
                "   PATH," +
                "   TYPE)\n" +
                "VALUES\n" +
                "  (SYS_ID.NEXTVAL,?,?,?,?,sysdate,sysdate,?,?)\n";
        try {
            List list = new ArrayList<>();
            list.add(attach.getObjId());
            list.add(attach.getObjName());
            list.add(attach.attachSize);
            list.add(attach.attachType);
            list.add(attach.path);
            list.add(attach.getType());
            int result = jdbcTemplate.update(sql, list.toArray());
            if (result > 0) {
                rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
                rs.setMsg("业务执行成功!");
                Map map = new HashMap<>();
                map.put("OBJID", attach.getObjId());
                map.put("OBJNAME", attach.getObjId() + "." + attach.getAttachType());
                rs.setResult(map);
            }

        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }

    /**
     * 附件下载
     *
     * @return
     */
    public String downLoad(String objId) {
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        ResponseResult rs = new ResponseResult();
        String sql = "SELECT * FROM SYS_ATTACH SA WHERE SA.IS_DELETE =0 AND SA.OBJID = ?";
        try {
            List list = new ArrayList<>();
            list.add(objId);
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, list.toArray());
            if (result.size() == 1) {
                rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
                rs.setMsg("业务执行成功!");
                rs.setResult(result.get(0));
            } else {
                rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
                rs.setMsg("业务执行失败!");
                rs.setResult(result);
            }
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }

    /**
     * 查询附件列表
     *
     * @param jsonParams
     * @return
     */
    public String findAttachList(String jsonParams) {
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        ResponseResult rs = new ResponseResult();
        StringBuffer sb = new StringBuffer();
        String sql = "SELECT * FROM SYS_ATTACH SA WHERE SA.IS_DELETE =0 ORDER BY SA.CRT_TIME DESC";
        try {
            List list = new ArrayList<>();
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, list.toArray());
            if (result.size() >= 0) {
                rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
                rs.setMsg("业务执行成功!");
                rs.setResult(result);
            } else {
                rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
                rs.setMsg("业务执行失败!");
                rs.setResult(result);
            }
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }
}
