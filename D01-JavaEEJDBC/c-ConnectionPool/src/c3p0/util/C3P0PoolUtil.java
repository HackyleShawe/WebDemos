package c3p0.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * C3P0连接池工具类
 */
public class C3P0PoolUtil {
    private static C3P0PoolUtil singleInstance = null;
    private ComboPooledDataSource dataSource = null; //连接池对象，也应该保证只有一个


    /**
     * 私有构造器，单例模式，防止反射创建多个线程池
     */
    private C3P0PoolUtil() {
        dataSource=new ComboPooledDataSource();
    }

    public static C3P0PoolUtil getInstance() {
        if(singleInstance == null) {
            singleInstance = new C3P0PoolUtil();
        }
        return singleInstance;
    }

    public Connection getConnection() throws SQLException {
        if(dataSource == null) {
            getInstance();
        }
        return dataSource.getConnection();
    }

    public void releaseConnection(Connection conn) throws SQLException {
        conn.close();
    }

    public String getPoolInfo() throws SQLException{
        return "总连接数："+dataSource.getNumConnections() +
                "；正在使用连接数："+dataSource.getNumBusyConnections()+
                "；空闲的连接数："+dataSource.getNumIdleConnections();
    }
}
