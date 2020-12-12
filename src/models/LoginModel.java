package models;

/* 
	This class serves as "a model" for the Login Controller. It
	interacts with the database and provides information needed
	by the controller. 
	Programmer: Manyanga Marie Ber
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.DaoModel;

public class LoginModel extends DaoModel {

	private String role;
	
	public String getRole() {

		return role;
	
	}
	
	/*
	 *  This method is used to get credentials fom the database
	 */ 
	public boolean getCredentials(String username, String password){
	
		try {

			statement = connection.connect().createStatement();
			String sql = "SELECT * FROM cookhelper_users";
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()){
				
				String tempUsername = rs.getString("username");
				String tempPassword = rs.getString("password");
				boolean isAdmin = rs.getBoolean("admin");
				
				if ( username.equals(tempUsername) && password.equals(tempPassword) ) {
					
					
					if(isAdmin) {
						
						role = "admin";
					}
					
					else {
						
						
						role = "user";
					}
					
					return true;
					
				}
				
			}

			rs.close();
			connection.connect().close(); 

		}

		catch (SQLException se) { 
			se.printStackTrace();
		}

		return false;

	}

}
