package SoftwareEngineering.lyx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * ���ݿ�������.
 * 
 * @author liuyx
 * @param ��TableAction����ܲ��� ����sql��䲢����MySQLOperation���޸����ݿ�
 * @return �����ɹ����
 */
public class DBConnection {
  static String sql = null;
  static MySQLOperation dbHelper = null;
  static ResultSet resultSet = null;
  static boolean retb = false;

  /**
   * ע��ʱ�ж��û��Ƿ����.
   * 
   * @param Username
   * @return ����true,������false ���ڱ��������û������������������û���������ֻҪ�û������ڱ�ʹ���.
   */
  public boolean UserIsExist(String Username) {
    boolean result = false;
    sql = "select * from `userRegedit`";// SQL���
    dbHelper = new MySQLOperation(sql);

    try {
      resultSet = dbHelper.pst.executeQuery();// ִ����䣬�õ������
      while (resultSet.next()) {
        String getUsername = resultSet.getString(Username);

        System.out.println(getUsername);
        if (getUsername.equals(Username)) {
          result = true;
          break;
        }
        resultSet.close();// �رս����
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbHelper.close();// �ر�����
    }
    return result;
  }

  // /**
  // * ���û���ֻ��һ����ʱ�����жϣ���Ϊ�û����������ͬ
  // * �ж�һ�����Ƿ����.
  // */
  // public boolean TableIsExist(final String Username) {
  // boolean result = true;
  // return result;
  // }

  /**
   * ע�����û�.
   */
  public boolean Register(String Username, String Password) {
    boolean result = false;
    sql = "insert into UserRegedit set " + "Username=" + "'" + Username + "'," + "Password=" + "'"
        + Password + "';";// SQL���
    dbHelper = new MySQLOperation(sql);

    try {
      result = dbHelper.pst.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbHelper.close();// �ر�����
    }
    return result;
  }

  /**
   * ��½ʱ�ж��û��������Ƿ����.
   */
  public boolean Login(String Username, String Password) {
    boolean result = false;
    sql = "select * from `UserRegedit`";
    dbHelper = new MySQLOperation(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();
      while (resultSet.next()) {
        String getUsername = resultSet.getString(Username);
        String getPassword = resultSet.getString(Password);

        System.out.println("<" + getUsername + "," + getPassword + ">");
        if (getUsername.equals("Username")) {
          if (getPassword.equals(Password)) {
            result = true;
            break;
          }
        }
        resultSet.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }


  /**
   * ����һ���±�.
   */
  public boolean Create(final String Username, final String[][] Table) {
    boolean result = true;
    return result;
  }

  /**
   * ɾ��һ����.
   */
  public boolean Delete(final String Username) {
    boolean result = true;
    return result;
  }

  /**
   * ����һ��������(��ṹ����).
   */
  public boolean Update(final List Change) {
    boolean result = true;
    return result;
  }

  /**
   * ɾ�����ֶ�.
   */
  public boolean DeleteCol(final int ColumnNum) {
    boolean result = true;
    return result;
  }

  /**
   * ɾ��ĳһ��.
   */
  public boolean DeleteRow(final int RowNum) {
    boolean result = true;
    return result;
  }

  /**
   * ����һ��������������.
   */
  public String[][] Search(final String Username) {
    boolean result = true;
    String[][] Table;
    return null;
  }

}
