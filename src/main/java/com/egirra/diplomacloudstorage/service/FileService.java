package com.egirra.diplomacloudstorage.service;

import com.egirra.diplomacloudstorage.entity.File;
import com.egirra.diplomacloudstorage.exception.FileStorageException;
import com.egirra.diplomacloudstorage.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final Path root = Paths.get("uploads");

    public List<File> showAllFiles() {
        return fileRepository.findAllBy();
    }

    public void uploadFile(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                fileRepository.save(new File(file.getOriginalFilename(), file.getBytes()));
            } else {
                throw new FileStorageException("Failed to load empty file");
            }
        } catch (IOException e) {
            throw new FileStorageException("Failed to upload file", e);
        }
    }

    public boolean deleteByFileName(String fileName) {
        try {
            Path fileToDelete = root.resolve(fileName);
            return Files.deleteIfExists(fileToDelete);
        } catch (IOException e) {
            throw new FileStorageException("Unable to delete empty file", e);
        }
    }
}