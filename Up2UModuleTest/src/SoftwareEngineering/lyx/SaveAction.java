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
    int tableBegin;
    int tableFinish = 0;
    // 找到某一个标签内容
    for (int i = 0; i < origin.length(); i++) {
      // 找到第一个<>匹配
      if (origin.charAt(i) == '<') {

        for (int j = i; j < origin.length(); j++) {
          if (origin.charAt(j) == '>') {
            tag = origin.substring(i, j + 1);
            i = j;
            break;
          }
        }

      }

      if (tag != null && tag.contains(begin)) {
        tableBegin = i;

        for (; i < origin.length(); i++) {
          if (origin.charAt(i) == '<') {

            for (int j = i; j < origin.length(); j++) {
              if (origin.charAt(j) == '>') {
                tag = origin.substring(i, j + 1);
                tableFinish = i;
                i = j;
                break;
              }
            }
            if (tag.contains(finish)) {
              segment.add(origin.substring(tableBegin + 1, tableFinish));
              tag = null;
              break;
            }

          }
        }

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
    String[][] table = null;

    DBConnection dbHelper = new DBConnection();
    if (dbHelper.Create(Username, table)) {
      result = "success";
    }
    return result;
  }

  public static void main(String args[]) {
    SaveAction sa = new SaveAction();
    String s = "<table border=\"1\">" + "<tr>" + "<td>row <ddd>1, <kkk>cell 1</td>"
        + "<td>row 1, cell 2</td>" + "</tr>" + "<tr>" + "<td>row 2, cell 1</td>"
        + "<td>row 2, cell 2</td>" + "</tr>" + "</table>";
    String[][][] tables = sa.grabWebTable(s);
    if (tables != null && tables[0] != null && tables[0][0] != null) {
      for (int i = 0; i < tables.length; i++) {
        System.out.println("table No." + i);
        for (int j = 0; j < tables[0].length; j++) {
          for (int k = 0; k < tables[0][0].length; k++) {
            System.out.print(tables[i][j][k] + "\t");
          }
          System.out.println();
        }
        System.out.println();
        System.out.println();
      }
    }

  }
}
