package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.assistantEntity.ColumnUnit;
import entity.assistantEntity.SqlConst;

public class Database {
  private String tablename = null;

  public Database() {

  }

  public Database(String tablename) {
    this.tablename = tablename;
  }

  public void setTablename(String tablename) {
    this.tablename = tablename;
  }

  public boolean create(ArrayList<ColumnUnit> column) {
    boolean result = true;
    StringBuffer sqlBuffer = new StringBuffer();
    String sql = null;

    sqlBuffer.append("create table if not exists `" + tablename + "` (");
    for (int i = 0; i < column.size(); i++) {
      sqlBuffer.append("`" + column.get(i).getColumnName() + "` ");
      sqlBuffer.append(column.get(i).getColumnType() + " ");
      if (column.get(i).isPrimaryKey()) {
        sqlBuffer.append("primary key ");
      }
      sqlBuffer.append("not null ");
      sqlBuffer.append(column.get(i).getDefaultValue() + ",");
    }
    sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
    sqlBuffer.append(")");

    sql = sqlBuffer.toString();
    SqlConst sqlHelper = new SqlConst(sql);
    System.out.println(sql);

    try {
      sqlHelper.getPst().execute();
    } catch (SQLException e) {
      result = false;
      System.out.println("Database create.");
      e.printStackTrace();
    } finally {
      sqlHelper.close();
    }
    return result;
  }

  public boolean insert(ArrayList<String> columnName, ArrayList value) {
    boolean result = true;
    if (columnName.size() != value.size()) {
      result = false;
    } else {
      StringBuffer sqlBuffer = new StringBuffer();
      String sql = null;

      sqlBuffer.append("insert into `" + tablename + "` set ");
      for (int i = 0; i < columnName.size(); i++) {
        if (value.get(i) instanceof String) {
          sqlBuffer.append("`" + columnName.get(i) + "`='" + value.get(i) + "',");
        } else {
          sqlBuffer.append("`" + columnName.get(i) + "`=" + value.get(i) + ",");
        }
      }
      sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);

      sql = sqlBuffer.toString();
      SqlConst sqlHelper = new SqlConst(sql);
      System.out.println(sql);
      try {
        sqlHelper.getPst().execute();
      } catch (SQLException e) {
        result = false;
        System.out.println("Database insert.");
        e.printStackTrace();
      } finally {
        sqlHelper.close();
      }
    }
    return result;
  }

  public boolean delete() {
    return false;
  }

  public boolean deleteRow(int id) {
    return false;
  }

  public boolean deleteCol(String colName) {
    return false;
  }

  // update 表名 set 字段1=值1，字段2=值2 where 查询条件
  // 第0组是条件组，即条件为'where columnName[0] = value[0]'
  public boolean update(ArrayList<String> columnName, ArrayList<Object> value) {
    boolean result = true;
    if (columnName.size() != value.size() || columnName.size() <= 1) {
      result = false;
    } else {
      String sql = null;
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("update `" + tablename + "` set ");
      for (int i = 1; i < columnName.size(); i++) {
        if (value.get(i) instanceof String) {
          sqlBuffer.append("`" + columnName.get(i) + "`='" + value.get(i) + "',");
        } else {
          sqlBuffer.append("`" + columnName.get(i) + "`=" + value.get(i) + ",");
        }
      }
      sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
      sqlBuffer.append(" where ");
      if (value.get(0) instanceof String) {
        sqlBuffer.append("`" + columnName.get(0) + "`='" + value.get(0) + "'");
      } else {
        sqlBuffer.append("`" + columnName.get(0) + "`=" + value.get(0));
      }
      
      sql = sqlBuffer.toString();
      SqlConst sqlHelper = new SqlConst(sql);
      System.out.println(sql);
      try {
        sqlHelper.getPst().execute();
      } catch (SQLException e) {
        result = false;
        System.out.println("Database update.");
        e.printStackTrace();
      } finally {
        sqlHelper.close();
      }
    }
    return result;
  }

  public ArrayList<String[]> getResultSet() {
    ResultSet resultSet = null;
    ArrayList<String[]> resultList = new ArrayList<String[]>();
    int columnCount = 0;
    String sql = "select * from `" + tablename + "`";
    SqlConst sqlHelper = new SqlConst(sql);
    try {
      resultSet = sqlHelper.getPst().executeQuery();
      columnCount = resultSet.getMetaData().getColumnCount();
      while (resultSet.next()) {
        String[] currentResult = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
          currentResult[i] = resultSet.getObject(i + 1).toString();
        }
        resultList.add(currentResult);
      }
    } catch (SQLException e) {
      System.out.println("Database getColumnCount.");
      e.printStackTrace();
    } finally {
      sqlHelper.close();
    }
    return resultList;
  }

  public int getColumnCount() {
    int columnCount = 0;
    String sql = "select * from `" + tablename + "`";
    SqlConst sqlHelper = new SqlConst(sql);
    try {
      ResultSet resultSet = sqlHelper.getPst().executeQuery();
      columnCount = resultSet.getMetaData().getColumnCount();
    } catch (SQLException e) {
      System.out.println("Database getColumnCount.");
      e.printStackTrace();
    } finally {
      sqlHelper.close();
    }
    return columnCount;
  }
}
