package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class LoveRow extends Action {
  private int rowNum = 0;
  
  public int getRowNum() {
    return rowNum;
  }


  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
  }
  
  //使用数据库类中的update方法
  //将数据库中对应表格行的一行中"0XX"->"1XX"
  @Override
  public String execute() {
    String result = "success";
    char[] tag = null;
    ArrayList<String> rowName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    rowName.add("id");
    value.add(new Integer(rowNum+1));
    Database db = new Database(tablename);
    tag = db.getRecord("id",rowNum+1)[1].toCharArray();
    tag[1] = '1';
    rowName.add("0");
    value.add(new String(tag));
    
    if(!db.update(rowName, value)){
     result = "failure";
    }
    return result;
  }

}
