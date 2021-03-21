package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory;

    /*
     * 加载配置文件
     */
    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("Mybatis-config.xml"); //加载主配置文件
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        if(sqlSessionFactory != null) {
            return sqlSessionFactory.openSession();
        }
        return null;
    }
}
