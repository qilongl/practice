package com.lql.spring.transactionmanager;

import org.junit.jupiter.api.Test;

/**
 * Created by StrangeDragon on 2019/8/2 14:41
 * 验证嵌套事务
 **/
public class Main {

    /**
     * 嵌套事务用法--->事务提交
     * 嵌套事务：在一个事务中加入一个新的事务，该事务会随外层事务提交和回滚
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        JdbcTransactionManager dbServer = new JdbcTransactionManager();
        JdbcTransactionManager dbServer1 = new JdbcTransactionManager();
        dbServer.startTransaction();
        dbServer1.startTransaction();//嵌套的事务
        String sql = "insert into test1 values(1,1,1,sysdate)";
        String sql2 = "update test1 set obj_name='测试' where id=1";
        try {
            dbServer.update(sql);
            dbServer1.update(sql2);

            dbServer.commit();
        } catch (Exception e) {
            dbServer.rollback();
        }
    }

    /**
     * 嵌套事务用法--->事务回滚
     * 嵌套事务：在一个事务中加入一个新的事务，该事务会随外层事务提交和回滚
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        JdbcTransactionManager dbServer = new JdbcTransactionManager();
        JdbcTransactionManager dbServer1 = new JdbcTransactionManager();
        dbServer.startTransaction();
        dbServer1.startTransaction();//嵌套的事务
        String sql = "insert into test1 values(1,1,1,sysdate)";
        String sql2 = "update test1 set obj_name='测试' where id=1";
        try {
            dbServer.update(sql);
            dbServer1.update(sql2);
            int a = 1 / 0;
            dbServer.commit();
        } catch (Exception e) {
            dbServer.rollback();
        }
    }

    /**
     * 错误示范
     * 内部事务提交会报异常，但不影响外层事务的提交
     * 只提交内部事务 + 不提交外部事务 = 整个事务没有提交
     * 即提交内部事务 + 提交外部事务   = 整个事务提交
     * <p>
     * 注意：提交内部事务会报异常 “java.sql.SQLException: 不支持的特性”
     * 正确用法：
     * 在事务中加入新的事务，只需要开启新的事务即可，整体事务随外部事务的提交和回滚而执行操作
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        JdbcTransactionManager dbServer = new JdbcTransactionManager();
        JdbcTransactionManager dbServer1 = new JdbcTransactionManager();
        dbServer.startTransaction();
        dbServer1.startTransaction();//嵌套的事务
        String sql = "insert into test1 values(1,1,1,sysdate)";
        String sql2 = "update test1 set obj_name='测试' where id=1";
        try {
            dbServer.update(sql);
            dbServer1.update(sql2);
            dbServer1.commit();
            dbServer.commit();
        } catch (Exception e) {
            dbServer.rollback();
        }
    }

    /**
     * 错误示范
     * 内部事务回滚 + 外部事务提交 = 整个事务回滚
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        JdbcTransactionManager dbServer = new JdbcTransactionManager();
        JdbcTransactionManager dbServer1 = new JdbcTransactionManager();
        dbServer.startTransaction();
        dbServer1.startTransaction();//嵌套的事务
        String sql = "insert into test1 values(1,1,1,sysdate)";
        String sql2 = "update test1 set obj_name='测试' where id=1";
        try {
            dbServer.update(sql);
            dbServer1.update(sql2);
            dbServer1.rollback();
            dbServer.commit();
        } catch (Exception e) {
            dbServer.rollback();
        }
    }


}
