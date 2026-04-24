package com.dhruvlab.cloudshare.service;

import com.dhruvlab.cloudshare.entity.FileEntity;
import com.dhruvlab.cloudshare.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<FileEntity> getAllFiles(String email) {
        return fileRepository.findByUploadedBy(email);
    }

    @Override
    public String deleteFileById(Integer id, String email) {

        FileEntity fileEntity = fileRepository.findById(id)
                .orElseThrow(()->new RuntimeException("file not found"));

        if(!fileEntity.getUploadedBy().equals(email))
        {
            throw new RuntimeException("not authorized to delete file");
        }
        File file = new File(fileEntity.getFilePath());
        if(file.exists())
            file.delete();

        fileRepository.delete(fileEntity);

        return "file deleted successfully";
    }

    @Override
    public Resource downloadFile(Integer id, String email) throws Exception
    {
        FileEntity fileEntity = fileRepository.findById(id)
                .orElseThrow(()->new RuntimeException("File not found"));
        if(!fileEntity.getUploadedBy().equals(email))
            throw new RuntimeException("unaothorized user");

        File file = new File(fileEntity.getFilePath());
        return new UrlResource(file.toURI());
    }

}
