/**
 * Rent Class
 * 
 * @author Christopher O'Connor
 * @version 1.0
 * @date 12.10.2018
 * 
 */

package application.view.tab.rent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javafx.scene.control.Label;

public class Rent {

	private int rental_id, anz, storeID;
	
	private double rentPrice;
	
	private String startRentalDate, endRentalDate, expiresDate, storeName;

	private ArrayList<Book> bookList;
	
	
	/**
	 * Custom constructor Instantiates the Rent Class.
	 * @param rental_id ID in DataBase of the Rent.
	 * @param anz The Amount of Books in the Rent
	 * @param startRentalDate Start Date of the Rent.
	 * @param endRentalDate End Date of the Rent.
	 * @param expiresDate Expires Date of the Rent.
	 * @param storeID The Store ID Where the Rent is made.
	 * @param storeName The Store Name Where the Rent is made.
	 */
	public Rent(int rental_id, int anz, String startRentalDate, 
				  String endRentalDate, String expiresDate, int storeID, String storeName) {
		
		this.rental_id       = rental_id;
		this.anz             = anz;
		this.startRentalDate = startRentalDate;
		this.endRentalDate   = endRentalDate;
		this.expiresDate     = expiresDate;
		this.storeName       = storeName;
		this.storeID         = storeID;
		
	}
	
	
	/**
	 * Custom constructor Instantiates the Rent Class.
	 * @param startRentalDate Start Date of the Rent.
	 * @param expiresDate Expires Date of the Rent.
	 * @param storeID The Store ID Where the Rent is made.
	 */
	public Rent(String startRentalDate, String expiresDate, int storeID) {
		
		this.startRentalDate = startRentalDate;
		this.expiresDate     = expiresDate;
		this.storeID         = storeID;

		this.storeName       = "New Rent";
		this.anz             = 0;
		this.rentPrice       = 0.00;
		
		this.bookList = new ArrayList<>();
	}
	
	
	/**
	 * Get Rent Values and Return them as a Label to the Call.
	 * @return Label returns Label with Info of the Rent (Start, End, Expires Date, Books Count, Store Name and Price.
	 */
	public Label toLabel() {
		return new Label(String.format("%-15s %-15s %5d %-5s %-10s %16.2f CHF.-", startRentalDate, endRentalDate, anz, " ", storeName, rentPrice));
	}
	
	
	/**
	 * Calculates the Price of the Rent:
	 * ENDDATE - STARTDATE = DAYS *= rentPrice.
	 */
	public void calcPriceOfRent() {
		
		this.rentPrice = 0.0;
		
		for(Book b : bookList) {
			this.rentPrice += Double.parseDouble(b.getPrice_day().replace(" CHF", ""));
		}
		
		if (endRentalDate != null) 
			 rentPrice *= ChronoUnit.DAYS.between(
					getDateOfString(startRentalDate), getDateOfString(endRentalDate));
		
		else rentPrice *= ChronoUnit.DAYS.between(
					getDateOfString(startRentalDate), LocalDate.now());
		
	}
	
	
	/**
	 * Formats String (yyyy-MM-dd) to LocalDate Value.
	 * However, If the String is null, return null.
	 * 
	 * @param date String Value
	 * @return LocalDate Value
	 * 
	 */
	private LocalDate getDateOfString(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	

	
	public int getRental_id() {
		return rental_id;
	}
	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}
	public int getAnz() {
		return anz;
	}
	public void setAnz(int anz) {
		this.anz = anz;
	}
	public String getStartRentalDate() {
		return startRentalDate;
	}
	public void setStartRentalDate(String startRentalDate) {
		this.startRentalDate = startRentalDate;
	}
	public String getEndRentalDate() {
		return endRentalDate;
	}
	public void setEndRentalDate(String endRentalDate) {
		this.endRentalDate = endRentalDate;
	}
	public String getExpiresDate() {
		return expiresDate;
	}
	public void setExpiresDate(String expiresDate) {
		this.expiresDate = expiresDate;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public ArrayList<Book> getBookList() {
		return bookList;
	}
	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}
	public double getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

}
