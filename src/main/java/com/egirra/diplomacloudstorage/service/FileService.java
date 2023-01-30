package com.egirra.diplomacloudstorage.service;

import com.egirra.diplomacloudstorage.entity.File;
import com.egirra.diplomacloudstorage.exception.FileStorageException;
import com.egirra.diplomacloudstorage.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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

    public Resource loadAsResource(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileStorageException("File cannot be read");
            }
        } catch (MalformedURLException e) {
            throw new FileStorageException("File cannot be read", e);
        }
    }

    public Optional<File> editFile(String fileName, String newFileName) {
        Optional<File> fileToEdit = fileRepository.findByFileName(fileName);
        fileToEdit.ifPresent(file -> file.setFileName(newFileName));
        return Optional.of(fileRepository.save(fileToEdit.get()));
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