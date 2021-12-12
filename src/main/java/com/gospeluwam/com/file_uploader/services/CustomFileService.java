package com.gospeluwam.com.file_uploader.services;

import com.gospeluwam.com.file_uploader.models.CustomFileType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;


public interface CustomFileService {
    ResponseEntity<String> addFile(MultipartFile multipartFile,String DIR) throws IOException;
    ResponseEntity<String> deleteFile(int fileId);
    ResponseEntity<String>  updateFile(int fileId,String fileName);
    ResponseEntity<List<CustomFileType>> allFiles();

}
