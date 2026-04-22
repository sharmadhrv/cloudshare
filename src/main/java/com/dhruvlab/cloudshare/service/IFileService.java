package com.dhruvlab.cloudshare.service;

import com.dhruvlab.cloudshare.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    String UploadFiles(MultipartFile file, String email) throws Exception;
    List<FileEntity> getAllFiles(String email);
    String deleteFileById(Integer id, String email);
}
