package SoftwareEngineering.lyx;

import java.util.List;

/**
 * ���ݿ�������.
 * 
 * @author liuyx
 * @param ��TableAction����ܲ��� ����sql��䲢����MySQLOperation���޸����ݿ�
 * @return �����ɹ����
 */
public class DBConnection {
  private MySQLOperation MySQLHelper;
  
  /**
   * ע��ʱ�ж��û��Ƿ����.
   */
  public boolean UserIsExist(String Username) {
    boolean result = true;
    return result;
  }

  /**
   * �ж�һ�����Ƿ����.
   */
  public boolean TableIsExist(final String Username) {
    boolean result = true;
    return result;
  }

  /**
   * ע�����û�.
   */
  public boolean Register(String Username, String Password) {
    boolean result = true;
    return result;
  }

  /**
   * ��½ʱ�ж��û��������Ƿ����.
   */
  public boolean Login(String Username, String Password) {
    boolean result = false;
    return result;
  }


  /**
   * ����һ���±�.
   */
  public boolean Create(final String Username, final String[][] Table) {
    boolean result = true;
    return result;
  }

  /**
   * ɾ��һ����.
   */
  public boolean Delete(final String Username) {
    boolean result = true;
    return result;
  }

  /**
   * ����һ��������(��ṹ����).
   */
  public boolean Update(final List Change) {
    boolean result = true;
    return result;
  }

  /**
   * ɾ�����ֶ�.
   */
  public boolean DeleteCol(final int ColumnNum) {
    boolean result = true;
    return result;
  }

  /**
   * ɾ��ĳһ��.
   */
  public boolean DeleteRow(final int RowNum) {
    boolean result = true;
    return result;
  }

  /**
   * ����һ��������������.
   */
  public String[][] Search(final String Username) {
    boolean result = true;
    String[][] Table = MySQLHelper.getTable();
    return Table;
  }

}
