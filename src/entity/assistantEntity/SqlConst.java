package entity.assistantEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SqlConst {
  private final String DB_URL =
      "jdbc:mysql://localhost:3306/graphgrabtest?useUnicode=true&characterEncoding=utf8&&useSSL=false";
  private  final String name = "com.mysql.jdbc.Driver";
  private final String USER = "root";
  private  final String PASS = "123456789";
  static int id =0;
  public static int getId() {
	return id;
}

public static void setId(int id) {
	SqlConst.id = id+1;
}
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
      conn = DriverManager.getConnection(DB_URL, USER,PASS);
      pst = conn.prepareStatement(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public String getDB_URL() {
	return DB_URL;
}

public  String getUSER() {
	return USER;
}

public  String getPASS() {
	return PASS;
}

public SqlConst() {
	    
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
