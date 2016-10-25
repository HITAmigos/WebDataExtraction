package SoftwareEngineering.lyx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 对数据库的直接操作.
 * @author liuyx 参数均为sql语句
 */
public class MySQLOperation {

  private static final String ulr = "jdbc:mysql://localhost:3306/grabgraphtest";
  private static final String name = "com.mysql.jdbc.Driver";
  private static final String user = "root";
  private static final String password = "123456789";
  
  public Connection conn = null;
  public PreparedStatement pst = null;
  
  public MySQLOperation(String sql)   {
      try{
          //加载JDBC驱动程序,指定连接类型
          Class.forName(name);
          //创建数据库的连接 
          conn=DriverManager.getConnection(ulr,user,password);
          //创建一个Statement,相当于输入命令行
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