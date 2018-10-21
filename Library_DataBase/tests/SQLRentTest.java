import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SQLRentTest {

    private Connection DBCon;
    private Statement st;
    private ResultSet rs;

    @Test(expected = NullPointerException.class)
    public void getStores() throws SQLException {

        String query = "";
        rs = st.executeQuery(query);
    }

    @Test
    public void getStoreIDFromStaff() {
    }

    @Test
    public void loadPersonSearchData() {
    }

    @Test
    public void loadBookSearchData() {
    }

    @Test
    public void loadBookRentData() {
    }

    @Test
    public void loadRentsOfPerson() {
    }

    @Test
    public void loadBooksOfRent() {
    }

    @Test
    public void deleteRent() {
    }

    @Test
    public void deleteBookFromRent() {
    }

    @Test
    public void addRent() {
    }

    @Test
    public void getFreeInventoryIDFROMBookID() {
    }

    @Test
    public void bookToRent() {
    }

    @Test
    public void updateRent() {
    }
}