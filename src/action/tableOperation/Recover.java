package action.tableOperation;

import java.util.ArrayList;
import java.util.List;

import action.Action;
import entity.Database;
import entity.SearchRecordTable;
import entity.assistantEntity.SearchRecord;

public class Recover extends Action {

  // 将第一行所有"XX"->"00"
  // 将第一列所有"XXX"->"00X"
  @Override
  public String execute() {
    String result = "success";
    int rawRowCount = 0;
    int rowCount = 0;
    int colCount = 0;
    char[] tag = null;
    Database db = new Database();
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList<Object> value = new ArrayList<Object>();
    List<SearchRecord> userSearchRecords = SearchRecordTable.getUserSearchRecord(username);
    // 对数据库中每一个表格进行恢复
    for (SearchRecord searchRecord : userSearchRecords) {
      db.setTablename(searchRecord.getTablename());
      rawRowCount = Integer.parseInt(db.getRecord("id", 1)[1]);
      rowCount = db.getRowCount();
      colCount = db.getColumnCount();
      // 恢复每行标志位
      columnName.add("id");
      columnName.add("0");
      for (int i = 1; i < rawRowCount; i++) {
        tag = db.getRecord("id", i + 1)[1].toCharArray();
        if (tag[0] == '1') {
          tag[0] = '0';
          // 收藏是否恢复？
          // tag[1] = '0';
          value.add(new Integer(i + 1));
          value.add(new String(tag));
          if (!db.update(columnName, value)) {
            result = "failure";
            break;
          }
          value.clear();
        }
      }
      columnName.clear();
      // 删除用户添加的行
      if (result.equals("success")) {
        for (int i = rawRowCount; i < rowCount; i++) {
          if (!db.deleteRow("id", i+1)) {
            result = "failure";
            break;
          }
        }
      }
      // 恢复每列标志位
      if (result.equals("success")) {
        for (int i = 2; i < colCount; i++) {
          tag = db.getRecord("id", 1)[i].toCharArray();
          if (tag[0] == '1') {
            tag[0] = '0';
            columnName.add("id");
            value.add(new Integer(1));
            columnName.add(new Integer(i-1).toString());
            value.add(new String(tag));
            if (!db.update(columnName, value)) {
              result = "failure";
              break;
            }
            columnName.clear();
            value.clear();
          }
        }
      }
      
    }
    return result;
  }

  public static void main(String args[]){
    Recover r = new Recover();
    r.setUsername("lyx");
    r.execute();
  }
}
