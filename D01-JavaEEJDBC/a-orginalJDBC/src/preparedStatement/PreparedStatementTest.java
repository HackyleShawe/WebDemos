package preparedStatement;

import org.junit.Test;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {

    @Test
    public void testUpdate() {
        String sql = "INSERT INTO person (name, age, sex, birthday, address, tel, email) " +
                " VALUES ('桃乃木香奈',?,?,'2001-01-03','JP','134324324','aaaaa32432@javbu.com') ";
        try {
            Connection connection = ConnectionUtil.getConnectionByReflect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,20);
            ps.setString(2,"woman");
            int rows = ps.executeUpdate();
            System.out.println("执行完毕，影响行数："+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testQuery() {
        String sql = "select * from person where sex=? ";
        try {
            Connection conn = ConnectionUtil.getConnectionByReflect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "woman");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                String age = rs.getString("age");
                String sex = rs.getString("sex");
                String birthday = rs.getString("birthday");
                String address = rs.getString("address");
                String tel = rs.getString("tel");
                String email = rs.getString("email");
                System.out.println(name+" "+age+" "+sex+" "+birthday+" "+address+" "+tel+" "+email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
