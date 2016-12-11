package action.tableOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.assistantEntity.SqlConst;

public class PushUpAction {
     private String tablename;
     private int rowNum;
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	SqlConst c=new SqlConst();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
	public String execute(){
		System.out.println(rowNum);
		System.out.println(tablename);
		Connection conn = null;
	    Statement stmt = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   stmt = conn.createStatement();
	   int num = rowNum - 1;
	   System.out.println(num);
	   String searchsql = "select * from `"+tablename+"` where id = "+num;
	   ResultSet rs = stmt.executeQuery(searchsql);
	   System.out.println(123);
	   while(rs.next()){
		   System.out.println(123444);
		   System.out.println(rs.getString("0"));
		   if(rs.getString("0").equals("001")){
			   System.out.println(15545);
			   return "success";
		   }else{
			   System.out.println(177777);
			   String sql = "update `"+tablename+"` set id=1000  where id="+rowNum;  
			   stmt.executeUpdate(sql); 
			   System.out.println("ssdkfg");
			   String sql2 = "update `"+tablename+"` set id="+rowNum+"  where id="+num; 
			   stmt.executeUpdate(sql2); 
			   System.out.println("sbfbe,f e");
			   String sql3 = "update `"+tablename+"` set id="+num+"  where id=1000";
			   stmt.executeUpdate(sql3);
			   return "success";
		   }
	   }
	  return "success";
	}catch(SQLException s){
		   return "error";
	}catch(Exception e){
		   return "error";
	}
	}
}
