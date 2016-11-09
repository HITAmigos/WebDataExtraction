package net.kuangmeng.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

import net.kuangmeng.Const;

@SuppressWarnings("serial")
public class DeletecolAction extends ActionSupport{
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
    private String tablename;
	private String colnum;
    public String execute() throws Exception {
    	    System.out.println(tablename);
    	    System.out.println(colnum);
    		 Connection conn = null;
    		 Statement stmt = null;
    		 try{
    			 Class.forName("com.mysql.jdbc.Driver").newInstance();
    		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		     stmt = conn.createStatement();
    		     String sqlupdate ="select * from  `" + tablename + "`  WHERE id = "+Integer.parseInt(colnum.trim());  
      		     ResultSet rss=stmt.executeQuery(sqlupdate);
      		     while(rss.next()){
      		    	int mark = Integer.parseInt(rss.getString("0"));
      		    	mark+=10;
      		    	 String sql ="update `" + tablename + "` set `0`= \'" + mark+ "\' WHERE id = "+Integer.parseInt(colnum.trim());  
       		      stmt.executeUpdate(sql); 
       		    return SUCCESS;
      		     }
    		    return ERROR;
    		 }catch(SQLException s){
    			   return ERROR;
    		 }catch(Exception e){
    			   return ERROR;
    		 }
    	   }
	public String getColnum() {
		return colnum;
	}
	public void setColnum(String colnum) {
		this.colnum = colnum;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}
