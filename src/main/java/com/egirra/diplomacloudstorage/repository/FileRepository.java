package com.egirra.diplomacloudstorage.repository;

import com.egirra.diplomacloudstorage.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

    List<File> findAllBy();

    Optional<File> findByFileName(String fileName);
}