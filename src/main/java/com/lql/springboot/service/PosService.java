package com.lql.springboot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lql.springboot.dao.PosDao;
import com.lql.springboot.dao.PosInterface;
import com.lql.springboot.dbutils.service.DataSourceUtil;
import com.lql.springboot.util.BaseSqlUtil;
import com.lql.util.CodeTreeUtil;
import com.lql.util.JsonValueFilter;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

import static com.lql.util.ResponseResult.RESULT_STATUS_CODE_SUCCESS;

/**
 * @author lql
 * @date 2018/6/10 19:21
 * @describe
 */
@Service
public class PosService {
    @Autowired
    PosInterface pos;
    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * 获取菜单列表
     * code树形结构
     *
     * @return
     */
    public String findFoodMenuList(Map map) {
        return common(PosDao.getAllFoodMenu(), map);
//        //获取JdbcTemplate,使用默认数据源
//        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
//        ResponseResult rs = new ResponseResult();
//        try {
//            rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
//            rs.setMsg("业务执行成功!");
//            rs.setResult(CodeTreeUtil.change2TreeFromCode(pos.getAllFoodMenu(jdbcTemplate)));
//        } catch (Exception e) {
//            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
//            rs.setMsg("业务执行失败!");
//            rs.setResult(StringHelper.toString(e));
//        }
//        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }

    /**
     * 获取菜单列表
     * 菜单管理
     *
     * @return
     */
    public String findFoodMenuList2() {
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        ResponseResult rs = new ResponseResult();
        try {
            rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
            rs.setMsg("业务执行成功!");
            rs.setResult(pos.getAllFoodMenu(jdbcTemplate));
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }


    /**
     * 获取热销商品列表
     *
     * @return
     */
    public String getOfferFoodlist() {
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        ResponseResult rs = new ResponseResult();
        try {
            rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
            rs.setMsg("业务执行成功!");
            rs.setResult(pos.getOfferFoodlist(jdbcTemplate));
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }

    /**
     * 获取历史订单
     *
     * @return
     */
    public String getAllOrderList(Map map) {
        return common(PosDao.getAllOrderListSql(), map);
    }

    /**
     * 下单
     *
     * @param jsonParams
     * @return
     */
    @Transactional
    public String addOrder(String jsonParams) throws SQLException {
        //获取JdbcTemplate,使用默认数据源
        jdbcTemplate = DataSourceUtil.getJdbcTemplate();
        JSONObject json = JSON.parseObject(jsonParams);
        JSONObject data = json.getJSONObject("data");
        JSONObject info = json.getJSONObject("info");
        String userId = info.getString("OBJID");
        List<Map<String, Object>> list = (List) data.getJSONArray("orderList");
        ResponseResult rs = new ResponseResult();
        try {
            List userOrderParams = new ArrayList<>();
            String orderObjId = UUID.randomUUID().toString().replace("-", "");
            userOrderParams.add(orderObjId);
            userOrderParams.add(userId);
            userOrderParams.add(1);//结账默认1 //// TODO: 2018/6/12
            userOrderParams.add(Integer.valueOf((Integer) data.get("total")));
            List<Object[]> list1 = new ArrayList<>();
            List<Object[]> list2 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Object[] object = {orderObjId, list.get(i).get("OBJID"), list.get(i).get("count")};
                Object[] object2 = {list.get(i).get("count"), userId, list.get(i).get("OBJID"), list.get(i).get("count")};
                list1.add(object);
                list2.add(object2);
            }
            //添加用户订单
            int result = pos.addUserOrder(userOrderParams, jdbcTemplate);
            //添加订单对应的食品
            int[] result1 = pos.addOrderFood(list1, jdbcTemplate);
            //更新库存
            int[] result2 = pos.reduceFoodStock(list2, jdbcTemplate);
            if (result < 1) throw new UnsupportedOperationException("添加用户订单失败，请联系管理员!");
            rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
            rs.setMsg("业务执行成功!");
//            rs.setResult();
        } catch (Exception e) {
            jdbcTemplate.getDataSource().getConnection().rollback();
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }

    /**
     * 订单详情
     *
     * @param jsonParams
     * @return
     */
    public String findOrderInfo(String jsonParams) {
        ResponseResult rs = new ResponseResult();
        try {
            jdbcTemplate = DataSourceUtil.getJdbcTemplate();
            JSONObject json = JSON.parseObject(jsonParams);
            JSONObject data = json.getJSONObject("data");
            JSONObject info = json.getJSONObject("info");
            String orderObjId = data.getString("objId");
            String sql = "SELECT C.OBJNAME, B.FOOD_COUNT, C.PRICE\n" +
                    "  FROM USER_ORDER A\n" +
                    "  LEFT JOIN ORDER_FOOD B\n" +
                    "    ON A.OBJID = B.ORDER_OBJID\n" +
                    "  LEFT JOIN FOOD_MENU C\n" +
                    "    ON B.FOOD_OBJID = C.OBJID\n" +
                    " WHERE A.OBJID = ?\n";
            List list = new ArrayList<>();
            list.add(orderObjId);
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, list.toArray());
            rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
            rs.setMsg("业务执行成功!");
            rs.setResult(result);
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }

    /**
     * 公共sql处理方法
     *
     * @param sql
     * @param map
     * @return
     */
    public String common(String sql, Map map) {
        ResponseResult rs = new ResponseResult();
        String finalSql = "";
        String countSql = "SELECT COUNT(1) AS TOTAL FROM ";
        try {
            String pageSize = StringHelper.toString(map.get("page_size"));//页面大小
            String pageNum = StringHelper.toString(map.get("page_num"));//当前页
            String isCodeTree = StringHelper.toString(map.get("isCodeTree"));//当前页
            Map condition = (Map) map.get("condition");//查询条件
            String conditonSql = ViewsService.createMutipleSelectSql(sql, condition);//查询Sql组合
            if (pageSize != "" && pageNum != "") {
                finalSql = BaseSqlUtil.paging(conditonSql, pageNum, pageSize);//分页Sql组合
            } else {
                finalSql = conditonSql;
            }
            countSql += "( " + conditonSql + " )";
            List<Map<String, Object>> total = jdbcTemplate.queryForList(countSql);
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(finalSql);
            Map data = new HashMap();
            data.put("total", total.get(0).get("TOTAL"));
            data.put("rows", rows);
            if (isCodeTree != "") {
                data.put("rows", CodeTreeUtil.change2TreeFromCode(rows));
            }
            rs.setResult(data);
            rs.setStatusCode(RESULT_STATUS_CODE_SUCCESS);
        } catch (Exception e) {
            rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rs.setMsg("业务执行失败!");
            rs.setResult(StringHelper.toString(e));
        }
        return JSON.toJSONString(rs, JsonValueFilter.changeNullToString());
    }

    public static void main(String[] args) {
        List<Map<String, Object>> rows = new ArrayList<>();
        Map map1 = new HashMap();
        map1.put("er", 123);
        map1.put("er123", 123);
        Map map2 = new HashMap();
        map2.put("er", 123);
        map2.put("er123", 123);
        rows.add(map1);
        rows.add(map2);
        Object[] rows2 = new Object[]{};
//        System.arraycopy(rows.toArray(), 0, rows2.toArray(), 0, rows.size());
        rows2 = Arrays.copyOf(rows.toArray(), rows.size());
        Arrays.asList(rows2);
//        for (Map test : rows2) {
//            Iterator iterable = test.keySet().iterator();
//            while (iterable.hasNext()) {
//                System.out.println(iterable.next()+": "+test.get(iterable.next()));
//            }
//        }
    }
}
