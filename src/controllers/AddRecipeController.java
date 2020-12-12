package controllers;

/* 
	This class is used to control the "add recipe" screen. It handles
	actions performed on that screen.  
	Programmer: Manyanga Marie Ber
*/


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.RecipeModel;


public class AddRecipeController {

	@FXML private TextField name;
	@FXML private TextField description;
	@FXML private TextField category;
	@FXML private TextField directions;
	@FXML private Button saveButton;
	@FXML private Button backButton;
	private Scene scene;


	public AddRecipeController(Scene scene)
	{
		this.scene = scene;
	}

	
	/*
	 *  This method is used to handle actions performed by the user
	 */
	public void handleAddRecipe(MainViewController mainViewController, boolean flag)
	{

		saveButton.setOnAction((e) -> {
			
			RecipeModel recipe = new RecipeModel (name.getText(), description.getText(), category.getText(), directions.getText());
			mainViewController.getRecipeController().addRecipe(recipe);
			mainViewController.showMainView(flag);
			
		});
		
	
		backButton.setOnAction((e) -> {

			mainViewController.showMainView(flag);

		});
			
		
	}

	
	/*
	 *  This method is used to display the desired page 
	 */
	public void showAddRecipe(MainViewController mainViewController, boolean flag) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddRecipeView.fxml"));
			loader.setController(this);
			scene.setRoot((Parent) loader.load());
			handleAddRecipe(mainViewController, flag);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
