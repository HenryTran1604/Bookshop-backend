package com.bookshop.service.Impl;

import com.bookshop.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
@Service
public class FileServiceImpl implements IFileService {

    @Override
    public String getFileName(MultipartFile file) {
            String fileName = file.getOriginalFilename();
            int index = fileName.lastIndexOf('.');
            String extension = fileName.substring(index);
            if(index > 0 && (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png"))) {
                fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date()) + extension;
                return fileName;
            }
            return null;
    }

    @Override
    public String saveFile(MultipartFile file, String fileDirectory) {
        String fileName = this.getFileName(file);
        if(fileName != null) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(fileDirectory + fileName);
                 FileOutputStream fos = null;
                fos = new FileOutputStream(path.toString());
                fos.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return fileName;
        }
        return "error";
    }
}
