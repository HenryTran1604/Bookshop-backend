package com.bookshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String getFileName(MultipartFile file);
    String saveFile(MultipartFile image, String fileDirectory);
}
