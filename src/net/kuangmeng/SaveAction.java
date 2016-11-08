package net.kuangmeng;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class SaveAction  {
  private String Url;
  private String Username;
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
  
  static {
    String escape[][] = {{"quot", "\""}, {"amp", "&"}, {"lt", "<"}, {"gt", ">"}, {"nbsp", " "}};
    for (int i = 0; i < escape.length; i++) {
      escapeSet.add(escape[i]);
    }
  }
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
      e.printStackTrace();
    } finally {
      ;
    }
    return webContent.toString();
  }

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
      sourceInfo[2][1] = "0";
      webContent = getHttp();
    } else if (variety.equals(HTTPS)) {
      sourceInfo[2][1] = "0";
      webContent = getHttps();
    } else if (variety.equals(ERRORINPUT)) {
      sourceInfo[2][1] = null;
      webContent = ERRORINPUT;
    }
    return webContent;
  }
  private List<String> cutOut(final String origin, final String begin, final String finish) {
    List<String> segment = new ArrayList<String>();
    String tag = new String();
    int tableBegin = 0;
    int tableFinish = 0;
    int quotationEnd = 0;
    int quotationNum = 0;
    String target = null;
    boolean cut = false;
    boolean tagContain = false;
    for (int i = 0; i < origin.length(); i++) {
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
          if (tag.charAt(j) == '\"' || j == tag.length() - 1){
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
            i -= tag.end + 1 - tag.start;
            strToDelete.delete(tag.start, tag.end + 1);
            break;
          } else {
            if (tag.key.charAt(0) != '/') {
              preTag.add(tag);
            } else {
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

  private String[][][] grabWebTable(final String webContent) {
    String[][][] tables = null;
    List<String> tableStr = null;
    List<String> trStr = null;
    List<String> tdStr = null;

    tableStr = cutOut(webContent, "table", "/table");
    tables = new String[tableStr.size()][][];
    for (int i = 0; i < tableStr.size(); i++) {
      boolean haveTh = false;
      trStr = cutOut(tableStr.get(i), "tr", "/tr");
      tables[i] = new String[trStr.size()][];
      for (int j = 0; j < trStr.size(); j++) {
        int jTemp = j;
        if(j==0&&!haveTh){
          tdStr = cutOut(trStr.get(j), "th", "/th");
          if(tdStr.size()==0){
            tdStr = cutOut(trStr.get(j),"td","/td");
            jTemp = j-1;
          }
          haveTh = true;
        }else{
          tdStr = cutOut(trStr.get(j),"td","/td");
        }
        
        tables[i][j] = new String[tdStr.size()];
        for (int k = 0; k < tdStr.size(); k++) {
          tables[i][j][k] = UnescapeCharacter(deleteTag(tdStr.get(k)));
        }
        j = jTemp;
      }
    }
    return tables;
  }

  public String execute() {
    String result = "error";
    String webContent;
    sourceInfo[0][1] = Username;
    sourceInfo[1][1] = Url;
    webContent = getContent();
    String[][][] tables = grabWebTable(webContent);
    List<String[][]> formalTable = new ArrayList<String[][]>();
    for (int i = 0; i < tables.length; i++) {
      int rowMax = 0;
      for (int j = 0; j < tables[i].length; j++) {
        if (rowMax < tables[i][j].length) {
          rowMax = tables[i][j].length;
        }
      }
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
    int tableNum = dbHelper.getLastId("Source");
    System.out.println(tableNum);
    for (int i = 0; i < formalTable.size(); i++) {
      sourceInfo[3][1] = Username + "-" +(tableNum+i+1);
      System.out.println("kkk___"+sourceInfo[3][1]);
      dbHelper.Insert("Source", sourceInfo);
      if (dbHelper.Create(sourceInfo[3][1], formalTable.get(i))) {
        result = "success";
      }
    }
    return result;
  }

public String getUsername() {
	return Username;
}

public void setUsername(String username) {
	Username = username;
}
}
