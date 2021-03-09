package util;


import org.apache.commons.dbutils.DbUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DButils用法
 */
public class ConnectionUtil {
    private static Properties properties;

    /*
     * 加载配置文件
     */
    static {
        try {
            properties = new Properties();
            InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        //DbUtils.loadDriver("com.mysql.jdbc.Driver"); 由于我的MySQL驱动程序版本较高，名字变为了com.mysql.cj.jdbc.Driver
        DbUtils.loadDriver("com.mysql.cj.jdbc.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"),properties);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
