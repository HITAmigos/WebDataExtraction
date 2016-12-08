package testPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import entity.assistantEntity.SqlConst;

public class ImageTest {
  // 图片流数据存取的工具类
  private static File file = null;
  private static String path = null;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  /**
   * 从本地文件读取图像的二进制流
   * 
   * @param infile
   * @return
   */
  public static FileInputStream getImageByte() {
    FileInputStream imageByte = null;
    file = new File(path);
    try {
      imageByte = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return imageByte;
  }

  /**
   * 将图片流读出为图片
   * 
   * @param inputStream
   * @param path
   */
  public static void readBlob(InputStream inputStream, String path) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(path);
      byte[] buffer = new byte[1024];
      int len = 0;
      while ((len = inputStream.read(buffer)) != -1) {
        fileOutputStream.write(buffer, 0, len);
      }
      inputStream.close();
      fileOutputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 将本地文件保存到数据库
  public static void saveImage() {
    InputStream inputStream = null;
    inputStream = getImageByte();
    String sql = "insert into `image` set image= " + inputStream + ", path = '" + path + "'";
    SqlConst sqlHelper = new SqlConst(sql);
    try {
      sqlHelper.getPst().execute();
      // preparedStatement.setBinaryStream(3, inputStream, inputStream.available());
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (inputStream != null)
          inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        sqlHelper.close();
      }
    }
  }

  // 从数据库中读取并生成图片
  public static void readImage() {
    String sql = "select image from `image` where id = 1";
    SqlConst sqlHelper = new SqlConst(sql);
    ResultSet resultSet = null;
    InputStream inputStream = null;
    try {
      resultSet = sqlHelper.getPst().executeQuery(sql);
      resultSet.next();
      inputStream = resultSet.getBinaryStream("photo");
      readBlob(inputStream, "C:\\Users\\liuyx\\Desktop\\mySql-1.jpg");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (inputStream != null)
          inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          if (resultSet != null)
            resultSet.close();
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
          sqlHelper.close();
        }
      }
    }
  }

  public static void main(String args[]) {
    ImageTest it = new ImageTest();
    it.setPath("E:\\照片\\拉花\\kafei.jpg");
    
  }

}
