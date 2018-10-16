import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DBTest {

    @Test(expected = SQLException.class)
    public void login() throws SQLException {
        Connection DBCon = DriverManager.getConnection("", "", "");
        Statement st = DBCon.createStatement();
    }
}