package SoftwareEngineering.lyx;

/*
 * 1�����ñ��λ����ɾ�������У�ֻ����Ƿ���� 2�������ص���λ 3��th���.
 */


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * ��ȡurl��ץȡ��񱣴������ݿ�.
 * 
 * @author liuyx. .
 */
public class SaveAction extends TableAction {
  /**
   * ��ҳ�������Url.
   */
  protected String Url;
  /**
   * ����.
   */
  private static String Encoding = "GB2312";
  /**
   * ��ʱʱ���޶�.
   */
  private static final long TIMEOUT = 100000;

  private static final String HTTP = "http";
  private static final String HTTPS = "https";
  private static final String ERRORINPUT = "error";

  /**
   * Url get����. @return.
   */
  public String getUrl() {
    return Url;
  }

  /**
   * Url set����.
   * 
   * @param url.
   */
  public void setUrl(String url) {
    Url = url;
  }

  /**
   * ����������http����https
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
   * ��http��ַ����תΪ�ַ���.
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
        // ;//��ʱ
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
   * ��https��ַ����תΪ�ַ���.
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
        // ;//��ʱ
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
   * ץȡ������ǩ<str1><str2>֮�������. .
   * 
   * @param WebContent. @return.
   */
  private List<String> cutOut(final String origin, final String begin, final String finish) {
    List<String> segment = new ArrayList<String>();
    String tag = new String();
    int tableBegin = 0;
    int tableFinish = 0;
    int quotationEnd = 0;
    int quotationNum = 0;
    String target = null;
    List<String> tagSegment = new ArrayList<String>();
    boolean cut = false;
    boolean tagContain = false;
    // �ҵ�ĳһ����ǩ����
    for (int i = 0; i < origin.length(); i++) {
      // �ҵ���һ��<>ƥ��
      if (origin.charAt(i) == '<') {

        for (int j = i; j < origin.length(); j++) {
          if (origin.charAt(j) == '>') {
            tag = origin.substring(i, j + 1);
            break;
          }
        }
      }

      if (cut && tag != null) {
        target = finish;
        for (int j = 0; j < tag.length(); j++) {
          if (tag.charAt(j) == '\"' || j == tag.length() - 1) {
            quotationNum++;
            if (quotationNum % 2 == 0) {
              quotationEnd = j + 1;
            } else if (quotationNum % 2 == 1 && tag.substring(quotationEnd, j).contains(target)) {
              tagContain = true;
              break;
            }
          }
        }
        if (tagContain) {
          tableFinish = i;
          segment.add(origin.substring(tableBegin, tableFinish));
          i += tag.length();
          cut = false;
        }
      } else if (tag != null) {
        target = begin;
        for (int j = 0; j < tag.length(); j++) {
          if (tag.charAt(j) == '\"' || j == tag.length() - 1) {
            quotationNum++;
            if (quotationNum % 2 == 0) {
              quotationEnd = j + 1;
            } else if (quotationNum % 2 == 1 && tag.substring(quotationEnd, j).contains(target)) {
              tagContain = true;
              break;
            }
          }
        }
        if (tagContain) {
          tableBegin = i + tag.length();
          i += tag.length();
          cut = true;
        }
      }
      quotationEnd = 0;
      quotationNum = 0;
      tagContain = false;
      tag = null;
    }

    return segment;
  }

  /**
   * ɾ���ַ����еĶ����ǩ.
   * 
   * @param str
   * @return
   */
  private String deleteTag(String str) {
    class tagUnit {
      public int start;
      public int end;
      public String key;
    }
    StringBuffer strToDelete = new StringBuffer();
    strToDelete.append(str);
    List<tagUnit> preTag = new ArrayList<tagUnit>();
    boolean inTag = false;
    int quotationNum = 0;

    tagUnit tag = null;
    for (int i = 0; i < strToDelete.length(); i++) {
      if (strToDelete.charAt(i) == '<') {
        tag = new tagUnit();
        tag.start = i;
        inTag = true;
      } else if (strToDelete.charAt(i) == ' ' && inTag && tag != null) {
        tag.key = strToDelete.substring(tag.start + 1, i);
      } else if (strToDelete.charAt(i) == '\"' && inTag && tag != null) {
        quotationNum++;
      } else if (strToDelete.charAt(i) == '>' && inTag && tag!=null && quotationNum % 2 == 0) {
        tag.end = i;
        tag.key = strToDelete.substring(tag.start, tag.end+1);
        inTag = false;
        quotationNum = 0;        
        //������ǰtagΪ<.../>����ֱ��ɾ��
        for (int j = i-1 ; j >= 0; j--) {
          if (str.charAt(j) == ' ') {
            continue;
          } else if (str.charAt(j) == '/') {
            //��ǰtagΪ<.../>��
            i -= tag.end+1 - tag.start;
            strToDelete.delete(tag.start, tag.end + 1);
            break;
          } else {
            //��ǰtag��Ϊ<.../>
            if(tag.key.charAt(1)!='/'){
            //��ǰ��ǩΪǰ׺,����'/'��ͷ,�ӵ�ǰ׺��ջ��
              preTag.add(tag);
            } else {
            //��ǰ��ǩΪ��׺��'/'��ͷ����ǰ׺��ջ���ҵ���Ӧ����߶�ɾ��
              for(int k = preTag.size()-1 ; k >= 0 ; k-- ){
                tagUnit temp = preTag.remove(k);
                if(tag.key.substring(2).equals(temp.key.substring(1))){
                  i -= tag.end+1 - tag.start;
                  i -= temp.end+1 - temp.start;
                  strToDelete.delete(tag.start, tag.end+1);
                  strToDelete.delete(temp.start, temp.end+1);
                  break;
                }
              }
            }
            break;
          }
        }
        
      }
    }

    // for (int i = str.length() - 1; i >= 0; i--) {
    // if (str.charAt(i) == '>') {
    // endPos = i;
    // } else if (str.charAt(i) == '<') {
    // startPos = i;
    // strToDelete.delete(startPos, endPos + 1);
    // }
    // }
    return strToDelete.toString();
  }

  /**
   * ���õ�����ҳ���ݱ�Ϊ�ַ�����ά���顣
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
    // �������
    for (int i = 0; i < tables.length; i++) {
      int rowMax = 0;
      // �õ�����ֶγ�
      for (int j = 0; j < tables[i].length; j++) {
        if (rowMax < tables[i][j].length) {
          rowMax = tables[i][j].length;
        }
      }
      // �����񻯸�ֵ
      String[][] temp = new String[tables[i].length + 2][rowMax + 1];
      for (int k = 0; k <= rowMax; k++) {
        temp[0][k] = new Integer(k).toString();
        temp[1][k] = "00";
      }
      for (int j = 2; j <= tables[i].length + 1; j++) {
        for (int k = 0; k <= rowMax; k++) {
          if (k == 0) {
            temp[j][k] = "00";
          } else if (k <= tables[i][j - 2].length) {
            temp[j][k] = tables[i][j - 2][k - 1];
          } else if (j == 0) {
            temp[j][k] = new Integer(k + 1).toString();
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
    // sa.setUsername("lyx");
    // sa.setUrl("http://www.w3school.com.cn/html/html_tables.asp");
    // sa.execute();
    String s = "<table><t>a<d/ ><sample>b</t></table>k<table><y/>ddd<s></table>";
    System.out.println(s + "\n-----");
    List<String> l = sa.cutOut(s, "table", "/table");
    for (int i = 0; i < l.size(); i++)
      System.out.println(sa.deleteTag(l.get(i)));

  }
}
