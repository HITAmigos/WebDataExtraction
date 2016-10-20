package SoftwareEngineering.lyx;

import java.util.ArrayList;
import java.util.List;

/**
 * 更新表格.
 * 
 * @author liuyx 接受一个表格并更新数据库
 */
public class UpdateAction extends TableAction {

  private int ColNum;
  private int RowNum;

  public int getColNum() {
    return ColNum;
  }

  public void setColNum(int colNum) {
    ColNum = colNum;
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

    if (dbHelper.Update(change)) {
      result = "success";
    }
    return result;
  }

  public String DeleteCol() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if(dbHelper.DeleteCol(ColNum)){
      result = "success";
    }
    return result;
  }

  public String DeleteRow() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if(dbHelper.DeleteRow(RowNum)){
      result = "success";
    }
    return result;
  }
}
