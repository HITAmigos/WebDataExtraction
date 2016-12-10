package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class DeleteRow extends Action {
  private int rowNum;
 
  public int getRowNum() {
    return rowNum;
  }


  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
  }
  
  //使用数据库类中的update方法
  //将数据库中对应表格行的一行中"X0X"->"X1X"
  public String execute() {
    String result = "success";
    char[] tag = null;
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    columnName.add("id");
    value.add(new Integer(rowNum));
    Database db = new Database(tablename);
    tag = db.getRecord("id",rowNum)[1].toCharArray();
    tag[1] = '1';
    columnName.add("0");
    value.add(new String(tag));
    
    if(!db.update(columnName, value)){
     result = "error";
    }
    return result;
  }
}
