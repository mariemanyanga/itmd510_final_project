package controllers;

/* 
	This class is used to control the "view recipe" screen. It handles
	actions performed on that screen.  
	Programmer: Manyanga Marie Ber
*/

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import models.RecipeModel;


public class ViewRecipeController {

	@FXML private TextArea name;
	@FXML private TextArea description;
	@FXML private TextArea category;
	@FXML private TextArea directions;
	@FXML private Button deleteButton;
	@FXML private Button editButton;
	@FXML private Button backButton;
	private Scene scene;


	public ViewRecipeController(Scene scene)
	{
		this.scene = scene;
	}

	/*
	 *  This method is used to handle actions performed by the user
	 */
	public void handleViewRecipe(RecipeModel recipe, MainViewController mainViewController, boolean flag)
	{

		name.setText(recipe.getName());
		description.setText(recipe.getDescription());
		category.setText(recipe.getCategory());
		directions.setText(recipe.getDirections());

		
		if(!flag) //disable buttons if user does not have admin rights
		{
			
			deleteButton.setDisable(true);
			editButton.setDisable(true);
		}
		
	
		backButton.setOnAction((e) -> {

			mainViewController.showMainView(flag);


		});
			
		
		deleteButton.setOnAction((e) -> {

			mainViewController.getRecipeController().deleteRecipeById(recipe.getId());
			mainViewController.showMainView(flag);

		});	
		
		

		editButton.setOnAction((e) -> {

			
			mainViewController.getRecipeController().getEditRecipeController().showEditRecipe(mainViewController, recipe, flag);
			

		});	
		
		
	}

	/*
	 *  This method is used to display the desired page 
	 */
	public void showViewRecipe(RecipeModel recipe, MainViewController mainViewController, boolean flag) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViewRecipe.fxml"));
			loader.setController(this);
			scene.setRoot((Parent) loader.load());
			handleViewRecipe(recipe, mainViewController, flag);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
