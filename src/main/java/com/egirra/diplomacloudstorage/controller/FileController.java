package com.egirra.diplomacloudstorage.controller;

import com.egirra.diplomacloudstorage.entity.File;
import com.egirra.diplomacloudstorage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/list")
    public List<File> showAllFiles() {
        return fileService.showAllFiles();
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.uploadFile(file);
    }

    @DeleteMapping("/{fileName}")
    public boolean deleteByFileName(@PathVariable String fileName) {
        return fileService.deleteByFileName(fileName);
    }
}