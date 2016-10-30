package net.kuangmeng;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
    static final String tableName = "user"; 
	private String username;
	private String password;
    public String execute() throws Exception {
    		 Connection conn = null;
    		 Statement stmt = null;
    		 try{
    			   Class.forName("com.mysql.jdbc.Driver").newInstance();
    		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		     stmt = conn.createStatement();
    		     String sql = "SELECT * FROM " + tableName + " WHERE username = "+ "\'"+username+"\'";  
    		     ResultSet rs = stmt.executeQuery(sql); 
    		     if(rs.next()){
    		    	 if(password.equals(rs.getString("password"))) return SUCCESS;
    		    	 else return "back";
    		     }else{
    		    	 return ERROR;
    		     }
    		 }catch(SQLException s){
    			   return ERROR;
    		 }catch(Exception e){
    			   return ERROR;
    		 }
    	   }
    public String getUsername(){
   	 return username;
    }
    public String getPassword(){
    	return password;
    }
    public void setUsername(String username){
    	this.username=username;
    }
   public void setPassword(String password){
	   this.password=password;
   }
  
}