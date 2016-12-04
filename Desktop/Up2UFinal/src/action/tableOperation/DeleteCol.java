package action.tableOperation;

import action.Action;

public class DeleteCol extends Action {
  private int colNum = 0;
  
  public int getColNum() {
    return colNum;
  }

  public void setColNum(int colNum) {
    this.colNum = colNum;
  }

  //使用数据库类中的update方法
  //将数据库中对应表格列的一行中"X0"->"X1"
  //传递给数据库类的参数在数据库类Update方法处有注释
  @Override
  public String execute() {
    return "failure";
  }

}
