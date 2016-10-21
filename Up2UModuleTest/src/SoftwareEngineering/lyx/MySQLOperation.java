package SoftwareEngineering.lyx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 对数据库的直接操作.
 * @author liuyx 参数均为sql语句
 */
public class MySQLOperation {

  public static final String ulr = "jdbc:mysql://localhost:3306/grabgraphtest";
  public static final String name = "com.mysql.jdbc.Driver";
  public static final String user = "root";
  public static final String password = "123456789";
  
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
//  /**
//   * 新建表
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean createTable(String sql) {
//    return false;
//  }
//
//  /**
//   * 查看表数据
//   * 
//   * @param sql
//   * @return
//   */
//  public String[][] getTable(String sql) {
//    return null;
//  }
//
//  /**
//   * 查看表结构
//   * 
//   * @param sql
//   * @return
//   */
//  public String[][] getStruction(String sql) {
//    return null;
//  }
//
//  /**
//   * 修改表结构
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean changeStruction(String sql) {
//    return false;
//  }
//
//  /**
//   * 删除表
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean deleteTable(String sql) {
//    return false;
//  }
//
//  /**
//   * 添加表数据
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean insertData(String sql) {
//    return false;
//  }
//
//  /**
//   * 更新表数据
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean updateData(String sql) {
//    return false;
//  }
//
//  /**
//   * 查询表数据
//   * 
//   * @param sql
//   * @return
//   */
//  public String getData(String sql) {
//    return null;
//  }
//}
