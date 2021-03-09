package qr;


import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试增删该
 */
public class CDU_Test {
    private Connection conn = ConnectionUtil.getConnection();
    private QueryRunner qr = new QueryRunner();

    @Test
    public void testInsert() {
        String sql = "INSERT INTO person (name, age, sex, birthday, address, tel, email) " +
                "    VALUES (?,?,?,'2001-01-03','JP','134324324','aaaaa32432@javbu.com') ";

        Object[] params = {"枫力怜",22,"woman"}; //把预编译的参数放置在一个Object数组总
        try {
            int rows = qr.update(conn, sql, params);
            System.out.println("执行成功，受影响行数："+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        String sql = "delete from person where sex=? and age>?";
        Object[] params = {"man",15};
        try {
            int rows = qr.update(conn,sql,params);
            System.out.println("执行成功，影响行数："+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testUpdate() {
        String sql = "update person set age=? where sex=? ";
        Object[] params = {20, "woman"};
        try {
            int rows = qr.update(conn,sql,params);
            System.out.println("执行成功，影响行数："+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
