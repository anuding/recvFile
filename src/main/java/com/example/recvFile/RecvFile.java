package com.example.recvFile;

import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class RecvFile {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        if (null == uploadFile) {
            return"上传失败，无法找到文件！";
        }
        String fileName = uploadFile.getOriginalFilename().toLowerCase();

        String basePath = "D:\\FD_Project\\recvFile";
        String filePath = basePath + "/" + fileName;
        File desFile = new File(filePath);
        if(!desFile.getParentFile().exists()){
            desFile.mkdirs();
        }
        try {
            uploadFile.transferTo(desFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return "成功";
    }
}
