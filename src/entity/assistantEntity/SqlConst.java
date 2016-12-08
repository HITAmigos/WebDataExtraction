package entity.assistantEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SqlConst {
  private static final String url =
      "jdbc:mysql://localhost:3306/graphgrabtest?useUnicode=true&characterEncoding=utf8&&useSSL=false";
  private static final String name = "com.mysql.jdbc.Driver";
  private static final String user = "root";
  private static final String password = "123456789";
  private Connection conn = null;
  private PreparedStatement pst = null;

  public Connection getConn() {
    return conn;
  }

  public PreparedStatement getPst() {
    return pst;
  }

  public SqlConst(String sql) {
    try {
      Class.forName(name);
      conn = DriverManager.getConnection(url, user, password);
      pst = conn.prepareStatement(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      this.pst.close();
      this.conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
