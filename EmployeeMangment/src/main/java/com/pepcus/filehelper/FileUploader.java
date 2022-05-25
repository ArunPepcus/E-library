package com.pepcus.filehelper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploader {

  public final String UPLOAD_DIR = "C:\\Users\\admin\\Documents"
      + "\\workspace-spring-tool-suite-4-4.14.0.RELEASE\\EmployeeMangment\\"
      + "src\\main\\resources\\static\\image";

  public boolean uploadFile(MultipartFile multipartFile) {
    boolean f = false;
    try {
      Files.copy(multipartFile.getInputStream(),
          Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
          StandardCopyOption.REPLACE_EXISTING);
      f = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return f;
  }
}
