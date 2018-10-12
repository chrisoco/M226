/**
 * Book Class
 * 
 * @author Christopher O'Connor
 * @version 1.0
 * @date 12.10.2018
 * 
 */

package application.view.tab.rent;

import javafx.scene.control.Label;

public class Book {

	private int invID, bID, anz;
	
	private String bTitle, price_day;
	
	
	/**
	 * Custom constructor Instantiates the Book Class.
	 * @param bID The ID of the Book in DataBase.
	 * @param anz The amount of Books currently Available in specific Store Location.
	 * @param bTitle The Title of the Book.
	 */
	public Book(int bID, int anz, String bTitle) {
		
		this.bID    = bID;
		this.bTitle = bTitle;
		this.anz    = anz;
	}

	
	/**
	 * Custom constructor Instantiates the Book Class.
	 * @param bID The ID of the Book in DataBase.
	 * @param anz The amount of Books currently Available in specific Store Location.
	 */
	public Book(int bID, int anz) {
		
		this.bID    = bID;
		this.anz    = anz;
	}
	
	
	/**
	 * Custom constructor Instantiates the Book Class.
	 * @param invID The ID of the Inventory Containing this Book.
	 * @param bID The ID of the Book in DataBase.
	 * @param bTitle The Title of the Book.
	 * @param price_day The Price per Day for this Book.
	 */
	public Book(int invID, int bID, String bTitle, String price_day) {
		
		this.invID     = invID;
		this.bID       = bID;
		this.bTitle    = bTitle;
		this.price_day = price_day;
		
		
	}
	
	
	/**
	 * Get Book Values and Return them as a Label to the Call.
	 * @return Label returns Label with Info of the Book (Title and Amount in Storage).
	 */
	public Label toLabel() {
		return new Label(String.format("%-20s (%d)", this.bTitle, this.anz));
	}
	
	
	/**
	 * Get Book Values and Return them as a Label to the Call.
	 * @return Label returns Label with Info of the Book (Title and Price per Day).
	 */
	public Label toLabel2() {
		return new Label(String.format("%-64s %10s.-", this.bTitle, this.price_day));
	}
	
	
	
	
	
	public int getInvID() {
		return invID;
	}
	public void setInvID(int invID) {
		this.invID = invID;
	}
	public int getbID() {
		return bID;
	}
	public void setbID(int bID) {
		this.bID = bID;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public int getAnz() {
		return anz;
	}
	public void setAnz(int anz) {
		this.anz = anz;
	}
	public String getPrice_day() {
		return price_day;
	}
	public void setPrice_day(String price_day) {
		this.price_day = price_day;
	}
	
}
