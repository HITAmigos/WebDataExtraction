package action.tableOperation;

import action.Action;

public class DeleteRow extends Action {
  private int rowNum = 0;
  
  public int getRowNum() {
    return rowNum;
  }


  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
  }
  
  //使用数据库类中的update方法
  //将数据库中对应表格行的一行中"X0X"->"X1X"
  @Override
  public String execute() {
    return "failure";
  }

}
