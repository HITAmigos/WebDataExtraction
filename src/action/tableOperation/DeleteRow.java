package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class DeleteRow extends Action {
  private int rowNum = 0;
  private int colnum;
  public int getColnum() {
	return colnum;
}


public void setColnum(int colnum) {
	this.colnum = colnum;
}
  public int getRowNum() {
    return rowNum;
  }


  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
  }
  
  //使用数据库类中的update方法
  //将数据库中对应表格行的一行中"0XX"->"1XX"
  public String execute() {
    String result = "success";
    setRowNum(colnum);
    char[] tag = null;
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    columnName.add("id");
    value.add(new Integer(rowNum+1));
    Database db = new Database(tablename);
    tag = db.getRecord("id",rowNum+1)[1].toCharArray();
    tag[0] = '1';
    columnName.add("0");
    value.add(new String(tag));
    
    if(!db.update(columnName, value)){
     result = "failure";
    }
    return result;
  }
}
