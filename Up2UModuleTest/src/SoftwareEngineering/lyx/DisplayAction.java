package SoftwareEngineering.lyx;

import java.util.List;

/**
 * ��ǰ�˴���һ���û������ҵ����񲢷���.
 * @author liuyx.
 */
public class DisplayAction extends TableAction {

  /**
   * @param  �û���.
   * @return request �������.
   * @return �����Ƿ�ɹ�.
   */
  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    
    List<String[][]> table = dbHelper.getUserTables(Username);
    
    return result;
  }

  public void main(String args[]){
    TableAction ta = new TableAction();
    ta.setUsername("lyx");
    ta.execute();
  }
  
}
