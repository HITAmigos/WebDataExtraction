package net.kuangmeng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport{
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
    static final String tableName = "user"; 
    static final String table = "user";
	private String username;
	private String password;
	private String email;
    public String execute() throws Exception {
    	     if(searchUsername(username).equals(ERROR)){
    	    	 return ERROR;
    	     }else if(searchUsername(username).equals("back")){
    	    	 return "back";
    	     }else{
    		 Connection conn = null;
    		 Statement stmt = null;
    		 try{
    			 Class.forName("com.mysql.jdbc.Driver").newInstance();
    		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		     stmt = conn.createStatement();
    		     String sqlupdate = "insert " + tableName + " values("+c.getId()+",\'"+username+"\',\'"+password+"\',\'"+email+ "\',"+0+","+0+","+0+")";  
    		     stmt.executeUpdate(sqlupdate);
    		     c.setId(c.getId()+1);
    		     return SUCCESS;
    		 }catch(SQLException s){
    			   return ERROR;
    		 }catch(Exception e){
    			   return ERROR;
    		 }
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String searchUsername(String username){
	 Connection conn = null;
	 Statement stmt = null;
	 try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
	     conn = DriverManager.getConnection(DB_URL,USER,PASS);
	     stmt = conn.createStatement();
	     String sql = "SELECT * FROM " + table + " WHERE username = "+ "\'"+username+"\'";  
	     ResultSet rs = stmt.executeQuery(sql); 
	     if(rs.next()){
	    	 return "back";
	     }else{
	    	 return SUCCESS;
	     }
	 }catch(SQLException s){
		   return ERROR;
	 }catch(Exception e){
		   return ERROR;
	 }
}
}
