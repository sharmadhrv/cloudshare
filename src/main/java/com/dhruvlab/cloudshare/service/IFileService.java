package com.dhruvlab.cloudshare.service;

import com.dhruvlab.cloudshare.entity.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    String UploadFiles(MultipartFile file, String email) throws Exception;
    List<FileEntity> getAllFiles(String email);
    String deleteFileById(Integer id, String email);
    Resource downloadFile(Integer id, String email) throws Exception;
}
