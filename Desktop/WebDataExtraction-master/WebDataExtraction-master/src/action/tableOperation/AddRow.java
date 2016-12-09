package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class AddRow extends Action {
  //从前台获取的用于添加的新数据行
  private String str;
  public String execute(){
    String result = "success";
    str = str.substring(0, str.length()-1);
    String[] newRow = str.split("\\,");
    int columnCount = 0;
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    Database db = new Database(tablename);
    columnCount = db.getColumnCount();
    columnName.add(new Integer(0).toString());
    value.add("000");
    for(int i = 0 ; i < columnCount-2 ; i++){
      columnName.add(new Integer(i+1).toString());
      value.add(newRow[i]);
    }
    if(!db.insert(columnName, value)){
      result = "error";
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
