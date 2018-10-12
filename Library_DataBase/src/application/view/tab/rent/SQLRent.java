/**
 * SQLRent Class
 * 
 * @author Christopher O'Connor
 * @version 1.0
 * @date 12.10.2018
 * 
 */

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
	
	
	
	/**
	 * Custom constructor Instantiates the SQLRent Class.
	 */
	public SQLRent() {
		try {
			this.DBCon = Main.db.getDBCon();
			this.st    = DBCon.createStatement();
			
		} catch (SQLException e) {}
		
	}
	
	
	
	/**
	 * Get All the Stores and Order them by ID
	 * @return ArrayList List Of all Stores in Correct Order.
	 */
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
				result.add(String.format("%-10s %s", rs.getString("storeName"), rs.getString("cityName")));
			}	
		} 
		catch (SQLException e) {}
		
		return result;
		
	}
	
	
	/**
	 * Get the Store ID from the Currently used StaffMember.
	 * @param staffID The ID of the Currently used StaffMember.
	 * @return int Returns the Store ID from the Staff.
	 */
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
	
	
	/**
	 * Load the PersonSearchData Matching the Value
	 * @param userSearch User Search Criteria from TextField
	 * @return ArrayList List of All Customers matching Search.
	 */
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

		try {
			rs = st.executeQuery(query);
		
			while (rs.next()) {			
				searchResult.add(new Person(rs.getInt("person_id"), rs.getString("firstName"), rs.getString("lastName")));
			}
		
		} catch (SQLException e) {}

		return searchResult;
	}
	
	
	/**
	 * Load all the Books Matching the Search Criteria and selected Store and Merge + Count them.
	 * @param userSearch User Search Criteria.
	 * @param store The Selected Store Index.
	 * @return ArrayList List of all books matching the Criterias.
	 */
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
	
	
	/**
	 * Load all the Books that are not available during specified Time.
	 * @param store Currently Selected Store Index.
	 * @param startDate Start Date of the Rent.
	 * @param endDate End Date of the Rent.
	 * @return ArrayList List of all not available Books.
	 */
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
							+ "(r.endRental IS null) OR "
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
	
	
	/**
	 * Loads all the Rents of the Selected Customer.
	 * @param customerID ID of the currently Selected Customer.
	 * @return ArrayList List of all Rents of Selected Customer.
	 */
	public ArrayList<Rent> loadRentsOfPerson(int customerID){
		
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
				searchResult.add(
						new Rent(rs.getInt("rental_id"), rs.getInt("anz"), rs.getString("startRental"),
								 rs.getString("endRental"), rs.getString("expires"), rs.getInt("tbl_store_fk"), 
								 rs.getString("storeName")));
			}
		
		} catch (SQLException e) {}
		
		return searchResult;
	}
	
	
	/**
	 * Load the Books containing in the Selected Rent.
	 * @param rentID ID of the Currently Selected Rent.
	 * @return ArrayList List of all Books contained in Selected Rent.
	 */
	public ArrayList<Book> loadBooksOfRent(int rentID){
		
		ArrayList<Book> result = new ArrayList<>();
		
		String query = "SELECT "
						+ "i.inventory_id, b.book_id, b.title, b.price_day "
						+ "FROM tbl_listOfRentalBooks AS l "
						
						+ "LEFT JOIN tbl_inventory AS i "
						+ "ON l.tbl_inventory_fk = i.inventory_id "
						
						+ "LEFT JOIN tbl_book AS b "
						+ "ON i.tbl_book_fk = b.book_id "
						
						+ "WHERE l.tbl_rental_fk = '" + rentID + "' "
						
						+ "ORDER BY b.title;";
		
		
		
		try {
			rs = st.executeQuery(query);
		
			while (rs.next()) {
				result.add(new Book(rs.getInt("inventory_id"), rs.getInt("book_id"),
									rs.getString("title"),     rs.getString("price_day")));
			}
		
		} catch (SQLException e) {}
		
		return result;
	}
	
	
	/**
	 * Delete the Selected Rent and all Book_FKs related.
	 * @param rentID ID of the Currently Selected Rent.
	 */
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
	
	
	/**
	 * Delete the Selected Book from the Selected Rent.
	 * @param rentID The Selected Rent ID.
	 * @param invID The Selected Inventory ID of the Book.
	 */
	public void deleteBookFromRent(int rentID, int invID) {
		
		String removeBook = "DELETE FROM tbl_listofrentalbooks "
							+ "WHERE  tbl_rental_fk = '" + rentID + "' "
							+ "AND tbl_inventory_fk = '" + invID  + "';";
		
		try {
			st.executeUpdate(removeBook);
		
		} catch (SQLException e) {}
		
	}
	
	
	/**
	 * Add new Rent to the DataBase with given params and Return the Rent with the associated RentID.
	 * @param newRent Rent with Default Date Values.
	 * @param staffID ID of the current Staff Member.
	 * @param customerID ID of the current Selected Customer.
	 * @param storeID ID of the currently Selected Store.
	 * @return Rent Returns the Rent as OBJ with the associated RentID.
	 */
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
		
		} catch (SQLException e) {}
		
		return newRent;
	}
	
	
	/**
	 * Get a Free Inventory ID from a Book between start AND end Date + the Given Store.
	 * @param newBook The Book which we want to search a new Inventory ID.
	 * @param startDate Start Date of the Selection.
	 * @param endDate End Date of the Selection.
	 * @param storeID ID of the Selected Store.
	 * @return Book Return the modified Book with its new Free Inventory ID.
	 */
	public Book getFreeInventoryIDFROMBookID(Book newBook, String startDate, String endDate, int storeID) {
		
		ArrayList<Integer> allInvIDs  = new ArrayList<>();
		ArrayList<Integer> notValidID = new ArrayList<>();
		
		String fillAll = "SELECT "
						+ "inventory_id "
						+ "FROM tbl_inventory "
						+ "WHERE tbl_book_fk = '" + newBook.getbID() + "' "
						+ "AND  tbl_store_fk = '" + storeID + "';";
		
		String notValid = "SELECT  "
							+ "i.inventory_id AS ID "
							+ "FROM tbl_listOfRentalBooks AS l "
							
							+ "LEFT JOIN tbl_rental AS r "
							+ "ON l.tbl_rental_fk = r.rental_id "
							
							+ "LEFT JOIN tbl_inventory AS i "
							+ "ON l.tbl_inventory_fk = i.inventory_id "
							
							+ "WHERE i.tbl_store_fk = '" + storeID + "' AND i.tbl_book_fk = '" + newBook.getbID() + "' "
								+ "AND ( (r.endRental IS null) OR "
								+ "('" + endDate   + "' >= r.startRental AND '" + endDate   + "' <= r.endRental  ) OR "
								+ "('" + startDate + "' <= r.endRental   AND '" + startDate + "' >= r.startRental) OR "
								+ "('" + startDate + "' <= r.startRental AND '" + endDate   + "' >= r.endRental  ));";
		
		
		String getPrice = "SELECT "
							+ "price_day "
							+ "FROM tbl_book "
							+ "WHERE book_id = '" + newBook.getbID() + "';";
		
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
			
			rs = st.executeQuery(getPrice);
			if (rs.next()) newBook.setPrice_day(rs.getString("price_day"));
			
		
		} catch (SQLException e) {}
		
		return newBook;
	}
	
	
	/**
	 * Add Book to a existing Rent.
	 * @param inventoryID ID of the Selected Book.
	 * @param rentalID ID of the Selected Rent.
	 */
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
	
	
	/**
	 * Update the Selected Rent with new Dates.
	 * @param rent Selected Rent.
	 */
	public void updateRent(Rent rent) {
		
		String update = "UPDATE "
							+ "tbl_rental "
							+ "SET "
								+ "startRental  = '" + rent.getStartRentalDate() + "', "
								+ "endRental    = '" + rent.getEndRentalDate()   + "', "
								+ "expires      = '" + rent.getExpiresDate()     + "' "
								
							+ "WHERE (rental_id = '" + rent.getRental_id() + "');";
		
		try {
			st.executeUpdate(update);
			
		} catch (SQLException e) {}
		
	}
	
}
