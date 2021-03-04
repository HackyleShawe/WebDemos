package druid.test;

import druid.util.DruidPoolUtil;

import java.sql.Connection;

public class DruidPoolTest {
    public static void main(String[] args) throws Exception {
        DruidPoolUtil druidPool = DruidPoolUtil.getInstance();

        Connection conn01 = druidPool.getConnection();
        System.out.println(druidPool.poolInfo());
        Connection conn02 = druidPool.getConnection();
        System.out.println(druidPool.poolInfo());

        //输出：
        //当前连接数：1
        //当前连接数：2
    }
}
