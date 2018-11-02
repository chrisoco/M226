/**
 * RentController
 * 
 * @author Christopher O'Connor
 * @version 1.0
 * @date 12.10.2018
 * 
 */

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

import application.Main;
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
	
	
	
	/**
	 * Customer Initialises Method which is run on Program Start.
	 * It sets the StaffID, StoreID and sets Default Values for the Date Fields.
	 * It creates the Dropdown-Items (All Store Names).
	 * 
	 */
	@FXML
	public void initialize() {
		
//		ENABLE THIS IF Login is enabled.
		staffID = Main.db.getStaff_ID();
		storeID = SQL.getStoreIDFromStaff(staffID);
		
//		staffID = 200;
//		storeID = 3;
		
		store.getItems().addAll(SQL.getStores());
		store.getSelectionModel().select(storeID - 1);
		
		today = LocalDate.now();
		setDefaultCalDates();
		
		initAllPopUps();
	}
	
	/**
	 * Sets Default Values for all Calendars.
	 * Sets the Current Date Value into the Rental Start Calendar Date.
	 * Sets the Current Date Value + 28 Days into the Rental Expires Calendar Date.
	 * Sets the End Calendar Date to null.
	 */
	private void setDefaultCalDates() {
		
		rentalStartCal.setValue(today);
		rentalExpCal  .setValue(today.plusDays(28));
		rentalEndCal  .setValue(null);
	}
	
	/**
	 * Calls the Initialise for all the PopUps for Rent-, Book- and BookSearch-List.
	 */
	private void initAllPopUps() {
		initPopUpRentList();
		initPopUpBookSearchList();
		initPopUpRentBookList();
	}
	
	
	/**
	 * Initialises the PopUpRentList. 
	 * Buttons: show, del, can.
	 */
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
	
	
	/**
	 * Initialises the PopUpBookSearchList.
	 * Buttons: add, can. 
	 */
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
	
	
	/**
	 * Initialises the PopUpRentBookList. 
	 * Buttons: del, can.
	 */
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
	
	
	
	
	/**
	 * Rent of CurrentSelected Person Controller.
	 * Load the BookList of the Selected Rent. OR Show PopUpRentList
	 * 
	 * @param event MouseEvent: Detect if the Main or Secondary Mouse-Button was clicked.
	 * 
	 */
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
	
	
	/**
	 * Books of CurrentSelected Rent Controller.
	 * Show PopUpRentList if Secondary Mouse-Button was clicked.
	 * 
	 * @param event MouseEvent: Detect if the Main or Secondary Mouse-Button was clicked.
	 * 
	 */
	@FXML
	private void rentCurrentListViewControll(MouseEvent event) {
		
		int index = getSelectedBookIndex();
		
		if (index > 0) {
			
			if (event.getButton() == MouseButton.SECONDARY) 
					popUpRentBookList.show(rentBookListView, JFXPopup.PopupVPosition.TOP,  JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
			
		}
		
	}
	
	
	/**
	 * BookList Controller of SearchBookList.
	 * Show PopUpRentList if Secondary Mouse-Button was clicked.
	 * 
	 * @param event MouseEvent: Detect if the Main or Secondary Mouse-Button was clicked.
	 * 
	 */
	@FXML
	private void bookListViewControll(MouseEvent event) {
		
		int index = getSelectedBookSearchIndex();
		
		if (index >= 0) {
		
			if(event.getButton() == MouseButton.SECONDARY) 
					popUpBookSearchList.show(bookSearchListView, JFXPopup.PopupVPosition.TOP,  JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
			
		}
		
	}
	
	
	
	
	/**
	 * Set the Date of the ExpiredCalendar to StartDate + 28 Days.
	 */
	@FXML
	private void setExpCal() {
		rentalExpCal.setValue(rentalStartCal.getValue().plusDays(28));
		loadBookSearchData();
	}
	
	
	
	/**
	 * @return int Returns the Current Selected Index of the RentList.
	 */
	private int getSelectedRentIndex() {
		return rentListView.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * @return int Returns the Current Selected Index of the RentBookList.
	 */
	private int getSelectedBookIndex() {
		return rentBookListView.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * @return int Returns the Current Selected Index of the BookSearchList.
	 */
	private int getSelectedBookSearchIndex() {
		return bookSearchListView.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * @return int Returns the Current Selected Index of the Person List.
	 */
	private int getSelectedPersonIndex() {
		return personListView.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * @return int Returns the Current Selected Index of the Store ComboBox + 1.
	 */
	private int getSelectedStoreIndex() {
		return store.getSelectionModel().getSelectedIndex() + 1;
	}
	
	
	
	/**
	 * Formats LocalDate Value to String: yyyy-MM-dd
	 * 
	 * @param date LocalDate Value
	 * @return String Value
	 * 
	 */
	private String formatDateOfCal(LocalDate date) {
		
		if (date == null) return null;
		
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	/**
	 * Formats String (yyyy-MM-dd) to LocalDate Value.
	 * However, If the String is null, return null.
	 * 
	 * @param date String Value
	 * @return LocalDate Value
	 * 
	 */
	private LocalDate getDateOfString(String date) {
		
		if (date == null) return null;
		
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
	}
	
	
	
	/**
	 * Clear the Content of the RentBookList.
	 */
	private void clearRentBookList() {
		rentBookListView.getItems().clear();
	}
	
	/**
	 * Clear the Content of the PersonList.
	 */
	private void clearPersonList() {
		personListView.getItems().clear();
	}
	
	/**
	 * Clear the Content of the RentList.
	 */
	private void clearRentList() {
		rentListView.getItems().clear();
	}
	
	/**
	 * Clear the Content of the BookSearchList.
	 */
	private void clearBookSearchList() {
		bookSearchListView.getItems().clear();
	}
	
	
	
	/**
	 * Create the Header for the RentList. It will act as an List Element.
	 * Also sets the Font to bold.
	 */
	private void createHeaderRentList() {
		Label header = new Label(String.format("%-15s %-16s %-10s %-20s %s", "Start Rent", "End Rent", "BOOKS", "STORE", "PRICE"));
		
		header.setStyle("-fx-font-weight:bold");
		
		rentListView.getItems().add(header);
	}
	
	/**
	 * Create the Header for the RentBookList. It will act as an List Element.
	 * Also sets the Font to bold.
	 */
	private void createHeaderBookRentList() {
		Label header = new Label(String.format("%-65s %-10s", "BOOK TITLE",	"PRICE / DAY"));
		
		header.setStyle("-fx-font-weight:bold");
		
		rentBookListView.getItems().add(header);
	}
	
	
	
	/**
	 * Load Person Matching the Search Criteria and Add them to the PersonSearchList.
	 */
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
	
	
	/**
	 * Get the selected Index of the PersonSearchList and write the Name to the TextField and set them as Customer.
	 */
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
	
	
	/**
	 * Load the Books from Database which are Free Between the Given start and end Date and Count them.
	 */
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
	
	
	/**
	 * Load all Rentals From the Selected Person.
	 */
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
	
	
	/**
	 * Load all Books From the Selected Rent and Calculate the Price.
	 * @param index The Index From the Selected Rent.
	 */
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
				rentBookListView.getItems().add(b.toLabel2());
			}
			
			rentPrice.setText(String.format("%-6.2f CHF.-", oldRent.getRentPrice()));

			rentalStartCal.setValue(getDateOfString(oldRent.getStartRentalDate()));
			rentalExpCal  .setValue(getDateOfString(oldRent.getExpiresDate    ()));
			rentalEndCal  .setValue(getDateOfString(oldRent.getEndRentalDate  ()));
			
			store.getSelectionModel().select(oldRent.getStoreID() - 1);
			
		}
		
	}
	
	
	
	
	/**
	 * Delete the Selected Rent from the Selected Person.
	 */
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
	
	
	/**
	 * Delete the Selected Book from the Selected Rent from the Selected Person.
	 */
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
	
	
	/**
	 * Create a new Rent with startDate = current Date, and Expires Date (current Date + 28 Days).
	 * The Selected Customer is the Owner of the Rent.
	 */
	private void createNewRent() {

		Rent newRent = SQL.addRent(new Rent(formatDateOfCal(today), formatDateOfCal(today.plusDays(28)), storeID), staffID, customer.getID(), getSelectedStoreIndex());
		
		rentList.add(newRent);
		
		int lastIndex = rentList.size();
		
		rentListView.getItems().add(lastIndex, newRent.toLabel());
		
		rentListView.getSelectionModel().select(lastIndex);
		
		loadBookListOfSelectedRent(lastIndex);
		
	}
	
	
	/**
	 * Add the Selected Book from the BookSearchList to the Currently Selected Rent.
	 * To do so the Selected Book needs to get a free (between start- AND end-Date) Inventory ID.
	 */
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

	
	/**
	 * Update the Currently Selected Rent from the Customer with new Dates.
	 */
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
