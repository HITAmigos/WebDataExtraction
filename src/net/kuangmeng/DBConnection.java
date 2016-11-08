package net.kuangmeng;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
  private static String sql = null;
  private static SQLHelper dbHelper = null;
  private static ResultSet resultSet = null;
  public boolean UserIsExist(String Username) {
    boolean result = false;
    sql = "select * from `user`";
    dbHelper = new SQLHelper(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();
      while (resultSet.next()) {
        String getUsername = resultSet.getString("Username");

        if (getUsername.equals(Username)) {
          result = true;
          break;
        }
        resultSet.close();
      }
    } catch (SQLException e) {
      result = false;
      e.printStackTrace();
    } finally {
      dbHelper.close();// �ر�����
    }
    return result;
  }

  public boolean TableIsExist(final String Tablename) {
    boolean result = true;
    dbHelper = new SQLHelper(null);

    try {
      DatabaseMetaData dbData = dbHelper.conn.getMetaData();
      if (dbData.getTables(null, null, Tablename, null) != null) {
        result = true;
      } else {
        result = false;
      }
    } catch (SQLException e) {
      result = false;
      e.printStackTrace();
    } finally {
      dbHelper.close();// �ر�����
    }

    return result;
  }


  public boolean Register(String Username, String Password, String Email,String Tel) {
    boolean result = true;
    // ,No,level,searchNum,serchThings,uploadNum,uploadThings,coins
    sql = "insert into user (Username,Password,Tel,Email) values (" + "\"" + Username + "\","
        + "\"" + Password +"\",\""+ Tel  + "\"," + "\"" + Email + "\")";// SQL���
    dbHelper = new SQLHelper(sql);

    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;

      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  public boolean Login(String Username, String Password) {
    boolean result = false;
    sql = "select * from `user`";
    dbHelper = new SQLHelper(sql);

    try {
      resultSet = dbHelper.pst.executeQuery();
      while (resultSet.next()) {
        String getUsername = resultSet.getString("Username");
        String getPassword = resultSet.getString("Password");

        if (getUsername.equals(Username) && getPassword.equals(Password)) {
          result = true;
          resultSet.close();
          break;
        }
      }
      resultSet.close();
    } catch (SQLException e) {
      result = false;
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  public boolean CreateResource(String Username) {
    boolean result = true;
    sql = "create table `"+Username+"-Search/Upload`("
        + " id int primary key auto_increment,"
        + " tag int default 0 ,"
        + " Link varchar(200) not null ,"
        + " Num int default 0 )";
    dbHelper = new SQLHelper(sql);

    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  public boolean Create(final String Tablename, final String[][] Table) {
    boolean result = true;
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("create table if not exists `" + Tablename + "` (");
    sqlBuffer.append("`id` int primary key auto_increment,");
    if (Table.length >= 0)
      for (int i = 0; i < Table[0].length; i++) {
        sqlBuffer.append("`" + Table[0][i].toString() + "` varchar(30) not null,");
    }
    sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
    sqlBuffer.append(")");
    sql = sqlBuffer.toString();
    dbHelper = new SQLHelper(sql);

    try {
      dbHelper.pst.execute();
      String[][] line = new String[Table[0].length][2];
      for(int i = 0 ; i < Table[0].length ; i++ ){
        line[i][0] = Table[0][i];
      }
      for (int i = 1; i < Table.length; i++) {
        for(int j = 0 ; j < Table[i].length ; j++ ){
          line[j][1] = Table[i][j];
        }
        Insert(Tablename, line);
      }
    } catch (SQLException e) {
      result = false;
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }

  public boolean Delete(final String Tablename) {
    boolean result = true;
    sql = "drop table if exists `" + Tablename + "`";
    dbHelper = new SQLHelper(sql);

    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }
  public boolean Insert(String Tablename, String[][] map) {
    boolean result = true;
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("insert into `" + Tablename + "` set ");
    for(int i = 0 ; i < map.length ; i++ ){
      sqlBuffer.append("`"+map[i][0]+"`"+"="+"'"+map[i][1]+"',");
    }
    sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
    sqlBuffer.append(";");
    sql = sqlBuffer.toString();
    dbHelper = new SQLHelper(sql);
    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return result;
  }
  public int getLastId(String Tablename) {
    int LastId = 1;
    sql = "select * from `" + Tablename + "`";
    dbHelper = new SQLHelper(sql);

    try {
      resultSet = dbHelper.pst.executeQuery();
      resultSet.last();
      LastId = resultSet.getInt("id");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    return LastId;
  }
}
