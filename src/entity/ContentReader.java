package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class ContentReader {

  private String url = null;
  private File file = null;
  public File getFile() {
	return file;
}

public void setFile(File file) {
	this.file = file;
}

private String content = null;
  private static String Encoding = "utf-8";

  private static final String HTTP = "http";
  private static final String HTTPS = "https";
  private static final String UPLOAD = "upload";

  public void setUrl(String url) {
    this.url = url;
  }

  public String getContent() {
    return content;
  }

  private static void getCharset(String variety, String Url) {
    try {
      URL url = new URL(Url);
      BufferedReader br = null;
      if (variety.equals("http")) {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "MSIE 9.0");
        br = new BufferedReader(new InputStreamReader(connection.getInputStream(), Encoding));

      } else if (variety.equals("https")) {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "MSIE 9.0");
        br = new BufferedReader(new InputStreamReader(connection.getInputStream(), Encoding));
      }

      int start = 0;
      int end = 0;
      boolean cut = false;
      String line = br.readLine();
      while (line != null) {
        if (line.contains("charset")) {
          for (int i = 0; i < line.length() - 7; i++) {
            if (line.substring(i, i + 7).equals("charset")) {
              for (int j = i + 7; j < line.length(); j++) {
                if (!cut && line.charAt(j) == '\"') {
                  start = j + 1;
                  cut = true;
                } else if (cut && line.charAt(j) == '\"') {
                  end = j;
                  cut = false;
                }
              }
            }
          }
          Encoding = line.substring(start, end);
          System.out.println(Encoding);
          break;
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    } finally {
      ;
    }
  }

  private static void getCharset(File file) {
    try {
      FileReader reader = new FileReader(file);
      BufferedReader br = new BufferedReader(reader);

      int start = 0;
      int end = 0;
      boolean cut = false;
      String line = br.readLine();
      while (line != null) {
        if (line.contains("charset")) {
          for (int i = 0; i < line.length() - 7; i++) {
            if (line.substring(i, i + 7).equals("charset")) {
              for (int j = i + 7; j < line.length(); j++) {
                if (!cut && line.charAt(j) == '\"') {
                  start = j + 1;
                  cut = true;
                } else if (cut && line.charAt(j) == '\"') {
                  end = j;
                  cut = false;
                }
              }
            }
          }
          Encoding = line.substring(start, end);
          break;
        }
        line = br.readLine();
      }


      br.close();
      reader.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  private static String getHttp(String Url) {
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

  private static String getHttps(String Url) {
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

  private void getString(File file) {

    getCharset(file);
    InputStreamReader reader = null;
    StringWriter writer = new StringWriter();
    try {
      reader = new InputStreamReader(new FileInputStream(file), Encoding);
      char[] buffer = new char[1000];
      int pos = 0;
      while (-1 != (pos = reader.read(buffer))) {
        writer.write(buffer, 0, pos);
      }
    } catch (Exception e) {
      e.printStackTrace();
      writer = null;
    } finally {
      if (reader != null)
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    content = writer.toString();
  }

  private void getString(String variety, String Url) {
    getCharset(variety, Url);

    if (variety.equals("http")) {
      content = getHttp(Url);
    } else if (variety.equals("https")) {
      content = getHttps(Url);
    }

  }

  /**
   * 判断输入Url类型：http、https、file. 并以字符串形式存储在variable中.
   * 
   * @return
   */
  public String varifyInput() {
    String variety = new String();
  if(url == null){
	  variety = UPLOAD;
  }else if (url.length() >= 7) {
      if (url.substring(0, 5).equals(HTTPS)) {
        variety = HTTPS;
      } else if (url.substring(0, 4).equals(HTTP)) {
        variety = HTTP;
      } 
  }
    return variety;
  }

  /**
   * 将url的文本作为字符串存储在content中.
   * 
   * @return
   */
  public String readAsString() {
    String webContent = null;
    String variety = varifyInput();

    if (variety.equals(HTTP)) {
      getString(variety, url);
      // sourceInfo[3][1] = "0";
    } else if (variety.equals(HTTPS)) {
      getString(variety, url);
      // sourceInfo[3][1] = "0";
    } else if (variety.equals(UPLOAD)) {
      getString(file);
      // sourceInfo[3][1] = "1";
    }

    return webContent;
  }

}
