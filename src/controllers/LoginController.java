package controllers;

/* 
	This class is used to control the "login" screen. It handles
	actions performed on that screen.  
	Programmer: Manyanga Marie Ber
*/

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import models.LoginModel;

public class LoginController {

	@FXML private TextField user;
	@FXML private TextField password;
	@FXML private Button loginButton;

	private Scene scene;
	private LoginModel loginModel;
	private MainViewController mainViewController;

	public LoginController(Scene scene, MainViewController mainViewController) {

		this.scene = scene;
		loginModel = new LoginModel();
		this.mainViewController = mainViewController;	
	}

	/*
	 *  This method is used to handle actions performed by the user
	 */
	public void handleLogin() {

		loginButton.setOnAction((e) -> {

			if(checkCrentials().equals("admin")) {

				System.out.println("Success! You are connected and your role is : admin");
				mainViewController.showMainView(true);
			}

			else if(checkCrentials().equals("user")) {

				System.out.println("Success! You are connected and your role is : user");
				mainViewController.showMainView(false);
			}

			else {

				System.out.println("Wrong username or passowrd! Please try again...");				

			}

		});

	}

	/*
	 *  This method is used to check authorization credentials
	 */ 
	private String checkCrentials() {

		if(loginModel.getCredentials(user.getText(), password.getText())) {

			return loginModel.getRole();
		}

		return "not found";

	}
	
	/*
	 *  This method is used to display the desired page 
	 */
	public void showLoginView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
			loader.setController(this);
			scene.setRoot((Parent) loader.load());
			handleLogin();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	


}
