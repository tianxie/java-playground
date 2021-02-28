package me.txie.openfeignfileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenFeignFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignFileUploadApplication.class, args);
    }

}
