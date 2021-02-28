package me.txie.openfeignfileupload.service;

import feign.Feign;
import feign.Response;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
    private static final String HTTP_FILE_UPLOAD_URL = "http://httpbin.org";

    @Autowired
    private UploadClient client;

    public String uploadFile(MultipartFile file) {
        return client.fileUpload(file);
    }

    public boolean uploadFileWithManualClient(MultipartFile file) {
        UploadResource fileUploadResource = Feign.builder().encoder(new SpringFormEncoder())
                .target(UploadResource.class, HTTP_FILE_UPLOAD_URL);
        Response response = fileUploadResource.uploadFile(file);
        return response.status() == 200;
    }
}
