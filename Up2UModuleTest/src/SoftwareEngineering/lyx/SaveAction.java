package SoftwareEngineering.lyx;

/**
 * 获取url并抓取表格保存至数据库.
 * @author liuyx.
 * .
 */
public class SaveAction extends TableAction {
  /**
   * 网页上输入的Url.
   */
  protected String Url;

  /**
   * Url get方法.
   * @return.
   */
  public String getUrl() {
    return Url;
  }
  /**
   * Url set方法.
   * @param url.
   */
  public void setUrl(String url) {
    Url = url;
  }

  /**
   * 将网址内容转为字符串.
   * @return webContent.
   */
  private String getWebContent() {
    String webContent = null;

    return webContent;
  }

  /**
   * 将字符串中的表格信息抓取出来.
   * @param WebContent.
   * @return.
   */
  private String[][] grabWebTable(final String WebContent) {
    return null;
  }

  @Override
  public String execute() {
    String result = "failure";

    String webContent = getWebContent();
    String[][] Table = grabWebTable(webContent);

    DBConnection dbHelper = new DBConnection();
    if (dbHelper.Create(Username, Table)) {
      result = "success";
    }
    return result;
  }
}
