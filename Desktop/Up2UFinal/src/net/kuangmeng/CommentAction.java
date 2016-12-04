package net.kuangmeng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CommentAction extends ActionSupport{
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
    static final String tableName = "comment"; 
	private String username;
	private String name;
	private String email;
	private String message;
	private String selection;
    public String execute() throws Exception{
    	     int ss = 0;
    	     if(selection.equals("question")){
    	    	 ss=1;
    	     }
    		 Connection conn = null;
    		 Statement stmt = null;
    		 try{
    			   Class.forName("com.mysql.jdbc.Driver").newInstance();
    		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		     stmt = conn.createStatement();
    		     String sql = "insert into  " + tableName + " set username = \'"+username+"\' name = \'"+name+"\' email = \'"+email+"\' selection = \'"+ss+"\' message = \'"+message+"\'";  
    		     stmt.executeUpdate(sql); 
    		     return SUCCESS;
    		 }catch(SQLException s){
    			   return ERROR;
    		 }catch(Exception e){
    			   return ERROR;
    		 }
    	   }
}
