https://www.baeldung.com/java-feign-file-upload

## 依赖项
* feign-core
* feign-form
* feign-form-spring


## 文件上传接口
curl -F file=@fileupload.txt http://httpbin.org/post

## 两种上传方式

### @FeignClient 注解

me.txie.openfeignfileupload.service.UploadService.uploadFile

### Feign.builder

me.txie.openfeignfileupload.service.UploadService.uploadFileWithManualClient
