package me.txie.openfeignfileupload.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.web.client.RestTemplate;

public class FeignConfig {

    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
            @Override
            public HttpMessageConverters getObject() throws BeansException {
                return new HttpMessageConverters(new RestTemplate().getMessageConverters());
            }
        }));
    }
}
