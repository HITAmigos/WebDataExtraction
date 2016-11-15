package SoftwareEngineering.lyx;

import java.util.List;

/**
 * 数据库连接类.
 * 
 * @author liuyx
 * @param 从TableAction类接受参数 生成sql语句并调用MySQLOperation类修改数据库
 * @return 操作成功与否
 */
public class DBConnection {
  private MySQLOperation MySQLHelper;
  
  /**
   * 注册时判断用户是否存在.
   */
  public boolean UserIsExist(String Username) {
    boolean result = true;
    return result;
  }

  /**
   * 判断一个表是否存在.
   */
  public boolean TableIsExist(final String Username) {
    boolean result = true;
    return result;
  }

  /**
   * 注册新用户.
   */
  public boolean Register(String Username, String Password) {
    boolean result = true;
    return result;
  }

  /**
   * 登陆时判断用户名密码是否相符.
   */
  public boolean Login(String Username, String Password) {
    boolean result = false;
    return result;
  }


  /**
   * 增加一个新表.
   */
  public boolean Create(final String Username, final String[][] Table) {
    boolean result = true;
    return result;
  }

  /**
   * 删除一个表.
   */
  public boolean Delete(final String Username) {
    boolean result = true;
    return result;
  }

  /**
   * 更新一个表内容(表结构不变).
   */
  public boolean Update(final List Change) {
    boolean result = true;
    return result;
  }

  /**
   * 删除表字段.
   */
  public boolean DeleteCol(final int ColumnNum) {
    boolean result = true;
    return result;
  }

  /**
   * 删除某一行.
   */
  public boolean DeleteRow(final int RowNum) {
    boolean result = true;
    return result;
  }

  /**
   * 查找一个表，并返回内容.
   */
  public String[][] Search(final String Username) {
    boolean result = true;
    String[][] Table = MySQLHelper.getTable();
    return Table;
  }

}
