package com.gospeluwam.com.file_uploader.api;

import com.gospeluwam.com.file_uploader.models.CustomFileType;
import com.gospeluwam.com.file_uploader.services.CustomFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@RequestMapping("api/v1/files")
@RestController
public class FileController {

    @Autowired
    private CustomFileService customFileService;


    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;
    @PostMapping("/add")
    public ResponseEntity<String> addFile(@RequestParam("File")  MultipartFile multipartFile) throws IOException {
        return customFileService.addFile(multipartFile,FILE_DIRECTORY);
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateFile(@RequestBody CustomFileType customFileType){
        return customFileService.updateFile(customFileType.getId(),customFileType.getFileName());
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable int fileId){
        return customFileService.deleteFile(fileId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomFileType>> allFiles(){
        return customFileService.allFiles();
    }

}
