package controllers;

/* 
	This class is used to control the "edit recipe" screen. It handles
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


public class EditRecipeController {

	@FXML private TextField name;
	@FXML private TextField description;
	@FXML private TextField category;
	@FXML private TextField directions;
	@FXML private Button saveButton;
	@FXML private Button backButton;
	private Scene scene;


	public EditRecipeController(Scene scene)
	{
		this.scene = scene;
	}


	/*
	 *  This method is used to handle actions performed by the user
	 */
	public void handleEditRecipe(MainViewController mainViewController, RecipeModel recipe, boolean flag)
	{
		
		name.setText(recipe.getName());
		description.setText(recipe.getDescription());
		category.setText(recipe.getCategory());
		directions.setText(recipe.getDirections());
		
		saveButton.setOnAction((e) -> {
			
			RecipeModel tempRecipe = new RecipeModel (recipe.getId(), name.getText(), description.getText(), category.getText(), directions.getText());
			mainViewController.getRecipeController().editRecipe(tempRecipe);
			mainViewController.showMainView(flag);
			
		});
		
	
		backButton.setOnAction((e) -> {

			mainViewController.showMainView(flag);

		});
			
		
	}


	/*
	 *  This method is used to display the desired page 
	 */
	public void showEditRecipe(MainViewController mainViewController, RecipeModel recipe, boolean flag) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditRecipeView.fxml"));
			loader.setController(this);
			scene.setRoot((Parent) loader.load());
			handleEditRecipe(mainViewController, recipe, flag);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
