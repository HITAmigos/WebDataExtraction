package net.kuangmeng.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

import net.kuangmeng.Const;

@SuppressWarnings("serial")
public class DeleterowAction extends ActionSupport{
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
    private String tablename;
	private String rownum;
    public String execute() throws Exception {
    	    System.out.println(tablename);
    	    System.out.println(rownum);
    		 Connection conn = null;
    		 Statement stmt = null;
    		 try{
    			 Class.forName("com.mysql.jdbc.Driver").newInstance();
    		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		     stmt = conn.createStatement();
    		     String sqlupdate="Select * from `"+tablename+"` where id = 1";
    		     ResultSet rs=stmt.executeQuery(sqlupdate);
    		     while(rs.next()){
    		       char[] mark = rs.getString(rownum).toCharArray();
//    		    	 int mark=Integer.parseInt(rs.getString(rownum));
//    		    	 mark+=1;
    		       mark[1]='1';
    		    	 String sql ="update `"+tablename+"` set "+"`"+rownum+"`= \'" + (new String(mark)) +"\'  where  id = 1 ";
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
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
}
