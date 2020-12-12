package controllers;

/* 
	This class serves as the main controller for the application. It 
	initializes other controllers and displays the first screen.
	Programmer: Manyanga Marie Ber 
*/


import javafx.scene.Scene;

public class MainController {
	
	private LoginController loginController;
	private RecipeController recipeController;
	private MainViewController mainViewController;
		
	public MainController(Scene scene) {
		
		recipeController = new RecipeController(scene);
		mainViewController = new MainViewController(scene, recipeController);
		loginController = new LoginController(scene, mainViewController);
		
	}
	
	/*
	 *  This method is used to initialize the main controller
	 */
	public void initiliaze() {
		
		loginController.showLoginView();
		
	}
	
	
}
