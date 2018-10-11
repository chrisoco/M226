package application.view.tab.rent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;

import application.view.tab.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class RentController {

	@FXML
	private JFXTextField personSearch, bookSearch, customerName, rentPrice;
	
	@FXML
	private JFXListView<Label> personListView, bookListView, rentOfPersonListView, rentCurrentListView;
	
	@FXML
	private JFXDatePicker rentalStartCal, rentalExpCal, rentalEndCal;
	
	@FXML
	private JFXButton submit, addBook;
	
	@FXML
	private JFXComboBox<String> store;
	
	@FXML
	private JFXPopup popUpRentOfPerson, popUpCurrentRent, popUpBookList;
	
	
	private SQLRent SQL = new SQLRent();
	
	
	private ArrayList<Person>  personSearchList;
	private ArrayList<Book>    bookSearchList;
	private ArrayList<Rent>    rentList;

	private Person customer;
	private LocalDate today;
	
	private int storeID;
	private int staffID = 200;
	
	@FXML
	public void initialize() {
		storeID = 3; // SQL.getStoreIDFromStaff(Main.db.getStaff_ID()) - 1; ENABLE THIS IF Login is enabled.
		
		store.getItems().addAll(SQL.getStores());
		store.getSelectionModel().select(storeID - 1);	// TODO: GET BD current Staff Store
		
		setDefaultCalDates();
		
		initPopUpRentOfPerson();
		initPopUpBookList();
		initPopUpCurrentRent();
	}
	
	
	@FXML
	private void rentOfPersonListViewControll(MouseEvent event) {
		
		int index = rentOfPersonListView.getSelectionModel().getSelectedIndex();
		
		if (index > 0) {
		
			if(event.getButton() == MouseButton.SECONDARY) {
				popUpRentOfPerson.show(rentOfPersonListView, 
										JFXPopup.PopupVPosition.TOP,  JFXPopup.PopupHPosition.LEFT, 
																		event.getX(), event.getY());
			}
				
			else {
				
				loadBooksOfRent(index);
			}
		}
		
		if (index == 0) {
			rentCurrentListView.getItems().clear();
		}
		
	}
	
	@FXML
	private void bookListViewControll(MouseEvent event) {
		
		int index = bookListView.getSelectionModel().getSelectedIndex();
		
		if (index >= 0) {
		
			if(event.getButton() == MouseButton.SECONDARY) popUpBookList.show(bookListView, 
										JFXPopup.PopupVPosition.TOP,  JFXPopup.PopupHPosition.LEFT, 
																		event.getX(), event.getY());
		}
		
	}
	
	@FXML
	private void rentCurrentListViewControll(MouseEvent event) {
		
		int index = rentCurrentListView.getSelectionModel().getSelectedIndex();
		
		if (index > 0) {
			
			if (event.getButton() == MouseButton.SECONDARY) {
				popUpCurrentRent.show(rentCurrentListView, 
										JFXPopup.PopupVPosition.TOP,  JFXPopup.PopupHPosition.LEFT, 
																		event.getX(), event.getY());
			}
			
		}
		
	}
	
	
	private void initPopUpRentOfPerson() {
		
		JFXButton show = new JFXButton("SHOW");
		JFXButton del  = new JFXButton("DELETE");
		JFXButton can  = new JFXButton("CANCLE");
		
		show.setOnAction(e -> {
			popUpRentOfPerson.hide(); 
			loadBooksOfRent(rentOfPersonListView.getSelectionModel().getSelectedIndex());
		});
		
		del .setOnAction(e -> {
			popUpRentOfPerson.hide();
			delRentOfPerson();
		});
		
		can .setOnAction(e -> popUpRentOfPerson.hide());
		
		show.setPrefSize(100, 40);
		del .setPrefSize(100, 40);
		can .setPrefSize(100, 40);
		
		
		VBox vBox = new VBox(show, del, can);
		vBox.setSpacing(5);
		vBox.setStyle("-fx-background-color: lightgray;");
		
		popUpRentOfPerson = new JFXPopup(vBox);
		
	}
	
	private void initPopUpBookList() {
		
		JFXButton add = new JFXButton("ADD");
		JFXButton can = new JFXButton("CANCLE");
		
		add.setOnAction(e -> {
			popUpBookList.hide();
			addBookToRent();
			
		});	// TODO: CREATE ADD METHOD.
		
		can.setOnAction(e -> popUpBookList.hide());
		
		add.setPrefSize(100, 40);
		can.setPrefSize(100, 40);
		
		
		VBox vBox = new VBox(add, can);
		vBox.setSpacing(5);
		vBox.setStyle("-fx-background-color: lightgray;");
		
		popUpBookList = new JFXPopup(vBox);
	}
	
	private void initPopUpCurrentRent() {
		
		JFXButton del = new JFXButton("DELETE");
		JFXButton can = new JFXButton("CANCLE");
		
		del.setOnAction(e -> {
			popUpCurrentRent.hide();
			delRentBook();
		});
		
		can.setOnAction(e -> popUpCurrentRent.hide());
		
		del.setPrefSize(100, 40);
		can.setPrefSize(100, 40);
		
		
		VBox vBox = new VBox(del, can);
		vBox.setSpacing(5);
		vBox.setStyle("-fx-background-color: lightgray;");
		
		popUpCurrentRent = new JFXPopup(vBox);
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
		
		rentList = SQL.loadOldRents(customer.getID());
		
		
		for (Rent r : rentList) {
			
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
	
	
	private void loadBooksOfRent(int index) {
		
		int size = rentOfPersonListView.getItems().size() - 1;
		
		if (index == size) {
			setDefaultCalDates();
			store.getSelectionModel().select(storeID - 1);
			
			rentCurrentListView.getItems().clear();
			createNewRent();
		}
		else {

			Rent oldRent = rentList.get(index - 1);
			
			rentCurrentListView.getItems().clear();
			setHeaderCurrentRentBooks();
			
			for(Book b : oldRent.getBookList()) {
				rentCurrentListView.getItems().add(new Label(b.toLabel2()));
			}
			
			rentPrice.setText(String.format("%-6.2f CHF.-", oldRent.getRentPrice()));

			rentalStartCal.setValue(getDateOfString(oldRent.getStartRentalDate()));
			rentalExpCal  .setValue(getDateOfString(oldRent.getExpiresDate()));
			rentalEndCal  .setValue(getDateOfString(oldRent.getEndRentalDate()));
			
			store.getSelectionModel().select(oldRent.getStoreID() - 1);
			
		}
		
	}
	
	
	private void delRentOfPerson() {
		
		int index = (rentOfPersonListView.getSelectionModel().getSelectedIndex()) - 1;
		
		if (index == rentList.size()) return;
	
		
		Rent rent = rentList.get(index);
		
		if (rent.getRental_id() > 0) {
		
			SQL.deleteRent(rent.getRental_id());
			
			loadOldRents();
			rentCurrentListView.getItems().clear();
			
		} else {
			rentList.remove(index);
			rentOfPersonListView.getItems().remove(index + 1);
		}
		
	}
	
	private void createNewRent() {

		Rent newRent = SQL.addRent(new Rent(formatDateOfCal(today), formatDateOfCal(today.plusDays(28)), storeID), 
										staffID, customer.getID(), store.getSelectionModel().getSelectedIndex() + 1);
		
		rentList.add(newRent);
		rentOfPersonListView.getItems().add(rentList.size(), new Label(newRent.toLabel()));
		rentOfPersonListView.getSelectionModel().select(rentList.size());
		loadBooksOfRent(rentList.size());
		
	}
	
	private void delRentBook() {
		
		int indexBook = (rentCurrentListView .getSelectionModel().getSelectedIndex()) - 1;
		int indexRent = (rentOfPersonListView.getSelectionModel().getSelectedIndex()) - 1;
		
		Rent r = rentList.get(indexRent);
		
		Book b = r.getBookList().get(indexBook);
		
		SQL.deleteBookFromRent(r.getRental_id(), b.getInvID());
		
		loadOldRents();
		rentOfPersonListView.getSelectionModel().select(indexRent + 1);
		loadBooksOfRent(indexRent + 1);
		
	}
	
	private void addBookToRent() {
		
		int index = rentOfPersonListView.getSelectionModel().getSelectedIndex();
		if (index < 0) return;
		
		Rent currRent = rentList.get(index - 1);
		
		Book newBook  = bookSearchList.get(bookListView.getSelectionModel().getSelectedIndex());
		
		newBook = SQL.getInvIDOfBook(newBook, currRent.getStartRentalDate(), currRent.getExpiresDate(), currRent.getStoreID());
		
		//TODO: CONTINUE HERE

		currRent.getBookList().add(newBook);
		
		loadBooksOfRent(index);
		
		//TODO Reload bookSearch.
		
	}

	
	
	private void setDefaultCalDates() {
		
		today = LocalDate.now();
		
		rentalStartCal.setValue(today);
		rentalExpCal  .setValue(today.plusDays(28));
		rentalEndCal  .setValue(null);
	}
	
	
	private String formatDateOfCal(LocalDate date) {

		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	private LocalDate getDateOfString(String date) {
		if (date == null) return null;
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	
}
