package net.kuangmeng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleterecordAction extends ActionSupport{
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
    static final String tableName = "SearchRecord"; 
    private String username;
    public String execute() throws Exception {
    		 Connection conn = null;
    		 Statement stmt = null;
    		 try{
    			 Class.forName("com.mysql.jdbc.Driver").newInstance();
    		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		     stmt = conn.createStatement();
    		     String sqlsearch="select * from "+tableName+" WHERE username = \'"+username+"\'";
    		     ResultSet rss=stmt.executeQuery(sqlsearch);
    		     while(rss.next()){
    		    	 String del="DROP TABLE "+"`"+rss.getString("tablename")+"`";
    		    	 stmt.executeUpdate(del);
    		     }
    		     String sql = "DELETE FROM "+tableName+" WHERE username = \'"+username+"\'";  
    		     stmt.executeUpdate(sql); 
    		     return SUCCESS;
    		 }catch(SQLException s){
    			   return ERROR;
    		 }catch(Exception e){
    			   return ERROR;
    		 }
    	   }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
