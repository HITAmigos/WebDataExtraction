package action.admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import  entity.assistantEntity.*;
public class AdminOperation {
   SqlConst ac = new SqlConst();
   final String DB_URL= ac.getDB_URL();
   final String PASS = ac.getPASS();
   final String USER = ac.getUSER();
   String[] tablename = {"user","comment","searchrecord"};
   public int UserNum() throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   stmt = conn.createStatement();
	   String sql = "select * from "+tablename[0];  
	   ResultSet rs = stmt.executeQuery(sql); 
	   int num = 0;
	   while(rs.next()){
		   if(rs.getInt("id")>0){
			   num++;
		   }
	   }
	   return num;
	}catch(SQLException s){
		   return 0;
	}catch(Exception e){
		   return 0;
	}
}
   public int CommentNum() throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   stmt = conn.createStatement();
	   String sql = "select * from "+tablename[1];  
	   ResultSet rs = stmt.executeQuery(sql); 
	   int num = 0;
	   while(rs.next()){
		   if(rs.getInt("id")>0){
			   num++;
		   }
	   }
	   return num;
	}catch(SQLException s){
		   return 0;
	}catch(Exception e){
		   return 0;
	}
}
   public int RecordNum() throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   stmt = conn.createStatement();
	   String sql = "select * from "+tablename[2];  
	   ResultSet rs = stmt.executeQuery(sql); 
	   int num = 0;
	   while(rs.next()){
		   if(rs.getInt("id")>0){
			   num++;
		   }
	   }
	   return num;
	}catch(SQLException s){
		   return 0;
	}catch(Exception e){
		   return 0;
	}
}
   public boolean AdminOrNot(String username) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   stmt = conn.createStatement();
	   String sql = "select * from "+tablename[0]+" where username = \'"+username+"\'";  
	   ResultSet rs = stmt.executeQuery(sql); 
	   while(rs.next()){
		   if(rs.getInt("limited")==1){
			  return true;
		   }
	   }
	   return false;
	}catch(SQLException s){
		   return false;
	}catch(Exception e){
		   return false;
	}
}
}
