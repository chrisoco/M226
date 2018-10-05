package application.view.tab.rent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Main;
import application.view.tab.person.Person;

public class SQLRent {

	private Connection DBCon;
	private Statement  st;
	private ResultSet  rs;
	
	public SQLRent() {
		try {
			this.DBCon = Main.db.getDBCon();
			this.st    = DBCon.createStatement();
			
		} catch (SQLException e) {}
		
	}
	
	
	public ArrayList<String> getStores(){
		
		ArrayList<String> result = new ArrayList<>();
		
		String query = "SELECT "
						+ "s.store_id, s.storeName, a.tbl_city_fk AS plz, c.cityName "
						+ "FROM tbl_store AS s "
						
						+ "LEFT JOIN tbl_address AS a "
						+ "ON s.tbl_address_fk = a.address_id "
						
						+ "LEFT JOIN tbl_city AS c "
						+ "ON a.tbl_city_fk = c.city_plz_id "
						
						+ "ORDER BY store_id ASC;";
		
		try {
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				result.add(String.format("%-10s %s", 
						rs.getString("storeName"), rs.getString("cityName")));
			}	
		} 
		catch (SQLException e) {}
		
		return result;
		
	}
	
	
	public ArrayList<Person> loadPersonSearchData(String userSearch) {
		
		ArrayList<Person> searchResult = new ArrayList<Person>();
		
		String query =    "SELECT "
							+ "* "
						+ "FROM "
							+ "m153.tbl_person "
						+ "WHERE "
							+ "lastName LIKE '" + userSearch + "%'"
						+ "ORDER BY "
							+ "lastName, firstName;";
		
		int i = 0;
		
		try {
			rs = st.executeQuery(query);
		
			while (rs.next()) {
				if(i > 15) break;
				
				searchResult.add(new Person(rs.getInt("person_id"), rs.getString("firstName"), rs.getString("lastName")));
				
				i++;
			}
		
		} catch (SQLException e) {}
		
		
		return searchResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
