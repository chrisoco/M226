package application.view.tab.book;

import application.view.tab.person.SQLPerson;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
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

    @FXML
    private void loadBookSearchData() {
        bookListView.getItems().clear();

        if (!bookSearch.getText().isEmpty()) {

            bookSearchList = SQL.loadBookSearchData(bookSearch.getText());

            for (Book b : bookSearchList) {
                bookListView.getItems().add(new Label(b.getTitle()));
            }
        }
    }

    @FXML
    private void loadBookToLabel() {
        int listIndex = bookListView.getSelectionModel().getSelectedIndex();

        if (listIndex >= 0) {
            Book b = bookSearchList.get(listIndex);
        }
    }

    private void setBasicBookLabel(Book b) {
        title           .setText(b.getTitle());
        description     .setText(b.getDescription());
        //releaseDate
        rating          .setText(String.valueOf(b.getRating()));
        priceDay        .setText();
    }
}
