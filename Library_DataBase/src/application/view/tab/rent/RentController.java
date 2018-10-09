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
	JFXTextField personSearch, bookSearch, customerName, rentPrice;
	
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
	private ArrayList<Book>   bookSearchList;
	private ArrayList<Rent>   oldRents;
	private Person customer;
	private LocalDate today;
	
	
	@FXML
	public void initialize() {
		
		store.getItems().addAll(SQL.getStores());
		store.getSelectionModel().select(0);	// TODO: GET BD current Staff Store
		
		today = LocalDate.now();
		
		rentalStartCal.setValue(today);
		rentalExpCal  .setValue(today.plusDays(28));
		
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
			
			rentCurrentListView.getItems().clear();
			rentPrice.setText(null);
			
			customer = personSearchList.get(listIndex);
			customerName.setText(customer.getLastName() + " " + customer.getFirstName());
			
			loadOldRents();
		}
	}
	
	@FXML
	private void setExpCal() {
		rentalExpCal.setValue(rentalStartCal.getValue().plusDays(28));
		loadBookSearchData();
	}	
	
	/** BOOK **/ 
	@FXML
	private void loadBookSearchData() {

		bookSearchList = new ArrayList<>();
		bookListView.getItems().clear();

		if (!bookSearch.getText().isEmpty()) {
			int storeID = store.getSelectionModel().getSelectedIndex() + 1;

			ArrayList<Book> tempAll  = SQL.loadBookSearchData("BOOK " + bookSearch.getText(), storeID);
			
			ArrayList<Book> tempRent = SQL.loadBookRentData(storeID, formatDateOfCal(rentalStartCal.getValue()), 
																		  formatDateOfCal(rentalExpCal  .getValue()));
			
			
			for (Book t1 : tempAll) {
				for (Book t2 : tempRent) {
					if (t1.getbID() == t2.getbID()) {
						t1.setAnz(t1.getAnz() - t2.getAnz());
					}
				}
			}
			
			for (Book t1 : tempAll) {
				if (t1.getAnz() > 0) {
					bookSearchList.add(t1);
				}
			}
			
			for (Book b : bookSearchList) {
				bookListView.getItems().add(new Label(b.toLabel()));
			}
			
		} 

	}
	
	private void loadOldRents() {
		
		rentOfPersonListView.getItems().clear();
		setHeaderOldRents();
		
		oldRents = SQL.loadOldRents(customer.getID());
		
		
		for (Rent r : oldRents) {
			
			r.setBookList(SQL.loadBooksOfRent(r.getRental_id()));	// LOAD BOOKS OF RENT
			r.calcPriceOfRent();
			
			rentOfPersonListView.getItems().add(new Label(r.toLabel()));
			
		}
		
		rentOfPersonListView.getItems().add(new Label("NEW RENT.."));
		
	}
	
	
	private void setHeaderOldRents() {
		Label header = new Label(String.format("%-15s %-16s %-10s %-20s %s",
				"Start Rent", "End Rent", "BOOKS", "STORE", "PRICE"));
		
		header.setStyle("-fx-font-weight:bold");
		
		rentOfPersonListView.getItems().add(header);
	}
	
	private void setHeaderCurrentRentBooks() {
		Label header = new Label(String.format("%-65s %-10s", "BOOK TITLE",
																"PRICE / DAY"));
		
		header.setStyle("-fx-font-weight:bold");
		
		rentCurrentListView.getItems().add(header);
	}
	
	@FXML
	private void loadBooksOfRent() {
		
		int rent = rentOfPersonListView.getSelectionModel().getSelectedIndex();
		int size = rentOfPersonListView.getItems().size() - 1;
		
		
		if (rent == size) {
			// TODO: CREATE NEW LIST & ADD BOOKS
			
			rentCurrentListView.getItems().clear();
		}
		else if (rent > 0) {

			Rent oldRen = oldRents.get(rent - 1);
			
			rentCurrentListView.getItems().clear();
			setHeaderCurrentRentBooks();
			
			for(Book b : oldRen.getBookList()) {
				rentCurrentListView.getItems().add(new Label(b.toLabel2()));
			}
			
			rentPrice.setText(String.format("%-6.2f CHF.-", oldRen.getRentPrice()));
		}
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	private String formatDateOfCal(LocalDate date) {

		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	
}
