package util;

import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * 初学者的数据库连接工具类
 */
public class ConnectionUtil {
    private static Properties properties;

    /*
     * 读取配置文件
     */
    static {
        InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
        try {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过new驱动程序来连接
     *
     * @return Connection
     */
    public static Connection getConnectionByNewDriver() {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            connection = driver.connect(properties.getProperty("url"), properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 使用驱动管理器类连接数据库
     *
     * @return Connection
     */
    public static Connection getConnectionByDriverManager() {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(properties.getProperty("url"),properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


    public static Connection getConnectionByReflect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(properties.getProperty("url"),properties);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
