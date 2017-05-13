package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by quchentao on 2016/11/1.
 */
public class JdbcTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.2.10:3306/qct?"
                + "user=root&password=123&useUnicode=true&characterEncoding=UTF8";
        String sql = "select id,name from person";
        Connection conn = null;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("id: " + id + ", name: " + name);
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
