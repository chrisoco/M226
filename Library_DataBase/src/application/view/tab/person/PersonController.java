package application.view.tab.person;

import java.util.ArrayList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import application.Main;
import application.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PersonController {
	
	@FXML
	private JFXTextField userSearch, firstName, lastName, street, 
						 str_nmbr, plz, city, country, tel, email, username;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXToggleButton isCustomer, isStaff;
	@FXML
	private JFXDatePicker customerCal, staffCal;
	@FXML
	private JFXListView<Label> personListView;
	@FXML
	private JFXButton personSubmit;
	
	private ArrayList<Person> personSearchList;
	
	
	
	public void loadPersonSearchData() {
		if (!userSearch.getText().isEmpty()) {

			personListView.getItems().clear();
			
			personSearchList = Main.db.loadPersonSearchData(userSearch.getText());
			
			
			for (Person p : personSearchList) {
				personListView.getItems().add(new Label(String.format("%-20s %s", p.getLastName(), p.getFirstName())));
			}
			
		} else {
			personListView.getItems().clear();
		}
		
	}
	
	public void loadPersonToLabel() {
		int index = personListView.getSelectionModel().getSelectedIndex();
		
		if (index >= 0) {
			Person p = personSearchList.get(index);
			firstName.setText(p.getFirstName());
			lastName .setText(p.getLastName());
		}
	}

	
	
	
	

	
	

	
	
	

}
