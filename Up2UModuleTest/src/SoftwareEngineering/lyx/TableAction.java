package SoftwareEngineering.lyx;

public class TableAction {

  /**
   * 用户名.
   */
  protected String Username;
  /**
   * 密码.
   */
  protected String Password;
  /**
   * 确认密码.
   */
  
  protected String Email;
  

  protected String RePassword;
  /**
   * 抓取道德表格.
   */
  protected String[][] Table;

  public String[][] getTable() {
    return Table;
  }

  public void setTable(String[][] table) {
    Table = table;
  }

  public String getRePassword() {
    return RePassword;
  }

  public void setRePassword(String rePassword) {
    RePassword = rePassword;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }
  
    public String getUsername() {
    return Username;
  }

  public void setUsername(String username) {
    Username = username;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    Password = password;
  }

  public String execute() {
    return "other";
  }
}
