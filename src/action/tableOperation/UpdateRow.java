package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class UpdateRow extends Action {
  private int rowNum = 0;
  private String[] newRow = null;
  
  public int getRowNum() {
    return rowNum;
  }

  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
  }

  public String[] getNewRow() {
    return newRow;
  }

  public void setNewRow(String[] newRow) {
    this.newRow = newRow;
  }

  //行号即为id的值，利用这个条件update
  //若某个元素为null表示不修改
  //传递给数据库类的参数在数据库类Update方法处有注释
  @Override
  public String execute() {
    String result = "success";
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
      result = "failure";
    }
    for(int i = 0 ; i < columnName.size() ; i++){
      System.out.println(columnName.get(i)+":"+value.get(i));
    }
    return result;
  }

  public static void main(String args[]){
    String[] newRow = {"abc",null,"kkk",null};
    UpdateRow ur = new UpdateRow();
    ur.setTablename("lyx-1");
    ur.setRowNum(3);
    ur.setNewRow(newRow);
    ur.execute();
  }
  
}
