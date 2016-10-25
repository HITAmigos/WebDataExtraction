//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
package SoftwareEngineering.lyx;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库连接类. .
 * 
 * @author liuyx.
 * @param 从TableAction类接受参数 生成sql语句并调用MySQLOperation类修改数据库.
 * @return 操作成功与否.
 */
public class DBConnection {
  static String sql = null;
  static MySQLOperation dbHelper = null;
  static ResultSet resultSet = null;
  static boolean retb = false;

  /**
   * 通过用户名与一些参数获得映射的表名.
   * @param Username
   * @return
   */
  private String getTableName(String Username) {
    return Username;
  }

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

    System.out.println(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();// 执行语句，得到结果集
      while (resultSet.next()) {
        String getUsername = resultSet.getString("Username");

        System.out.println(getUsername);
        if (getUsername.equals(Username)) {
          result = true;
          break;
        }
        resultSet.close();// 关闭结果集
      }
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection UserIsExist.");
      e.printStackTrace();
    } finally {
      dbHelper.close();// 关闭连接
    }
    return result;
  }

  /**
   * 判断一个表是否存在.
   */
  public boolean TableIsExist(final String Tablename) {
    boolean result = true;
    dbHelper = new MySQLOperation(null);

    System.out.println(sql);
    try {
      DatabaseMetaData dbData = dbHelper.conn.getMetaData();
      if (dbData.getTables(null, null, Tablename, null) != null) {
        result = true;
      } else {
        result = false;
      }
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection TableIsExist.");
      e.printStackTrace();
    } finally {
      dbHelper.close();// 关闭连接
    }

    return result;
  }

  /**
   * 注册新用户.
   */
  public boolean Register(String Username, String Password) {
    boolean result = true;
    sql = "insert into UserRegedit (Username,Password) values (" 
          + "\"" + Username + "\"," 
          + "\"" + Password + "\")";// SQL语句
    dbHelper = new MySQLOperation(sql);

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Register.");
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

    System.out.println(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();
      while (resultSet.next()) {
        String getUsername = resultSet.getString("Username");
        String getPassword = resultSet.getString("Password");

        System.out.println("<" + getUsername + "," + getPassword + ">");
        if (getUsername.equals(Username) && getPassword.equals(Password)) {
          result = true;
          resultSet.close();
          break;
        }
      }
      resultSet.close();
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Login.");
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
    StringBuffer sqlBuffer = new StringBuffer();
    String Tablename = getTableName(Username);
    //生成sql语句的缓存
    sqlBuffer.append("create table " + Tablename + " (");
    sqlBuffer.append("id int primary key not null,");
    if (Table.length >= 0)
      for (int i = 0; i < Table[0].length; i++) {
        sqlBuffer.append(Table[0][i] + " text not null,");
      }
    sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
    sqlBuffer.append(");");
    sql = sqlBuffer.toString();
    dbHelper = new MySQLOperation(sql);

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
      for (int i = 0; i < Table.length; i++) {
        Insert(Tablename, Table[i]);
      }
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Create.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  /**
   * 删除一个表.
   */
  public boolean Delete(final String Tablename) {
    boolean result = true;
    sql = "drop table if exists `" + Tablename + "`";
    dbHelper = new MySQLOperation(sql);

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Delete.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  /**
   * 插入一个行数据.
   * 
   * @param Tablename
   * @param Line
   * @return
   */
  public boolean Insert(String Tablename, String[] Line) {
    boolean result = true;
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("insert into " + Tablename + " set ");
    sql = "select * from " + Tablename;
    dbHelper = new MySQLOperation(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();
      ResultSetMetaData data = resultSet.getMetaData();
      int ColumnNum = data.getColumnCount();
      String ColumnName = null;

      for (int i = 1; i < ColumnNum; i++) {
        ColumnName = data.getColumnName(i);
        sqlBuffer.append(ColumnName + "=" + Line[i - 1] + " ,");
      }
      sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
      sqlBuffer.append(";");

      sql = sqlBuffer.toString();
      System.out.println(sql);
      dbHelper = new MySQLOperation(sql);

      System.out.println(sql);
      dbHelper.pst.execute();

    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Insert.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  /**
   * 更新一个表内容(表结构不变).
   */
  public boolean Update(final String Tablename, final List<ChangeUnit> Change) {
    boolean result = true;
    sql = "select * from `" + Tablename + "`";
    dbHelper = new MySQLOperation(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();
      ResultSetMetaData data = resultSet.getMetaData();
      // 找到所有的列名
      // 并依次存入字符串数组中
      int ColNum = data.getColumnCount();
      String[] ColName = new String[ColNum];
      for (int i = 0; i < ColNum; i++) {
        ColName[i] = data.getColumnName(i);
      }
      // 更新Change中每一项
      for (int i = 0; i < Change.size(); i++) {
        sql = "update " + Tablename + " " + ColName[Change.get(i).getPosition()[1]] + " = "
            + Change.get(i).getChange() + " where id = " + Change.get(i).getPosition()[0];
        System.out.println(sql);
        dbHelper = new MySQLOperation(sql);
        dbHelper.pst.execute();
      }

    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Update.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  /**
   * 删除表字段.
   */
  public boolean DeleteCol(final String Tablename, final String ColumnName) {
    boolean result = true;
    sql = "alter tabel " + Tablename + " drop " + ColumnName;
    dbHelper = new MySQLOperation(sql);

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection DeleteCol.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  /**
   * 删除某一行.
   */
  public boolean DeleteRow(final String Tablename, final int RowID) {
    boolean result = true;
    sql = "delete from " + Tablename + " where id=" + RowID;
    dbHelper = new MySQLOperation(sql);

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection DeleteRow.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  /**
   * 查找一个表，并返回内容.
   */
  public String[][] Search(final String Tablename) {
//    boolean result = true;
    String[][] Table = null;
    sql = "select * from `" + Tablename + "`";
    dbHelper = new MySQLOperation(sql);

    System.out.println(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();
      ResultSetMetaData data = resultSet.getMetaData();
      resultSet.last();
      int RowNum = resultSet.getRow();
      int ColNum = data.getColumnCount();
      Table = new String[RowNum][ColNum];

      resultSet.first();
      for (int i = 0; i < RowNum; i++) {
        for (int j = 0; j < ColNum; j++) {
          Table[i][j] = resultSet.getString(j);
        }
        resultSet.next();
      }

    } catch (SQLException e) {
//      result = false;
      System.out.println("DBConnection Search.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }

    if (Table.length > 0) {
      for (int i = 0; i < Table.length; i++) {
        for (int j = 0; j < Table[0].length; j++) {
          System.out.print(Table[i][j] + "\t");
        }
        System.out.println();
      }
    }

    return Table;
  }

  public boolean Cancellation(String Username) {
    boolean result = true;
    sql = "delete from UserRegedit where Username = '"+Username+"'";
    dbHelper = new MySQLOperation(sql);

    System.out.println(sql);
    try{
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Search.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }

    return result;
  }

}
