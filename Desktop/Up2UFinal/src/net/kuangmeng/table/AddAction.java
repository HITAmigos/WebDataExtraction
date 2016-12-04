package net.kuangmeng.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.kuangmeng.Const;

@SuppressWarnings("serial")
public class AddAction extends ActionSupport {
  Const c = new Const();
  final String DB_URL = c.getDB_URL();
  final String USER = c.getUSER();
  final String PASS = c.getPASS();
  private String tablename;
  private int num;
  private String str;
  
  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public String getTablename() {
    return tablename;
  }

  public void setTablename(String tablename) {
    this.tablename = tablename;
  }

  public String execute() throws Exception {
    Connection conn = null;
    Statement stmt = null;
    if (str == null) {
      System.out.println("STR:NULL");
      return ERROR;
    }else{
      System.out.println("STR:("+str+")");
    }
    String[] col = str.split("\\,");
    try {

      StringBuffer sqlBuf = new StringBuffer();
      sqlBuf.append("INSERT INTO `" + tablename + "` set `0` = \'000\' ,");
      for (int i = 0; i < num - 2; i++) {
        sqlBuf.append(" `" + (i+1) + "` = \'" + col[i] + "\' ,");
      }
      sqlBuf.deleteCharAt(sqlBuf.length()-1);
      String sql = sqlBuf.toString();
      System.out.println(sql);
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.prepareStatement(sql);
      stmt.execute(sql);
      return SUCCESS;
    } catch (SQLException s) {
      return ERROR;
    } catch (Exception e) {
      return ERROR;
    }
  }

}
