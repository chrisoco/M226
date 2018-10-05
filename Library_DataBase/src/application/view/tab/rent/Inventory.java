package application.view.tab.rent;

public class Inventory {

	private int invID, bID;
	private String bTitle;
	
	
	public Inventory(int invID, int bID, String bTitle) {
		
		this.invID  = invID;
		this.bID    = bID;
		this.bTitle = bTitle;
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
	
	
	
	
	
	
}
