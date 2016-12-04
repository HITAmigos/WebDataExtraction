package action.tableOperation;

import action.Action;

public class AddRow extends Action {
  //从前台获取的用于添加的新数据行
  private String[] newRow;

  public void setNewRow(String[] newRow) {
    this.newRow = newRow;
  }

  @Override
  public String execute() {
    return "failure";
  }

}
