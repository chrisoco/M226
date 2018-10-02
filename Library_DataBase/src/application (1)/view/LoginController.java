package application.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class LoginController {

	@FXML
	private JFXTextField userLoginNameField;
	@FXML
	private JFXPasswordField userLoginPWField;
	@FXML
	private StackPane dialogPane;
	
	private Main main;
	
	private String userLoginName, userLoginPW;
	
	

	
	@FXML
	private void login() {
		this.userLoginName = userLoginNameField.getText();
		this.userLoginPW   = userLoginPWField  .getText();

		if (!userLoginName.isEmpty() && !userLoginPW.isEmpty()) {
			
			if (Main.db.login(userLoginName, userLoginPW))
				loadDialog("LOGIN SUCCESS!", "Congratulations!\nYou Are a real Genius and haven't forgotten your PW!", true);
			else 
				loadDialog("LOGIN FAILED!" , "'" + userLoginName + "' Does not match with the Entered Password!\nRE-ENTER Your info ...", false);
		}
		
	}
	
	
	private void loadDialog(String title, String msg, boolean next) {
		
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(title));
		content.setBody   (new Text(msg  ));
		
		JFXDialog jDialog = new JFXDialog(dialogPane, content, JFXDialog.DialogTransition.TOP);
		jDialog.setBackground(null);
		
		JFXButton button = new JFXButton("OK");
		button.setButtonType(JFXButton.ButtonType.RAISED);
		button.setStyle("-fx-background-color: lightgrey");
		button.setOnAction(e -> {
			jDialog.close();
			if (next) {
				main.getRootLayout().getChildren().remove(1);
				main.initWorkLayout();
			}
		});
		
		content.setActions(button);
		
		userLoginPWField.setText("");
		
		jDialog.show();
	}
	
	public void setMainApp(Main main) {
        this.main = main;
    }
	
}





























