package action.assistantOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import entity.assistantEntity.SqlConst;

public class DeleteTable {
    private String tablename;

	
	SqlConst c=new SqlConst();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
	 public String execute() throws Exception{
			Connection conn = null;
		    Statement stmt = null;
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   stmt = conn.createStatement();
		   String sql = "DELETE FROM  searchRecord WHERE tablename = \'"+tablename+"\'";  
		   stmt.executeUpdate(sql); 
		  return "success";
		}catch(SQLException s){
			   return "error";
		}catch(Exception e){
			   return "error";
		}
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}
