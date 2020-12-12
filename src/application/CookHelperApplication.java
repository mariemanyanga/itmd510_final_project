package application;

/* 
	This class contains the main method used to run the application  
	Programmer: Manyanga Marie Ber
*/

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import controllers.MainController;

public class CookHelperApplication extends Application {

	public static void main(String[] args) { launch(args); }
	@Override public void start(Stage stage) throws IOException {

		Scene scene = new Scene(new StackPane());
		MainController mainController = new MainController(scene);
		mainController.initiliaze();
			
		stage.setTitle("CookHelper");
		stage.setScene(scene);
		stage.show();

	}
}