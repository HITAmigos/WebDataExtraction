package SoftwareEngineering.lyx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库连接类.
 * 
 * @author liuyx
 * @param 从TableAction类接受参数 生成sql语句并调用MySQLOperation类修改数据库
 * @return 操作成功与否
 */
public class DBConnection {
  static String sql = null;
  static MySQLOperation dbHelper = null;
  static ResultSet resultSet = null;
  static boolean retb = false;

  /**
   * 注册时判断用户是否存在.
   * 
   * @param Username
   * @return 存在true,不存在false 由于表名根据用户名创建，表名等于用户名，于是只要用户名存在表就存在.
   */
  public boolean UserIsExist(String Username) {
    boolean result = false;
    sql = "select * from `userRegedit`";// SQL语句
    dbHelper = new MySQLOperation(sql);

    try {
      resultSet = dbHelper.pst.executeQuery();// 执行语句，得到结果集
      while (resultSet.next()) {
        String getUsername = resultSet.getString(Username);

        System.out.println(getUsername);
        if (getUsername.equals(Username)) {
          result = true;
          break;
        }
        resultSet.close();// 关闭结果集
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbHelper.close();// 关闭连接
    }
    return result;
  }

  // /**
  // * 当用户名只有一个表时无需判断，因为用户名与表名相同
  // * 判断一个表是否存在.
  // */
  // public boolean TableIsExist(final String Username) {
  // boolean result = true;
  // return result;
  // }

  /**
   * 注册新用户.
   */
  public boolean Register(String Username, String Password) {
    boolean result = false;
    sql = "insert into UserRegedit set " + "Username=" + "'" + Username + "'," + "Password=" + "'"
        + Password + "';";// SQL语句
    dbHelper = new MySQLOperation(sql);

    try {
      result = dbHelper.pst.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbHelper.close();// 关闭连接
    }
    return result;
  }

  /**
   * 登陆时判断用户名密码是否相符.
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
   * 增加一个新表.
   */
  public boolean Create(final String Username, final String[][] Table) {
    boolean result = true;
    return result;
  }

  /**
   * 删除一个表.
   */
  public boolean Delete(final String Username) {
    boolean result = true;
    return result;
  }

  /**
   * 更新一个表内容(表结构不变).
   */
  public boolean Update(final List Change) {
    boolean result = true;
    return result;
  }

  /**
   * 删除表字段.
   */
  public boolean DeleteCol(final int ColumnNum) {
    boolean result = true;
    return result;
  }

  /**
   * 删除某一行.
   */
  public boolean DeleteRow(final int RowNum) {
    boolean result = true;
    return result;
  }

  /**
   * 查找一个表，并返回内容.
   */
  public String[][] Search(final String Username) {
    boolean result = true;
    String[][] Table;
    return null;
  }

}
