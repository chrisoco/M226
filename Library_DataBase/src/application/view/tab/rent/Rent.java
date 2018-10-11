package application.view.tab.rent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Rent {

	private int rental_id, anz, storeID;
	
	private String startRentalDate, endRentalDate, expiresDate, storeName;

	private ArrayList<Book> bookList;
	
	private double rentPrice;
	
	
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
	
	
	public Rent(String startRentalDate, String expiresDate, int storeID) {
		
		this.startRentalDate = startRentalDate;
		this.expiresDate     = expiresDate;
		this.storeID         = storeID;

		this.storeName       = "New Rent";
		this.anz             = 0;
		this.rentPrice       = 0.00;
		
		this.bookList = new ArrayList<>();
	}
	
	
	
	
	public String toLabel() {
		return String.format("%-15s %-15s %5d %-5s %-10s %16.2f CHF.-", startRentalDate, endRentalDate, anz, " ", storeName, rentPrice);
	}
	
	
	public void calcPriceOfRent() {
		
		this.rentPrice = 0.0;
		
		for(Book b : bookList) {
			this.rentPrice += Double.parseDouble(b.getPrice_day().replace(" CHF", ""));
		}
		
		// ENDDATE - STARTDATE = DAYS -> DAYS * rentPrice.
		if (endRentalDate != null) 
			 rentPrice *= ChronoUnit.DAYS.between(
					getDateOfString(startRentalDate), getDateOfString(endRentalDate));
		
		else rentPrice *= ChronoUnit.DAYS.between(
					getDateOfString(startRentalDate), LocalDate.now());
		
	}
	
	
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
