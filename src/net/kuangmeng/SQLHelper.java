package net.kuangmeng;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SQLHelper {

  private static final String url = "jdbc:mysql://localhost:3306/up2u";
  private static final String name = "com.mysql.jdbc.Driver";
  private static final String user = "root";
  private static final String password = "";
  
  public Connection conn = null;
  public PreparedStatement pst = null;
  
  public SQLHelper(String sql)   {
      try{
          Class.forName(name);
          conn=DriverManager.getConnection(url,user,password);
          pst=conn.prepareStatement(sql);
      } catch(Exception e){
          e.printStackTrace();
      }
  }
  
  public void close()
  {
      try{
          this.pst.close();
          this.conn.close();
      } catch(Exception e){
          e.printStackTrace();
      }
  }

}