package com.pepcus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pepcus.filehelper.FileUploader;

@RestController
public class FileUploadController {
  @Autowired
  private FileUploader fileUploader;
  
  
  @PostMapping("/file")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
//    System.out.println(file.getOriginalFilename());
    
    System.out.println(file.getContentType());
//    System.out.println(file.getName());
//    System.out.println(file.getSize());
   
    try {
    if(file.isEmpty()) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
    }
    
    if(file.getContentType().equals("image/jepg")) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jepg file upload");
    }
    //file upload
    boolean f=fileUploader.uploadFile(file);
    
   if(f) {
     
     return ResponseEntity.ok("File is Successfuly upload");
   }
    }catch(Exception e) {
      e.printStackTrace();
    }       
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is not upload");
  }
}
