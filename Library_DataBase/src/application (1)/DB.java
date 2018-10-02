package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.view.tab.person.Person;

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
			
		} 
		catch (SQLException e) {}
		
		return false;
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
	
	
	public void loadPersonData(Person p) {
		
		String basic = "SELECT "
					 	+ "p.person_id, p.firstName, p.lastName, p.email, p.tel, p.str_nmbr, "
					 	+ "a.street, a.tbl_city_fk AS plz, "
					 	+ "c.cityName AS city, "
					 	+ "co.countryName AS country "
					 
					 + "FROM tbl_person AS p " 
					 
					 	+ "LEFT JOIN tbl_address AS a "
					 	+ "ON p.tbl_address_fk = a.address_id " 
					 	
					 	+ "LEFT JOIN tbl_city AS c "
					 	+ "ON a.tbl_city_fk = c.city_plz_id "
					 	
					 	+ "LEFT JOIN tbl_country AS co "
					 	+ "ON c.tbl_country_fk = co.country_id "
					 
					 + "WHERE person_id = " + p.getID() + ";";
		
		
		String customer = "SELECT * FROM tbl_customer "
						+ "WHERE tbl_person_fk = " + p.getID() + ";";
		
		String staff    = "SELECT * FROM tbl_staff "
						+ "WHERE tbl_person_fk = " + p.getID() + ";";
		 

		try {
			
			rs = st.executeQuery(basic);
			rs.next();
			
			p.setEmail   (rs.getString("email"));
			p.setTel     (rs.getString("tel"));
			p.setStreet  (rs.getString("street"));
			p.setHouseNumber(rs.getString("str_nmbr"));
			p.setPlz     (rs.getString("plz"));
			p.setCity    (rs.getString("city"));
			p.setCountry (rs.getString("country"));
			
			
			rs = st.executeQuery(customer);
			if (rs.next()) {
				p.setCustomer(true);
				p.setCustomerCal(rs.getString("since"));
			}
			
			
			rs = st.executeQuery(staff);
			if (rs.next()) {
				p.setStaff(true);
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("password"));
				p.setStaffCal(rs.getString("since"));
				p.setStore_id(rs.getInt   ("tbl_store_fk"));
			}
			
			
			
			
			} catch (SQLException e) {
				System.out.println(e);
			}
		
	}
	
	
	public String [] getAutoFill(String plz) {
		
		String query = 	"SELECT "
							+ "c.city_plz_id AS plz, c.cityName AS city, co.countryName AS country "
							+ "FROM tbl_city AS c "
						
							+ "LEFT JOIN tbl_country AS co "
							+ "ON c.tbl_country_fk = co.country_id "
					  
					  + "WHERE c.city_plz_id = " + plz +";";
		
		String [] result = {"", ""};
		
		try {
			rs = st.executeQuery(query);
			
			if (rs.next()) {
				result[0] = rs.getString("city");
				result[1] = rs.getString("country");
			}
			
		} catch (SQLException e) {}
		
		return result;
	}
	
	
	public int newCountry(String countryName) {
		
		String search = "SELECT "
					  + "country_id FROM tbl_country "
					  + "WHERE countryName = '" + countryName + "';";
		
		String newCountry = "INSERT INTO "
						  + "tbl_country(countryName) "
						  + "VALUE ('" + countryName + "');";
		
		try {
			rs = st.executeQuery(search);
			
			if (rs.next()) {
				return rs.getInt("country_id");
			}
			else {
				insert(newCountry);
				return newCountry(countryName);
			}
			
		} catch (SQLException e) {}
		
		return 0;
	}
	
	
	public void newCity(String city_plz_id, String cityName, int country_id) {
		
		String search = "SELECT "
					  + "* FROM tbl_city "
					  + "WHERE city_plz_id = " + city_plz_id + ";";
		
		String newCountry = "INSERT INTO "
				  		  + "tbl_city VALUES "
				  		  + "('" + city_plz_id + "', '" + cityName + "', '" + country_id + "');";
		
		try {
			rs = st.executeQuery(search);
			
			if (rs.next()) {
				return;
			}
			else {
				insert(newCountry);
				return;
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	
	
	public int newAddress(String streetName, String city_plz_fk) {
		
		String search = "SELECT "
				  	  + "address_id FROM tbl_address "
				  	  + "WHERE street = '" + streetName + "';";
		
		String newAddress = "INSERT INTO "
				  		  + "tbl_address(street, tbl_city_fk) "
				  		  + "VALUES ('" + streetName + "', '" + city_plz_fk + "');";
		
		try {
			rs = st.executeQuery(search);
			
			if (rs.next()) {
				return rs.getInt("address_id");
			}
			else {
				insert(newAddress);
				return newAddress(streetName, city_plz_fk);
			}
			
		} catch (SQLException e) {}
		
		return 0;
	}
	
	
	public int insertBasicPerson(String firstName, String lastName, String str_nmbr, 
							String tel      , String email   , int address_id) {
		
		String query = "INSERT "
						+ "INTO tbl_person "
							+ "(firstName, lastName, email, tel, tbl_address_fk, str_nmbr) "
						+ "VALUES "
							+ "('" + firstName + "', '" + lastName + "', '" + email + "', '" + tel + "', '" + address_id + "', '" + str_nmbr + "');";
		
		
		String getID = "SELECT "
					 	+ "person_id FROM tbl_person "
					 + "WHERE "
					 	+ "firstName = '" + firstName + "' AND "
					 	+ "lastName  = '" + lastName  + "' AND "
					 	+ "str_nmbr  = '" + str_nmbr  + "' AND "
					 	+ "tel       = '" + tel       + "';";
		
		insert(query);
		
		try {
			rs = st.executeQuery (getID );
			
			if (rs.next()) return rs.getInt("person_id");
			
		} catch (SQLException e) {}
		
		return 0;
	}
	
	
	public boolean insertCustomer(int ID, String since) {
		
		String query = "INSERT " 
					 + "INTO tbl_customer "
					 + "(tbl_person_fk, since) "
					 + "VALUES "
					 + "('" + ID + "', '" + since + "');";
		
		if (insert(query) == 1) return true;

		return false;
	}
	
	
	public boolean insertStaff(int ID, String userName, String password, String since, int storeID) {
		
		String query = "INSERT "
						+ "INTO tbl_staff "
							+ "(tbl_person_fk, username, password, wage, since, tbl_store_fk) "
						+ "VALUES "
							+ "('" + ID + "', '" + userName + "', '" + password 
							+ "', '8000.00' , '" + since    + "', '" + storeID + "');";
		
		if(insert(query) == 1) return true;
		
		return false;
	}
	
	
	public boolean updateBasicPerson() {
		
		
		
		
		
		
		
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	private int insert(String query) {
		try {
			return st.executeUpdate(query);
			
		} catch (SQLException e) {}
		return 0;
	}
	
	public int getStaff_ID() {
		return staff_ID;
	}
	public void setStaff_ID(int staff_ID) {
		this.staff_ID = staff_ID;
	}
	
	
	
	
	
	
	
	
}

















