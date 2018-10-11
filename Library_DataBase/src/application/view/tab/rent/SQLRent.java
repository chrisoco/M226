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
	
	
	public int getStoreIDFromStaff(int staffID) {
		
		String query = "SELECT "
						+ "tbl_store_fk AS storeID "
						+ "FROM tbl_staff "
						+ "WHERE tbl_person_fk = '" + staffID + "';";
		
		try {
			rs = st.executeQuery(query);
			
			if (rs.next()) return rs.getInt("storeID");
			
		} catch (SQLException e) {}
		
		
		return 0;
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
	
	
	public ArrayList<Book> loadBookSearchData(String userSearch, int store) {
		
		ArrayList<Book> searchResult = new ArrayList<>();
		
		String query = "SELECT " 
						+ "COUNT(b.book_id) AS anz, b.book_id, b.title "
						+ "FROM tbl_inventory AS i " 
						
						+ "LEFT JOIN tbl_book AS b "
						+ "ON i.tbl_book_fk = b.book_id "
						
						+ "WHERE i.tbl_store_fk = '" + store + "' AND b.title LIKE '" + userSearch + "%' "
						
						+ "GROUP BY b.title "
						+ "ORDER BY b.title;";
		
		try {
			rs = st.executeQuery(query);
		
			while (rs.next()) {
				searchResult.add(new Book(rs.getInt("book_id"), rs.getInt("anz"), rs.getString("title")));
			}
		
		} catch (SQLException e) {}
		
		
		return searchResult;
	}
	
	
	public ArrayList<Book> loadBookRentData(int store, String startDate, String endDate) {
		
		ArrayList<Book> searchResult = new ArrayList<>();
		
		String query = "SELECT "
						+ "COUNT(i.tbl_book_fk) AS anz, i.tbl_book_fk AS book_id "
						+ "FROM tbl_listOfRentalBooks AS l " 
						
						+ "LEFT JOIN tbl_rental AS r "
						+ "ON l.tbl_rental_fk = r.rental_id "
						
						+ "LEFT JOIN tbl_inventory AS i "
						+ "ON l.tbl_inventory_fk = i.inventory_id "
						
						+ "WHERE i.tbl_store_fk = '" + store + "' AND ( "
							+ "(r.endRental = null) OR "
							+ "('" + endDate   + "' >= r.startRental AND '" + endDate   + "' <= r.endRental  ) OR "
							+ "('" + startDate + "' <= r.endRental   AND '" + startDate + "' >= r.startRental) OR "
							+ "('" + startDate + "' <= r.startRental AND '" + endDate   + "' >= r.endRental  ) "

						+ ") GROUP BY book_id;";
		
		try {
			rs = st.executeQuery(query);
		
			while (rs.next()) {
				searchResult.add(new Book(rs.getInt("book_id"), rs.getInt("anz")));
			}
		
		} catch (SQLException e) {}
		
		
		return searchResult;
	}
	
	
	public ArrayList<Rent> loadOldRents(int customerID){
		
		ArrayList<Rent> searchResult = new ArrayList<>();
		
		String query = "SELECT "
						+ "COUNT(r.rental_id) AS anz, r.rental_id, r.startRental, r.endRental, r.expires, r.tbl_store_fk, s.storeName "
						+ "FROM tbl_listofrentalbooks AS l "
						
						+ "LEFT JOIN tbl_inventory AS i "
						+ "ON l.tbl_inventory_fk = i.inventory_id "
						
						+ "LEFT JOIN tbl_rental AS r "
						+ "ON l.tbl_rental_fk = r.rental_id "
						
						+ "LEFT JOIN tbl_store AS s "
						+ "ON r.tbl_store_fk = s.store_id "
						
						+ "WHERE r.tbl_customer_fk = '" + customerID + "' "
						
						+ "GROUP BY r.rental_id "
						+ "ORDER BY r.startRental;";
		
		try {
			rs = st.executeQuery(query);
		
			while (rs.next()) {
				searchResult.add(new Rent(rs.getInt("rental_id"), rs.getInt("anz"), rs.getString("startRental"),
									   rs.getString("endRental"), rs.getString("expires"), rs.getInt("tbl_store_fk"), rs.getString("storeName")));
			}
		
		} catch (SQLException e) {}
		
		return searchResult;
	}
	
	
	public ArrayList<Book> loadBooksOfRent(int rentID){
		
		ArrayList<Book> result = new ArrayList<>();
		
		String query = "SELECT "
						+ "i.inventory_id, b.book_id, b.title, b.price_day "
						+ "FROM tbl_listOfRentalBooks AS l "
						
						+ "LEFT JOIN tbl_inventory AS i "
						+ "ON l.tbl_inventory_fk = i.inventory_id "
						
						+ "LEFT JOIN tbl_book AS b "
						+ "ON i.tbl_book_fk = b.book_id "
						
						+ "WHERE l.tbl_rental_fk = '" + rentID + "';";
		
		
		
		try {
			rs = st.executeQuery(query);
		
			while (rs.next()) {
				result.add(new Book(rs.getInt("inventory_id"), rs.getInt("book_id"),
									rs.getString("title"),     rs.getString("price_day")));
			}
		
		} catch (SQLException e) {}
		
		return result;
	}
	
	
	public void deleteRent(int rentID) {
		
		String delBooks = "DELETE FROM tbl_listofrentalbooks "
							+ "WHERE tbl_rental_fk = '" + rentID + "';";
		
		
		String delRent  = "DELETE FROM tbl_rental "
							+ "WHERE rental_id = '" + rentID + "';";
		
		try {
			st.executeUpdate(delBooks);
			st.executeUpdate(delRent);
		
		} catch (SQLException e) {}
		
	}
	
	
	public void deleteBookFromRent(int rentID, int invID) {
		
		String removeBook = "DELETE FROM tbl_listofrentalbooks "
							+ "WHERE  tbl_rental_fk = '" + rentID + "' "
							+ "AND tbl_inventory_fk = '" + invID  + "';";
		
		
		try {
			st.executeUpdate(removeBook);
		
		} catch (SQLException e) {}
		
	}
	
	
	public Rent addRent(Rent newRent, int staffID, int customerID, int storeID) {
		
		String addRent = "INSERT "
						+ "INTO tbl_rental "
						+ "(startRental, expires, payed, tbl_staff_fk, tbl_customer_fk, tbl_store_fk) "
						
						+ "VALUES "
						+ "('" + newRent.getStartRentalDate() + "', '" + newRent.getExpiresDate() 
						+ "', '0', '" + staffID + "', '" + customerID + "', '" + storeID + "');";
		
		String getIndex = "SELECT LAST_INSERT_ID() AS ID;";
		
		try {
			st.executeUpdate(addRent);
			rs = st.executeQuery(getIndex);
			
			if (rs.next()) newRent.setRental_id(rs.getInt("ID"));
		
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return newRent;
	}
	
	
	public Book getInvIDOfBook(Book newBook, String startDate, String endDate, int storeID) {
		
		ArrayList<Integer> allInvIDs  = new ArrayList<>();
		ArrayList<Integer> notValidID = new ArrayList<>();
		
		String fillAll = "SELECT "
						+ "inventory_id "
						+ "FROM tbl_inventory "
						+ "WHERE tbl_book_fk = '" + newBook.getbID() + "' "
						+ "AND  tbl_store_fk = '" + storeID + "';";
		
		// TODO: CONTINUE HERE
		String notValid = "SELECT  "
							+ "i.inventory_id AS ID "
							+ "FROM tbl_listOfRentalBooks AS l "
							
							+ "LEFT JOIN tbl_rental AS r "
							+ "ON l.tbl_rental_fk = r.rental_id "
							
							+ "LEFT JOIN tbl_inventory AS i "
							+ "ON l.tbl_inventory_fk = i.inventory_id "
							
							+ "WHERE i.tbl_store_fk = '3' AND i.tbl_book_fk = '3' AND ( "
								+ "(r.endRental = null) OR "
								+ "('" + endDate   + "' >= r.startRental AND '" + endDate   + "' <= r.endRental  ) OR "
								+ "('" + startDate + "' <= r.endRental   AND '" + startDate + "' >= r.startRental) OR "
								+ "('" + startDate + "' <= r.startRental AND '" + endDate   + "' >= r.endRental  ));";
		
		
//		String getPrice = "SELECT " TODO: CONTINUE HERE
		
		try {
			rs = st.executeQuery(fillAll);
			
			while (rs.next()) {
				allInvIDs.add(rs.getInt("inventory_id"));
			}
			
			rs = st.executeQuery(notValid);
			
			while (rs.next()) {
				notValidID.add(rs.getInt("ID"));
			}
			
			
			for (int ALLID : allInvIDs) {
				
				if (!notValidID.contains(ALLID)) {
					newBook.setInvID(ALLID);
					break;
				}
				
			}
			
		
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		
		
		
		return newBook;
	}
	
	
	public void bookToRent(int inventoryID, int rentalID) {
		
		String query  = "INSERT "
						+ "INTO tbl_listofrentalbooks "
							+ "(tbl_inventory_fk, tbl_rental_fk) "
						+ "VALUES "
							+ "('" + inventoryID + "', '" + rentalID + "');";

		try {
			
			st.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	
	
	
}
