package com.egirra.diplomacloudstorage.controller;

import com.egirra.diplomacloudstorage.entity.File;
import com.egirra.diplomacloudstorage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/list")
    public List<File> showAllFiles() {
        return fileService.showAllFiles();
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, value = "/file")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.uploadFile(file);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> getFile(@RequestParam String fileName) {
        Resource file = fileService.loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/file")
    public boolean deleteByFileName(@RequestParam String fileName) throws IOException {
        return fileService.deleteByFileName(fileName);
    }

    @PutMapping("/file")
    public ResponseEntity editFileName(@RequestParam String fileName, @RequestBody Map<String, String> bodyParams) {
        fileService.editFile(fileName, bodyParams.get("fileName"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}