package com.securemart.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {


    public String updateImage(String path, MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String ramdomId = UUID.randomUUID().toString();

        String newFileName = ramdomId.concat(extension);

        String filePath = path + File.separator + newFileName;

        File folder = new File(path);
        if (!folder.exists())
            folder.mkdir();

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return newFileName;
    }
}
