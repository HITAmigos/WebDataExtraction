package SoftwareEngineering.lyx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import javax.net.ssl.HttpsURLConnection;


public class download extends GrabAction {

  public static String getHttps(String checkUrl, String checkContent) {
   StringBuffer pageHTML = new StringBuffer();
    try {
      URL url = new URL(checkUrl);
      HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
      // 取得该连接的输入流，以读取响应内容
      InputStream ins = httpsConn.getInputStream();
      BufferedReader breader = new BufferedReader(new InputStreamReader(ins));
      String info = breader.readLine();
      long nowTime = new java.util.Date().getTime();
      while (info != null) {
        pageHTML.append(info+"\n");
        if (new java.util.Date().getTime() - nowTime > 10000)
          return "time over";// 达到10秒就认为超时
        if (info != null && info.indexOf(checkContent) != -1)
          return "null";
        info = breader.readLine();
      }
    } catch (Exception e) {
      System.out.println("Can't get content:" + checkContent + " from URL:" + checkUrl);
      System.out.println("The error is:" + e.getMessage());
      e.printStackTrace();
      return "error";
    }
    return pageHTML.toString();
  }

  public static String getHttp(String pageURL, String encoding) {
    StringBuilder pageHTML = new StringBuilder();
    try {
      URL url = new URL(pageURL);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("User-Agent", "MSIE 7.0");
      BufferedReader br =
          new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
      String line = null;
      while ((line = br.readLine()) != null) {
        pageHTML.append(line);
        pageHTML.append("\r\n");
      }
      connection.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pageHTML.toString();
  }

  public static void main(String args[]) {
    //System.out.println(getHttp("http://www.w3school.com.cn/tags/tag_meta.asp", "GB2312"));
    System.out.println(getHttps("https://zhidao.baidu.com/question/473692023.html", "GB2312"));
  }

  
  
  
  
  
  
  
  
  
  
  
}
