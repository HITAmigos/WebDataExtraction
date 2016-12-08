package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class AddRow extends Action {
  //从前台获取的用于添加的新数据行
  private String[] newRow;

  public void setNewRow(String[] newRow) {
    this.newRow = newRow;
  }

  @Override
  public String execute() {
    String result = "success";
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
      result = "failure";
    }
    return result;
  }

  public static void main(String args[]){
    AddRow ar = new AddRow();
    String[] newRow = {"刘严欣","教授","计算机科学与技术","水电费"};
    ar.setTablename("lyx-6");
    ar.setNewRow(newRow);
    ar.execute();
  }
  
}
