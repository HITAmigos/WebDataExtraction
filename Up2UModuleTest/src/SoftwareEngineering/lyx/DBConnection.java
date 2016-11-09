//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
package SoftwareEngineering.lyx;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
  private static String sql = null;
  private static SQLHelper dbHelper = null;
  private static ResultSet resultSet = null;

  public boolean UserIsExist(String Username) {
    boolean result = false;
    sql = "select * from `user`";
    dbHelper = new SQLHelper(sql);

    System.out.println(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();
      while (resultSet.next()) {
        String getUsername = resultSet.getString("Username");

        System.out.println(getUsername);
        if (getUsername.equals(Username)) {
          result = true;
          break;
        }
        resultSet.close();// �رս����
      }
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection UserIsExist.");
      e.printStackTrace();
    } finally {
      dbHelper.close();// �ر�����
    }
    return result;
  }

  public boolean TableIsExist(final String Tablename) {
    boolean result = true;
    dbHelper = new SQLHelper(null);

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
      dbHelper.close();// �ر�����
    }

    return result;
  }

  public boolean Register(String Username, String Password, String Email, String Tel) {
    boolean result = true;
    // ,No,level,searchNum,serchThings,uploadNum,uploadThings,coins
    sql = "insert into user (Username,Password,Tel,Email) values (" + "\"" + Username + "\"," + "\""
        + Password + "\",\"" + Tel + "\"," + "\"" + Email + "\")";// SQL���
    dbHelper = new SQLHelper(sql);

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Register.");
      e.printStackTrace();
    } finally {
      dbHelper.close();// �ر�����
    }
    return result;
  }

  public boolean Login(String Username, String Password) {
    boolean result = false;
    sql = "select * from `user`";
    dbHelper = new SQLHelper(sql);

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

  public boolean CreateResource(String Username) {
    boolean result = true;
    sql = "create table `" + Username + "-Search/Upload`(" + " id int primary key auto_increment,"
        + " tag int default 0 ," + " Link varchar(200) not null ," + " Num int default 0 )";
    dbHelper = new SQLHelper(sql);

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection Create.");
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

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
      String[][] line = new String[Table[0].length][2];
      for (int i = 0; i < Table[0].length; i++) {
        line[i][0] = Table[0][i];
      }
      for (int i = 1; i < Table.length; i++) {
        for (int j = 0; j < Table[i].length; j++) {
          line[j][1] = Table[i][j];
        }
        Insert(Tablename, line);
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

  public boolean Delete(final String Tablename) {
    boolean result = true;
    sql = "drop table if exists `" + Tablename + "`";
    dbHelper = new SQLHelper(sql);

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

  public boolean Insert(String Tablename, String[][] map) {
    boolean result = true;
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("insert into `" + Tablename + "` set ");
    for (int i = 0; i < map.length; i++) {
      sqlBuffer.append("`" + map[i][0] + "`" + "=" + "'" + map[i][1] + "',");
    }
    sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
    sqlBuffer.append(";");
    sql = sqlBuffer.toString();
    dbHelper = new SQLHelper(sql);
    System.out.println(sql);
    try {
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

  public boolean Update(final String Tablename, final List<ChangeUnit> Change) {
    boolean result = true;
    sql = "select * from `" + Tablename + "`";
    dbHelper = new SQLHelper(sql);
    try {
      resultSet = dbHelper.pst.executeQuery();
      ResultSetMetaData data = resultSet.getMetaData();
      int ColNum = data.getColumnCount();
      String[] ColName = new String[ColNum];
      for (int i = 0; i < ColNum; i++) {
        ColName[i] = data.getColumnName(i);
      }
      for (int i = 0; i < Change.size(); i++) {
        sql = "update " + Tablename + " " + ColName[Change.get(i).getPosition()[1]] + " = "
            + Change.get(i).getChange() + " where id = " + Change.get(i).getPosition()[0];
        System.out.println(sql);
        dbHelper = new SQLHelper(sql);
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

  public boolean DeleteCol(final String Tablename, final String ColumnName) {
    boolean result = true;
    sql = "alter tabel " + Tablename + " drop " + ColumnName;
    dbHelper = new SQLHelper(sql);

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

  public boolean DeleteRow(final String Tablename, final int RowID) {
    boolean result = true;
    sql = "delete from " + Tablename + " where id=" + RowID;
    dbHelper = new SQLHelper(sql);

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

  public String[] getTablenames(final String Username) {
    List<String> tablenames = new ArrayList<String>();
    sql = "SELECT * FROM GRAPHGRABTEST.TABLES";
    dbHelper = new SQLHelper(sql);
    int nameLength = Username.length();

    try {
      resultSet = dbHelper.conn.getMetaData().getTables(null, null, null, new String[] {"TABLE"});
      while (resultSet.next()) {
        String tempTablename = resultSet.getString(3);
        String name = tempTablename.substring(0, nameLength + 1);
        if (name.equals(Username + "-")) {
          tablenames.add(tempTablename);
        }
      }
    } catch (SQLException e) {
      System.out.println("DBConnection getTablenames.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }
    String[] getTablenames = new String[tablenames.size()];
    for (int i = 0; i < tablenames.size(); i++) {
      getTablenames[i] = tablenames.get(i);
    }

    return getTablenames;
  }

  public String[][] SearchTable(final String Tablename) {
    // boolean result = true;
    String[][] Table = null;
    sql = "select * from `" + Tablename + "`";
    dbHelper = new SQLHelper(sql);

    try {
      resultSet = dbHelper.pst.executeQuery();
      ResultSetMetaData data = resultSet.getMetaData();
      resultSet.last();
      int RowNum = resultSet.getRow();
      int ColNum = data.getColumnCount();
      Table = new String[RowNum][ColNum - 1];

      resultSet.first();
      for (int i = 0; i < RowNum; i++) {
        for (int j = 1; j < ColNum; j++) {
          Table[i][j - 1] = resultSet.getString(j + 1);
        }
        resultSet.next();
      }

    } catch (SQLException e) {
      // result = false;
      System.out.println("DBConnection Search.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }

    return Table;
  }

  public List<String[][]> getUserTables(final String Username) {
    List<String[][]> userTables = new ArrayList<String[][]>();
    String[] tablenames = getTablenames(Username);
    for (int i = 0; i < tablenames.length; i++) {
      userTables.add(SearchTable(tablenames[i]));
    }

    for (int i = 0; i < userTables.size(); i++) {
      System.out.println("Table No." + i);
      for (int j = 0; j < userTables.get(i).length; j++) {
        for (int k = 0; k < userTables.get(i)[j].length; k++) {
          System.out.print(userTables.get(i)[j][k] + "\t|");
        }
        System.out.println();
      }
      System.out.println();
    }

    return userTables;
  }

  public boolean RecoverTable(String Tablename) {
    boolean result = true;
    try {
      sql = "select * from `" + Tablename + "`";
      dbHelper = new SQLHelper(sql);
      System.out.println(sql);

      resultSet = dbHelper.pst.executeQuery();
      ResultSetMetaData data = resultSet.getMetaData();
      int colNum = data.getColumnCount();
      String temp = null;
      resultSet.next();
      // 更新第一行标记的删除位
      for (int colNo = 3; colNo <= colNum; colNo++) {
        temp = resultSet.getString(colNo);
        if (temp.charAt(1) == '0') {
          continue;
        }
        if (temp.charAt(0) == '0') {
          temp = "00";
        } else {
          temp = "10";
        }
        sql = "update `" + Tablename + "` set `" + (colNo - 2) + "`='" + temp + "' where id=1";
        System.out.println(sql);

        dbHelper = new SQLHelper(sql);
        dbHelper.pst.execute();
      }
      int id = 2;
      // 更新每一列的删除标记
      while (resultSet.next()) {
        char[] tempToRecover = resultSet.getString("0").toCharArray();
        tempToRecover[1] = '0';
        temp = new String(tempToRecover);
        sql = "update `" + Tablename + "` set `0`='" + temp + "' where id=" + (id++);
        System.out.println(sql);

        dbHelper = new SQLHelper(sql);
        dbHelper.pst.execute();
      }
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection RecoverTable.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }

    return result;
  }

  public boolean RecoverAll(String Username) {
    boolean result = true;
    sql = "select * from `source`";
    dbHelper = new SQLHelper(sql);

    System.out.println(sql);
    try {
      String Tablename = null;
      resultSet = dbHelper.pst.executeQuery();
      //ResultSetMetaData data = resultSet.getMetaData();
      while (resultSet.next()) {
        ResultSet tempSet = resultSet;
        System.out.println(resultSet.getString(1));
        if(resultSet.getString("Username").equals(Username)){
          Tablename = resultSet.getString("Tablename");
          RecoverTable(Tablename);
        }
        resultSet = tempSet;
      }
    } catch (SQLException e) {
      result = false;
      System.out.println("DBConnection RecoverTable.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }

    return result;
  }

  public boolean Cancellation(String Username) {
    boolean result = true;
    sql = "delete from user where Username = '" + Username + "'";
    dbHelper = new SQLHelper(sql);

    System.out.println(sql);
    try {
      dbHelper.pst.execute();
      sql = "drop table `" + Username + "-SaveResource`";
      dbHelper = new SQLHelper(sql);
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


  public int getLastId(String Tablename) {
    int LastId = 0;
    sql = "select * from `" + Tablename + "`";
    dbHelper = new SQLHelper(sql);

    try {
      resultSet = dbHelper.pst.executeQuery();

      if (resultSet.last()) {
        LastId = resultSet.getInt("id");
      } else {
        ResultSetMetaData data = resultSet.getMetaData();
        int ColNum = data.getColumnCount();
        String[][] temp = new String[ColNum - 1][2];
        for (int index = 0, i = 0; i < ColNum; i++) {
          String col = data.getColumnName(i + 1);
          if (!col.equals("id")) {
            temp[index][0] = data.getColumnName(i + 1);
            temp[index++][1] = "";
          }
        }
        Insert(Tablename, temp);
      }
    } catch (SQLException e) {
      System.out.println("DBConnection getSize.");
      e.printStackTrace();
    } finally {
      dbHelper.close();
    }

    if (LastId == 0) {
      sql = "select * from `" + Tablename + "`";
      dbHelper = new SQLHelper(sql);
      try {
        resultSet = dbHelper.pst.executeQuery();
        resultSet.last();
        LastId = resultSet.getInt("id");
        DeleteRow(Tablename, LastId);
      } catch (SQLException e) {
        System.out.println("DBConnection getSize.");
        e.printStackTrace();
      } finally {
        dbHelper.close();
      }
    }

    return LastId;
  }

  public static void main(String args[]) {
    DBConnection dbHelper = new DBConnection();
    dbHelper.getLastId("source");
  }

}
