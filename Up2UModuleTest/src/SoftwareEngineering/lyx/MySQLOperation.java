package SoftwareEngineering.lyx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * �����ݿ��ֱ�Ӳ���.
 * @author liuyx ������Ϊsql���
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