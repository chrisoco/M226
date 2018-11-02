package application.view.tab.book;

import application.view.tab.person.Person;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private JFXListView<Label> bookListView;
    @FXML
    private JFXTextField bookSearch;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXDatePicker releaseDate;
    @FXML
    private JFXTextField priceDay;
    @FXML
    private JFXTextField priceReplace;
    @FXML
    private JFXTextField rating;
    @FXML
    private JFXTextField publisher;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField language;
    @FXML
    private JFXTextField category;
    @FXML
    private JFXToggleButton isNewBook;
    @FXML
    private JFXButton bookSubmit;
    @FXML
    private JFXButton clear;
    @FXML
    private StackPane dialogPane;

    private SQLBook SQL = new SQLBook();

    private ArrayList<Book> bookSearchList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    /**
     * Add Book To DataBase or Update if he already Exists.
     */
    @FXML
    private void updateDB() {

        int index = bookListView.getSelectionModel().getSelectedIndex();

        if (advInputCheck()) {
            if (isNewBook.isSelected()) {
                addNewBookToDB();
            } else if (index >= 0) {
                updateBookInDB();
            } else {
                loadDialog("ERROR 403..", "You have not selected a Book in the Search List...\nPLEASE DO SO...");
            }

        }
    }

    /**
     * Update an existing Book in the DataBase.
     */
    private void updateBookInDB() {

        Book b = update(bookSearchList.get(
                bookListView.getSelectionModel().getSelectedIndex()));

        boolean basic = SQL.updateBasicBook(b);

        loadDialog("B: " + b.getTitle() + " ",
                "Book UPDATED : " + basic + "\n\n");
    }

    /**
     * Get Values from TextField and save them in the Object.
     *
     * @param b Book Class
     * @return Book Return Modified Person
     */
    private Book update(Book b) {

        b.setTitle(title.getText());
        b.setDescription(description.getText());
        b.setPrice_day(priceDay.getText());
        b.setPrice_replace(priceReplace.getText());
        b.setRating(Integer.valueOf(rating.getText()));
        b.setCompany(publisher.getText());
        b.setFirstName(author.getText());
        b.setLang(language.getText());
        b.setCat(category.getText());
        return b;
    }

    /**
     * Add a new Book to the DataBase.
     */
    private void addNewBookToDB() {


        // CREATE NEW BOOK
//        Book b = new Book(title.getText(), description.getText(),
//                priceDay.getText(), releaseDate.getValue().toString(), priceReplace.getText(),
//                rating      .getText(), publisher.getText(), author.getText(), language.getText(), category.getText());
//
//        b.setID(SQL.insertBasicBook(b));
//
//
//        loadDialog( "B: " + b.getFirstName() + " " + b.getLastName() ,
//                "BOOK ADDED : " + (b.getID() > 0) + "\n\n");
    }


    @FXML
    public void loadBookSearchData() {
        bookListView.getItems().clear();

        if (!bookSearch.getText().isEmpty()) {

            bookSearchList = SQL.loadBookSearchData(bookSearch.getText());

            for (Book b : bookSearchList) {
                bookListView.getItems().add(new Label(b.getTitle()));
            }
        }
    }

    /**
     * Set the TextField values to the Book Selected.
     *
     * @param b Book Object
     */
    private void setBasicBookLabel(Book b) {

        title.setText(b.getTitle());
        description.setText(b.getDescription());
//        releaseDate.set(b.getReleaseDate());
        priceDay.setText(b.getPrice_day());
        priceReplace.setText(b.getPrice_replace());
        rating.setText(String.valueOf(b.getRating()));
        publisher.setText(b.getCompany());
        author.setText(b.getFirstName() + " " + b.getLastName());
        language.setText(b.getLang());
        category.setText(b.getCat());

    }

    /**
     * Load Book Information to the Book Object and then to the TextFields.
     */
    @FXML
    private void loadBookToLabel() {

        int listIndex = bookListView.getSelectionModel().getSelectedIndex();

        if (listIndex >= 0) {
            Book b = bookSearchList.get(listIndex);

            if (b.getLang() == null || b.getLang().isEmpty()) SQL.loadBookData(b);

            setBasicBookLabel(b);

            isNewBook.setSelected(false);
        }
    }

    /**
     * Formats String (yyyy-MM-dd) to LocalDate Value.
     * However, If the String is null, return null.
     *
     * @param date String Value
     * @return LocalDate Value
     */
    private LocalDate getDateOfString(String date) {

        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Formats LocalDate Value to String: yyyy-MM-dd
     *
     * @param date LocalDate Value
     * @return String Value
     */
    private String formatDateOfCal(LocalDate date) {

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Reset all TextFields to Empty Value
     */
    @FXML
    private void resetAll() {

        title.setText("");
        description.setText("");
        priceDay.setText("");
        priceReplace.setText("");
        rating.setText("");
        publisher.setText("");
        author.setText("");
        language.setText("");
        category.setText("");
        isNewBook.setSelected(true);
    }

    /**
     * Advanced InputCheck If Book is Entered with Values.
     *
     * @return boolean All Good / Someting is missing.
     */
    private boolean advInputCheck() {

        if (basicInputCheck()) {

            if (title.getText() == null || title.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Title Entered!..");
                return false;
            }

            if (description.getText() == null || description.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Description Entered!..");
                return false;
            }

            if (priceDay.getText() == null || priceDay.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Price Day Entered!..");
                return false;
            }

            if (priceReplace.getText() == null || priceReplace.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Price Replace Entered!..");
                return false;
            }

            if (rating.getText() == null || rating.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Rating Entered!..");
                return false;
            }

            if (publisher.getText() == null || publisher.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Publisher Entered!..");
                return false;
            }

            if (author.getText() == null || author.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Author Entered!..");
                return false;
            }

            if (language.getText() == null || language.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Language Entered!..");
                return false;
            }

            if (category.getText() == null || category.getText().isEmpty()) {
                loadDialog("ER: EMPTY FIELD", "No Category Entered!..");
                return false;
            }
        }
        return true;
    }

    /**
     * Check If all Basic TextFields have a Value.
     *
     * @return boolean Basic TextFields All Entered.
     */
    private boolean basicInputCheck() {

        if (title.getText().isEmpty() || description.getText().isEmpty() ||
                priceDay.getText().isEmpty() || priceReplace.getText().isEmpty() ||
                rating.getText().isEmpty() || publisher.getText().isEmpty() ||
                author.getText().isEmpty() || language.getText().isEmpty() ||
                category.getText().isEmpty()) {
            loadDialog("ER: EMPTY FIELD(s)", "ReCheck if all Basic Informations Have been Entered!..");
            return false;
        }

        return true;
    }

    /**
     * Load a Dialog
     *
     * @param title Title of Dialog
     * @param msg   Message of the Dialog
     */
    private void loadDialog(String title, String msg) {

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(title));
        content.setBody(new Text(msg));


        JFXDialog jDialog = new JFXDialog(dialogPane, content, JFXDialog.DialogTransition.TOP);

        JFXButton button = new JFXButton("OKEY");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-color: lightgrey");
        button.setOnAction(e -> {
            jDialog.close();
        });

        content.setActions(button);

        jDialog.show();
    }
}
