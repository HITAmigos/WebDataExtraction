package net.kuangmeng.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.kuangmeng.*;

@SuppressWarnings("serial")
public class AddAction extends ActionSupport {
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
    private ConstPost cp;
	public String execute() throws Exception {
    		 Connection conn = null;
    		 Statement stmt = null;
    		 System.out.println(cp);
    		 String[] mycol=cp.getCol();
    		 try{
    			   Class.forName("com.mysql.jdbc.Driver").newInstance();
    		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		     stmt = conn.createStatement();
    		     String sql ="insert into "+cp.getTablename().trim()+" set `0`=\'000\' "; 
    		     for(int i=1;i<cp.getNum()-1;i++){
    		    	 sql+=" `"+i+"`="+mycol[i];
    		     }
    		    stmt.executeUpdate(sql); 
    		    return SUCCESS;
    		 }catch(SQLException s){
    			   return ERROR;
    		 }catch(Exception e){
    			   return ERROR;
    		 }
	}
	public ConstPost getCp() {
		return cp;
	}
	public void setCp(ConstPost cp) {
		this.cp = cp;
	}
}
