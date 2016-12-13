package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class UpdateCol extends Action {
  private int colNum;
  private String str;

  public int getColNum() {
    return colNum;
  }

  public void setColNum(int colNum) {
    this.colNum = colNum;
  }

  // 获取的newRow数组中第i个元素即为第i-1行元素，利用这个条件：id = （i+1）
  // 传递给数据库类的参数在数据库类Update方法处有注释
  @Override
  public String execute() {
    System.out.println(str);
    String result = "success";
    str = str.substring(0, str.length() - 1);
    String[] newCol = str.split("\\,");
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    columnName.add("id");
    columnName.add(new Integer(colNum).toString());
    Database db = new Database(tablename);
    for (int i = 0; i < newCol.length; i++) {
      if (newCol[i] != null && !newCol[i].equals("")) {
        value.add(i + 2);
        value.add(newCol[i]);
        if (!db.update(columnName, value)) {
          result = "error";
          break;
        }
        value.clear();
      }
    }
    return result;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

}
