package application.view.tab.rent;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import application.view.tab.book.Book;
import application.view.tab.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RentController {

	@FXML
	JFXTextField personSearch, bookSearch, name;
	
	@FXML
	JFXListView<Label> personListView, bookListView, rentOfPersonListView, rentCurrentListView;
	
	@FXML
	JFXDatePicker rentalStartCal, rentalExpCal, rentalEndCal;
	
	@FXML
	JFXButton submit, addBook;
	
	@FXML
	JFXComboBox<String> store;
	
	
	private SQLRent SQL = new SQLRent();
	
	private ArrayList<Person> personSearchList;
	private ArrayList<Book> bookSearchList; // TODO: create Class Book :)
	
	
	@FXML
	public void initialize() {
		
		store.getItems().addAll(SQL.getStores());

	}
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
