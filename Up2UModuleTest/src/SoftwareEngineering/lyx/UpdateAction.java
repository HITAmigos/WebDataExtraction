package SoftwareEngineering.lyx;

import java.util.ArrayList;
import java.util.List;

/**
 * 更新表格.
 * 
 * @author liuyx 接受一个表格并更新数据库
 */
public class UpdateAction extends TableAction {

  private String ColName;
  private int RowNum;


  public String getColName() {
    return ColName;
  }

  public void setColName(String colName) {
    ColName = colName;
  }

  public int getRowNum() {
    return RowNum;
  }

  public void setRowNum(int rowNum) {
    RowNum = rowNum;
  }

  public String Update() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    List<ChangeUnit> change = new ArrayList<ChangeUnit>();

    if (dbHelper.Update(Username,change)) {
      result = "success";
    }
    return result;
  }

  public String DeleteCol() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if(dbHelper.DeleteCol(Username,ColName)){
      result = "success";
    }
    return result;
  }

  public String DeleteRow() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if(dbHelper.DeleteRow(Username,RowNum)){
      result = "success";
    }
    return result;
  }
  
  public void main(String args[]){
    UpdateAction ua = new UpdateAction();
    ua.DeleteRow();
  }
}
