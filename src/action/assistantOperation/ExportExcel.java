package action.assistantOperation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Database;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@SuppressWarnings("serial")
public class ExportExcel extends ActionSupport {
  private String tablename;

  public String getTablename() {
    return tablename;
  }

  public void setTablename(String tablename) {
    this.tablename = tablename;
  }

  public String execute() {
    Database db = new Database(tablename);
    List<String> listth = new ArrayList<String>();
    List<String> listtd = new ArrayList<String>();
    try {
      int columnCount = db.getColumnCount();
      ArrayList<String[]> resultSet = db.getResultSet();

      int coltag[] = new int[columnCount];
      String[] currentSet = null;
      for (int i = 0; i < resultSet.size(); i++) {
        currentSet = resultSet.get(i);
        if (currentSet[0].equals("1")) {
          for (int j = 2; j < columnCount; j++) {
            coltag[j] = Integer.parseInt(currentSet[j]);
          }
        } else {
          if (currentSet[1].charAt(0) == '1') {
            for (int j = 2; j < columnCount; j++) {
              if (coltag[j] == 0 || coltag[j] == 10) {
                listth.add(currentSet[j]);
              }
            }
          } else if (currentSet[1].charAt(0) == '0') {
            for (int j = 2; j < columnCount; j++) {
              if (coltag[j] == 0 || coltag[j] == 10) {
                listtd.add(currentSet[j]);
              }
            }
          }
        }
      }

      // 调用方法
      System.out.println(listth);
      System.out.println(listtd);
      System.out.println(listth.size());
      System.out.println(listtd.size());
      System.out.println(columnCount);
      exportExcel(tablename, listth, listtd, columnCount - 2);
      return SUCCESS;
    } catch (Exception e) {
      return ERROR;
    }

  }

  private void exportExcel(String fileName, List<String> listth, List<String> listtd,
      int columnCount) {
    String excelName = "http://139.199.32.135/Up2UFinal/" + fileName + ".xls";
    int[] strLength = new int[columnCount];
    try {
      File excelFile = new File(excelName);
      // 如果文件存在就删除它
      if (excelFile.exists()) {
        excelFile.delete();
      }
      // 打开文件
      WritableWorkbook book = Workbook.createWorkbook(excelFile);
      // 生成名为“第一页”的工作表，参数0表示这是第一页
      WritableSheet sheet = book.createSheet("Up2U导出表格 ", 0);
      // 合并单元格
      // sheet.mergeCells(5, 5, 6, 6);
      // 文字样式
      jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10,
          WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
      jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(wfc);
      jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wfc);
      // 设置单元格样式
      wcfF.setBackground(jxl.format.Colour.BLACK);
      wcfFC.setBackground(jxl.format.Colour.GRAY_25);// 单元格颜色
      wcfFC.setAlignment(jxl.format.Alignment.CENTRE);// 单元格居中

      for (int i = 0; i < listth.size(); i++) {
        if (strLength[i % columnCount] < listth.get(i).getBytes().length) {
          strLength[i % columnCount] = listth.get(i).getBytes().length;
        }
      }
      for (int i = 0; i < listtd.size(); i++) {
        if (strLength[i % columnCount] < listtd.get(i).getBytes().length) {
          strLength[i % columnCount] = listtd.get(i).getBytes().length;
        }
      }
      for (int i = 0; i < columnCount; i++) {
        sheet.setColumnView(i, strLength[i] + 5);
      }

      // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
      // 以及单元格内容为
      for (int i = 0; i < listth.size() / columnCount; i++) {
        for (int j = 0; j < columnCount; j++) {
          sheet.addCell(new Label(j, i, listth.get(i * columnCount + j), wcfFC));
        }
      }
      for (int i = listth.size() / columnCount; i < (listth.size() + listtd.size())
          / columnCount; i++) {
        for (int j = 0; j < columnCount; j++) {
          sheet.addCell(new Label(j, i, listtd.get(i * columnCount + j), wcfF));
        }
      }
      // 写入数据并关闭文件
      book.write();
      book.close();
      System.out.println("Excel创建成功");

      DownloadToLocal("http://139.199.32.135/Up2UFinal/","");

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void DownloadToLocal(String url, String filePath) {
    try {
      URL theURL = new URL(url);
      URLConnection con = theURL.openConnection();
      // String urlPath = con.getURL().getFile();
      String fileFullName = tablename;
      if (fileFullName != null) {
        byte[] buffer = new byte[4 * 1024];
        int read;
        String path = filePath + "/" + fileFullName;
        File fileFolder = new File(filePath);
        if (!fileFolder.exists()) {
          fileFolder.mkdirs();
        }
        InputStream in = con.getInputStream();
        FileOutputStream os = new FileOutputStream(path);
        try {
          while ((read = in.read(buffer)) > 0) {
            os.write(buffer, 0, read);
          }
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          os.close();
          in.close();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
