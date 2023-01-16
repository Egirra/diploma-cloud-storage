package com.egirra.diplomacloudstorage.repository;

import com.egirra.diplomacloudstorage.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

    List<File> findAllBy();

    File findByFileName(String fileName);
}