package controllers;

/* 
	This class is used to control the "main view" screen. It handles
	actions performed on that screen.  
	Programmer: Manyanga Marie Ber
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.RecipeModel;

public class MainViewController {

	@FXML private TableView<RecipeModel> tableView;
	@FXML private TableColumn<RecipeModel, String> recipeNameColumn;
	@FXML private TextField search;
	@FXML private Button addButton;
	private Scene scene;
	private RecipeController recipeController;

	
	public MainViewController(Scene scene, RecipeController recipeController)
	{
		tableView = new TableView<RecipeModel>();
		this.scene = scene;
		this.recipeController = recipeController;
	}

		
	public RecipeController getRecipeController() {
		return recipeController;
	}


	/*
	 *  This method is used to handle actions performed by the user
	 */
	
	public void handleMainView(boolean flag)
	{	
		
		MainViewController mainViewController = this;
		
		if(!flag) //disable the button if user does not have admin rights
		{
			
			addButton.setDisable(true);
		}
		
		
		addButton.setOnAction((e) -> {

			
			recipeController.getAddRecipeController().showAddRecipe(mainViewController, flag);			

		});
		
		//display the selected recipe on the "view recipe" screen
		tableView.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				if (event.getClickCount() == 2) //Check if the user has clicked twice
			    {
			        
					int id = tableView.getSelectionModel().getSelectedItem().getId();
					RecipeModel recipe = recipeController.getRecipeById(id);
					recipeController.getViewRecipeController().showViewRecipe(recipe, mainViewController, flag);
			    }
				
			}
		});
		
		
		// add recipes' names in the table
		ArrayList<RecipeModel> recipes = recipeController.getRecipes();
		ObservableList<RecipeModel> items = FXCollections.observableArrayList(recipes);
		recipeNameColumn = (TableColumn<RecipeModel, String>) tableView.getColumns().get(0);
		recipeNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

		
		//handle search functionality  
		FilteredList<RecipeModel> filteredItems = new FilteredList<>(items);
		search.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredItems.setPredicate(data -> {
				if (newValue == null || newValue.isEmpty()) {

					return true;
				}

				if (data.getName().toLowerCase().contains(newValue.toLowerCase())) {

					return true; 
				}

				return false; 
			});
		});

		//display recipes' names in the table
		SortedList<RecipeModel> sortedItems = new SortedList<RecipeModel>(filteredItems);
		sortedItems.comparatorProperty().bind((ObservableValue<? extends Comparator<? super RecipeModel>>) tableView.comparatorProperty());
		tableView.setItems(sortedItems);

	}

	/*
	 *  This method is used to display the desired page 
	 */
	public void showMainView(boolean flag) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
			loader.setController(this);
			scene.setRoot((Parent) loader.load());
			handleMainView(flag);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	

}
