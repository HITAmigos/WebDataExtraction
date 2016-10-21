package SoftwareEngineering.lyx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * �����ݿ��ֱ�Ӳ���.
 * @author liuyx ������Ϊsql���
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
          //����JDBC��������,ָ����������
          Class.forName(name);
          //�������ݿ������ 
          conn=DriverManager.getConnection(ulr,user,password);
          //����һ��Statement,�൱������������
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
//   * �½���
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean createTable(String sql) {
//    return false;
//  }
//
//  /**
//   * �鿴������
//   * 
//   * @param sql
//   * @return
//   */
//  public String[][] getTable(String sql) {
//    return null;
//  }
//
//  /**
//   * �鿴��ṹ
//   * 
//   * @param sql
//   * @return
//   */
//  public String[][] getStruction(String sql) {
//    return null;
//  }
//
//  /**
//   * �޸ı�ṹ
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean changeStruction(String sql) {
//    return false;
//  }
//
//  /**
//   * ɾ����
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean deleteTable(String sql) {
//    return false;
//  }
//
//  /**
//   * ��ӱ�����
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean insertData(String sql) {
//    return false;
//  }
//
//  /**
//   * ���±�����
//   * 
//   * @param sql
//   * @return
//   */
//  public boolean updateData(String sql) {
//    return false;
//  }
//
//  /**
//   * ��ѯ������
//   * 
//   * @param sql
//   * @return
//   */
//  public String getData(String sql) {
//    return null;
//  }
//}
