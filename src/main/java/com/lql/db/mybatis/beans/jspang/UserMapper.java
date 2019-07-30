package com.lql.db.mybatis.beans.jspang;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}