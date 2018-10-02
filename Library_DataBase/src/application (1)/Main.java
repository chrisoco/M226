package application;
	
import java.io.IOException;

import application.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public Stage primaryStage;
	public BorderPane rootLayout;

	public static DB db;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library DataBase M153 / M226a");
		
		Main.db = new DB();
		
		initRootLayout();
//		initLoginLayout();	// Use    Line for LoginScreen
		initWorkLayout();	// Remove Line for LoginScreen
		
	}
	
	public void initRootLayout() {
		try {
			// Load root layout from FXML file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initLoginLayout() {
		try {
			// Load LoginLayout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/LoginScreen.fxml"));
			AnchorPane loginScreen = (AnchorPane) loader.load();
			
			// Set LoginScreen into center of root layout.
			rootLayout.setCenter(loginScreen);
			
			// Give Access to Main:
			LoginController loginCon = loader.getController();
	        loginCon.setMainApp(this);
			
//			((LoginController) loader.getController()).setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initWorkLayout() {
		try {
			// Load LoginLayout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/WorkLayout.fxml"));
			AnchorPane WorkLayout = (AnchorPane) loader.load();
			
			// Set LoginScreen into center of root layout.
			rootLayout.setCenter(WorkLayout);
			
			// Give Access to Main:
//			loader.setLocation(Main.class.getResource("view/tab/person/PersonController.fxml"));
//			LoginController personCon = loader.getController();
//	        personCon.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}
}
