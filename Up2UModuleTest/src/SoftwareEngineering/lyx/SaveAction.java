package SoftwareEngineering.lyx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * 获取url并抓取表格保存至数据库.
 * 
 * @author liuyx. .
 */
public class SaveAction extends TableAction {
  /**
   * 网页上输入的Url.
   */
  protected String Url;
  /**
   * 编码.
   */
  private static String Encoding = "GB2312";
  /**
   * 超时时间限定.
   */
  private static final long TIMEOUT = 100000;

  private static final String HTTP = "http";
  private static final String HTTPS = "https";
  private static final String ERRORINPUT = "error";

  /**
   * Url get方法. @return.
   */
  public String getUrl() {
    return Url;
  }

  /**
   * Url set方法.
   * 
   * @param url.
   */
  public void setUrl(String url) {
    Url = url;
  }

  private String varifyInput() {
    String variety = new String();
    String url = Url.toLowerCase();

    if (url.length() >= 7) {
      if (url.substring(0, 4).equals(HTTPS)) {
        variety = HTTPS;
      } else if (url.substring(0, 3).equals(HTTP)) {
        variety = HTTP;
      }
    } else {
      variety = ERRORINPUT;
    }

    return variety;
  }


  /**
   * 将http网址内容转为字符串.
   * 
   * @return webContent.
   */
  private String getHttp() {
    StringBuffer webContent = new StringBuffer();
    try {
      URL url = new URL(Url);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("User-Agent", "MSIE 9.0");
      BufferedReader br =
          new BufferedReader(new InputStreamReader(connection.getInputStream(), Encoding));

      // long nowTime ;
      String line = null;
      line = br.readLine();
      while (line != null) {
        // nowTime = new java.util.Date().getTime();
        webContent.append(line + "\r\n");
        line = br.readLine();
        // if (new java.util.Date().getTime() - nowTime > TIMEOUT){
        // ;//超时
        // }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    } finally {
      ;
    }
    return webContent.toString();
  }

  /**
   * 将https网址内容转为字符串.
   * 
   * @return webContent.
   */
  private String getHttps() {
    StringBuffer webContent = new StringBuffer();
    try {
      URL url = new URL(Url);
      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
      connection.setRequestProperty("User-Agent", "MSIE 9.0");
      BufferedReader br =
          new BufferedReader(new InputStreamReader(connection.getInputStream(), Encoding));

      // long nowTime ;
      String line = null;
      line = br.readLine();
      while (line != null) {
        // nowTime = new java.util.Date().getTime();
        webContent.append(line + "\r\n");
        line = br.readLine();
        // if (new java.util.Date().getTime() - nowTime > TIMEOUT){
        // ;//超时
        // }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    } finally {
      ;
    }
    return webContent.toString();
  }

  private String getContent(){
    String webContent = new String();
    String variety = varifyInput();
    
    if(variety.equals(HTTP)){
      webContent = getHttp();
    }else if(variety.equals(HTTPS)){
      webContent = getHttps();
    }else if(variety.equals(ERRORINPUT)){
      webContent = ERRORINPUT;
    }
    
    return webContent;
  }
  
  /**
   * 将字符串中的表格信息抓取出来.
   * 
   * @param WebContent. @return.
   */
  private String[][] grabWebTable(final String webContent) {
    
    return null;
  }

  @Override
  public String execute() {
    String result = "failure";
    String webContent ;
    
    webContent = getContent();
    String[][] table = grabWebTable(webContent);

    DBConnection dbHelper = new DBConnection();
    if (dbHelper.Create(Username, table)) {
      result = "success";
    }
    return result;
  }
}
