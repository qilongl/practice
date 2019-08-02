package com.lql.designpattern.templatepattern.test2;

import org.apache.poi.hssf.record.SSTRecord;

import java.sql.*;

/**
 * Created by StrangeDragon on 2019/8/1 17:04
 **/
public class JDBCUtil {
    private static String url = "jdbc:mysql://localhost:3306/xa";
    private static String username = "root";
    private static String password = "123456";

    /**
     * 私有化构造方法，不允许外部创建实例。单例模式
     */
    private JDBCUtil() {
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != ps) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }


}
