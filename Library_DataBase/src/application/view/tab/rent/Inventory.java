package application.view.tab.rent;

public class Inventory {

	private int invID, bID, anz;
	private String bTitle;
	
	
	public Inventory(int bID, int anz, String bTitle) {
		
		this.bID    = bID;
		this.bTitle = bTitle;
		this.anz    = anz;
	}

	public Inventory(int bID, int anz) {
		
		this.bID    = bID;
		this.anz    = anz;
	}
	
	
	
	
	
	public String toLabel() {
		return String.format("%-20s (%d)", this.bTitle, this.anz);
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
	
	
	
	
	
	
}
