package com.dhruvlab.cloudshare.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String UploadFiles(MultipartFile file, String email) throws Exception;
}
