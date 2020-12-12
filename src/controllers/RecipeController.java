package controllers;

/* 
	This class controls the data flow within the application. It
	provides methods needed to interact with the database in
	order to get recipes' information or to perform similar opera-
	tions.  
	Programmer: Manyanga Marie Ber
*/

import java.util.ArrayList;
import javafx.scene.Scene;
import models.RecipeModel;

public class RecipeController {
	
	private RecipeModel recipeModel;
	private ViewRecipeController viewRecipeController;
	private AddRecipeController addRecipeController;
	private EditRecipeController editRecipeController;
		
	public RecipeController(Scene scene) {
		
		recipeModel = new RecipeModel();
		viewRecipeController = new ViewRecipeController(scene);
		addRecipeController = new AddRecipeController(scene);
		editRecipeController = new EditRecipeController(scene);

	}
	
	public ViewRecipeController getViewRecipeController() {
		
		return viewRecipeController;
	}
	
	public AddRecipeController getAddRecipeController() {
		
		return addRecipeController;
	}
	
	public EditRecipeController getEditRecipeController() {
		
		return editRecipeController;
	}
	
	/* 
		This method adds a recipe to the database
	 */
	public void addRecipe(RecipeModel recipe){
		
		 recipeModel.addRecipe(recipe);
		
	}
	
	/* 
		This method changes information related to 
		a recipe in the database
	 */
	public void editRecipe(RecipeModel recipe){
		
		recipeModel.editRecipe(recipe);
		
	}
	
	/* 
		This method gets all recipes from the database
	*/
	public ArrayList<RecipeModel> getRecipes(){
		
		return recipeModel.getRecipes();
		
	}
	
	/* 
		This method gets a recipe from the database
	 */
	public RecipeModel getRecipeById(int id){
		
		return recipeModel.getRecipeById(id);
		
	}
	
	/* 
		This method deletes a recipe in the database
	 */
	public void deleteRecipeById(int id){
		
		 recipeModel.deleteRecipe(id);
		
	}

}
