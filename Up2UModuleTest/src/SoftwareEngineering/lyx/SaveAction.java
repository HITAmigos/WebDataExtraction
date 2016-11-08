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
 * ��ȡurl��ץȡ��񱣴������ݿ�.
 * 
 * @author liuyx.
 */
public class SaveAction extends TableAction {

  protected String Url;
  // ת���ַ�ӳ��
  private static List<String[]> escapeSet = new ArrayList<String[]>();
  private static String Encoding = "GB2312";
  private static final String HTTP = "http";
  private static final String HTTPS = "https";
  private static final String UPLOAD = "upload";
  private static final String ERRORINPUT = "error";
  private static String[][] sourceInfo = new String[4][2];
  static {
    sourceInfo[0][0] = "Username";
    sourceInfo[1][0] = "Link";
    sourceInfo[2][0] = "Tag";
    sourceInfo[3][0] = "Tablename";
  }

  public String getUrl() {
    return Url;
  }

  public void setUrl(String url) {
    Url = url;
  }

  /**
   * ����ת���ַ�ӳ��.
   */
  static {
    String escape[][] = {{"quot", "\""}, {"amp", "&"}, {"lt", "<"}, {"gt", ">"}, {"nbsp", " "}};
    for (int i = 0; i < escape.length; i++) {
      escapeSet.add(escape[i]);
    }
  }

  /**
   * ����������http����https����upload.
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
      } else if (url.substring(0, 6).equals(UPLOAD)) {
        variety = UPLOAD;
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

      String line = null;
      line = br.readLine();
      while (line != null) {
        webContent.append(line + "\r\n");
        line = br.readLine();
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

      String line = null;
      line = br.readLine();
      while (line != null) {
        webContent.append(line + "\r\n");
        line = br.readLine();
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
   * �������벻ͬ��ȡ��ͬ;�������ݲ�����Ϊ�ַ���.
   */
  private String getContent() {
    String webContent = new String();
    String variety = varifyInput();

    if (variety.equals(HTTP)) {
      sourceInfo[2][1] = "0";
      webContent = getHttp();
    } else if (variety.equals(HTTPS)) {
      sourceInfo[2][1] = "0";
      webContent = getHttps();
      // } else if (variety.equals(UPLOAD)) {
      // sourceInfo[0][1] = "1";
      // webContent = getUploadContent();
    } else if (variety.equals(ERRORINPUT)) {
      sourceInfo[2][1] = null;
      webContent = ERRORINPUT;
    }

    return webContent;
  }

  /**
   * ץȡ������ǩ<str1><str2>֮�������.
   * 
   * @param WebContent.
   * @return ���ݱ�ǩ�г������ַ�������.
   */
  private List<String> cutOut(final String origin, final String begin, final String finish) {
    List<String> segment = new ArrayList<String>();
    String tag = null;
    int tableBegin = 0;
    int tableFinish = 0;
    int quotationEnd = 0;
    int quotationNum = 0;
    String target = null;
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
          i += tag.length()-1;
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
      } else if (strToDelete.charAt(i) == '>' && inTag && tag != null && quotationNum % 2 == 0) {
        tag.end = i;
        if (tag.key == null) {
          tag.key = strToDelete.substring(tag.start + 1, tag.end);
        }
        inTag = false;
        quotationNum = 0;
        // ������ǰtagΪ<.../>����ֱ��ɾ��
        for (int j = i - 1; j >= 0; j--) {
          if (str.charAt(j) == ' ') {
            continue;
          } else if (str.charAt(j) == '/') {
            // ��ǰtagΪ<.../>��
            i -= tag.end + 1 - tag.start;
            strToDelete.delete(tag.start, tag.end + 1);
            break;
          } else {
            // ��ǰtag��Ϊ<.../>
            if (tag.key.charAt(0) != '/') {
              // ��ǰ��ǩΪǰ׺,����'/'��ͷ,�ӵ�ǰ׺��ջ��
              preTag.add(tag);
            } else {
              // ��ǰ��ǩΪ��׺��'/'��ͷ����ǰ׺��ջ���ҵ���Ӧ����߶�ɾ��
              for (int k = preTag.size() - 1; k >= 0; k--) {
                tagUnit temp = preTag.remove(k);
                if (tag.key.substring(2).equals(temp.key.substring(1))) {
                  i -= tag.end + 1 - tag.start;
                  i -= temp.end + 1 - temp.start;
                  strToDelete.delete(tag.start, tag.end + 1);
                  strToDelete.delete(temp.start, temp.end + 1);
                  break;
                }
              }
            }
            break;
          }
        }

      }
    }

    return strToDelete.toString();
  }

  /**
   * ��ת��.
   */
  private String UnescapeCharacter(String str) {
    StringBuffer strToUnescape = new StringBuffer(str);
    boolean inEscape = false;
    int escapeStart = 0;
    int escapeEnd = 0;
    String escapeStr;
    for (int i = 0; i < strToUnescape.length(); i++) {
      if (strToUnescape.charAt(i) == '&') {
        inEscape = true;
        escapeStart = i;
      } else if (inEscape && strToUnescape.charAt(i) == ';') {
        inEscape = false;
        escapeEnd = i;
        escapeStr = strToUnescape.substring(escapeStart + 1, escapeEnd);
        for (int j = 0; j < escapeSet.size(); j++) {
          if (escapeStr.equals(escapeSet.get(j)[0])) {
            strToUnescape.replace(escapeStart, escapeEnd + 1, escapeSet.get(j)[1]);
            i -= escapeEnd - escapeStart;
            break;
          }
        }
      }
    }
    return strToUnescape.toString();
  }

  /**
   * ���õ�����ҳ���ݱ�Ϊ�ַ�����ά����.
   * 
   * @param webContent.
   */
  private List<List<String[]>> grabWebTable(final String Content) {
    List<List<String[]>> resultSet = new ArrayList<List<String[]>>();
    List<String[]> thTags = new ArrayList<String[]>();


    List<String> tableStr = null;
    List<String> theadStr = null;
    List<String> tbodyStr = null;
    List<String> tfootStr = null;
    List<String> trStr = null;
    List<String> thStr = null;
    List<String> tdStr = null;

    tableStr = cutOut(Content, "table", "/table");
    for (int tableNo = 0; tableNo < tableStr.size(); tableNo++) {
      List<String> thTagTemp = new ArrayList<String>();
      List<String[]> table = new ArrayList<String[]>();
      // �ޱ�ǩ��Ϊ�յ�sizeΪ0
      theadStr = cutOut(tableStr.get(tableNo), "thead", "/thead");
      tbodyStr = cutOut(tableStr.get(tableNo), "tbody", "/tbody");
      tfootStr = cutOut(tableStr.get(tableNo), "tfoot", "/tfoot");
      // ץȡth��ǩ�����
      if (theadStr.size() > 0) {
        for (int theadNum = 0; theadNum < theadStr.size(); theadNum++) {
          trStr = cutOut(theadStr.get(theadNum), "tr", "/tr");
          String[] temp = null;
          if (trStr.size() != 0) {
            for (int trNum = 0; trNum < trStr.size(); trNum++) {
              thStr = cutOut(trStr.get(trNum), "th", "/th");
              temp = new String[thStr.size()];
              for (int thNum = 0; thNum < thStr.size(); thNum++) {
                temp[thNum] = UnescapeCharacter(deleteTag(thStr.get(thNum)));
              }
            }
          } else {

            thStr = cutOut(theadStr.get(theadNum), "th", "/th");
            temp = new String[thStr.size()];
            for (int thNum = 0; thNum < thStr.size(); thNum++) {
              temp[thNum] = UnescapeCharacter(deleteTag(thStr.get(thNum)));
            }

          }
          if (temp.length != 0) {
            thTagTemp.add("1");
            table.add(temp);
          }
        }

      } else {
        trStr = cutOut(tableStr.get(tableNo), "tr", "/tr");
        for (int trNum = 0; trNum < trStr.size(); trNum++) {
          thStr = cutOut(trStr.get(trNum), "th", "/th");
          String[] temp = new String[thStr.size()];
          for (int thNum = 0; thNum < thStr.size(); thNum++) {
            temp[thNum] = UnescapeCharacter(deleteTag(thStr.get(thNum)));
          }
          if (temp.length != 0) {
            thTagTemp.add("1");
            table.add(temp);
          }
        }
      }

      // ץȡtd��ǩ�����
      if (tbodyStr.size() > 0) {
        for (int tbodyNum = 0; tbodyNum < tbodyStr.size(); tbodyNum++) {
          trStr = cutOut(tbodyStr.get(tbodyNum), "tr", "/tr");
          for (int trNum = 0; trNum < trStr.size(); trNum++) {
            tdStr = cutOut(trStr.get(trNum), "td", "/td");
            String[] temp = new String[tdStr.size()];
            for (int tdNum = 0; tdNum < tdStr.size(); tdNum++) {
              temp[tdNum] = UnescapeCharacter(deleteTag(tdStr.get(tdNum)));
            }
            if (temp.length != 0) {
              thTagTemp.add("0");
              table.add(temp);
            }
          }
        }

        if (tfootStr.size() > 0) {
          for (int tfootNum = 0; tfootNum < tfootStr.size(); tfootNum++) {
            trStr = cutOut(tfootStr.get(tfootNum), "tr", "/tr");
            for (int trNum = 0; trNum < trStr.size(); trNum++) {
              tdStr = cutOut(trStr.get(trNum), "td", "/td");
              String[] temp = new String[tdStr.size()];
              for (int tdNum = 0; tdNum < tdStr.size(); tdNum++) {
                temp[tdNum] = UnescapeCharacter(deleteTag(tdStr.get(tdNum)));
              }
              boolean flag = false;
              for (int tdNum = 0; tdNum < tdStr.size(); tdNum++) {
                if (tdStr.get(tdNum).length() != 0) {
                  flag = true;
                  break;
                }
              }
              if (flag && temp.length != 0) {
                thTagTemp.add("0");
                table.add(temp);
              }
            }
          }
        }

      } else {
        trStr = cutOut(tableStr.get(tableNo), "tr", "/tr");
        for (int trNum = 0; trNum < trStr.size(); trNum++) {
          tdStr = cutOut(trStr.get(trNum), "td", "/td");
          String[] temp = new String[tdStr.size()];
          for (int tdNum = 0; tdNum < tdStr.size(); tdNum++) {
            temp[tdNum] = UnescapeCharacter(deleteTag(tdStr.get(tdNum)));
          }
          if (temp.length != 0) {
            thTagTemp.add("0");
            table.add(temp);
          }
        }
      }
      String[] thTag = new String[thTagTemp.size()];
      for (int i = 0; i < thTagTemp.size(); i++) {
        thTag[i] = thTagTemp.get(i);
      }

      resultSet.add(table);
      thTags.add(thTag);
    }

    resultSet.add(thTags);
    return resultSet;
  }

  @Override
  public String execute() {
    String result = "failure";
    String webContent;
    sourceInfo[0][1] = Username;
    sourceInfo[1][1] = Url;

    webContent = getContent();
    List<List<String[]>> resultSet = grabWebTable(webContent);
    List<String[]> table = null;
    List<String[]> thTags = resultSet.get(resultSet.size() - 1);

    List<String[][]> formalTable = new ArrayList<String[][]>();
    // �������
    for (int tableNo = 0; tableNo < resultSet.size() - 1; tableNo++) {
      table = resultSet.get(tableNo);
      int rowMax = 0;
      // �õ�����ֶγ�
      for (int rowNum = 0; rowNum < table.size(); rowNum++) {
        if (rowMax < table.get(rowNum).length) {
          rowMax = table.get(rowNum).length;
        }
      }
      // �����񻯸�ֵ
      String[][] temp = new String[table.size() + 2][rowMax + 1];
      String[] thTag = thTags.get(tableNo);
      for (int colNum = 0; colNum <= rowMax; colNum++) {
        // ��Ǹ�ʽ:�Ƿ�ɾ�����Ƿ��Ǳ�
        temp[0][colNum] = new Integer(colNum).toString();
        temp[1][colNum] = "00";
      }
      for (int newRowNum = 2; newRowNum <= table.size() + 1; newRowNum++) {
        for (int colNum = 0; colNum <= rowMax; colNum++) {
          if (colNum == 0) {
            // ��Ǹ�ʽ:�Ƿ�ɾ�����Ƿ��Ǳ�,�Ƿ�Ϊth
            temp[newRowNum][colNum] = "00" + thTag[newRowNum - 2];
          } else if (colNum <= table.get(newRowNum - 2).length) {
            temp[newRowNum][colNum] = table.get(newRowNum - 2)[colNum - 1];
          } else if (newRowNum == 0) {
            temp[newRowNum][colNum] = new Integer(colNum + 1).toString();
          } else {
            temp[newRowNum][colNum] = "\t";
          }
        }
      }
      formalTable.add(temp);
    }

    for (int i = 0; i < formalTable.size(); i++) {
      System.out.println("table No." + (i + 1));
      String[][] Table = formalTable.get(i);
      for (int j = 0; j < Table.length; j++) {
        for (int k = 0; k < Table[j].length; k++) {
          System.out.print(Table[j][k] + "\t|");
        }
        System.out.println();
      }
      System.out.println("-------------------");
    }

    DBConnection dbHelper = new DBConnection();

    int tableNum = dbHelper.getLastId("Source");
    for (int i = 0; i < formalTable.size(); i++) {
      sourceInfo[3][1] = Username + "-" + (tableNum + i + 1);
      dbHelper.Insert("Source", sourceInfo);
      if (dbHelper.Create(sourceInfo[3][1], formalTable.get(i))) {
        result = "success";
      }
    }
    return result;
  }

  public static void main(String args[]) {
    SaveAction sa = new SaveAction();
    sa.setUsername("lyx");
    // http://www.jq22.com/demo/Fixed-Header-Table-master20161013/demo/
    // https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?spm=a1z02.1.a2109.d1000368.eijg3M
    // http://www.jq22.com/demo/sortableTable20160801/
    // http://www.w3school.com.cn/html/html_tables.asp
    sa.setUrl("http://www.jq22.com/demo/sortableTable20160801/");
    sa.execute();

  }
}
