package SoftwareEngineering.lyx;

public class TableAction {

  /**
   * �û���.
   */
  protected String Username;
  /**
   * ����.
   */
  protected String Password;
  /**
   * ȷ������.
   */
  
  protected String Email;
  

  protected String RePassword;
  /**
   * ץȡ���±��.
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
