package SoftwareEngineering.lyx;

/**
 * ��ȡurl��ץȡ��񱣴������ݿ�.
 * @author liuyx.
 * .
 */
public class SaveAction extends TableAction {
  /**
   * ��ҳ�������Url.
   */
  protected String Url;

  /**
   * Url get����.
   * @return.
   */
  public String getUrl() {
    return Url;
  }
  /**
   * Url set����.
   * @param url.
   */
  public void setUrl(String url) {
    Url = url;
  }

  /**
   * ����ַ����תΪ�ַ���.
   * @return webContent.
   */
  private String getWebContent() {
    String webContent = null;

    return webContent;
  }

  /**
   * ���ַ����еı����Ϣץȡ����.
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
