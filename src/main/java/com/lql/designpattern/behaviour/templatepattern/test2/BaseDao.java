package com.lql.designpattern.behaviour.templatepattern.test2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by StrangeDragon on 2019/8/1 17:16
 * jdbc查询模板，查询实体类自己封装
 **/
public abstract class BaseDao {

    protected Object find(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object object = null;
        try {
            conn = JDBCUtil.getConnect();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                object = rowMapper(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return object;

    }

    protected abstract Object rowMapper(ResultSet rs) throws SQLException;
}
