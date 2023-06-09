package com.shruteekatech.electronic.store.services.impl;

import com.shruteekatech.electronic.store.exceptions.BadApiRequest;
import com.shruteekatech.electronic.store.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {


        String originalFilename = file.getOriginalFilename();
        log.info("Filename: {}", originalFilename);
        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename + extension;
        String fullPathFileName = path  + fileNameWithExtension;
        if (extension.equalsIgnoreCase(".png") ||
                extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".jpeg"))
        {
            File folder = new File(path);
            if(!folder.exists())
            {
                folder.mkdirs();
            }

            Files.copy(file.getInputStream(), Paths.get(fullPathFileName));
            return fileNameWithExtension;

        }
        else
        {
            throw new BadApiRequest("File with this "+extension+"not allowed");
        }

    }


    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        log.info("Initiating the getResource data");
        String fullPath = path+File.separator+name;

        InputStream inputStream = new FileInputStream(fullPath);
        log.info("Completed the getResource data");
        return inputStream;
    }
}
