package SoftwareEngineering.lyx;

/**
 * ��ǰ�˴���һ���û������ҵ����񲢷���.
 * @author liuyx
 */
public class DisplayAction extends TableAction {

  /**
   * @param  �û���
   * @return request �������
   * @return �����Ƿ�ɹ�
   */
  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    
    String[][] table = dbHelper.Search(Username);
    
    return result;
  }

}
