package com.lql.designpattern.templatepattern.test2;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by StrangeDragon on 2019/8/1 17:26
 **/
public class UserDao extends BaseDao {

    public User findUser(int userId) {
        String sql = "select * from sys_user su where su.userId=?";
        return (User) super.find(sql, new Object[]{2});
    }

    @Override
    protected Object rowMapper(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("userId"));
        user.setName(rs.getString("name"));
        return user;
    }
}
