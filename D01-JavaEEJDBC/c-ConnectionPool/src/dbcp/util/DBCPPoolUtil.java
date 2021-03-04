package dbcp.util;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * DBCP单例(饿汉模式)连接池
 */
public class DBCPPoolUtil {
    private static DBCPPoolUtil singleInstace = new DBCPPoolUtil();
    private BasicDataSource dataSource;

    private DBCPPoolUtil() {
        try {
            InputStream is = DBCPPoolUtil.class.getClassLoader().getResourceAsStream("dbcp.properties");
            Properties pros = new Properties();
            pros.load(is);
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            System.out.println("读取配置文件dbcp.properties异常，请检查！");
            e.printStackTrace();
        }
    }

    public static DBCPPoolUtil getInstance() {
        return singleInstace;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void releaseConnection(Connection conn) {
        try{
            conn.close();
        } catch (Exception e) {
            System.out.println("关闭连接失败");
            e.printStackTrace();
        }
    }

    public String getPoolInfo() {
        return "正在使用连接数："+dataSource.getNumActive();
    }
}
