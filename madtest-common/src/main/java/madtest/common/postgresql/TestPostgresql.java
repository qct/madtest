package madtest.common.postgresql;

import org.postgresql.core.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alex on 2016/10/21.
 */
public class TestPostgresql {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:postgresql://192.168.2.61/test";
        String user = "root";
        String password = "123";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = LoggerFactory.getLogger(Version.class.getName());
            lgr.debug(ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = LoggerFactory.getLogger(Version.class.getName());
                lgr.debug(ex.getMessage(), ex);
            }
        }
    }
}
