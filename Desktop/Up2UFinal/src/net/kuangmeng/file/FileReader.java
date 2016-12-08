package net.kuangmeng.file;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileReader extends ActionSupport {
  private File myFile;
  private String myFileContentType;
  private String myFileFileName;
  private String destPath;
  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String execute() {
    /* Copy file to a safe location */
    destPath = "C:\\Users\\Meng Kuang\\Desktop\\upload\\";
    try {
      System.out.println("Src File name: " + myFile);
      System.out.println("Dst File name: " + myFileFileName);
      File destFile = new File(destPath, myFileFileName);
      FileUtils.copyFile(myFile, destFile);
//      sa.setUrl(destPath + myFileFileName);
//      sa.setUsername(getUsername());
//      sa.execute();
    } catch (IOException e) {
      e.printStackTrace();
      return ERROR;
    }
    return SUCCESS;
  }

  public File getMyFile() {
    return myFile;
  }

  public void setMyFile(File myFile) {
    this.myFile = myFile;
  }

  public String getMyFileContentType() {
    return myFileContentType;
  }

  public void setMyFileContentType(String myFileContentType) {
    this.myFileContentType = myFileContentType;
  }

  public String getMyFileFileName() {
    return myFileFileName;
  }

  public void setMyFileFileName(String myFileFileName) {
    this.myFileFileName = myFileFileName;
  }
}
