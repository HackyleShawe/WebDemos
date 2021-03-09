package statement;

import org.junit.Test;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

    /**
     * 测试DML、DDL
     *
     * int executeUpdate(String sqlString)
     * 用于运行INSERT、UPDATE或DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等。返回影响作用的行数。
     */
    @Test
    public void testExecuteUpdate() {
        String sql = "INSERT INTO person (name, age, sex, birthday, address, tel, email) " +
                " VALUES ('桃乃木香奈',19,'woman','2001-01-03','JP','134324324','aaaaa32432@javbu.com') ";
        try {
            Connection connection = ConnectionUtil.getConnectionByReflect();
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("执行完毕，影响行数："+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试DQL
     *
     * ResultSet executeQuery(String sqlString)
     *   运行查询数据库的SQL语句。返回一个结果集（ResultSet）对象。
     */
    @Test
    public void testExecuteQuery() {
        String sql = "select * from person ";
        try {
            Connection conn = ConnectionUtil.getConnectionByReflect();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String sex = resultSet.getString("sex");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String tel = resultSet.getString("tel");
                String email = resultSet.getString("email");
                System.out.println(name+" "+age+" "+sex+" "+birthday+" "+address+" "+tel+" "+email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
