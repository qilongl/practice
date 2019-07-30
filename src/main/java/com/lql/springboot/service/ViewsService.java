package com.lql.springboot.service;

import com.lql.springboot.util.BaseSqlUtil;
import com.lql.springboot.util.FieldTypeChange;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2018/8/30 13:39
 **/
@Service
public class ViewsService {
    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * 参数：
     * 1、视图名称
     * 2、查询参数
     * 结果：满足条件的视图数据
     *
     * @param map
     * @return
     */
    public ResponseResult getlist(Map map, JdbcTemplate outJdbcTemplate) {
        JdbcTemplate jdbcTemplate1;
        String baseSql = "";
        String finalSql = "";
        String dataSql = "SELECT * FROM ";
        String countSql = "SELECT COUNT(1) AS TOTAL FROM ";
        String basePostSql = " A WHERE 1 =1 ";
        ResponseResult responseResult = new ResponseResult();
        try {
            String viewName = StringHelper.toString(map.get("view_name"));//视图名称
            String sSql = StringHelper.toString(map.get("sSql"));//视图名称
            String uidSql = StringHelper.toString(map.get("uidSql"));//忽略slq注入问题
            if (viewName == "") {
                if (sSql != "") {
                    baseSql = sSql + basePostSql;
                }
                if (uidSql != "") {
                    try {
                        int uidResult = jdbcTemplate.update(uidSql);
                        responseResult.setMsg("执行成功!");
                        responseResult.setStatusCode(responseResult.RESULT_STATUS_CODE_SUCCESS);
                        return responseResult;
                    } catch (Exception e) {
                        throw new UnsupportedOperationException("sql 异常" + e.getMessage());
                    }
                }
            }
            if (map.containsKey("view_name") && viewName != "") {
                baseSql = dataSql + viewName + basePostSql;
            }
            String pageSize = StringHelper.toString(map.get("page_size"));//页面大小
            String pageNum = StringHelper.toString(map.get("page_num"));//当前页
            Map conditon = (Map) map.get("condition");//查询条件

            String conditonSql = createMutipleSelectSql(baseSql, conditon);//查询Sql组合
            if (pageSize != "" && pageNum != "") {
                finalSql = BaseSqlUtil.paging(conditonSql, pageNum, pageSize);//分页Sql组合
            } else {
                finalSql = conditonSql;
            }
//                countSql += viewName;
            countSql += "( " + conditonSql + " )";
            if (outJdbcTemplate == null) {
                jdbcTemplate1 = jdbcTemplate;
            } else {
                jdbcTemplate1 = outJdbcTemplate;
            }
            List<Map<String, Object>> total = jdbcTemplate1.queryForList(countSql);
            List<Map<String, Object>> rows = jdbcTemplate1.queryForList(finalSql);
            Map data = new HashMap();
            data.put("total", total.get(0).get("TOTAL"));
            data.put("rows", rows);
            responseResult.setResult(data);
            responseResult.setStatusCode(responseResult.RESULT_STATUS_CODE_SUCCESS);
        } catch (Exception e){
            responseResult.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            responseResult.setMsg(e.getMessage());
        }
        return responseResult;

    }

    /**
     * 根据码表的key值获取码表数据
     *
     * @return
     */
    public ResponseResult getOpsCommonList(Map map) {

        //根据码表code获取相应的列表
        String sql = "SELECT A.TYPE_CODE,A.TYPE_NAME,A.TYPE_DATATYPE AS TYPE FROM OPS_COMMON_MINI A WHERE A.ISCURRENT=1 AND A.COMMON_CODE=?";
        ResponseResult ResponseResult = new ResponseResult();
        try {
            if (map.containsKey("common_code")) {
                String commonCode = StringHelper.toString(map.get("common_code"));
                List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, commonCode);
                List changedList = FieldTypeChange.changeFieldType(list);
                ResponseResult.setResult(changedList);
                ResponseResult.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
            } else {
                throw new UnsupportedOperationException("缺少查询关键字   common_code !!!");
            }
        } catch (Exception e) {
            ResponseResult.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            ResponseResult.setMsg(e.getMessage());
        }
        return ResponseResult;
    }

    /**
     * 初始化表字段数据到码表中，用于在码表中控制表的展示和下拉查询字段
     *
     * @param map
     * @return
     */
    @Transactional
    public ResponseResult init(Map map) throws SQLException {
        ResponseResult ResponseResult = new ResponseResult();
        int checkResult = 0;
        int checkResult2 = 0;
        Connection connection;
        try {
            if (map.containsKey("tableName")) {
                String tableName = StringHelper.toString(map.get("tableName"));
                String[] tableArray = tableName.split(",");
                String checkSql = "SELECT COUNT(1) AS TOTAL FROM OPS_COMMON_MINI MI WHERE MI.ISCURRENT =1 AND UPPER(MI.COMMON_CODE) = UPPER(?)";
                String checkSql2 = "SELECT COUNT(1) AS TOTAL FROM OPS_COMMON_MINI MI WHERE MI.ISCURRENT =1 AND MI.COMMON_CODE  ='USER_VIEWS'  AND UPPER(MI.TYPE_CODE) = UPPER(?)";
                String insertCommonSql = "INSERT INTO OPS_COMMON_MINI (COMMON_KEY,\n" +
                        "                             COMMON_CODE,\n" +
                        "                             COMMON_NAME,\n" +
                        "                             TYPE_CODE,\n" +
                        "                             TYPE_NAME,\n" +
                        "                             TYPE_DATATYPE,\n" +
                        "                             FEED\n" +
                        "                             ) " +
                        "                 SELECT SEQ_OPS_COMMON_MINI.NEXTVAL ,\n" +
                        "                        A.TABLE_NAME,\n" +
                        "                        DECODE(C.COMMENTS,NULL,'未知通用名称','','未知通用名称',C.COMMENTS),\n" +
                        "                        A.COLUMN_NAME,\n" +
                        "                        DECODE(B.COMMENTS,NULL,'未知通用名称','','未知通用名称',B.COMMENTS)," +
                        "                        A.DATA_TYPE,\n" +
                        "                        A.TABLE_NAME\n" +
                        "                  FROM USER_TAB_COLUMNS A, USER_COL_COMMENTS B,USER_TAB_COMMENTS C\n" +
                        "                  WHERE A.TABLE_NAME = UPPER (?)\n" +
                        "                      AND A.COLUMN_NAME = B.COLUMN_NAME\n" +
                        "                      AND A.TABLE_NAME = B.TABLE_NAME\n" +
                        "                      AND A.TABLE_NAME = C.TABLE_NAME";
                String insertUserViewSql = "INSERT INTO OPS_COMMON_MINI (COMMON_KEY,\n" +
                        "                             COMMON_CODE,\n" +
                        "                             COMMON_NAME,\n" +
                        "                             TYPE_CODE,\n" +
                        "                             TYPE_NAME,\n" +
                        "                             FEED\n" +
                        "                             ) SELECT SEQ_OPS_COMMON_MINI.NEXTVAL ,\n" +
                        "                             'USER_VIEWS',\n" +
                        "                             '视图列表',\n" +
                        "                              C.TABLE_NAME,\n" +
                        "                              DECODE(C.COMMENTS,NULL,'未知通用名称','','未知通用名称',C.COMMENTS),\n" +
                        "                              C.TABLE_NAME\n" +
                        "                         FROM USER_TAB_COMMENTS C\n" +
                        "                         WHERE C.TABLE_NAME = UPPER (?)";
                ResponseResult.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
                for (int i = 0; i < tableArray.length; i++) {
                    Map map1 = jdbcTemplate.queryForList(checkSql, tableArray[i]).get(0);
                    Map map2 = jdbcTemplate.queryForList(checkSql2, tableArray[i]).get(0);
                    checkResult = Integer.valueOf(map1.get("TOTAL").toString());//校验USER_VIEW 中是否存在该表的数据
                    checkResult2 = Integer.valueOf(map2.get("TOTAL").toString());//校验该表中是否存在相关数据
                    if (checkResult == 0 && checkResult2 == 0) {
                        int initTableTotal = jdbcTemplate.update(insertCommonSql, tableArray[i]);
                        int initUserViewTotal = jdbcTemplate.update(insertUserViewSql, tableArray[i]);
                    } else {
                        jdbcTemplate.getDataSource().getConnection().rollback();
                        ResponseResult.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
                        ResponseResult.setMsg(tableArray[i] + "表/视图 在码表中已存在数据，新增前请到码表中处理!!!");
                        ResponseResult.setResult(tableArray[i] + "表/视图 在码表中已存在数据，新增前请到码表中处理!!!");
                        break;
                    }
                    ResponseResult.setMsg("初始化数据成功!!!");
                }
            } else {
                throw new UnsupportedOperationException("缺少查询关键字   tableName !!!");
            }
        } catch (Exception e) {
            //涉及到对数据库表数据的改动，异常时回滚
            jdbcTemplate.getDataSource().getConnection().rollback();
            ResponseResult.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            ResponseResult.setMsg(e.getMessage());
        }
        return ResponseResult;
    }

    /**
     * 获取该用户下的所有表和视图
     *
     * @return
     */

    public ResponseResult getTableAndView() {
        ResponseResult ResponseResult = new ResponseResult();
        String sql = " SELECT NAME,NAME_DESC,FLAG,EXISTED FROM (\n" +
                "SELECT * FROM (\n" +
                "SELECT VIEW_NAME AS NAME, '视图' AS NAME_DESC, '0' AS FLAG\n" +
                "  FROM USER_VIEWS\n" +
                "UNION\n" +
                "SELECT A.TABLE_NAME AS NAME, C.COMMENTS AS NAME_DESC, '1' AS FLAG\n" +
                "  FROM USER_TABLES A, USER_TAB_COMMENTS C\n" +
                " WHERE A.TABLE_NAME = C.TABLE_NAME\n" +
                " ORDER BY FLAG ASC)A\n" +
                "LEFT JOIN\n" +
                "(SELECT MI.TYPE_CODE ,'1'AS EXISTED FROM OPS_COMMON_MINI MI WHERE MI.ISCURRENT =1 AND MI.COMMON_CODE = 'USER_VIEWS')B\n" +
                "ON A.NAME = B.TYPE_CODE\n" +
                ")";
        try {
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
            ResponseResult.setResult(list);
            ResponseResult.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
        } catch (Exception e) {
            ResponseResult.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            ResponseResult.setMsg(e.getMessage());
        }
        return ResponseResult;
    }

    /**
     * Sql 拼接查询条件
     *
     * @param sql      待拼接的sql
     * @param map      查询条件
     * @return
     */
    public static String createMutipleSelectSql(String sql, Map<String, Object> map) {
//        Map mapParam = new HashMap();
//        mapParam.put("common_code", viewName);
//        ResponseResult ResponseResult = getOpsCommonList(mapParam);
//        List fieldList = (List) ResponseResult.getResult();
        StringBuilder sqlResult = new StringBuilder(sql);
        String orderbySql = "";
        for (String key : map.keySet()) {
            String[] word = key.split("_");
            String field = word.length > 1 ? key.substring(word[0].length() + 1, key.length()) : word[0];//字段名称
            /**
             *  由于 between 查询时期类型和字符串类型时需要格式转换，因此再次判断待查询的字段类型
             */
            String fieldType = "";
//            for (int i = 0; i < fieldList.size(); i++) {
//                Map mapT = (Map) fieldList.get(i);
//                if (field.equalsIgnoreCase(mapT.get("TYPE_CODE").toString())) {
//                    fieldType = mapT.get("TYPE").toString();
//                }
//            }
            sqlResult = word.length > 1 ?
                    fieldType.equalsIgnoreCase("date") ?
                            sqlResult.append(" and to_char(").append(field).append(",'YYYY-MM-DD')")
                            : sqlResult.append(" and ").append(field)
                    : sqlResult;
            if (!map.get(key).toString().equals("")) {
                switch (word[0]) {
                    case "orderBy":
                        orderbySql = changeOrder(map.get(key).toString());
                        break;
//                    case "groupby":
//                        break;
//                    case "sort":
//                        break;
//                    case "or":
//                        break;
//                    case "orLike":
//                        break;
//                    case "isNull":
//                        break;
//                    case "isNotNull":
//                        break;
//                    case "notEq":
//                        break;
//                    case "notIn":
//                        break;
                    case "eq":
                        sqlResult = sqlResult.append("='").append(map.get(key)).append("'");
                        break;
                    case "like":
                        sqlResult = sqlResult.append(" like '%").append(map.get(key)).append("%'");
                        break;
                    case "likeR":
                        sqlResult = sqlResult.append(" like '").append(map.get(key)).append("%'");
                        break;
                    case "likeL":
                        sqlResult = sqlResult.append(" like '%").append(map.get(key)).append("'");
                        break;
                    case "le":
                        sqlResult = sqlResult.append("<='").append(map.get(key)).append("'");
                        break;
                    case "lt":
                        sqlResult = sqlResult.append("<'").append(map.get(key)).append("'");
                        break;
                    case "ge":
                        sqlResult = sqlResult.append(">='").append(map.get(key)).append("'");
                        break;
                    case "gt":
                        sqlResult = sqlResult.append(">'").append(map.get(key)).append("'");
                        break;
                    case "in":
                        StringBuilder inSb = new StringBuilder();
                        List list = (List) map.get(key);
                        for (Object inParam : list) {
                            inSb.append("'" + inParam + "',");
                        }
                        inSb.deleteCharAt(inSb.length() - 1);
                        sqlResult = sqlResult.append(" and a.").append(word[1]).append(" in (").append(inSb).append(")");
                        break;
                    case "between":
                        String params[] = map.get(key).toString().split(",");
                        switch (fieldType) {
                            case "date":
                                sqlResult = sqlResult.append(">='").append(params[0]).append("'").append(" and  to_char(a.").append(field).append(",'YYYY-MM-DD')").append("<='").append(params[1]).append("'");
                                break;
                            default:
                                sqlResult = sqlResult.append(">='").append(params[0]).append("'").append(" and a.").append(field).append("<='").append(params[1]).append("'");
                                break;
                        }
                        break;
                    default:
//                        sqlResult = sqlResult.append(" like '%").append("%'");
                        break;

                }
            }
        }
        sqlResult = sqlResult.append(orderbySql);//把排序放在查询之后
        return sqlResult.toString();
    }


    /**
     * 组装排序sql
     * <p>
     * 前端参数  eg: "orderBy":"NAME,asc,ID,desc"
     *
     * @param str
     * @return
     */
    public static String changeOrder(String str) {
        String order[] = str.split(",");
        String orderSql = " order by ";
        for (int i = 0; i < order.length; i += 2) {
            orderSql += order[i] + " " + order[i + 1];
            if (i + 2 != order.length) {
                orderSql += " , ";
            }
        }
        return orderSql;
    }
}
