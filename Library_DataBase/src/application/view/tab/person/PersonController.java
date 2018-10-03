package application.view.tab.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PersonController {
	
	@FXML
	private JFXTextField 
		userSearch, firstName, lastName, street, houseNumber, 
		plz, city , country  , tel     , email , username   ;
	
	@FXML
	private JFXPasswordField password;
	
	@FXML
	private JFXToggleButton 
		isCustomer, isStaff, isNewPerson;
	
	@FXML
	private JFXDatePicker 
		customerCal, staffCal;
	
	@FXML
	private JFXListView<Label> personListView;
	
	@FXML
	private JFXButton personSubmit;
	
	@FXML
	private JFXComboBox<String> store;
	
	
	
	private SQLPerson SQL = new SQLPerson();
	
	private ArrayList<Person> personSearchList;
	
	
	@FXML
	public void initialize() {
		
		store.getItems().addAll(SQL.getStores());

	}
	
	
	
	
	@FXML
	private void loadPersonSearchData() {
		
		personListView.getItems().clear();
		
		if (!userSearch.getText().isEmpty()) {
			
			personSearchList = SQL.loadPersonSearchData(userSearch.getText());
			
			for (Person p : personSearchList) {
				personListView.getItems().add(new Label(p.getNameLabel()));
			}
		} 
		
	}
	
	private void setBasicPersonLabel(Person p) {
		
		firstName  .setText(p.getFirstName  ());
		lastName   .setText(p.getLastName   ());
		street     .setText(p.getStreet     ());
		houseNumber.setText(p.getHouseNumber());
		plz        .setText(p.getPlz        ());
		city       .setText(p.getCity       ());
		country    .setText(p.getCountry    ());
		tel        .setText(p.getTel        ());
		email      .setText(p.getEmail      ());
	}
	
	@FXML
	private void loadPersonToLabel() {
		
		int listIndex = personListView.getSelectionModel().getSelectedIndex();
		
		if (listIndex >= 0) {
			Person p = personSearchList.get(listIndex);
			
			if (p.getStreet() == null || p.getStreet().isEmpty()) SQL.loadPersonData(p);
			
			setBasicPersonLabel(p);
			
			/** CUSTOMER CAL **/
			isCustomer.setSelected(p.isCustomer());
			setCustomerCalActiveState();
			
			customerCal.setValue((p.isCustomer()) ?  getDateOfString(p.getCustomerCal()) : null);

			
			/** STAFF CAL **/			
			isStaff.setSelected(p.isStaff());
			setStaffCalActiveState();
			
			
			if (p.isStaff()) {
				staffCal.setValue(getDateOfString(p.getStaffCal()));
				
				username.setText (p.getUsername());
				password.setText (p.getPassword());
				
				store.getSelectionModel().select(p.getStore_id() - 1);
			
			} else {setStaffLabelNull();}
			
			isNewPerson.setSelected(false);
		}
	}
	
	private LocalDate getDateOfString(String date) {
		
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	private String formatDateOfCal(LocalDate date) {

		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	@FXML
	private void setStaffCalActiveState() {
		
		boolean active = !isStaff.isSelected();

		username.setDisable(active);
		password.setDisable(active);
		staffCal.setDisable(active);
		store   .setDisable(active);
	}
	
	@FXML
	private void setCustomerCalActiveState() {
		
		customerCal.setDisable(!isCustomer.isSelected());
		
	}
	
	private void setStaffLabelNull() {
		
		staffCal.setValue(null);
		username.setText (null);
		password.setText (null);
		
		store.getSelectionModel().select(null);
	}
	
	@FXML
	private void resetAll() {
		
		firstName  .setText("");
		lastName   .setText("");
		street     .setText("");
		houseNumber.setText("");
		plz        .setText("");
		city       .setText("");
		country    .setText("");
		tel        .setText("");
		email      .setText("");
		
		isCustomer .setSelected(false);
		isStaff    .setSelected(false);
		isNewPerson.setSelected(true );
		
		setStaffLabelNull();
	}
	
	@FXML
	private void autoFill() {
		String [] result = SQL.getAutoFill(plz.getText());
		
		city   .setText(result[0]);
		country.setText(result[1]);
	}
	
	@FXML
	private void updateDB() {
		
		if (advInputCheck()) {
			if (isNewPerson.isSelected()) addNewPersonToDB();
			else                          updatePersonInDB();
		}
		
	}
	
	private int getAddressID(String countryName, String plzID, String cityName, String streetName) {
		
		SQL.newCity(plzID, cityName, SQL.newCountry(countryName));

		return SQL.newAddress(streetName, plzID);
		
	}
	
	private void addNewPersonToDB() {
		
		int addressID = getAddressID(country.getText(),    plz.getText(), 
										 city.getText(), street.getText());
		
		// CREATE NEW PERSON
		Person p = new Person(firstName.getText(), lastName.getText(), 
							houseNumber.getText(),      tel.getText(), 
							email      .getText(), addressID);
		
		p.setID(SQL.insertBasicPerson(p));
		
		
		System.out.println("Person Added to DB: " + (p.getID() > 0));
		// TODO: USER POPUP ... :)

		if (isCustomer.isSelected()) {

			// Insert to DB
			boolean completedCustomer = 
					SQL.insertCustomer(p.getID(), formatDateOfCal(customerCal.getValue()));
			
			System.out.println("Customer Added to DB: " + completedCustomer);
		}

		if (isStaff.isSelected()) {
			
			// Insert to DB
			boolean completedStaff = 
					SQL.insertStaff(p.getID(), username.getText(), password.getText(), 
											formatDateOfCal(staffCal.getValue()), 
											store.getSelectionModel().getSelectedIndex() + 1);
			
			System.out.println("Staff Added to DB: " + completedStaff);
		}
		
	}
	
	private void updatePersonInDB() {
		
		Person p = update(personSearchList.get(
							personListView.getSelectionModel().getSelectedIndex()));
		
		boolean basic = SQL.updateBasicPerson(p);
		System.out.println("UPDATED BASIC INFO... : " + basic);
		
		/** CUSTOMER **/
		boolean customer = SQL.updateCustomer(p.getID(), p.getCustomerCal(), p.isCustomer());
		System.out.println("UPDATED CUSTOMER... : " + customer);
		
		/** STAFF **/
		boolean staff = SQL.updateStaff(p);
		System.out.println("UPDATED STAFF... : " + staff);
		
		
	}

	private Person update(Person p) {

		p.setFirstName  (firstName  .getText());
		p.setLastName   (lastName   .getText());
		p.setStreet     (street     .getText());
		p.setHouseNumber(houseNumber.getText());
		p.setTel        (tel        .getText());
		p.setEmail      (email      .getText());
		p.setPlz        (plz        .getText());
		p.setCity       (city       .getText());
		p.setCountry    (country    .getText());
		
		p.setCustomer(isCustomer.isSelected());
		p.setStaff   (isStaff   .isSelected());
		
		
		p.setCustomerCal((p.isCustomer()) ? formatDateOfCal(customerCal.getValue()) : null);
		
		
		if  (p.isStaff()) {
			 p.setStaffCal(formatDateOfCal(staffCal.getValue()));
			 p.setUsername(username.getText());
			 p.setPassword(password.getText());
			 p.setStore_id(store.getSelectionModel().getSelectedIndex() + 1);
			 
		} else {
			 p.setStaffCal(null);
			 p.setUsername(null);
			 p.setPassword(null);
		}
		
		p.setAddress_id(getAddressID(p.getCountry(), p.getPlz(), p.getCity(), p.getStreet()));
		
		return p;
	}
	
	
	
	
	
	
	private boolean basicInputCheck() { // TODO: Highlight wrong Input?
		
		if (firstName.getText().isEmpty() || lastName   .getText().isEmpty() || 
			street   .getText().isEmpty() || houseNumber.getText().isEmpty() ||
			tel      .getText().isEmpty() || plz        .getText().isEmpty() || 
			city     .getText().isEmpty() || country    .getText().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	private boolean advInputCheck() {   // TODO: MAYBE CREATE User MSG?
		
		if (basicInputCheck()) {
			/** CUSTOMER CALENDER TEST **/
			if (isCustomer.isSelected() && customerCal.getValue() == null) {
				System.out.println("CUSTOMER CAL...");
				return false;
			}
			
			/** STAFF INFO TEST **/
			if (isStaff.isSelected()) {
				if (staffCal.getValue() == null) {
					System.out.println("STAFF CAL...");
					return false;
				}
				
				if (username.getText() == null || username.getText().isEmpty()) {
					
					System.out.println("NO USERNAME ENTERED...");
					return false;
				}
						
				if (password.getText() == null || password.getText().isEmpty()) {
					System.out.println("NO PASSWORD ENTERED...");
					return false;
				}
				
				if (store.getSelectionModel().isEmpty()) {
					System.out.println("NO STORE SELECTED...");
					return false;
				}
			}
			return true;
		}
		return false;
	}


	
	
	
	
	
	

}














