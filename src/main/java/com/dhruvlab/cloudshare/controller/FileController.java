package com.dhruvlab.cloudshare.controller;

import com.dhruvlab.cloudshare.entity.FileEntity;
import com.dhruvlab.cloudshare.service.FileServiceImplementation;
import com.dhruvlab.cloudshare.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/files")
public class FileController {

    @Autowired
    private FileServiceImplementation fileServiceImplementation;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/upload")
    public String uploadFiles(@RequestParam("file") MultipartFile file,
                              @RequestHeader("Authorization") String authHeader) throws Exception {
        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        return fileServiceImplementation.UploadFiles(file, email);
    }

    @GetMapping("/myFiles")
    public List<FileEntity> getMyFiles(@RequestHeader("Authorization") String authHeader) throws Exception {
        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        return fileServiceImplementation.getAllFiles(email);
    }

    @DeleteMapping("/deleteFile/{id}")
    public String deleteFileById(@PathVariable Integer id , @RequestHeader("Authorization") String authHeader) throws Exception{
        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        return fileServiceImplementation.deleteFileById(id,email);
    }
}
