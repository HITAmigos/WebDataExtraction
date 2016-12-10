package action.assistantOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.UserTable;
import entity.assistantEntity.SqlConst;
import entity.assistantEntity.User;

public class AddUser {
	private String password;
	  private String email;
	  private String username;
	  
	  public void setPassword(String password) {
	    this.password = password;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	  public String execute(){
	    String result = "success";
	    System.out.println(password);
	    System.out.println(username);
	    if(UserTable.getUser(username)!=null){
	      result = "exist";
	    }else{
	      User user = new User();
	      user.setUsername(username);
	      user.setPassword(password);
	      user.setEmail(email);
	      user.setLimited(0);
	      user.setLevel(0);
	      user.setCoins(0);
	      System.out.println(username);
	      System.out.println(email);
	      
	      if(!UserTable.Regist(user)){
	        result = "error";
	      }
	    }
	    System.out.println(result);
	    return result;
	  }
}
