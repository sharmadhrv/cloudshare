package com.dhruvlab.cloudshare.repository;

import com.dhruvlab.cloudshare.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity,Integer> {

    List<FileEntity> findByUploadedBy(String email);
}
