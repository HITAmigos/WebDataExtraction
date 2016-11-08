package net.kuangmeng.table;
import java.sql.Connection;
import java.sql.DriverManager;
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
    		     String sql ="delete from `" + tablename + "` WHERE id = "+Integer.parseInt(colnum.trim());  
    		     int rs = stmt.executeUpdate(sql); 
    		     if(rs>=1) return SUCCESS;
    		     else return ERROR;
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
