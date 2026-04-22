package com.dhruvlab.cloudshare.service;

import com.dhruvlab.cloudshare.entity.FileEntity;
import com.dhruvlab.cloudshare.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Service
public class FileServiceImplementation implements IFileService{

    @Autowired
    FileRepository fileRepository;

    private final String uploadDir = "D:/cloudshare/uploads/";


    @Override
    public String UploadFiles(MultipartFile file, String email) throws Exception {

        String fileName = file.getOriginalFilename();

        File folder = new File(uploadDir);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File destination = new File(uploadDir + fileName);

        file.transferTo(destination);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(fileName);
        fileEntity.setFilePath(destination.getAbsolutePath());
        fileEntity.setFileSize(file.getSize());
        fileEntity.setUploadedAt(LocalDateTime.now());
        fileEntity.setUploadedBy(email);

        fileRepository.save(fileEntity);

        return "file uploaded successfully";
    }
}
