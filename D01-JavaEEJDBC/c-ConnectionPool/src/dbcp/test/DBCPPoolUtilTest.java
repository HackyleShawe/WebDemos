package dbcp.test;

import dbcp.util.DBCPPoolUtil;

import java.sql.Connection;

public class DBCPPoolUtilTest {
    public static void main(String[] args) throws Exception {
        DBCPPoolUtil dbcpPool = DBCPPoolUtil.getInstance(); //获取一个DBCP连接池

        Connection conn01 = dbcpPool.getConnection();
        Connection conn02 = dbcpPool.getConnection();

        System.out.println(dbcpPool.getPoolInfo());

        conn01.close();
        System.out.println(dbcpPool.getPoolInfo());

        //输出：
        //正在使用连接数：2
        //正在使用连接数：1
    }
}
