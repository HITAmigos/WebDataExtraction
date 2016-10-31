package SoftwareEngineering.lyx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

  /**
   * 区分输入是http还是https
   * 
   * @return
   */
  private String varifyInput() {
    String variety = new String();
    String url = Url.toLowerCase();

    if (url.length() >= 7) {
      if (url.substring(0, 5).equals(HTTPS)) {
        variety = HTTPS;
      } else if (url.substring(0, 4).equals(HTTP)) {
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

  private String getContent() {
    String webContent = new String();
    String variety = varifyInput();

    if (variety.equals(HTTP)) {
      webContent = getHttp();
    } else if (variety.equals(HTTPS)) {
      webContent = getHttps();
    } else if (variety.equals(ERRORINPUT)) {
      webContent = ERRORINPUT;
    }

    return webContent;
  }

  /**
   * 抓取两个标签<str1><str2>之间的内容. .
   * 
   * @param WebContent. @return.
   */
  private List<String> cutOut(final String origin, final String begin, final String finish) {
    List<String> segment = new ArrayList<String>();
    String tag = new String();
    int tableBegin = 0;
    int tableFinish = 0;
    boolean cut = false;
    // 找到某一个标签内容
    for (int i = 0; i < origin.length(); i++) {
      // 找到第一个<>匹配
      if (origin.charAt(i) == '<') {

        for (int j = i; j < origin.length(); j++) {
          if (origin.charAt(j) == '>') {
            tag = origin.substring(i, j + 1);
            break;
          }
        }
      }

      if (cut && tag != null && tag.contains(finish)) {
        tableFinish = i;
        segment.add(origin.substring(tableBegin, tableFinish));
        i += tag.length();
        cut = false;
        tag = null;
      } else if (tag != null && tag.contains(begin)) {
        tableBegin = i + tag.length();
        i += tag.length();
        cut = true;
        tag = null;
      }
    }

    return segment;
  }

  /**
   * 删除字符串中的多余标签.
   * 
   * @param str
   * @return
   */
  private String deleteTag(String str) {
    StringBuffer strToDelete = new StringBuffer();
    strToDelete.append(str);
    int startPos = 0;
    int endPos = str.length();

    for (int i = str.length() - 1; i >= 0; i--) {
      if (str.charAt(i) == '>') {
        endPos = i;
      } else if (str.charAt(i) == '<') {
        startPos = i;
        strToDelete.delete(startPos, endPos + 1);
      }
    }
    return strToDelete.toString();
  }

  /**
   * 将得到的网页内容变为字符串二维数组。
   * 
   * @param webContent
   * @return
   */
  private String[][][] grabWebTable(final String webContent) {
    String[][][] tables = null;
    List<String> tableStr = null;
    List<String> trStr = null;
    List<String> tdStr = null;

    tableStr = cutOut(webContent, "table", "/table");
    tables = new String[tableStr.size()][][];
    for (int i = 0; i < tableStr.size(); i++) {
      trStr = cutOut(tableStr.get(i), "tr", "/tr");
      tables[i] = new String[trStr.size()][];
      for (int j = 0; j < trStr.size(); j++) {
        tdStr = cutOut(trStr.get(j), "td", "/td");
        tables[i][j] = new String[tdStr.size()];
        for (int k = 0; k < tdStr.size(); k++) {
          tables[i][j][k] = deleteTag(tdStr.get(k));
        }
      }
    }
    return tables;
  }

  @Override
  public String execute() {
    String result = "failure";
    String webContent;

    webContent = getContent();
    String[][][] tables = grabWebTable(webContent);
    List<String> titleName = cutOut(webContent, "title", "/title");
    if (titleName.isEmpty()) {
      titleName.add(Url);
    }

    List<String[][]> formalTable = new ArrayList<String[][]>();
    // 将表格规格化
    for (int i = 0; i < tables.length; i++) {
      int rowMax = 0;
      // 得到最大字段长
      for (int j = 0; j < tables[i].length; j++) {
        if (rowMax < tables[i][j].length) {
          rowMax = tables[i][j].length;
        }
      }
      // 将表规格化赋值
      String[][] temp = new String[tables[i].length+1][rowMax];
      for(int k = 0 ; k < rowMax ; k++ ){
        temp[0][k] = new Integer(k+1).toString();
      }
      for (int j = 1; j <= tables[i].length; j++) {
        for (int k = 0; k < rowMax; k++) {
          if (k < tables[i][j-1].length) {
            temp[j][k] = tables[i][j-1][k];
          } else if (j == 0) {
            temp[j][k] = new Integer(k+1).toString();
          } else {
            temp[j][k] = "  ";
          }
        }
      }
      formalTable.add(temp);
    }

    DBConnection dbHelper = new DBConnection();
    for (int i = 0; i < formalTable.size(); i++) {
      if (dbHelper.Create(Username + "-" + titleName.get(0) + "-" + (i + 1), formalTable.get(i))) {
        result = "success";
      }
    }
    return result;
  }

  public static void main(String args[]) {
    SaveAction sa = new SaveAction();
    // https://www.sogou.com/sie?hdq=AQxRG-4472&query=contain%E6%96%B9%E6%B3%95&ie=utf8
    // http://www.w3school.com.cn/html/html_tables.asp
    sa.setUsername("lyx");
    sa.setUrl("http://www.w3school.com.cn/html/html_tables.asp");
    sa.execute();

  }
}
