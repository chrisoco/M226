package application.view.tab.rent;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import application.view.tab.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RentController {

	@FXML
	JFXTextField personSearch, bookSearch, personName;
	
	@FXML
	JFXListView<Label> personListView, bookListView, rentOfPersonListView, rentCurrentListView;
	
	@FXML
	JFXDatePicker rentalStartCal, rentalExpCal, rentalEndCal;
	
	@FXML
	JFXButton submit, addBook;
	
	@FXML
	JFXComboBox<String> store;
	
	
	private SQLRent SQL = new SQLRent();
	
	private ArrayList<Person>    personSearchList;
	private ArrayList<Inventory> inventorySearchList; // TODO: create Class Book :)
	private Person customer;
	
	
	@FXML
	public void initialize() {
		
		store.getItems().addAll(SQL.getStores());

	}
	

	/** BOOK **/ 
	@FXML
	private void loadPersonSearchData() {
		
		personListView.getItems().clear();
		
		if (!personSearch.getText().isEmpty()) {
			
			personSearchList = SQL.loadPersonSearchData(personSearch.getText());
			
			for (Person p : personSearchList) {
				personListView.getItems().add(new Label(p.getNameLabel()));
			}
		} 
		
	}
	
				/** BOOK **/ 
	@FXML
	private void loadBookSearchData() {
		
		bookListView.getItems().clear();
		
		if (!bookSearch.getText().isEmpty()) {
			
			inventorySearchList = SQL.loadBookSearchData("BOOK " + bookSearch.getText());
			
			for (Inventory i : inventorySearchList) {
				bookListView.getItems().add(new Label(i.getbTitle()));
			}
		} 
		
	}
	
	
	@FXML
	private void loadPersonToLabel() {
		
		int listIndex = personListView.getSelectionModel().getSelectedIndex();
		
		if (listIndex >= 0) {
			customer = personSearchList.get(listIndex);
			personName.setText(customer.getLastName() + " " + customer.getFirstName());
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
