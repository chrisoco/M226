package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		
		String query = "SELECT tbl_person_fk, password FROM m153.tbl_staff " + 
						"WHERE username = '" + userName + "';";
		
		try {
			rs = st.executeQuery(query);
			
			if(rs.next()) {
				if (rs.getString("password").equals(userPW)) {
					staff_ID = rs.getInt("tbl_person_fk");
					return true;
				}
			}
			
			rs.close();
		} 
		catch (SQLException e) {}
		
		return false;
	}


	public ArrayList<Person> loadPersonSearchData(String userSearch) {
		
		ArrayList<Person> searchResult = new ArrayList<Person>();
		
		String query = "SELECT * FROM m153.tbl_person "
					 + "WHERE lastName LIKE '" + userSearch + "%'"
					 + "ORDER BY lastName, firstName;";
		
		int i = 0;
		
		try {
			rs = st.executeQuery(query);
		
			while (rs.next()) {
				if(i > 10) break;
				
				searchResult.add(new Person(rs.getInt("person_id"), rs.getString("firstName"), rs.getString("lastName")));
				
				i++;
			}
		
		} catch (SQLException e) {}
		
		
		return searchResult;
	}
	
	
	
	
	
	
	public int getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(int staff_ID) {
		this.staff_ID = staff_ID;
	}
	
	
}
