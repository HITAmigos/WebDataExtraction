package action.tableOperation;

import action.Action;

public class UpdateCol extends Action {
  private int colNum = 0;
  private String[] newCol = null;
  
  public int getColNum() {
    return colNum;
  }

  public void setColNum(int colNum) {
    this.colNum = colNum;
  }

  public String[] getNewCol() {
    return newCol;
  }

  public void setNewCol(String[] newCol) {
    this.newCol = newCol;
  }

  //获取的newRow数组中第i个元素即为第i-1行元素，利用这个条件：id = （i+1）
  //传递给数据库类的参数在数据库类Update方法处有注释
  @Override
  public String execute() {
    return "failure";
  }

}
