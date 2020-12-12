package dao;

/* 
	This class contains methods used to perform database related
	operations such as creating a table, inserting records, etc.

	Programmer: Manyanga Marie Ber
*/

import java.sql.SQLException;
import java.sql.Statement;


public class DaoModel {

	protected DBConnect connection = null;
	protected Statement statement = null;


	public DaoModel() { 

		connection = new DBConnect();	
	}

	/*
	 *  This method is used to create a table to store users information
	 */
	public void createUsersTable() {

		try {

			statement = connection.connect().createStatement();
			String sql = "CREATE TABLE cookhelper_users " + 
					"(id INTEGER NOT NULL AUTO_INCREMENT, " + 
					" username VARCHAR(50) NOT NULL, " +
					" password VARCHAR(50) NOT NULL, " + 
					" admin BOOLEAN NOT NULL, " +
					" PRIMARY KEY ( id ))";

			statement.executeUpdate(sql);
			connection.connect().close(); 

		}catch (SQLException se) { 
			se.printStackTrace();
		}

	}

	/*
	 *  This method is used to add a user with admin rights in the database
	 */
	public void addAdmin() {

		try {

			statement = connection.connect().createStatement();
			String sql = "INSERT INTO cookhelper_users (username, password, admin) " + 
					"VALUES ('admin', 'admin', true)";

			statement.executeUpdate(sql);
			connection.connect().close(); 

		}catch (SQLException se) { 
			se.printStackTrace();
		}

	}

	/*
	 *  This method is used to add a user in the database (no admin rights)
	 */
	public void addUser() {

		try {

			statement = connection.connect().createStatement();
			String sql = "INSERT INTO cookhelper_users (username, password, admin) " + 
					"VALUES ('user', 'user', false)";

			statement.executeUpdate(sql);
			connection.connect().close(); 

		}catch (SQLException se) { 
			se.printStackTrace();
		}

	}

	/*
	 *  This method is used to create a table to store recipes
	 */
	public void createRecipeTable() {

		try {

			statement = connection.connect().createStatement();
			String sql = "CREATE TABLE cookhelper_recipe " + 
					"(id INTEGER NOT NULL AUTO_INCREMENT, " + 
					" name VARCHAR(150) NOT NULL, " +
					" description VARCHAR(255) NOT NULL, " + 
					" category VARCHAR(50) NOT NULL, " +
					" directions TEXT NOT NULL, " +
					" PRIMARY KEY ( id ))";

			statement.executeUpdate(sql);
			connection.connect().close(); 

		}catch (SQLException se) { 
			se.printStackTrace();
		}

	}

}
