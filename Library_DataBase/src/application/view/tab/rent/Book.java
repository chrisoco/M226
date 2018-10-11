package application.view.tab.rent;

public class Book {

	private int invID, bID, anz;
	
	private String bTitle, price_day;
	
	
	
	public Book(int bID, int anz, String bTitle) {
		
		this.bID    = bID;
		this.bTitle = bTitle;
		this.anz    = anz;
	}

	public Book(int bID, int anz) {
		
		this.bID    = bID;
		this.anz    = anz;
	}
	
	public Book(int invID, int bID, String bTitle, String price_day) {
		
		this.invID     = invID;
		this.bID       = bID;
		this.bTitle    = bTitle;
		this.price_day = price_day;
		
		
	}
	
	
	
	
	public String toLabel() {
		return String.format("%-20s (%d)", this.bTitle, this.anz);
	}
	
	
	public String toLabel2() {
		return String.format("%-64s %10s.-", this.bTitle, this.price_day);
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
