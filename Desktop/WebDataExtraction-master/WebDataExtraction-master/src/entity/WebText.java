package entity;

import java.util.ArrayList;
import java.util.List;

public class WebText {
  private String content;
  private ArrayList<String[][]> table = new ArrayList<String[][]>();
  private static List<String[]> escapeSet = new ArrayList<String[]>();

  public void setContent(String content) {
    this.content = content;
  }

  public ArrayList<String[][]> getTable() {
    return table;
  }

  /**
   * 设置转义字符集
   */
  static {
    String escape[][] = {{"quot", "\""}, {"amp", "&"}, {"lt", "<"}, {"gt", ">"}, {"nbsp", " "}};
    for (int i = 0; i < escape.length; i++) {
      escapeSet.add(escape[i]);
    }
  }


  private List<String> cutOutTag(String origin, String tagKey) {
    List<String> segment = new ArrayList<String>();
    String begin = tagKey;
    String finish = "/" + tagKey;
    String tag = null;
    int tableBegin = 0;
    int tableFinish = 0;
    int quotationEnd = 0;
    int quotationNum = 0;
    String target = null;
    boolean cut = false;
    boolean tagContain = false;

    for (int i = 0; i < origin.length(); i++) {
      // 找到一个<>标签
      if (origin.charAt(i) == '<') {

        for (int j = i; j < origin.length(); j++) {
          if (origin.charAt(j) == '>') {
            tag = origin.substring(i, j + 1);
            break;
          }
        }
      }

      // 判断是否已经切割，是否有标签内容
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
          i += tag.length() - 1;
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
          i += tag.length() - 1;
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
      } else if (strToDelete.charAt(i) == ' ' && inTag && tag != null && tag.key == null) {
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
        for (int j = i - 1; j >= 0; j--) {
          if (str.charAt(j) == ' ') {
            continue;
          } else if (strToDelete.charAt(j) == '/') {
            i -= tag.end + 1 - tag.start;
            strToDelete.delete(tag.start, tag.end + 1);
            break;
          } else {
            if (tag.key.charAt(0) != '/') {
              preTag.add(tag);
            } else {
              for (int k = preTag.size() - 1; k >= 0; k--) {
                tagUnit temp = preTag.remove(k);
                if (tag.key.substring(1).equals(temp.key)) {
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
   * 反转义.
   * 
   * @param str
   * @return
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
   * 将content内容中的table标签内容抓取出来. 用两个list作为中间变量用来存储每个table. thTags:List th标签内容. table:List 非th标签内容.
   * 每次处理一个表格，就将表格规格化后放入属性table表中.
   */
  public void grabTable() {
    List<String[]> tdTags = new ArrayList<String[]>();
    List<String[]> thTags = new ArrayList<String[]>();

    List<String> tableStr = null;
    List<String> theadStr = null;
    List<String> tbodyStr = null;
    List<String> tfootStr = null;
    List<String> trStr = null;
    List<String> thStr = null;
    List<String> tdStr = null;

    tableStr = cutOutTag(content, "table");
    for (int tableNo = 0; tableNo < tableStr.size(); tableNo++) {
      // 将三种table标签剪切出来.
      theadStr = cutOutTag(tableStr.get(tableNo), "thead");
      tbodyStr = cutOutTag(tableStr.get(tableNo), "tbody");
      tfootStr = cutOutTag(tableStr.get(tableNo), "tfoot");
      // 判断有无thead标签.
      // 若有，则将切割thead内容来找到th.
      // 若无，则切割tr，在每组tr中找到th.
      if (theadStr.size() > 0) {
        for (int theadNum = 0; theadNum < theadStr.size(); theadNum++) {
          trStr = cutOutTag(theadStr.get(theadNum), "tr");
          String[] temp = null;
          // 判断有误tr标签.
          // 若有，则表示分行，在每组tr中找到th标签内容分别存入每行.
          // 若无，表示不分行，直接找到th标签内容存入一行.
          if (trStr.size() != 0) {
            for (int trNum = 0; trNum < trStr.size(); trNum++) {
              thStr = cutOutTag(trStr.get(trNum), "th");
              temp = new String[thStr.size()];
              for (int thNum = 0; thNum < thStr.size(); thNum++) {
                // 先删除无用标签，然后解释转义字符
                temp[thNum] = UnescapeCharacter(deleteTag(thStr.get(thNum)));
              }
              thTags.add(temp);
            }
          } else {
            thStr = cutOutTag(theadStr.get(theadNum), "th");
            temp = new String[thStr.size()];
            for (int thNum = 0; thNum < thStr.size(); thNum++) {
              temp[thNum] = UnescapeCharacter(deleteTag(thStr.get(thNum)));
            }
            thTags.add(temp);
          }

        }
      } else {
        trStr = cutOutTag(tableStr.get(tableNo), "tr");
        for (int trNum = 0; trNum < trStr.size(); trNum++) {
          thStr = cutOutTag(trStr.get(trNum), "th");
          if (thStr.size() == 0) {
            continue;
          }
          String[] temp = new String[thStr.size()];
          for (int thNum = 0; thNum < thStr.size(); thNum++) {
            temp[thNum] = UnescapeCharacter(deleteTag(thStr.get(thNum)));
          }
          thTags.add(temp);
        }
      }

      // 判断有无tbody标签.
      // 若有，则将切割tbody,考虑tfoot.
      // 若无，则切割tr，在每组tr中找到td,不考虑tfoot.
      if (tbodyStr.size() > 0) {
        for (int tbodyNum = 0; tbodyNum < tbodyStr.size(); tbodyNum++) {
          trStr = cutOutTag(tbodyStr.get(tbodyNum), "tr");
          for (int trNum = 0; trNum < trStr.size(); trNum++) {
            tdStr = cutOutTag(trStr.get(trNum), "td");
            String[] temp = new String[tdStr.size()];
            for (int tdNum = 0; tdNum < tdStr.size(); tdNum++) {
              temp[tdNum] = UnescapeCharacter(deleteTag(tdStr.get(tdNum)));
            }
            tdTags.add(temp);
          }
        }
        // 判断有无tfoot.
        // 若有，则将tfoot中的td标签切割出来.
        if (tfootStr.size() > 0) {
          for (int tfootNum = 0; tfootNum < tfootStr.size(); tfootNum++) {
            trStr = cutOutTag(tfootStr.get(tfootNum), "tr");
            for (int trNum = 0; trNum < trStr.size(); trNum++) {
              tdStr = cutOutTag(trStr.get(trNum), "td");
              String[] temp = new String[tdStr.size()];
              for (int tdNum = 0; tdNum < tdStr.size(); tdNum++) {
                temp[tdNum] = UnescapeCharacter(deleteTag(tdStr.get(tdNum)));
              }
              tdTags.add(temp);
            }
          }
        }

      } else {
        trStr = cutOutTag(tableStr.get(tableNo), "tr");
        for (int trNum = 0; trNum < trStr.size(); trNum++) {
          tdStr = cutOutTag(trStr.get(trNum), "td");
          if (tdStr.size() == 0) {
            continue;
          }
          String[] temp = new String[tdStr.size()];
          for (int tdNum = 0; tdNum < tdStr.size(); tdNum++) {
            temp[tdNum] = UnescapeCharacter(deleteTag(tdStr.get(tdNum)));
          }
          tdTags.add(temp);
        }
      }

      table.add(formalTable(thTags, tdTags));
      thTags.clear();
      tdTags.clear();
    }
  }

  public String[][] formalTable(List<String[]> thTags, List<String[]> tdTags) {
    // 将两个List合并成一个table并放入属性table表中
    // 找到最长字段
    int colNum = 0;
    int thLength = thTags.size();
    int tdLength = tdTags.size();
    int rowNum = thLength + tdLength + 2;
    int temp = 0;
    for (int i = 0; i < thTags.size(); i++) {
      temp = thTags.get(i).length;
      if (colNum < temp) {
        colNum = temp;
      }
    }
    for (int i = 0; i < tdTags.size(); i++) {
      temp = tdTags.get(i).length;
      if (colNum < temp) {
        colNum = temp;
      }
    }
    colNum += 2;
    String[][] tableTemp = new String[rowNum][colNum];

    for (int i = 0; i < rowNum; i++) {
      if (i == 0) {
        for (int j = 0; j < colNum; j++) {
          if (j == 0) {
            tableTemp[i][j] = "id";
          } else {
            tableTemp[i][j] = new Integer(j - 1).toString();
          }
        }
      } else if (i == 1) {
        for (int j = 0; j < colNum; j++) {
          if (j == 0) {
            tableTemp[i][j] = "1";
          } else if (j == 1) {
            tableTemp[i][j] = new Integer(rowNum - 1).toString();
          } else {
            tableTemp[i][j] = "00";
          }
        }
      } else if (i < 2 + thLength) {
        for (int j = 0; j < colNum; j++) {
          if (j == 0) {
            tableTemp[i][j] = new Integer(i).toString();
          } else if (j == 1) {
            tableTemp[i][j] = "001";
          } else {
            if (j < thTags.get(i - 2).length + 2) {
              tableTemp[i][j] = thTags.get(i - 2)[j - 2];
            } else {
              tableTemp[i][j] = "";
            }
          }
        }
      } else {
        for (int j = 0; j < colNum; j++) {
          if (j == 0) {
            tableTemp[i][j] = new Integer(i).toString();
          } else if (j == 1) {
            tableTemp[i][j] = "000";
          } else {
            if (j < tdTags.get(i - thLength - 2).length + 2) {
              tableTemp[i][j] = tdTags.get(i - thLength - 2)[j - 2];
            } else {
              tableTemp[i][j] = "";
            }
          }
        }
      }
    }
    return tableTemp;
  }
}
