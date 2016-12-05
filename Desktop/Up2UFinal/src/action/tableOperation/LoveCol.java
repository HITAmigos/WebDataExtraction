package action.tableOperation;

import action.Action;

public class LoveCol extends Action {
  private int colNum = 0;
  
  public int getColNum() {
    return colNum;
  }

  public void setColNum(int colNum) {
    this.colNum = colNum;
  }

  //使用数据库类中的update方法
  //将数据库中对应表格列的一行中"0X"->"1X"
  @Override
  public String execute() {
    return "failure";
  }

}
