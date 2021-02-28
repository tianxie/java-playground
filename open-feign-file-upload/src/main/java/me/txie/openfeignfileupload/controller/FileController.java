package me.txie.openfeignfileupload.controller;

import me.txie.openfeignfileupload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    private UploadService service;

    @PostMapping(value = "/upload")
    public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
        return service.uploadFile(file);
    }

    @PostMapping(value = "/upload-manual-client")
    public boolean handleFileUploadWithManualClient(@RequestPart(value = "file") MultipartFile file) {
        return service.uploadFileWithManualClient(file);
    }
}
