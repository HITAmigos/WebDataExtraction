package entity;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.assistantEntity.ColumnUnit;
import entity.assistantEntity.SqlConst;

public class Database {
  private String tablename = null;

  public Database(){

  }
  
  public Database(String tablename){
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
        if(value.get(i) instanceof String){
          sqlBuffer.append("`"+columnName.get(i)+"`='"+value.get(i)+"',");
        }else{
          sqlBuffer.append("`"+columnName.get(i)+"`="+value.get(i)+",");
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

  // 第0组是条件组，即条件为'where columnName[0] = value[0]'
  public boolean update(ArrayList<String> columnName, ArrayList value) {
    return false;
  }
}
