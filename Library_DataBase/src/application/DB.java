package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	private Connection DBCon;
	private Statement  st;
	private ResultSet  rs;

	private String jdbcUrl  = "jdbc:mysql://localhost/m153?";
	
	private String settings = "useSSL=false&"
							+ "useUnicode=true&"
							+ "useJDBCCompliantTimezoneShift=true&"
							+ "useLegacyDatetimeCode=false&"
							+ "serverTimezone=UTC";
	
	private int staff_ID;
	
	
	public DB() {
		try {
			this.DBCon = DriverManager.getConnection(jdbcUrl+settings, "JavaApp", "javaapp");
			this.st    = DBCon.createStatement();
			
		} catch (SQLException e) {
			System.out.println("Connection FAILED ...");
		}

		
	}
	
	
	public boolean login(String userName, String userPW) {
		
		String query = "SELECT "
						+ "tbl_person_fk, password "
					+ "FROM "
						+ "m153.tbl_staff "
					+ "WHERE"
						+ " username = '" + userName + "';";
		
		try {
			rs = st.executeQuery(query);
			
			if(rs.next()) {
				if (rs.getString("password").equals(userPW)) {
					staff_ID = rs.getInt("tbl_person_fk");
					return true;
				}
			}
			
		} 
		catch (SQLException e) {}
		
		return false;
	}
	
	

	public Connection getDBCon() {
		return DBCon;
	}
	
	public int getStaff_ID() {
		return staff_ID;
	}
	
}

