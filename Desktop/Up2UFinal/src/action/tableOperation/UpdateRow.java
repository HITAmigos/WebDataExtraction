package action.tableOperation;

import action.Action;

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
    return "failure";
  }

}
