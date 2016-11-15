package SoftwareEngineering.lyx;

import java.util.ArrayList;
import java.util.List;

public class UpdateAction extends TableAction {

  private String Tablename;
  private int ColNum;
  private int RowNum;
  private String[] newData;

  public String getTablename() {
    return Tablename;
  }

  public void setTablename(String tablename) {
    Tablename = tablename;
  }
  
  public String[] getNewData() {
    return newData;
  }

  public void setNewData(String[] newData) {
    this.newData = newData;
  }

  public void setColNum(int ColNum) {
    this.ColNum = ColNum;
  }

  public int getColNum() {
    return ColNum;
  }

  public int getRowNum() {
    return RowNum;
  }

  public void setRowNum(int RowNum) {
    this.RowNum = RowNum;
  }

  /**
   * 更新列数据.
   * @return
   */
  public String UpdateCol() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    List<ChangeUnit> change = new ArrayList<ChangeUnit>();

    for(int i = 0 ; i < newData.length ; i++ ){
      if(newData[i]!=null){
        ChangeUnit temp = new ChangeUnit();
        int[] pos = new int[2];
        pos[0] = ColNum+3;
        pos[1] = i+2;
        temp.setChange(newData[i]);
        temp.setPosition(pos);
        change.add(temp);
      }
    }
    
    if (dbHelper.Update(Tablename,change)) {
      result = "success";
    }
    return result;
  }
  
  /**
   * 更新行数据.
   * @return
   */
  public String UpdateRow() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    List<ChangeUnit> change = new ArrayList<ChangeUnit>();

    for(int i = 0 ; i < newData.length ; i++ ){
      if(newData[i]!=null){
        ChangeUnit temp = new ChangeUnit();
        int[] pos = new int[2];
        pos[0] = RowNum+2;
        pos[1] = i+1;
        temp.setChange(newData[i]);
        temp.setPosition(pos);
        change.add(temp);
      }
    }
    
    if (dbHelper.Update(Tablename,change)) {
      result = "success";
    }
    return result;
  }

  public String DeleteCol() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if(dbHelper.DeleteCol(Username,ColNum)){
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
  
  public static void main(String args[]){
    UpdateAction ua = new UpdateAction();
    
//    String[] updateRow = new String[11];
//    for(int i = 0 ; i < updateRow.length ; i++){
//      updateRow[i] = null;
//    }
//    updateRow[0] = "description";
//    updateRow[10] = "define column's group";
//    ua.setTablename("lyx-39");
//    ua.setColNum(2);
//    ua.setNewData(updateRow);
//    ua.UpdateCol();
  
//    String[] updateCol = new String[2];
//    updateCol[0] = "aaa";
//    updateCol[1] = "bbb";
//    ua.setTablename("lyx-39");
//    ua.setRowNum(2);
//    ua.setNewData(updateCol);
//    ua.UpdateCol();
  }
}
