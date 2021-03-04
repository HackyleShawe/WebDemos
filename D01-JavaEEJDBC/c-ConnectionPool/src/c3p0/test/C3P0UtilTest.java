package c3p0.test;

import c3p0.util.C3P0PoolUtil;

import java.sql.Connection;

public class C3P0UtilTest {

    public static void main(String[] args) throws Exception {
        C3P0PoolUtil pool_01 = C3P0PoolUtil.getInstance(); //获取一个连接池

        for(int i=0; i<3; i++) {
            System.out.println(pool_01.getConnection());
            System.out.println(pool_01.getPoolInfo());
        }
        //输出：
        //com.mchange.v2.c3p0.impl.NewProxyConnection@6a6afff2
        //总连接数：3；正在使用连接数：1；空闲的连接数：2
        //com.mchange.v2.c3p0.impl.NewProxyConnection@55536d9e
        //总连接数：5；正在使用连接数：2；空闲的连接数：3
        //com.mchange.v2.c3p0.impl.NewProxyConnection@3d1cfad4
        //总连接数：5；正在使用连接数：3；空闲的连接数：2

        C3P0PoolUtil pool_02 = C3P0PoolUtil.getInstance(); //获取一个连接池
        for(int i=0; i<3; i++) {
            Connection conn = pool_02.getConnection();
            System.out.println(conn);
            pool_02.releaseConnection(conn);

            System.out.println(pool_02.getPoolInfo());
        }
        //输出：
        //com.mchange.v2.c3p0.impl.NewProxyConnection@6a6afff2
        //总连接数：3；正在使用连接数：1；空闲的连接数：2
        //com.mchange.v2.c3p0.impl.NewProxyConnection@62230c58
        //总连接数：5；正在使用连接数：1；空闲的连接数：4
        //com.mchange.v2.c3p0.impl.NewProxyConnection@2e55dd0c
        //总连接数：5；正在使用连接数：1；空闲的连接数：4
    }

}
