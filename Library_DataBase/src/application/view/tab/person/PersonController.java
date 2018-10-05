/**
 *  @author Chris O'Connor
 *  @author Umut Savas
 *  
 *  TODO:
 *  	1) Check UserName UNIQUE ? MarkField Green / Red
 *  	2) Comment All
 *  	3) Are there Improvements?
 *  
 */

package application.view.tab.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
	
	@FXML 
	private StackPane dialogPane;
	
	
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
			if (isNewPerson.isSelected() || 
			 personListView.getSelectionModel().getSelectedIndex() == -1) addNewPersonToDB();
			else                          updatePersonInDB();
		}
		
	}
	
	private int getAddressID(String countryName, String plzID, String cityName, String streetName) {
		
		SQL.newCity(plzID, cityName, SQL.newCountry(countryName));

		return SQL.newAddress(streetName, plzID);
		
	}
	
	private void addNewPersonToDB() {
		
		boolean customer = false, staff = false;
		
		int addressID = getAddressID(country.getText(),    plz.getText(), 
										 city.getText(), street.getText());
		
		// CREATE NEW PERSON
		Person p = new Person(firstName.getText(), lastName.getText(), 
							houseNumber.getText(),      tel.getText(), 
							email      .getText(), addressID);
		
		p.setID(SQL.insertBasicPerson(p));
		

		if (isCustomer.isSelected()) {

			customer = SQL.insertCustomer(p.getID(), formatDateOfCal(customerCal.getValue()));
		}

		if (isStaff.isSelected()) {
			
			// Insert to DB
			staff = SQL.insertStaff(p.getID(), username.getText(), password.getText(), 
							formatDateOfCal(staffCal.getValue()), 
							store.getSelectionModel().getSelectedIndex() + 1);
			
		}
		
		loadDialog( "P: " + p.getFirstName() + " " + p.getLastName() , 
					"Person ADDED : " + (p.getID() > 0) + "\n\n" +
					"isCustomer   : " + customer + "\n\n" +
					"isStaff      : " + staff);
		
	}
	
	private void updatePersonInDB() {
		
		Person p = update(personSearchList.get(
							personListView.getSelectionModel().getSelectedIndex()));
		
		boolean basic    = SQL.updateBasicPerson(p);
		boolean customer = SQL.updateCustomer(p.getID(), p.getCustomerCal(), p.isCustomer());
		boolean staff    = SQL.updateStaff(p);
		
		loadDialog("P: " + p.getFirstName() + " " + p.getLastName() , 
					"Person UPDATED : " + basic    + "\n\n" +
					"Customer       : " + customer + "\n\n" +
					"Staff          : " + staff);
		
		
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
	
	
	
	
	
	
	private boolean basicInputCheck() {
		
		if (firstName.getText().isEmpty() || lastName   .getText().isEmpty() || 
			street   .getText().isEmpty() || houseNumber.getText().isEmpty() ||
			tel      .getText().isEmpty() || plz        .getText().isEmpty() || 
			city     .getText().isEmpty() || country    .getText().isEmpty()) {
			loadDialog("ER: EMPTY FIELD(s)", "ReCheck if all Basic Informations Have been Entered!..");
			return false;
		}
		
		return true;
	}
	
	private boolean advInputCheck() {
		
		if (basicInputCheck()) {
			/** CUSTOMER CALENDER TEST **/
			if (isCustomer.isSelected() && customerCal.getValue() == null) {
				loadDialog("ER: EMPTY FIELD", "No Value Entered in Customer Cal!..");
				return false;
			}
			
			/** STAFF INFO TEST **/
			if (isStaff.isSelected()) {
				if (staffCal.getValue() == null) {
					loadDialog("ER: EMPTY FIELD", "No Value Entered in Staff Cal!..");
					return false;
				}
				
				if (username.getText() == null || username.getText().isEmpty()) {
					loadDialog("ER: EMPTY FIELD", "No UserName Entered!..");
					return false;
				}
						
				if (password.getText() == null || password.getText().isEmpty()) {
					loadDialog("ER: EMPTY FIELD", "No PassWord Entered!..");
					return false;
				}
				
				if (store.getSelectionModel().isEmpty()) {
					loadDialog("ER: EMPTY FIELD", "No Store Selected!..");
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private void loadDialog(String title, String msg) {
		
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(title));
		content.setBody   (new Text(msg  ));
		
		
		JFXDialog jDialog = new JFXDialog(dialogPane, content, JFXDialog.DialogTransition.TOP);
		
		JFXButton button = new JFXButton("OKEY");
		button.setButtonType(JFXButton.ButtonType.RAISED);
		button.setStyle("-fx-background-color: lightgrey");
		button.setOnAction(e -> { jDialog.close(); });
		
		content.setActions(button);
		
		jDialog.show();
	}
	
	
	
	

}














