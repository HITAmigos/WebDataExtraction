package action.tableOperation;

import java.util.ArrayList;

import action.Action;
import entity.Database;

public class DeleteCol extends Action {
  private int colNum;
public int getColNum() {
    return colNum;
  }

  public void setColNum(int colNum) {
    this.colNum = colNum;
  }

  //使用数据库类中的update方法
  //将数据库中对应表格列的一行中"X0"->"X1"
  //传递给数据库类的参数在数据库类Update方法处有注释
  public String execute() {
    String result = "success";
    char[] tag = null;
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    columnName.add("id");
    value.add(new Integer(1));
    Database db = new Database(tablename);
    tag = db.getRecord("id",1)[colNum+1].toCharArray();
    tag[1] = '1';
    columnName.add(new Integer(colNum).toString());
    value.add(new String(tag));
    if(!db.update(columnName, value)){
     result = "error";
    }
    return result;
  }
  
}
