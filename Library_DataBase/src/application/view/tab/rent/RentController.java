package application.view.tab.rent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	JFXTextField personSearch, bookSearch, customerName;
	
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
	private ArrayList<Inventory> bookSearchList;
	private Person customer;
	
	
	@FXML
	public void initialize() {
		
		store.getItems().addAll(SQL.getStores());
		store.getSelectionModel().select(0);	// TODO: GET BD current Staff Store
		
		rentalStartCal.setValue(LocalDate.now());
		rentalEndCal  .setValue(LocalDate.now().plusDays(10));
		
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
	
	@FXML
	private void loadPersonToLabel() {
		
		int listIndex = personListView.getSelectionModel().getSelectedIndex();
		
		if (listIndex >= 0) {
			this.customer = personSearchList.get(listIndex);
			customerName.setText(customer.getLastName() + " " + customer.getFirstName());
			
		}
	}
	
	
	
	/** BOOK **/ 
	@FXML
	private void loadBookSearchData() {

		bookSearchList = new ArrayList<>();
		bookListView.getItems().clear();

		if (!bookSearch.getText().isEmpty()) {
			int storeID = store.getSelectionModel().getSelectedIndex() + 1;

			ArrayList<Inventory> tempAll  = SQL.loadBookSearchData("BOOK " + bookSearch.getText(), storeID);
			
			ArrayList<Inventory> tempRent = SQL.loadBookRentData(storeID, formatDateOfCal(rentalStartCal.getValue()), 
																		  formatDateOfCal(rentalEndCal  .getValue()));
			
			
			for (Inventory t1 : tempAll) {
				for (Inventory t2 : tempRent) {
					if (t1.getbID() == t2.getbID()) {
						t1.setAnz(t1.getAnz() - t2.getAnz());
					}
				}
			}

			
			for (Inventory t1 : tempAll) {
				if (t1.getAnz() > 0) {
					bookSearchList.add(t1);
				}
			}
			
			for (Inventory b : bookSearchList) {
				bookListView.getItems().add(new Label(b.toLabel()));
			}
			
			
		} 

	}
	
	
	private String formatDateOfCal(LocalDate date) {

		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
}
