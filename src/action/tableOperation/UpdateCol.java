package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class UpdateCol extends Action {
  private int colNum = 0;
  private String[] newCol = null;

  public int getColNum() {
    return colNum;
  }

  public void setColNum(int colNum) {
    this.colNum = colNum;
  }

  public String[] getNewCol() {
    return newCol;
  }

  public void setNewCol(String[] newCol) {
    this.newCol = newCol;
  }

  // 获取的newRow数组中第i个元素即为第i-1行元素，利用这个条件：id = （i+1）
  // 传递给数据库类的参数在数据库类Update方法处有注释
  @Override
  public String execute() {
    String result = "success";
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    columnName.add("id");
    columnName.add(new Integer(colNum).toString());
    Database db = new Database(tablename);
    for (int i = 0; i < newCol.length; i++) {
      if (newCol[i] != null) {
        value.add(i + 2);
        value.add(newCol[i]);
        if (!db.update(columnName, value)) {
          result = "failure";
          break;
        }
        value.clear();
      }
    }
    return result;
  }

  public static void main(String args[]) {
    String[] newCol = new String[29];
    newCol[3] = "kkk";
    newCol[28] = "aaa";
    newCol[5] = "dfg";
    UpdateCol uc = new UpdateCol();
    uc.setTablename("lyx-6");
    uc.setColNum(1);
    uc.setNewCol(newCol);
    uc.execute();
  }

}
