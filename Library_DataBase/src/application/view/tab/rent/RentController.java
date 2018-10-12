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
	private JFXListView<Label> personListView, bookSearchListView, rentListView, rentBookListView;
	
	@FXML
	private JFXDatePicker rentalStartCal, rentalExpCal, rentalEndCal;
	
	@FXML
	private JFXButton submit;
	
	@FXML
	private JFXComboBox<String> store;
	
	@FXML
	private JFXPopup popUpRentList, popUpRentBookList, popUpBookSearchList;
	
	
	private SQLRent SQL = new SQLRent();
	
	
	private ArrayList<Person>  personSearchList;
	private ArrayList<Book>    bookSearchList;
	private ArrayList<Rent>    rentList;

	private Person customer;
	private LocalDate today;
	
	private int storeID;
	private int staffID;
	
	
	
	
	
	@FXML
	public void initialize() {
		
//		ENABLE THIS IF Login is enabled.
//		staffID = Main.db.getStaff_ID();
//		storeID = SQL.getStoreIDFromStaff(staffID);
		
		staffID = 200;
		storeID = 3;
		
		store.getItems().addAll(SQL.getStores());
		store.getSelectionModel().select(storeID - 1);
		
		today = LocalDate.now();
		setDefaultCalDates();
		
		initAllPopUps();
	}
	
	private void setDefaultCalDates() {
		
		rentalStartCal.setValue(today);
		rentalExpCal  .setValue(today.plusDays(28));
		rentalEndCal  .setValue(null);
	}
	
	private void initAllPopUps() {
		initPopUpRentList();
		initPopUpBookSearchList();
		initPopUpRentBookList();
	}
	
	
	
	private void initPopUpRentList() {
		
		JFXButton show = new JFXButton("SHOW");
		JFXButton del  = new JFXButton("DELETE");
		JFXButton can  = new JFXButton("CANCLE");
		
		show.setOnAction(e -> {
			popUpRentList.hide(); 
			loadBookListOfSelectedRent(getSelectedRentIndex());
		});
		
		del .setOnAction(e -> {
			popUpRentList.hide();
			delRentOfPerson();
		});
		
		can .setOnAction(e -> popUpRentList.hide());
		
		show.setPrefSize(100, 40);
		del .setPrefSize(100, 40);
		can .setPrefSize(100, 40);
		
		
		VBox vBox = new VBox(show, del, can);
		vBox.setSpacing(5);
		vBox.setStyle("-fx-background-color: lightgray;");
		
		popUpRentList = new JFXPopup(vBox);
		
	}
	
	private void initPopUpBookSearchList() {
		
		JFXButton add = new JFXButton("ADD");
		JFXButton can = new JFXButton("CANCLE");
		
		add.setOnAction(e -> {
			popUpBookSearchList.hide();
			addBookToRent();
			
		});
		
		can.setOnAction(e -> popUpBookSearchList.hide());
		
		add.setPrefSize(100, 40);
		can.setPrefSize(100, 40);
		
		
		VBox vBox = new VBox(add, can);
		vBox.setSpacing(5);
		vBox.setStyle("-fx-background-color: lightgray;");
		
		popUpBookSearchList = new JFXPopup(vBox);
	}
	

	private void initPopUpRentBookList() {
		
		JFXButton del = new JFXButton("DELETE");
		JFXButton can = new JFXButton("CANCLE");
		
		del.setOnAction(e -> {
			popUpRentBookList.hide();
			delRentBook();
		});
		
		can.setOnAction(e -> popUpRentBookList.hide());
		
		del.setPrefSize(100, 40);
		can.setPrefSize(100, 40);
		
		
		VBox vBox = new VBox(del, can);
		vBox.setSpacing(5);
		vBox.setStyle("-fx-background-color: lightgray;");
		
		popUpRentBookList = new JFXPopup(vBox);
	}
	
	
	
	
	
	@FXML
	private void rentOfPersonListViewControll(MouseEvent event) {
		
		int index = getSelectedRentIndex();
		
		if (index > 0) {
		
			if(event.getButton() == MouseButton.SECONDARY) 
					popUpRentList.show(rentListView, JFXPopup.PopupVPosition.TOP,  JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
			
			else	loadBookListOfSelectedRent(index);
			
		}
		
		if (index == 0) clearRentBookList();
		
	}
	
	
	@FXML
	private void rentCurrentListViewControll(MouseEvent event) {
		
		int index = getSelectedBookIndex();
		
		if (index > 0) {
			
			if (event.getButton() == MouseButton.SECONDARY) 
					popUpRentBookList.show(rentBookListView, JFXPopup.PopupVPosition.TOP,  JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
			
		}
		
	}
	
	
	@FXML
	private void bookListViewControll(MouseEvent event) {
		
		int index = getSelectedBookSearchIndex();
		
		if (index >= 0) {
		
			if(event.getButton() == MouseButton.SECONDARY) 
					popUpBookSearchList.show(bookSearchListView, JFXPopup.PopupVPosition.TOP,  JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
			
		}
		
	}
	
	
	
	
	
	@FXML
	private void setExpCal() {
		rentalExpCal.setValue(rentalStartCal.getValue().plusDays(28));
		loadBookSearchData();
	}
	
	
	
	private int getSelectedRentIndex() {
		return rentListView.getSelectionModel().getSelectedIndex();
	}
	
	private int getSelectedBookIndex() {
		return rentBookListView.getSelectionModel().getSelectedIndex();
	}
	
	private int getSelectedBookSearchIndex() {
		return bookSearchListView.getSelectionModel().getSelectedIndex();
	}
	
	private int getSelectedPersonIndex() {
		return personListView.getSelectionModel().getSelectedIndex();
	}
	
	private int getSelectedStoreIndex() {
		return store.getSelectionModel().getSelectedIndex() + 1;
	}
	
	
	
	
	private String formatDateOfCal(LocalDate date) {

		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	private LocalDate getDateOfString(String date) {
		
		if (date == null) return null;
		
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
	}
	
	
	
	
	private void clearRentBookList() {
		rentBookListView.getItems().clear();
	}
	
	private void clearPersonList() {
		personListView.getItems().clear();
	}
	
	private void clearRentList() {
		rentListView.getItems().clear();
	}
	
	private void clearBookSearchList() {
		bookSearchListView.getItems().clear();
	}
	
	
	
	
	private void createHeaderRentList() {
		Label header = new Label(String.format("%-15s %-16s %-10s %-20s %s", "Start Rent", "End Rent", "BOOKS", "STORE", "PRICE"));
		
		header.setStyle("-fx-font-weight:bold");
		
		rentListView.getItems().add(header);
	}
	
	private void createHeaderBookRentList() {
		Label header = new Label(String.format("%-65s %-10s", "BOOK TITLE",	"PRICE / DAY"));
		
		header.setStyle("-fx-font-weight:bold");
		
		rentBookListView.getItems().add(header);
	}
	
	
	
	
	@FXML
	private void loadPersonSearchData() {
		
		clearPersonList();
		
		if (!personSearch.getText().isEmpty()) {
			
			personSearchList = SQL.loadPersonSearchData(personSearch.getText());
			
			for (Person p : personSearchList) {
				personListView.getItems().add(p.getNameLabel());
			}
		} 
		
	}
	
	
	@FXML
	private void loadPersonToLabel() {
		
		int index = getSelectedPersonIndex();
		
		if (index >= 0) {
			
			clearRentBookList();
			rentPrice.setText(null);
			
			customer = personSearchList.get(index);
			customerName.setText(customer.getLastName() + " " + customer.getFirstName());
			
			setDefaultCalDates();
			loadRentsOfPerson();
		}
	}
	
	
	@FXML
	private void loadBookSearchData() {

		bookSearchList = new ArrayList<>();
		
		clearBookSearchList();

		if (!bookSearch.getText().isEmpty()) {

			ArrayList<Book> tempAll  = SQL.loadBookSearchData("BOOK " + bookSearch.getText(), getSelectedStoreIndex());
			
			ArrayList<Book> tempRent = SQL.loadBookRentData  (getSelectedStoreIndex(), 
																formatDateOfCal(rentalStartCal.getValue()), 
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
				bookSearchListView.getItems().add(b.toLabel());
			}
			
		} 

	}
	
	
	private void loadRentsOfPerson() {
		
		clearRentList();
		createHeaderRentList();
		
		rentList = SQL.loadRentsOfPerson(customer.getID());
		
		for (Rent r : rentList) {
			
			r.setBookList(SQL.loadBooksOfRent(r.getRental_id()));
			r.calcPriceOfRent();
			
			rentListView.getItems().add(r.toLabel());
			
		}
		
		rentListView.getItems().add(new Label("ADD NEW RENT +++.."));
		
	}
	
	
	private void loadBookListOfSelectedRent(int index) {
		
		int size = rentListView.getItems().size() - 1;
		
		clearRentBookList();
		
		
		if (index == size) {
			setDefaultCalDates();
			store.getSelectionModel().select(storeID - 1);
			
			createNewRent();
			
		} else {

			Rent oldRent = rentList.get(index - 1);
			
			createHeaderBookRentList();
			
			for(Book b : oldRent.getBookList()) {
				rentBookListView.getItems().add(new Label(b.toLabel2()));
			}
			
			rentPrice.setText(String.format("%-6.2f CHF.-", oldRent.getRentPrice()));

			rentalStartCal.setValue(getDateOfString(oldRent.getStartRentalDate()));
			rentalExpCal  .setValue(getDateOfString(oldRent.getExpiresDate    ()));
			rentalEndCal  .setValue(getDateOfString(oldRent.getEndRentalDate  ()));
			
			store.getSelectionModel().select(oldRent.getStoreID() - 1);
			
		}
		
	}
	
	
	
	
	private void delRentOfPerson() {
		
		int index = getSelectedRentIndex() - 1;
		
		if (index == rentList.size()) return;
	
		
		Rent rent = rentList.get(index);
		
		if (rent.getRental_id() > 0) {
		
			SQL.deleteRent(rent.getRental_id());
			
			loadRentsOfPerson();
			clearRentBookList();
			
		}
		
	}
	
	
	private void delRentBook() {
		
		int indexBook = getSelectedBookIndex();
		int indexRent = getSelectedRentIndex();
		
		Rent r = rentList.get(indexRent - 1);
		
		Book b = r.getBookList().get(indexBook - 1);
		
		SQL.deleteBookFromRent(r.getRental_id(), b.getInvID());
		
		loadRentsOfPerson();
		
		rentListView.getSelectionModel().select(indexRent);
		
		loadBookListOfSelectedRent(indexRent);
		
	}
	
	
	private void createNewRent() {

		Rent newRent = SQL.addRent(new Rent(formatDateOfCal(today), formatDateOfCal(today.plusDays(28)), storeID), staffID, customer.getID(), getSelectedStoreIndex());
		
		rentList.add(newRent);
		
		int lastIndex = rentList.size();
		
		rentListView.getItems().add(lastIndex, newRent.toLabel());
		
		rentListView.getSelectionModel().select(lastIndex);
		
		loadBookListOfSelectedRent(lastIndex);
		
	}
	
	
	private void addBookToRent() {
		
		int index = getSelectedRentIndex();
		
		if (index < 0) return;
		
		Rent currRent = rentList.get(index - 1);
		
		Book newBook  = bookSearchList.get(getSelectedBookSearchIndex());
		
		newBook = SQL.getFreeInventoryIDFROMBookID(newBook, currRent.getStartRentalDate(), currRent.getExpiresDate(), currRent.getStoreID());
		
		
		
		SQL.bookToRent(newBook.getInvID(), currRent.getRental_id());

		currRent.getBookList().add(newBook);
		currRent.calcPriceOfRent();
		
		loadBookListOfSelectedRent(index);
		
		loadBookSearchData();
		
	}

	
	@FXML
	private void updateRent() {
		
		int index = getSelectedRentIndex();
		
		Rent currRent = rentList.get(index - 1);
		
		currRent.setStartRentalDate(formatDateOfCal(rentalStartCal.getValue()));
		currRent.setExpiresDate    (formatDateOfCal(rentalExpCal  .getValue()));
		currRent.setEndRentalDate  (formatDateOfCal(rentalEndCal  .getValue()));
		
		currRent.calcPriceOfRent();
		
		SQL.updateRent(currRent);
		
		loadRentsOfPerson();
		
		rentListView.getSelectionModel().select(index);
		
		loadBookListOfSelectedRent(index);
		
	}
	
	
}
