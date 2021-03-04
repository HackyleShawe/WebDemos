package druid.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid创建连接池(单例懒汉模式)
 */
public class DruidPoolUtil {
    private static DruidPoolUtil singleInstance = null;
    private DruidDataSource dataSource;

    private DruidPoolUtil() {
        InputStream is = DruidPoolUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties pros = new Properties();
        try {
            pros.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            System.out.println("加载配置文件druid.properties失败，请检查");
            e.printStackTrace();
        }
    }

    public static DruidPoolUtil getInstance() {
        if(singleInstance == null) {
            singleInstance = new DruidPoolUtil();
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

    public String poolInfo() {
        return "当前连接数：" + dataSource.getActiveCount();
    }
}
