package com.securemart.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String updateImage(String path, MultipartFile image) throws IOException;
}
