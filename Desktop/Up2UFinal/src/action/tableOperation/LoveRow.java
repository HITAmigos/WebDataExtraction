package action.tableOperation;

import action.Action;

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
    return "failure";
  }

}
