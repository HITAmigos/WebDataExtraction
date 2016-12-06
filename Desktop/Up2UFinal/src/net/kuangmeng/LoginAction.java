package net.kuangmeng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
  Const c = new Const();
  final String DB_URL = c.getDB_URL();
  final String USER = c.getUSER();
  final String PASS = c.getPASS();
  static final String tableName = "user";
  private String username;
  private String password;
  private int level;

  public String execute() throws Exception {
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM " + tableName + " WHERE username = " + "\'" + username + "\'";
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        if (password.equals(rs.getString("password"))) {
          if (rs.getInt("no") == 1)
            return "admin";
          return SUCCESS;
        } else {
          return "back";
        }
      } else {
        System.out.println(1);
        return ERROR;
      }
    } catch (SQLException s) {
      return ERROR;
    } catch (Exception e) {
      return ERROR;
    }
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int SearchLevel(String username) {
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM " + tableName + " WHERE username = " + "\'" + username + "\'";
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        return rs.getInt("level");
      } else {
        return 0;
      }
    } catch (SQLException s) {
      return 0;
    } catch (Exception e) {
      return 0;
    }
  }
  public int SearchCoin(String username) {
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM " + tableName + " WHERE username = " + "\'" + username + "\'";
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        return rs.getInt("coins");
      } else {
        return 0;
      }
    } catch (SQLException s) {
      return 0;
    } catch (Exception e) {
      return 0;
    }
  }

  public boolean SearchUrl(String url, String username) {
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM `SearchRecord` WHERE link = " + "\'" + url + "\' and username = \'"
          + username + "\'";
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException s) {
      return false;
    } catch (Exception e) {
      return false;
    }
  }
}
