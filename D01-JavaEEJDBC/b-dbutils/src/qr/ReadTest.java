package qr;

import entity.Person;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReadTest {
    private Connection connection = ConnectionUtil.getConnection();
    private QueryRunner qr = new QueryRunner();
    private String sql = "select * from person ";

    /**
     * 将查询结果存于一个Object数组中：
     * ArrayHandler类功能：将结果集第一行存储到Object数组中
     * ArrayListHandler类功能：将结果集全部存储到Object数组中
     */
    @Test
    public void testArrayHandler() {
        try {
            System.out.println("第一行数据：");
            Object[] result = qr.query(connection,sql,new ArrayHandler());
            for(Object e:result) {
                System.out.print(e + " ");
            }

            System.out.println("\n\n全部数据：");
            List<Object[]> resultList = qr.query(connection,sql,new ArrayListHandler());
            for(Object[] list:resultList) {
                System.out.println(Arrays.toString(list));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将查询结果存储于一个JavaBean中：
     * BeanHandler类功能：将第一条记录存放到一个JavaBean中
     * BeanListHandler类功能：将所有记录存放到一个List集合包装的JavaBean中
     */
    @Test
    public void testBeanHandler() {
        try {
            System.out.println("第一行数据：");
            Person person = qr.query(connection,sql,new BeanHandler<>(Person.class));
            System.out.println(person);

            System.out.println("\n全部数据：");
            List<Person> list = qr.query(connection,sql,new BeanListHandler<>(Person.class));
            for(Person e:list) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 将结果集中的指定列的数据值，存放到一个List集合中
     */
    @Test
    public void testColumnListHander() {
        try {
            List<Object> columnList = qr.query(connection,sql,new ColumnListHandler<Object>("name"));
            System.out.println("列名为name的所有值为：" +columnList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将查询结果封装到Map中：
     * MapHandler类功能：将结果集的第一行数据封装到Map集合中，其中“键”是列名，“值”是这列的数据；
     * MapListHandler类功能：将结果集的全部封装到List的Map<字段名，字段值>中
     */
    @Test
    public void testMapHandler() {
        try {
            Map<String,Object> map = qr.query(connection,sql,new MapHandler());
            System.out.println(map);

            List<Map<String,Object>> personMap = qr.query(connection,sql,new MapListHandler());
            for (Map<String, Object> mapEle : personMap) {
                for(String key:mapEle.keySet()) {
                    System.out.println("key:"+key+"  value:"+mapEle.get(key));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
