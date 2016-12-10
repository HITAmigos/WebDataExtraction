package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class UpdateRow extends Action {
  private int rowNum;
  private String str;
  public int getRowNum() {
    return rowNum;
  }

  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
  }


  //行号即为id的值，利用这个条件update
  //若某个元素为null表示不修改
  //传递给数据库类的参数在数据库类Update方法处有注释
  @Override
  public String execute(){
    String result = "success";
    str = str.substring(0, str.length()-1);
    String[] newRow = str.split("\\,");
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    columnName.add("id");
    value.add(new Integer(rowNum+1));
    Database db = new Database(tablename);
    for(int i = 0 ; i < newRow.length ; i++){
      if(newRow[i]!=null){
        columnName.add(new Integer(i+1).toString());
        value.add(newRow[i]);
      }
    }
    if(!db.update(columnName, value)){
      result = "error";
    }
    for(int i = 0 ; i < columnName.size() ; i++){
      System.out.println(columnName.get(i)+":"+value.get(i));
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
