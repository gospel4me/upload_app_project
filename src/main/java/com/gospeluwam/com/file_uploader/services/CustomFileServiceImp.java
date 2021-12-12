package com.gospeluwam.com.file_uploader.services;
import com.gospeluwam.com.file_uploader.models.CustomFileType;
import com.gospeluwam.com.file_uploader.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class CustomFileServiceImp implements CustomFileService{

    @Autowired
    private FileRepository fileRepository;


    @Override
    public ResponseEntity<String> addFile(MultipartFile multipartFile,String DIR) throws IOException {

        if(multipartFile.isEmpty()){
            return new ResponseEntity<>(
                    "Please select a file",
                    HttpStatus.BAD_REQUEST);
        }else{

            System.out.println("The up path is ----- " + DIR);
            System.out.println("The file path is ----- " +  multipartFile.getOriginalFilename());
            String filePath = DIR + multipartFile.getOriginalFilename();
            File file = new File(filePath);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();

            CustomFileType customFileType = new CustomFileType();
            customFileType.setFileName(multipartFile.getName());
            customFileType.setUrl(filePath);
            customFileType.setMymeType(multipartFile.getContentType());
            fileRepository.save(customFileType);
            return new ResponseEntity<>(
                    "File uploaded successfully",
                    HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> deleteFile(int fileId) {
        CustomFileType customFileType = fileRepository.findById(fileId).orElse(null);
        if(customFileType != null){
            fileRepository.deleteById(fileId);
            return new ResponseEntity<>(
                    "File Deleted successfully",
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>(
                    "File ID not found",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateFile(int fileId, String fileName) {
        CustomFileType customFileType = fileRepository.findById(fileId).orElse(null);
        if(customFileType != null){
            customFileType.setFileName(fileName);
            fileRepository.save(customFileType);
            return new ResponseEntity<>(
                    "File Updated successfully",
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>(
                    "File ID not found",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<CustomFileType>> allFiles() {
        List<CustomFileType> list = fileRepository.findAll();
        return new ResponseEntity<>(
               list,
                HttpStatus.OK);
    }
}
