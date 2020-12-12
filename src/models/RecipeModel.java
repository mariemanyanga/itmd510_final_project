package models;

/* 
	This class serves as "a model" for the Recipe Controller. It
	interacts with the database and provides information needed
	by the controller. 
	Programmer: Manyanga Marie Ber
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.DaoModel;

public class RecipeModel extends DaoModel{

	private int id;
	private String name;
	private String description;
	private String category;
	private String directions;

	public RecipeModel() {

	}

	public RecipeModel(int id, String name, String description, String category, String directions) {

		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.directions = directions;
	}

	public RecipeModel(String name, String description, String category, String directions) {

		this.name = name;
		this.description = description;
		this.category = category;
		this.directions = directions;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}

	
	/* 
		This method adds a recipe to the database
	*/
	public void addRecipe(RecipeModel recipeModel) {

		String name = recipeModel.getName();
		String description = recipeModel.getDescription();
		String category = recipeModel.getCategory();
		String directions = recipeModel.getDirections();

		try {

			statement = connection.connect().createStatement();
			String sql = "INSERT INTO cookhelper_recipe (name, description, category, directions) " + 
					"VALUES (" + "'" + name + "', " + "'" + description + "', " + "'" + category + "', " + "'" + directions + "')"; 

			statement.executeUpdate(sql);
			connection.connect().close(); 

		}catch (SQLException se) { 
			se.printStackTrace();
		}

	}
	
	/* 
		This method changes information related to 
		a recipe in the database
	 */
	public void editRecipe(RecipeModel recipeModel) {

		int id = recipeModel.getId();
		String name = recipeModel.getName();
		String description = recipeModel.getDescription();
		String category = recipeModel.getCategory();
		String directions = recipeModel.getDirections();

		try {

			statement = connection.connect().createStatement();
			String sql = "UPDATE cookhelper_recipe SET name =" + "'" + name + "', description = " + "'" + description + "', category = " + "'" + category + "', directions = " + "'" + directions 
				+ "'" + " where id = " + id;
			
			statement.executeUpdate(sql);
			connection.connect().close(); 

		}catch (SQLException se) { 
			se.printStackTrace();
		}

	}

	
	/* 
		This method gets all recipes from the database
	*/
	public ArrayList<RecipeModel> getRecipes(){

		ArrayList<RecipeModel> recipes = new ArrayList<RecipeModel>();

		try {

			statement = connection.connect().createStatement();
			String sql = "SELECT * FROM cookhelper_recipe";
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()){

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String category = rs.getString("category");
				String directions = rs.getString("directions");

				RecipeModel recipe = new RecipeModel(id, name, description, category, directions);
				recipes.add(recipe);

			}

			rs.close();
			connection.connect().close(); 

		}

		catch (SQLException se) { 
			se.printStackTrace();
		}

		return recipes;

	}

	/* 
		This method gets a recipe from the database
	*/
	public RecipeModel getRecipeById(int recipeId){

		RecipeModel recipe = null;

		try {

			statement = connection.connect().createStatement();
			String sql = "SELECT * FROM cookhelper_recipe where id = " + recipeId;
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()){

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String category = rs.getString("category");
				String directions = rs.getString("directions");

				recipe = new RecipeModel(id, name, description, category, directions);

			}

			rs.close();
			connection.connect().close(); 

		}

		catch (SQLException se) { 
			se.printStackTrace();
		}

		return recipe;

	}

	/* 
		This method deletes a recipe in the database
	 */
	public void deleteRecipe(int recipeId) {

		try {

			statement = connection.connect().createStatement();
			String sql = "DELETE FROM cookhelper_recipe where id = " + recipeId; 

			statement.executeUpdate(sql);
			connection.connect().close(); 

		}catch (Exception se) { 
			se.printStackTrace();
		}

	}


}
