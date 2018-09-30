package application;

public class Person {

	private int ID;
	
	private String firstName, lastName, street, str_nmbr, plz, city, country, tel, 
				   email, username, password, customerCal, staffCal;
	
	private boolean Customer, Staff;
	
	
	
	public Person(int ID, String firstName, String lastName) {
		this.ID = ID;
		
		this.firstName = firstName;
		this.lastName  = lastName;
	}



	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStr_nmbr() {
		return str_nmbr;
	}
	public void setStr_nmbr(String str_nmbr) {
		this.str_nmbr = str_nmbr;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerCal() {
		return customerCal;
	}
	public void setCustomerCal(String customerCal) {
		this.customerCal = customerCal;
	}
	public String getStaffCal() {
		return staffCal;
	}
	public void setStaffCal(String staffCal) {
		this.staffCal = staffCal;
	}
	public boolean isCustomer() {
		return Customer;
	}
	public void setCustomer(boolean customer) {
		Customer = customer;
	}
	public boolean isStaff() {
		return Staff;
	}
	public void setStaff(boolean staff) {
		Staff = staff;
	}
	
	
	
}
